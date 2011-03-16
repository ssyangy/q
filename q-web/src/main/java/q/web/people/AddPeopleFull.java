package q.web.people;

import org.apache.commons.lang.ArrayUtils;

import q.biz.exception.RequestParameterInvalidException;
import q.dao.AreaDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Degree;
import q.domain.Gender;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class AddPeopleFull extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private AreaDao areaDao;

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
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
		int areaId = context.getInt("county", -1);
		if (areaId <= 0) {
			areaId = context.getInt("city", -1); // only province, city
		}
		people.setDistrictId(areaId);
		long mobile = context.getLong("mobile", -1);
		if (mobile > 0) {
			people.setMobile(mobile);
		}
		int degree = context.getInt("degree", -1);
		people.setDegree(Degree.convertValue(degree));
		//FIXME add birthday check
		people.setYear(context.getInt("year", -1));
		people.setMonth(context.getInt("month", -1));
		people.setDay(context.getInt("day", -1));
		peopleDao.updatePeopleById(people);
		//FIXME add people join groups
		context.setModel("people", people);
		context.redirectServletPath("/group/feed");
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		Area province = null;
		Area city = null;
		Area county = null;
		if (provinceId <= 0) {
			throw new RequestParameterInvalidException("province:省份必填");
		}
		province = areaDao.getAreaById(provinceId);
		if (null == province) {
			throw new RequestParameterInvalidException("province:该省份不存在");
		}
		if (countyId <= 0) { // only province and city
			city = areaDao.getAreaById(cityId);
			if (null == city) {
				throw new RequestParameterInvalidException("city:该城市不存在");
			}
		} else { // county, city, province all exsit.
			county = areaDao.getAreaById(countyId);
			if (null == county) {
				throw new RequestParameterInvalidException("county:该县/区不存在");
			}
		}
		if (city == null && province.hasChilds()) { // need city after province
			throw new RequestParameterInvalidException("city:该省份下必填城市");
		}
		if (county == null && city.hasChilds()) { // need county after city
			throw new RequestParameterInvalidException("county:该城市下必填县/区");
		}
		if (city != null && province != null && !city.isChild(province)) {
			throw new RequestParameterInvalidException("city:该城市不属于该省份");
		}
		if (county != null && city != null && !county.isChild(city)) {
			throw new RequestParameterInvalidException("county:该县/区不属于该城市");
		}

		if (!Gender.valid(context.getInt("gender", -1))) {
			throw new RequestParameterInvalidException("gender:性别必选");
		}

		String[] groupIds = context.getStringArray("group");
		if (ArrayUtils.isEmpty(groupIds)) {
			throw new RequestParameterInvalidException("group:圈子必选");
		}

	}

}
