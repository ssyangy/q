/**
 * 
 */
package q.web.message;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.domain.MessageReply;
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
		if (IdCreator.isValidIds(replyMessageId)) {
			MessageReply replied = this.messageDao.getMessageReplyById(replyMessageId);
			if(replied == null) {
				throw new RequestParameterInvalidException("reply:invalid");
			}
			messageReply.setReplyMessageId(replied.getId());
			messageReply.setReplySenderId(replied.getSenderId());
		}
		long quoteMessageId = context.getResourceIdLong();
		if (IdCreator.isValidIds(quoteMessageId)) {
			Message quote = this.messageDao.getMessageById(quoteMessageId);
			if(quote == null) {
				throw new RequestParameterInvalidException("quote:invalid");
			}
			messageReply.setQuoteMessageId(quote.getId());
			messageReply.setQuoteSenderId(quote.getSenderId());
		} else {
			throw new RequestParameterInvalidException("quote:invalid");
		}
 		messageDao.addMessageReply(messageReply);
 		
 		String from = context.getString("from");
 		if (from != null) {
			context.redirectContextPath(from);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(senderId)) {
			throw new RequestParameterInvalidException("login:invalid");
		}
	}

}
