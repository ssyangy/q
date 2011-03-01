/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.MessagePage;
import q.domain.Message;

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
}
