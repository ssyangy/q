/**
 * 
 */
package q.web.message;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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
		message.setSenderId(context.getCookiePeopleId());
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
		long senderId = context.getCookiePeopleId();
		long receiverId = context.getIdLong("receiverId");
		if (senderId == 0)
			throw new RequestParameterInvalidException("loginId invalid");
		if (receiverId == 0)
			throw new RequestParameterInvalidException("receiverId invalid");

		People receiver = peopleDao.getPeopleById(receiverId);
		if (receiver == null)
			throw new RequestParameterInvalidException("receiverId invalid");

		long replyMessageId = context.getIdLong("replyMessageId");
		if (replyMessageId > 0) { // check has repliedId
			Message replied = messageDao.getMessageById(replyMessageId);
			if (replied == null) // check replied exists
				throw new RequestParameterInvalidException("replied invalid");

			if (replied.getReceiverId() != senderId) // check replied is mine
				throw new RequestParameterInvalidException("replied receiverId invlaid");
		}
	}

}
