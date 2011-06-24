package q.web.people;

import q.dao.PeopleDao;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;

public class GetProfileAvatar extends Resource {

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		// 传递一些大图，中图，小图地址的参数
		// 传递那张修改中的大图地址的参数

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		People people = peopleDao.getPeopleById(loginPeopleId);
		if (people.hasAvatar()) {
			context.setModel("avatarExists", true);
		} else {
			context.setModel("avatarExists", false);
		}
		context.setModel("avatarPath", people.getAvatarPath());
	}

}
