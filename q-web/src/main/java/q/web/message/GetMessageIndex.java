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
		page.setMyId(context.getLoginPeopleId());
		page.setStartIndex(0);
		page.setSize(20);
		List<Message> messages = DaoHelper.getPageMessageRealName(peopleDao, messageDao, page);
		context.setModel("messages", messages);
	}

}
