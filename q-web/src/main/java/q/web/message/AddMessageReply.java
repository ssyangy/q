/**
 *
 */
package q.web.message;

import java.util.ArrayList;
import java.util.List;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessageJoinPeoplePage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;
import q.domain.MessageReplyJoinPeople;
import q.util.IdCreator;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		MessageReply messageReply = new MessageReply();
		messageReply.setId(IdCreator.getLongId());
		messageReply.setContent(context.getString("content"));
		messageReply.setSenderId(context.getCookiePeopleId());

		long replyMessageId = context.getIdLong("replyMessageId");
		MessageReply replied = this.messageDao.getMessageReplyById(replyMessageId);
		if (replied == null) {
			throw new RequestParameterInvalidException("reply:invalid");
		}
		messageReply.setReplyMessageId(replied.getId());
		messageReply.setReplySenderId(replied.getSenderId());

		long quoteMessageId = context.getResourceIdLong();
		Message quote = this.messageDao.getMessageById(quoteMessageId);
		if (quote == null) {
			throw new RequestParameterInvalidException("quote:invalid");
		}
		messageReply.setQuoteMessageId(quote.getId());
		messageReply.setQuoteSenderId(quote.getSenderId());
		messageDao.addMessageReply(messageReply);

		// connect message reply with peoples
		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setMessageId(messageReply.getQuoteMessageId());
		List<MessageJoinPeople> receivers = messageDao.getMessageJoinPeoplesByPage(joinPage); // message receivers
		List<MessageReplyJoinPeople> replyJoins = new ArrayList<MessageReplyJoinPeople>(receivers.size());
		for (MessageJoinPeople mjp : receivers) {
			MessageReplyJoinPeople join = new MessageReplyJoinPeople();
			join.setId(IdCreator.getLongId());
			join.setReplyId(messageReply.getId());
			join.setSenderId(messageReply.getSenderId());
			join.setQuoteMessageId(messageReply.getQuoteMessageId());
			join.setQuoteSenderId(messageReply.getQuoteSenderId());
			join.setReceiverId(mjp.getReceiverId());
			replyJoins.add(join);
		}
		messageDao.addMessageReplyJoinPeoples(replyJoins);
		messageDao.incrAllMessageReplyNumberByMessageId(messageReply.getQuoteMessageId());
		
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
		if (IdCreator.isNotValidId(replyMessageId)) {
			throw new RequestParameterInvalidException("reply:invalid");
		}
	}

}
