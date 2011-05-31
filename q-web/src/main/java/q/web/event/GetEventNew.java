/**
 * 
 */
package q.web.event;

import java.util.Date;

import q.domain.Area;
import q.util.DateKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;

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
		context.setModel("rootArea", Area.getRootArea());
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = DateKit.date2Ymdhms(new Date());
		String date = datetime.substring(0, 10);
		String time = datetime.substring(10);
		context.setModel("day", date);
		context.setModel("time", time);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
