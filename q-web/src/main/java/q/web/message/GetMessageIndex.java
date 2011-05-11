/**
 * 
 */
package q.web.message;

import java.sql.SQLException;
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
		long loginPeopleId = context.getCookiePeopleId();
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		int type = context.getInt("type", 0);

		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setReceiverId(loginPeopleId);
		int asc = 1;
		if (type == asc) { // 1 indicate asc
			joinPage.setDesc(false);
		} else {
			joinPage.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		joinPage.setSize(fetchSize);
		joinPage.setStartIndex(0);
		if (startId > 0) {
			joinPage.setStartId(startId);
		}
		List<Long> messageIds = messageDao.getMessageReceiverIdsByJoinPage(joinPage);
		Map<Long, List<Long>> messageIdReceiversMap = getMessageReceiversMap(messageIds);
		if (CollectionKit.isNotEmpty(messageIds)) {
			Map<String, Object> api = new HashMap<String, Object>();
			if (messageIds.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				messageIds.remove(messageIds.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != 999999999999999999L) {// this action from previous page
				hasPrev = true;
			}
			MessagePage messagePage = new MessagePage();
			messagePage.setIds(messageIds);
			List<Message> messages = messageDao.getMessagesByPage(messagePage);
			if (CollectionKit.isNotEmpty(messages)) {
				for (Message msg : messages) {
					msg.setReceiverIds(messageIdReceiversMap.get(msg.getId())); // set message receiver
				}
				DaoHelper.injectMessagesWithSenderAndReceivers(peopleDao, messages);
				api.put("messages", messages);
			}
			api.put("hasPrev", hasPrev);
			api.put("hasNext", hasNext);
			context.setModel("api", api);
		}

	}

	/**
	 * 返回message id和参与者的映射
	 * 
	 * @param messageIds
	 * @return
	 * @throws SQLException
	 */
	private Map<Long, List<Long>> getMessageReceiversMap(List<Long> messageIds) throws SQLException {
		if (CollectionKit.isNotEmpty(messageIds)) {
			return null;
		}
		Map<Long, List<Long>> messageIdReceiversMap = new HashMap<Long, List<Long>>();
		MessageJoinPeoplePage receiversPage = new MessageJoinPeoplePage();
		receiversPage.setMessageIds(messageIds);
		for (MessageJoinPeople join : messageDao.getMessageJoinPeoplesByPage(receiversPage)) {
			List<Long> receivers = messageIdReceiversMap.get(join.getMessageId());
			if (null == receivers) {
				receivers = new ArrayList<Long>();
				messageIdReceiversMap.put(join.getMessageId(), receivers);
			}
			receivers.add(join.getReceiverId());
		}
		return messageIdReceiversMap;
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
