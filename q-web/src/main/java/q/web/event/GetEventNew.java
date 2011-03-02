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
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(date);
        String time1=time.substring(0,10);
        String time2=time.substring(10);
		context.setModel("day", time1);
		context.setModel("time", time2);
	}

}
