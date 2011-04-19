package q.web.people;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Degree;
import q.domain.Gender;
import q.domain.People;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;
import q.web.exception.PeopleNotExistException;
import q.web.exception.PeopleNotPermitException;
import q.web.exception.RequestParameterInvalidException;
import q.web.group.AddGroupJoin;

public class AddPeopleFull extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private String imageUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		long peopleId = context.getResourceIdLong();
		people.setId(peopleId);
		Gender gender = Gender.convertValue(context.getInt("gender", -1));
		if (gender.isFemale()) {
			people.setAvatarPath(imageUrl + "/default/female-def");
		} else {
			people.setAvatarPath(imageUrl + "/default/male-def");
		}
		people.setGender(gender);

		// add area
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		int areaId = AreaValidator.getAreaId(provinceId, cityId, countyId);
		people.setArea(Area.getAreaById(areaId));

		// add hometown
		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		int hometownAreaId = AreaValidator.getAreaId(hometownProvinceId, hometownCityId, hometownCountyId);
		people.setHometown(Area.getAreaById(hometownAreaId));

		long mobile = context.getLong("mobile", -1);
		if (mobile > 0) {
			people.setMobile(mobile);
		}
		int degree = context.getInt("degree", -1);
		people.setDegree(Degree.convertValue(degree));
		people.setYear(context.getInt("selYear", -1));
		people.setMonth(context.getInt("selMonth", -1));
		people.setDay(context.getInt("selDay", -1));
		peopleDao.updatePeopleById(people);
		List<Long> groupIds = context.getIdLongList("group");
		groupIds = groupDao.getExsitGroupIdsByIds(groupIds); // filter not exists
		AddGroupJoin action = new AddGroupJoin();
		action.setGroupDao(groupDao);
		if (CollectionKit.isNotEmpty(groupIds)) {
			for (Long groupId : groupIds) {
				action.addPeopleJoinGroup(peopleId, groupId);
			}
		}
		context.setModel("people", people);
		context.redirectServletPath("/group/feed");
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long peopleId = context.getResourceIdLong();
		if (peopleId != context.getCookiePeopleId()) {
			throw new PeopleNotPermitException("people:无操作权限");
		}
		if (this.peopleDao.getPeopleById(peopleId) == null) {
			throw new PeopleNotExistException("people:用户不存在");
		}

		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		AreaValidator.check(provinceId, cityId, countyId);

		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		AreaValidator.check(hometownProvinceId, hometownCityId, hometownCountyId);

		PeopleValidator.validateGender(context.getInt("gender", -1));
		PeopleValidator.validateBirthday(context.getInt("selYear", -1), context.getInt("selMonth", -1), context.getInt("selDay", -1));

		String[] groupIds = context.getStringArray("group");
		if (ArrayUtils.isEmpty(groupIds)) {
			throw new RequestParameterInvalidException("group:圈子必选");
		}

	}

}
