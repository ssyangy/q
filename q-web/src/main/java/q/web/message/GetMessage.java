/**
 * 
 */
package q.web.message;

import java.util.List;

import q.dao.MessageDao;
import q.dao.page.MessageReplyPage;
import q.domain.MessageReply;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class GetMessage extends Resource {
	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long mid = context.getResourceIdLong();
		MessageReplyPage page = new MessageReplyPage();
		page.setMessageId(mid);
		List<MessageReply> replies = messageDao.getMessageRepliesByPage(page);
		context.setModel("replies", replies);

	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long mid = context.getResourceIdLong();
		if(IdCreator.isNotValidId(mid)) {
			throw new RequestParameterInvalidException("message:invalid");
		}
	}

}
