/**
 * 
 */
package q.web.testrouter;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 18, 2011
 *
 */
public class AddAJoin extends Resource {

	/* (non-Javadoc)
	 * @see q.web.framework.Resource#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.util.Map)
	 */
	@Override
	public void execute(ResourceContext context) {
		context.setModel("action", "join");
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
