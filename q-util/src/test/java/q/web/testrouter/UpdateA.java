/**
 * 
 */
package q.web.testrouter;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author alin
 * @date Jan 19, 2011
 *
 */
public class UpdateA extends Resource {

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) {
		context.setModel("id", context.getResourceId());
		context.setModel("action", "update");
	}

}
