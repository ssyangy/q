/**
 * 
 */
package q.web.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class GetEventNew extends Resource {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("groupId", context.getString("groupId"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = df.format(new Date());
		String date = datetime.substring(0, 10);
		String time = datetime.substring(10);
		context.setModel("day", date);
		context.setModel("time", time);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
