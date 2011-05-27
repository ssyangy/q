package q.web.login;

import q.web.Resource;
import q.web.ResourceContext;

public class GetLoginNew extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("from", context.getString("from"));
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
