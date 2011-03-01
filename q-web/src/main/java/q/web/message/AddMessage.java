/**
 * 
 */
package q.web.message;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.domain.People;
import q.web.ParameterInvalidException;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddMessage extends Resource {
	protected MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	protected PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		Message message = new Message();
		message.setContent(context.getString("content"));
		message.setSenderId(context.getLoginPeopleId());
		message.setReceiverId(context.getIdLong("receiverId"));
		long replyMessageId = context.getIdLong("replyMessageId");
		if (replyMessageId > 0) {
			message.setReplyMessageId(replyMessageId);
		}
		messageDao.addMessage(message);
		context.redirectServletPath("/message");
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getLoginPeopleId();
		long receiverId = context.getIdLong("receiverId");
		if (senderId == 0 || receiverId == 0)
			throw new ParameterInvalidException();

		People receiver = peopleDao.getPeopleById(receiverId);
		if (receiver == null)
			throw new ParameterInvalidException();

		long replyMessageId = context.getIdLong("replyMessageId");
		if (replyMessageId > 0) {
			Message replied = messageDao.getMessageById(replyMessageId);
			if (replied == null)
				throw new ParameterInvalidException();

			if (replied.getReceiverId() != senderId) // check replied message is mine
				throw new ParameterInvalidException();
		}
	}

}
