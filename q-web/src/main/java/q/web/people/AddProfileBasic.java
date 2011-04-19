package q.web.people;

import q.biz.SearchService;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Gender;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;

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

		people.setId(peopleId);
		people.setRealName(context.getString("realName"));
		Gender gender = Gender.convertValue(context.getInt("gender", -1));
		people.setGender(gender);
		if (!databasePeople.hasAvatar()) {
			if (gender.isFemale()) {
				people.setAvatarPath(imageUrl + "/default/female-def");
			} else {
				people.setAvatarPath(imageUrl + "/default/male-def");
			}
		}

		// update area
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		int areaId = AreaValidator.getAreaId(provinceId, cityId, countyId);
		people.setArea(Area.getAreaById(areaId));

		// update hometown
		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		int hometownAreaId = AreaValidator.getAreaId(hometownProvinceId, hometownCityId, hometownCountyId);
		people.setHometown(Area.getAreaById(hometownAreaId));

		// update birthday
		people.setYear(context.getInt("year", -1));
		people.setMonth(context.getInt("month", -1));
		people.setDay(context.getInt("day", -1));

		String url = context.getString("url");
		if (null != url) {
			if (StringKit.isNotEmpty(url) && !(url.startsWith("http://") || url.startsWith("https://"))) {
				url = "http://" + url;
			}
			people.setUrl(url);
		}
		String intro = context.getString("intro");
		if (null != url) {
			people.setIntro(intro);
		}
		peopleDao.updatePeopleById(people);

		// FIXME will remove from here, seanlinwang
		if (!databasePeople.getRealName().equals(people.getRealName())) {
			people.setUsername(databasePeople.getUsername());
			searchService.updatePeople(people);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateId(context.getCookiePeopleId());
		PeopleValidator.validateGender(context.getInt("gender", -1));
		PeopleValidator.validateBirthday(context.getInt("year", -1), context.getInt("month", -1), context.getInt("day", -1));
		PeopleValidator.validateRealName(context.getString("realName"));
		PeopleValidator.validateUrl(context.getString("url"));
		PeopleValidator.validateIntro(context.getString("intro"));

		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		PeopleValidator.validateArea(provinceId, cityId, countyId);

		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		PeopleValidator.validateHometown(hometownProvinceId, hometownCityId, hometownCountyId);
	}

}
