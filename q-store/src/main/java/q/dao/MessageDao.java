/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessageReplyJoinPeoplePage;
import q.dao.page.MessagePage;
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
public interface MessageDao {

	/**
	 * @param message
	 * @throws SQLException
	 */
	public void addMessage(Message message) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<Message> getMessagesByPage(MessagePage page) throws SQLException;

	/**
	 * @param replyMessageId
	 * @return
	 */
	public Message getMessageById(long replyMessageId) throws SQLException;

	/**
	 * @param joins
	 */
	public void addMessageJoinPeoples(List<MessageJoinPeople> joins) throws SQLException;

	/**
	 * @param replyMessageId
	 * @return
	 */
	public MessageReply getMessageReplyById(long replyMessageId) throws SQLException;

	/**
	 * @param messageReply
	 */
	public void addMessageReply(MessageReply messageReply) throws SQLException;

	/**
	 * @param joinPage
	 * @return
	 */
	public List<MessageJoinPeople> getMessageJoinPeoplesByPage(MessageJoinPeoplePage joinPage) throws SQLException;

	/**
	 * @param page
	 * @return
	 */
	public List<MessageReply> getMessageRepliesByPage(MessageReplyPage page) throws SQLException;

	/**
	 * @param messageId
	 * @param peopleId
	 * @param status
	 *            TODO
	 * @return
	 */
	public MessageJoinPeople getMessageJoinPeopleByMessageIdReceiverIdStatus(long messageId, long peopleId, int status) throws SQLException;

	/**
	 * @param messageId
	 * @param peopleId
	 */
	public int clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(long messageId, long peopleId) throws SQLException;

	/**
	 * @param messageId
	 * @param peopleId
	 */
	public int deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(long quoteMessageId, long peopleId) throws SQLException;

	/**
	 * @param replyJoins
	 */
	public void addMessageReplyJoinPeoples(List<MessageReplyJoinPeople> replyJoins) throws SQLException;

	/**
	 * 根据page查询message reply id列表
	 * 
	 * @param replyJoinPage
	 * @return
	 */
	public List<Long> getMessageReplyIdsByJoinPage(MessageReplyJoinPeoplePage replyJoinPage) throws SQLException;

	/**
	 * @param replyIds
	 * @return
	 */
	public List<MessageReply> getMessageRepliesByIds(List<Long> replyIds) throws SQLException;

	/**
	 * @param joinPage
	 * @return
	 */
	public List<Long> getMessageIdsByJoinPage(MessageJoinPeoplePage joinPage) throws SQLException;

	/**
	 * @param replyId
	 * @param receiverId
	 * @return
	 */
	public int deleteMessageReplyById(long replyId, long receiverId) throws SQLException;

	/**
	 * @param messageId
	 * @param receiverId
	 * @throws SQLException
	 */
	public int decrMessageReplyNumberByMessageIdAndReceiverId(long messageId, long receiverId) throws SQLException;

	/**
	 * @param messageId
	 */
	public int incrAllMessageReplyNumberByMessageId(long messageId) throws SQLException;
}
