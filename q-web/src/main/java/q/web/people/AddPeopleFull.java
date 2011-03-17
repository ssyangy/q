package q.web.people;

import org.apache.commons.lang.ArrayUtils;

import q.biz.exception.RequestParameterInvalidException;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Degree;
import q.domain.Gender;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;

public class AddPeopleFull extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		people.setId(context.getResourceIdLong());
		people.setGender(Gender.convertValue(context.getInt("gender", -1)));
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		int areaId = AreaValidator.getAreaId(provinceId, cityId, countyId);
		people.setArea(Area.getAreaById(areaId));
		long mobile = context.getLong("mobile", -1);
		if (mobile > 0) {
			people.setMobile(mobile);
		}
		int degree = context.getInt("degree", -1);
		people.setDegree(Degree.convertValue(degree));
		// FIXME add birthday check
		people.setYear(context.getInt("year", -1));
		people.setMonth(context.getInt("month", -1));
		people.setDay(context.getInt("day", -1));
		peopleDao.updatePeopleById(people);
		// FIXME add people join groups
		context.setModel("people", people);
		context.redirectServletPath("/group/feed");
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		AreaValidator.check(provinceId, cityId, countyId);

		if (!Gender.valid(context.getInt("gender", -1))) {
			throw new RequestParameterInvalidException("gender:性别必选");
		}

		String[] groupIds = context.getStringArray("group");
		if (ArrayUtils.isEmpty(groupIds)) {
			throw new RequestParameterInvalidException("group:圈子必选");
		}

	}

}
