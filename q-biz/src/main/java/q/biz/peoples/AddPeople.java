/**
 * 
 */
package q.biz.peoples;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @date Jan 17, 2011
 *
 */
public class AddPeople extends Resource {

	/* (non-Javadoc)
	 * @see q.web.framework.Resource#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.util.Map)
	 */
	@Override
	public void execute(ResourceContext context) {
		String email = context.getString("email");
		String password = context.getString("password");
		String confirmPassword = context.getString("confirmPassword");

	}

}
