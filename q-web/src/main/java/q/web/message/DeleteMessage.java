/**
 *
 */
package q.web.message;

import q.dao.MessageDao;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.Status;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * url:\/message/xxxxxxx
 *
 * POST _method=delete
 *
 * @author seanlinwang at gmail dot com
 * @date May 10, 2011
 *
 */
public class DeleteMessage extends Resource {
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
		long peopleId = context.getCookiePeopleId();
		long messageId = context.getResourceIdLong();
		MessageJoinPeople join = this.messageDao.getMessageJoinPeopleByMessageIdReceiverIdStatus(messageId, peopleId, Status.COMMON.getValue());
		if (join != null) { // delete message replies if this receiver can read  this message
			this.messageDao.deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(messageId, peopleId);
			// reset reply num to zero
			this.messageDao.clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(messageId, peopleId);
		}
		context.setModel("id", messageId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(senderId)) {
			throw new RequestParameterInvalidException("login:invalid");
		}
		long messageId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(messageId)) {
			throw new RequestParameterInvalidException("message:invalid");
		}
		Message message = this.messageDao.getMessageById(messageId);
		if (message == null) {
			throw new RequestParameterInvalidException("message:invalid");
		}
	}

}
