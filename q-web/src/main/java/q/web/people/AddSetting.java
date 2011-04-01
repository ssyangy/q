package q.web.people;

import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

public class AddSetting extends Resource{
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		long pid = context.getCookiePeopleId();
		String password=peopleDao.selectPasswordById(pid);
		if(password.equals(context.getString("oldPassword"))){
			People people = new People();
			people.setPassword(context.getString("newPassword"));
			people.setId(pid);
			peopleDao.updatePeopleById(people);
		}
		else{
			throw new RequestParameterInvalidException("oldPassword:原先密码不正确，请重新输入。");
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateNewPassword(context.getString("newPassword"));
		if (!context.getString("rePassword").equals(context.getString("newPassword"))) {
			throw new RequestParameterInvalidException("confirmPassword:两次输入的密码不同。");
		}

	}

}
