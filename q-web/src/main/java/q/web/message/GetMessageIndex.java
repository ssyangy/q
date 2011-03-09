/**
 * 
 */
package q.web.message;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessagePage;
import q.domain.Message;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class GetMessageIndex extends Resource {
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
		MessagePage page = new MessagePage();
		long loginPeopleId = context.getLoginPeopleId();
		page.setMyId(loginPeopleId);
		page.setStartIndex(0);
		page.setSize(20);
		List<Message> messages = messageDao.getPageMessages(page);
		DaoHelper.injectMessagesWithSenderReceiverRealName(peopleDao, messages);
		context.setModel("messages", messages);
		context.setModel("loginId", loginPeopleId);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
