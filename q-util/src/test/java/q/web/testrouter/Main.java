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


}
