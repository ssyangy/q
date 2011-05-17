package q.web.people;

import q.biz.PictureService;
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

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		long peopleId = context.getCookiePeopleId();

		people.setId(peopleId);
		people.setRealName(context.getString("realName"));
		Gender gender = Gender.convertValue(context.getInt("gender", -1));
		people.setGender(gender);

		People databasePeople = peopleDao.getPeopleById(peopleId);
		if (!databasePeople.hasAvatar()) {
			if (gender.isFemale()) {
				people.setAvatarPath(this.pictureService.getDefaultFemaleAvatarPath());
			} else {
				people.setAvatarPath(this.pictureService.getDefaultMaleAvatarPath());
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
		}
		people.setUrl(url);
		String intro = context.getString("intro");
		people.setIntro(intro);
		peopleDao.updatePeopleById(people);
		context.setModel("people", people);
		// FIXME will remove from here, seanlinwang
		if (!databasePeople.getRealName().equals(people.getRealName())) {
			people.setUsername(databasePeople.getUsername());
			searchService.updatePeople(people);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateId(context.getCookiePeopleId());
		int gender = context.getInt("gender", -1);
		PeopleValidator.validateGender(gender);

		String intro = context.getString("intro");
		if (intro != null) {
			PeopleValidator.validateIntro(intro);
		}

		String realName = context.getString("realName");
		PeopleValidator.validateRealName(realName);

		int year = context.getInt("year", -1);
		int month = context.getInt("month", -1);
		int day = context.getInt("day", -1);
		if (year > 0) {
			PeopleValidator.validateBirthday(year, month, day);
		}

		String url = context.getString("url");
		if (url != null) {
			PeopleValidator.validateUrl(url);
		}

		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		if (provinceId > 0) {
			AreaValidator.check(provinceId, cityId, countyId);
		}

		int hometownProvinceId = context.getInt("hometownProvince", -1);
		int hometownCityId = context.getInt("hometownCity", -1);
		int hometownCountyId = context.getInt("hometownCounty", -1);
		if (hometownProvinceId > 0) {
			AreaValidator.check(hometownProvinceId, hometownCityId, hometownCountyId);
		}
	}

}
