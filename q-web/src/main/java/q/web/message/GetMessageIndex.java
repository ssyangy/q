/**
 * 
 */
package q.web.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.dao.DaoHelper;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessagePage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.util.CollectionKit;
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
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		long loginPeopleId = context.getCookiePeopleId();

		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setMyId(loginPeopleId);
		joinPage.setStartIndex(0);
		joinPage.setSize(size);
		if (startId > 0) {
			joinPage.setStartId(startId);
		}
		List<MessageJoinPeople> joins = messageDao.getMessageJoinPeoplesByPage(joinPage);
		if (CollectionKit.isNotEmpty(joins)) {
			List<Long> messageIds = new ArrayList<Long>(joins.size());
			for (MessageJoinPeople join: joins) {
				messageIds.add(join.getMessageId());
			}
			MessageJoinPeoplePage receiversPage = new MessageJoinPeoplePage();
			receiversPage.setMessageIds(messageIds);
			Map<Long, List<Long>> receiversMap = new HashMap<Long, List<Long>>();
			for (MessageJoinPeople join: messageDao.getMessageJoinPeoplesByPage(receiversPage)) {
				List<Long> list = receiversMap.get(join.getMessageId());
				if(null == list) {
					list = new ArrayList<Long>();
					receiversMap.put(join.getMessageId(), list);
				}
				list.add(join.getReceiverId());
			}
			
			
			MessagePage messagePage = new MessagePage();
			messagePage.setIds(messageIds);
			List<Message> messages = messageDao.getPageMessages(messagePage);
			for(Message msg: messages) {
				msg.setReceiverIds(receiversMap.get(msg.getId()));
			}
			DaoHelper.injectMessagesWithSenderAndReceivers(peopleDao, messages);
			context.setModel("messages", messages);
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
