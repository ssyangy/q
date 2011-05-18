/**
 * 
 */
package q.web.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import q.biz.NotifyService;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;
import q.domain.MessageReplyJoinPeople;
import q.domain.People;
import q.util.ArrayKit;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
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
	
	/**
	 * @param notifyService the notifyService to set
	 */
	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	private NotifyService notifyService;

	/**
	 * Add one message contains below steps:
	 * <ul>
	 * <li>add message thread, set reply num 1</li>
	 * <li>add firstmessage reply</li>
	 * <li>add message join peoples</li>
	 * </ul>
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		Message message = new Message();
		message.setId(IdCreator.getLongId());
		long senderId = context.getCookiePeopleId();
		message.setSenderId(senderId);
		// add message thread
		messageDao.addMessage(message);

		// add first message reply
		MessageReply messageReply = new MessageReply();
		messageReply.setId(IdCreator.getLongId());
		messageReply.setContent(context.getString("content"));
		messageReply.setSenderId(message.getSenderId());
		messageReply.setQuoteMessageId(message.getId());
		messageReply.setQuoteSenderId(message.getSenderId());
		// 因为是第一个回复,所以不需要设置replyMessageId和replySenderId
		messageDao.addMessageReply(messageReply);

		// connect message with peoples
		String idsString = context.getString("receiverId");
		String[] receiverStringIds = StringKit.split(idsString, ',');
		Set<Long> receiverIds = IdCreator.convertIfValidIds(receiverStringIds);
		receiverIds.add(senderId);// 发送者也需要接收该回复
		List<MessageJoinPeople> joins = new ArrayList<MessageJoinPeople>(receiverIds.size());
		for (Long receiverId : receiverIds) {
			MessageJoinPeople join = new MessageJoinPeople();
			join.setId(IdCreator.getLongId());
			join.setSenderId(senderId);
			join.setMessageId(message.getId());
			join.setReceiverId(receiverId);
			join.setReplyNum(1);// message thread has first reply
			join.setLastReplyId(messageReply.getId()); // set last message reply id
			join.setLastReplySenderId(messageReply.getSenderId()); // set last message reply sender id
			joins.add(join);
		}
		messageDao.addMessageJoinPeoples(joins);

		// connect message reply with peoples
		List<MessageReplyJoinPeople> replyJoins = new ArrayList<MessageReplyJoinPeople>(receiverIds.size());
		for (Long receiverId : receiverIds) {
			MessageReplyJoinPeople join = new MessageReplyJoinPeople();
			join.setId(IdCreator.getLongId());
			join.setReplyId(messageReply.getId());
			join.setSenderId(senderId);
			join.setQuoteMessageId(message.getId());
			join.setQuoteSenderId(message.getSenderId());
			join.setReceiverId(receiverId);
			replyJoins.add(join);
		}
		messageDao.addMessageReplyJoinPeoples(replyJoins);
		notifyService.notifyMessageReply(messageReply, receiverIds);//notify new messages

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (senderId == 0)
			throw new RequestParameterInvalidException("login:invalid");
		String[] receiverStringIds = StringKit.split(context.getString("receiverId"), ',');
		if (ArrayKit.isEmpty(receiverStringIds)) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		Set<Long> receiverIds = null;
		try {
			receiverIds = IdCreator.convertIfValidIds(receiverStringIds);
		} catch (Exception e) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		if (CollectionKit.isEmpty(receiverIds)) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		HashSet<Long> idSet = new HashSet<Long>(receiverIds);
		List<People> receivers = peopleDao.getPeoplesByIds(new ArrayList<Long>(idSet));
		if (CollectionKit.isEmpty(receivers) || receivers.size() != idSet.size()) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}

	}

}
