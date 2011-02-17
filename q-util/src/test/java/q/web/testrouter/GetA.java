package q.web.testrouter;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public class GetA extends Resource {

	@Override
	public void execute(ResourceContext context) {
		String aid = context.getResourceLastId();
		context.setModel("aid", aid);
	}

}
