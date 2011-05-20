/**
 * 
 */
package q.web.message;

import q.dao.MessageDao;
import q.domain.MessageReply;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * 
 * url:\/messageReply/xxxxxxx
 * 
 * POST _method=delete
 * 
 * @author seanlinwang at gmail dot com
 * @date May 10, 2011
 * 
 */
public class DeleteMessageReply extends Resource {
	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long replyId = context.getResourceIdLong();
		long loginId = context.getCookiePeopleId();
		MessageReply reply = this.messageDao.getMessageReplyById(replyId);
		if(reply == null || reply.getSenderId() != loginId) {
			throw new RequestParameterInvalidException("privilege:invalid");
		}
		int rowEffected = this.messageDao.deleteMessageReplyByIdAndReceiverId(replyId, loginId);
		if (rowEffected > 0) {
			if (rowEffected > 1) {
				log.error("delete row:", rowEffected);
			}
			this.messageDao.decrMessageReplyNumberByMessageIdAndReceiverId(reply.getQuoteMessageId(), loginId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
	}

}
