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
public class DeleteA extends Resource {

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) {
		context.setModel("id", context.getResourceId());
		context.setModel("action", "delete");
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
