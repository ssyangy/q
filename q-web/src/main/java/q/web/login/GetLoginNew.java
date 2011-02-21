package q.web.login;

import q.web.Resource;
import q.web.ResourceContext;

public class GetLoginNew extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("from", context.getString("from"));
	}

}
