/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessagePage;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.MessageReply;

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
	public List<Message> getPageMessages(MessagePage page) throws SQLException;

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
}
