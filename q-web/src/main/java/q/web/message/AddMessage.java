/**
 * 
 */
package q.web.message;

import java.sql.SQLException;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddMessage extends Resource {
	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	private PeopleDao peopleDao;

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
		messageDao.addMessage(message);
		context.redirectServletPath("/message");
	}

	@Override
	public boolean validate(ResourceContext context) {
		long receiverId = context.getIdLong("receiverId");
		long senderId = context.getLoginPeopleId();
		if (!IdCreator.isValidIds(senderId, receiverId)) {
			return false;
		}
		try {
			if (peopleDao.getPeopleById(receiverId) == null) {
				return false;
			}
		} catch (SQLException e) {
			log.error("", e);
			return false;
		}
		return true;
	}

}
