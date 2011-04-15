/**
 * 
 */
package q.web.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import q.commons.pinyin.Pinyin;
import q.dao.DaoHelper;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessagePage;
import q.dao.page.MessageReplyPage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;
import q.domain.People;
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
			for (MessageJoinPeople join : joins) {
				messageIds.add(join.getMessageId());
			}
			MessageJoinPeoplePage receiversPage = new MessageJoinPeoplePage();
			receiversPage.setMessageIds(messageIds);
			Map<Long, List<Long>> messageIdReceiversMap = new HashMap<Long, List<Long>>();
			for (MessageJoinPeople join : messageDao.getMessageJoinPeoplesByPage(receiversPage)) {
				List<Long> receivers = messageIdReceiversMap.get(join.getMessageId());
				if (null == receivers) {
					receivers = new ArrayList<Long>();
					messageIdReceiversMap.put(join.getMessageId(), receivers);
				}
				receivers.add(join.getReceiverId());
			}

			MessagePage messagePage = new MessagePage();
			messagePage.setIds(messageIds);
			List<Message> messages = messageDao.getMessagesByPage(messagePage);
			for (Message msg : messages) {
				msg.setReceiverIds(messageIdReceiversMap.get(msg.getId()));
			}
			Map<Long, People> peopleMap = DaoHelper.injectMessagesWithSenderAndReceivers(peopleDao, messages);
			context.setModel("messages", messages);
			
			long messageId = context.getIdLong("messageId");
			Message message = null;
			if(messageId > 0) {
				for(Message now: messages) {
					if (now.getId() == messageId) {
						message = now;
					}
				}
			} else {
				messageId = messages.get(0).getId();
				message = messages.get(0);
			}
			context.setModel("message", message);
			MessageReplyPage page = new MessageReplyPage();
			page.setQuoteMessageId(messageId);
			List<MessageReply> replies = messageDao.getMessageRepliesByPage(page);
			for(MessageReply reply: replies) {
				reply.setSender(peopleMap.get(reply.getSenderId()));
			}
			context.setModel("replies", replies);
		}

		List<Long> followingIds = peopleDao.getAllFollowingId(loginPeopleId);
		List<People> followings = peopleDao.getPeoplesByIds(followingIds);
		String peoplesHintJson = this.getPeoplesHintJson(followings);
		context.setModel("peoplesHintJson", peoplesHintJson);
	}

	/**
	 * [{ value: 'jin zi cheng', img: '1', name: '金梓成'},{ value: 'han han', img: '1', name: '韩寒'}]
	 * 
	 * @param peoples
	 * @return
	 */
	private String getPeoplesHintJson(List<People> peoples) {
		if (CollectionKit.isEmpty(peoples)) {
			return "[]";
		}
		StringBuilder buffer = new StringBuilder(peoples.size() * 32);
		buffer.append("[");
		Iterator<People> iterator = peoples.iterator();
		People first = iterator.next();
		appendPeople(buffer, first);
		while (iterator.hasNext()) {
			People people = iterator.next();
			if (people != null) {
				buffer.append(',');
				appendPeople(buffer, people);
			}
		}
		buffer.append("]");
		return buffer.toString();
	}

	private void appendPeople(StringBuilder buffer, People people) {
		buffer.append("{value:\"");
		Set<String> pinyins = Pinyin.getPinyin(people.getRealName());
		if (CollectionKit.isNotEmpty(pinyins)) {
			buffer.append(StringUtils.join(pinyins, ' '));
		}
		buffer.append(people.getUsername());
		buffer.append("\",");
		buffer.append("avatarPath:\"");
		buffer.append(people.getAvatarPath());
		buffer.append("\",");
		buffer.append("username:\"");
		buffer.append(people.getUsername());
		buffer.append("\",");
		buffer.append("realName:\"");
		buffer.append(people.getRealName());
		buffer.append("\",");
		buffer.append("id:\"");
		buffer.append(people.getId());
		buffer.append("\"}");
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
