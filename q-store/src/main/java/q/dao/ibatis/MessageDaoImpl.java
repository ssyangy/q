/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import q.dao.MessageDao;
import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessagePage;
import q.dao.page.MessageReplyPage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;

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

}
