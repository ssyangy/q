package q.web.check;
import q.dao.PeopleDao;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleAlreadyExistException;
import q.web.exception.RequestParameterInvalidException;
public class AddPeopleCheck extends Resource {
	private PeopleDao peopleDao;
	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		String email = context.getString("email");
		if (StringKit.isNotEmpty(email)) {
			People result = this.peopleDao.getPeopleByEmail(email);
			if (result != null) {
				throw new PeopleAlreadyExistException("email:该邮箱地址已经被使用");
			}
		}
		String username = context.getString("username");
		if (StringKit.isNotEmpty(username)) {
			People result = this.peopleDao.getPeopleByUsername(username);
			if (result != null) {
				throw new PeopleAlreadyExistException("username:该用户名已经被使用");
			}
		}
	}
	@Override
	public void validate(ResourceContext context) throws Exception {
		String email = context.getString("email");
		String username = context.getString("username");
		if (StringKit.isEmpty(email) && StringKit.isEmpty(username)) {
			throw new RequestParameterInvalidException("all:参数丢失");
		}
	}
}
