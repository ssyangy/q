/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import q.dao.MessageDao;
import q.dao.page.MessagePage;
import q.domain.Message;
import q.util.IdCreator;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class MessageDaoImpl extends AbstractDaoImpl implements MessageDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#addMessage(q.domain.Message)
	 */
	@Override
	public void addMessage(Message message) throws SQLException {
		message.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertMessage", message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.MessageDao#getPageMessages(q.dao.page.MessagePage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getPageMessages(MessagePage page) throws SQLException {
		return (List<Message>) this.sqlMapClient.queryForList("selectPageMessages", page);
	}

	/* (non-Javadoc)
	 * @see q.dao.MessageDao#getMessageById(long)
	 */
	@Override
	public Message getMessageById(long replyMessageId) throws SQLException {
		return (Message) this.sqlMapClient.queryForObject("selectMessageById", replyMessageId);
	}

}
