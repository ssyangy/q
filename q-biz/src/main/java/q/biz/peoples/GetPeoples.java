package q.biz.peoples;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 *
 */
public class GetPeoples extends Resource {

	@Override
	public void execute(ResourceContext context) {
		String uid = context.getResourceLastId();
		String nick = "seanlinwang";
		long fansNum = 100;
		long friendsNum = 20;
		context.setModel("nick", nick);
		context.setModel("fansNum", fansNum);
		context.setModel("friendsNum", friendsNum);
	}

}
