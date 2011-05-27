/**
 *
 */
package q.web.message;

import java.util.ArrayList;
import java.util.List;

import q.biz.NotifyService;
import q.biz.ShortUrlService;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessageJoinPeoplePage;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;
import q.domain.MessageReplyJoinPeople;
import q.domain.Status;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddMessageReply extends Resource {
	protected MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	protected PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private ShortUrlService shortUrlService;

	public void setShortUrlService(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}

	/**
	 * @param notifyService
	 *            the notifyService to set
	 */
	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	private NotifyService notifyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		MessageReply messageReply = new MessageReply();
		messageReply.setId(IdCreator.getLongId());
		messageReply.setContent(this.shortUrlService.urlFilter(context.getString("content")));
		long loginId = context.getCookiePeopleId();
		messageReply.setSenderId(loginId);

		long replyMessageId = context.getIdLong("replyMessageId");
		if (replyMessageId > 0) {
			MessageReply replied = this.messageDao.getMessageReplyById(replyMessageId);
			if (replied == null) {
				throw new RequestParameterInvalidException("reply:invalid");
			}
			messageReply.setReplyMessageId(replied.getId());
			messageReply.setReplySenderId(replied.getSenderId());
		}

		long quoteMessageId = context.getResourceIdLong();
		MessageJoinPeople messageJoinPeople = messageDao.getMessageJoinPeopleByMessageIdReceiverIdStatus(quoteMessageId, loginId, Status.COMMON.getValue());
		if (messageJoinPeople == null) {
			throw new RequestParameterInvalidException("quote:invalid");
		}
		messageReply.setQuoteMessageId(messageJoinPeople.getMessageId());
		messageReply.setQuoteSenderId(messageJoinPeople.getSenderId());
		// add message reply
		messageDao.addMessageReply(messageReply);

		// update last message reply
		MessageJoinPeoplePage updateJoinPage = new MessageJoinPeoplePage();
		updateJoinPage.setMessageId(quoteMessageId);
		updateJoinPage.setLastReplyId(messageReply.getId());
		updateJoinPage.setLastReplySenderId(messageReply.getSenderId());
		messageDao.updateMessageLastReplyByMessageId(updateJoinPage);

		// connect message reply with peoples
		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setMessageId(messageReply.getQuoteMessageId());
		List<MessageJoinPeople> receivers = messageDao.getMessageJoinPeoplesByPage(joinPage); // message
																								// receivers
		List<MessageReplyJoinPeople> replyJoins = new ArrayList<MessageReplyJoinPeople>(receivers.size());
		List<Long> receiverIds = new ArrayList<Long>(receivers.size());
		for (MessageJoinPeople mjp : receivers) {
			MessageReplyJoinPeople join = new MessageReplyJoinPeople();
			join.setId(IdCreator.getLongId());
			join.setReplyId(messageReply.getId());
			join.setSenderId(messageReply.getSenderId());
			join.setQuoteMessageId(messageReply.getQuoteMessageId());
			join.setQuoteSenderId(messageReply.getQuoteSenderId());
			join.setReceiverId(mjp.getReceiverId());
			replyJoins.add(join);
			receiverIds.add(mjp.getReceiverId());
		}
		messageDao.addMessageReplyJoinPeoples(replyJoins);
		messageDao.incrAllMessageReplyNumberByMessageId(messageReply.getQuoteMessageId());
		// notify new message
		notifyService.notifyMessageReply(messageReply, receiverIds, loginId);

		context.setModel("MessageReply", messageReply);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(senderId)) {
			throw new RequestParameterInvalidException("login:invalid");
		}
		long quoteMessageId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(quoteMessageId)) {
			throw new RequestParameterInvalidException("quote:invalid");
		}
		long replyMessageId = context.getIdLong("replyMessageId");
		if (replyMessageId > 0) {
			if (IdCreator.isNotValidId(replyMessageId)) {
				throw new RequestParameterInvalidException("reply:invalid");
			}
		}
		String content = context.getString("content");
		if (StringKit.isBlank(content)) {
			throw new RequestParameterInvalidException("content:invalid");
		}
		if (content.length() > 1400) {
			throw new RequestParameterInvalidException("content:overflow");
		}
	}

}
