/**
 * 
 */
package q.web.testrouter.a;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author alin
 * @date Jan 19, 2011
 *
 */
public class DeleteA extends Resource {

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) {
		context.setModel("id", context.getResourceLastId());
		context.setModel("action", "delete");
	}

}
