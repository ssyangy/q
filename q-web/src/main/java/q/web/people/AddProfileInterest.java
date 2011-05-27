package q.web.people;

import org.apache.commons.lang.StringUtils;

import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Gender;
import q.domain.People;
import q.log.Logger;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;

public class AddProfileInterest extends Resource{
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		long peopleId = context.getCookiePeopleId();
		people.setId(peopleId);
		people.setBook(mergeBlank(context.getString("book")));
		people.setFilm(mergeBlank(context.getString("film")));
		people.setMusic(mergeBlank(context.getString("music")));
		people.setIdol(mergeBlank(context.getString("idol")));
		String dd=mergeBlank(context.getString("hope"));
		people.setHope(mergeBlank(context.getString("hope")));
		people.setId(peopleId);
		peopleDao.updateInterestById(people);
	}
	private String mergeBlank(String source) {
		String[] bookArray = StringKit.split(source, ' ');
		source=StringUtils.join(bookArray, ' ');
		return source;
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateBook(context.getString("book"));
		PeopleValidator.validateFilm(context.getString("film"));
		PeopleValidator.validateMusic(context.getString("music"));
		PeopleValidator.validateIdol(context.getString("idol"));
		PeopleValidator.validateHope(context.getString("hope"));

	}


}
