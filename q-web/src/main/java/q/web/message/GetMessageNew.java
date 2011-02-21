/**
 * 
 */
package q.web.message;

import q.dao.MessageDao;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class GetMessageNew extends Resource {
	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
