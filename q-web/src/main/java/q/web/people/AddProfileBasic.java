package q.web.people;

import q.web.Resource;
import q.web.ResourceContext;

public class AddProfileBasic extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateRealName(context.getString("realName"));
		PeopleValidator.validateUrl(context.getString("url"));
	}

}
