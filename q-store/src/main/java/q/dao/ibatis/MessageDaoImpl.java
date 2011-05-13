/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import q.dao.MessageDao;
import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessagePage;
import q.dao.page.MessageReplyJoinPeoplePage;
import q.dao.page.MessageReplyPage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;
import q.domain.MessageReplyJoinPeople;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class MessageDaoImpl extends AbstractDaoImpl implements MessageDao {

	/*
	 * @see q.dao.MessageDao#addMessage(q.domain.Message)
	 */
	@Override
	public void addMessage(Message message) throws SQLException {
		this.sqlMapClient.insert("insertMessage", message);
	}

	/*
	 * @see q.dao.MessageDao#getPageMessages(q.dao.page.MessagePage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByPage(MessagePage page) throws SQLException {
		return (List<Message>) this.sqlMapClient.queryForList("selectMessagesByPage", page);
	}

	/*
	 * @see q.dao.MessageDao#getMessageRepliesByPage(q.dao.page.MessageReplyPage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageReply> getMessageRepliesByPage(MessageReplyPage page) throws SQLException {
		return (List<MessageReply>) this.sqlMapClient.queryForList("selectMessageRepliesByPage", page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#getMessageById(long)
	 */
	@Override
	public Message getMessageById(long messageId) throws SQLException {
		return (Message) this.sqlMapClient.queryForObject("selectMessageById", messageId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#addMessageJoinPeoples(java.util.List)
	 */
	@Override
	public void addMessageJoinPeoples(List<MessageJoinPeople> joins) throws SQLException {
		this.sqlMapClient.insert("insertMessageJoins", joins);
	}

	@Override
	public void addMessageReplyJoinPeoples(List<MessageReplyJoinPeople> replyJoins) throws SQLException {
		this.sqlMapClient.insert("insertMessageReplyJoins", replyJoins);
	}

	@Override
	public MessageReply getMessageReplyById(long replyMessageId) throws SQLException {
		return (MessageReply) this.sqlMapClient.queryForObject("selectMessageReplyById", replyMessageId);
	}

	@Override
	public void addMessageReply(MessageReply messageReply) throws SQLException {
		this.sqlMapClient.insert("insertMessageReply", messageReply);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#getMessageIdsByPage(q.dao.page.MessageJoinPeoplePage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MessageJoinPeople> getMessageJoinPeoplesByPage(MessageJoinPeoplePage joinPage) throws SQLException {
		return (List<MessageJoinPeople>) this.sqlMapClient.queryForList("selectMessageJoinPeoplesByPage", joinPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getMessageIdsByJoinPage(MessageJoinPeoplePage joinPage) throws SQLException {
		return (List<Long>) this.sqlMapClient.queryForList("getMessageIdsByJoinPage", joinPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getMessageReplyIdsByJoinPage(MessageReplyJoinPeoplePage replyJoinPage) throws SQLException {
		return (List<Long>) this.sqlMapClient.queryForList("getMessageReplyIdsByJoinPage", replyJoinPage);
	}

	@Override
	public List<MessageReply> getMessageRepliesByIds(List<Long> ids) throws SQLException {
		MessageReplyPage page = new MessageReplyPage();
		page.setIds(ids);
		return this.getMessageRepliesByPage(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#getMessageJoinPeopleByMessageIdAndPeopleId(long, long)
	 */
	@Override
	public MessageJoinPeople getMessageJoinPeopleByMessageIdReceiverIdStatus(long messageId, long receiverId, int status) throws SQLException {
		MessageJoinPeople join = new MessageJoinPeople();
		join.setReceiverId(receiverId);
		join.setMessageId(messageId);
		join.setStatus(status);
		return (MessageJoinPeople) this.sqlMapClient.queryForObject("getMessageJoinPeopleByMessageIdAndReceiverIdAndStatus", join);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#deleteMessageJoinPeopleByMessageIdAndPeopleId(long, long)
	 */
	@Override
	public int clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(long messageId, long receiverId) throws SQLException {
		MessageJoinPeople join = new MessageJoinPeople();
		join.setReceiverId(receiverId);
		join.setMessageId(messageId);
		join.setReplyNum(0);// reset reply num
		return this.sqlMapClient.update("updateMessageJoinPeopleReplyNumByMessageIdAndReceiverId", join);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(long, long)
	 */
	@Override
	public int deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(long quoteMessageId, long receiverId) throws SQLException {
		MessageReplyJoinPeople join = new MessageReplyJoinPeople();
		join.setReceiverId(receiverId);
		join.setQuoteMessageId(quoteMessageId);
		return this.sqlMapClient.update("deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId", join);
	}

	@Override
	public int deleteMessageReplyById(long replyId, long receiverId) throws SQLException {
		MessageReplyJoinPeople join = new MessageReplyJoinPeople();
		join.setReceiverId(receiverId);
		join.setReplyId(replyId);
		return this.sqlMapClient.update("deleteMessageReplyByIdAndReceiverId", join);
	}

	@Override
	public int decrMessageReplyNumberByMessageIdAndReceiverId(long messageId, long receiverId) throws SQLException {
		MessageJoinPeople join = new MessageJoinPeople();
		join.setMessageId(messageId);
		join.setReceiverId(receiverId);
		return this.sqlMapClient.update("decrMessageReplyNumberByMessageIdAndReceiverId", join);
	}

	@Override
	public int incrAllMessageReplyNumberByMessageId(long messageId) throws SQLException {
		return this.sqlMapClient.update("incrAllMessageReplyNumberByMessageId", messageId);
	}

}
