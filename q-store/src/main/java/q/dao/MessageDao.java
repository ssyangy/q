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

	public void addMessage(Message message) throws SQLException;
	
	public List<Message> getPageMessages(MessagePage page) throws SQLException;
}
