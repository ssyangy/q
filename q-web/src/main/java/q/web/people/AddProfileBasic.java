package q.web.people;

import java.util.List;

import q.biz.SearchService;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Degree;
import q.domain.Gender;
import q.domain.People;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;
import q.web.group.AddGroupJoin;

public class AddProfileBasic extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private String imageUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		long peopleId = context.getCookiePeopleId();
		People databasePeople = peopleDao.getPeopleById(peopleId);
		// long peopleId=1300368092229L;
		people.setId(peopleId);
		Gender gender = Gender.convertValue(context.getInt("gender", -1));
		people.setGender(gender);
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		int areaId = AreaValidator.getAreaId(provinceId, cityId, countyId);
		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		int hometownAreaId = AreaValidator.getAreaId(hometownProvinceId, hometownCityId, hometownCountyId);
		if (!databasePeople.hasAvatar()) {
			if (gender.isFemale()) {
				people.setAvatarPath(imageUrl + "/default/female-def");
			} else {
				people.setAvatarPath(imageUrl + "/default/male-def");
			}
		}
		people.setHometown(Area.getAreaById(hometownAreaId));
		people.setArea(Area.getAreaById(areaId));
		people.setYear(context.getInt("year", -1));
		people.setMonth(context.getInt("month", -1));
		people.setDay(context.getInt("day", -1));
		people.setUrl(context.getString("url"));
		people.setIntro(context.getString("intro"));
		people.setRealName(context.getString("realName"));
		peopleDao.updatePeopleById(people);
		if (!databasePeople.getRealName().equals(people.getRealName())) {
			people.setUsername(databasePeople.getUsername());
			searchService.updatePeople(people);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateRealName(context.getString("realName"));
		PeopleValidator.validateUrl(context.getString("url"));
	}

}
