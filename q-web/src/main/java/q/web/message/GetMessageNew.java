/**
 * 
 */
package q.web.message;

import q.domain.People;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class GetMessageNew extends AddMessage {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long receiverId = context.getIdLong("receiverId");
		long replyMessageId = context.getIdLong("replyMessageId");
		
		People receiver = peopleDao.getPeopleById(receiverId);
		context.setModel("receiver", receiver);
		context.setModel("replyMessageId", replyMessageId);

	}

}
