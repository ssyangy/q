package q.web.testrouter;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 *
 */
public class Main extends Resource {

	@Override
	public void execute(ResourceContext context) {
		context.setModel("action", "homepage");
		context.setModel("name", this.getName());
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
