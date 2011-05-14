/**
 * 
 */
package q.web.login;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class DeleteLogin extends Resource {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		((q.web.DefaultResourceContext) context).removeLoginCookie();
		String from = context.getString("from"); // redirect to origin refferr
		if (from == null) {
			context.redirectServletPath("");
		}
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
	}

}
