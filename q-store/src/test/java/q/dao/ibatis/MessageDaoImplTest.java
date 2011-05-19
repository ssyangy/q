/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang at gmail dot com
 * @date May 19, 2011
 *
 */
public class MessageDaoImplTest {
	
	private static MessageDaoImpl messageDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		messageDao = (MessageDaoImpl)TestSupport.getBean("messageDao");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#addMessage(q.domain.Message)}.
	 */
	@Test
	public void testAddMessage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessagesByPage(q.dao.page.MessagePage)}.
	 */
	@Test
	public void testGetMessagesByPage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageRepliesByPage(q.dao.page.MessageReplyPage)}.
	 */
	@Test
	public void testGetMessageRepliesByPage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageById(long)}.
	 */
	@Test
	public void testGetMessageById() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#addMessageJoinPeoples(java.util.List)}.
	 */
	@Test
	public void testAddMessageJoinPeoples() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#addMessageReplyJoinPeoples(java.util.List)}.
	 */
	@Test
	public void testAddMessageReplyJoinPeoples() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageReplyById(long)}.
	 */
	@Test
	public void testGetMessageReplyById() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#addMessageReply(q.domain.MessageReply)}.
	 */
	@Test
	public void testAddMessageReply() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageJoinPeoplesByPage(q.dao.page.MessageJoinPeoplePage)}.
	 */
	@Test
	public void testGetMessageJoinPeoplesByPage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageIdsByJoinPage(q.dao.page.MessageJoinPeoplePage)}.
	 */
	@Test
	public void testGetMessageIdsByJoinPage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageReplyIdsByJoinPage(q.dao.page.MessageReplyJoinPeoplePage)}.
	 */
	@Test
	public void testGetMessageReplyIdsByJoinPage() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageRepliesByIds(java.util.List)}.
	 */
	@Test
	public void testGetMessageRepliesByIds() {
		
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#getMessageJoinPeopleByMessageIdReceiverIdStatus(long, long, int)}.
	 * @throws SQLException 
	 */
	@Test
	public void testGetMessageJoinPeopleByMessageIdReceiverIdStatus() throws SQLException {
		messageDao.getMessageJoinPeopleByMessageIdReceiverIdStatus(0L, 0L, 0);
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(long, long)}.
	 * @throws SQLException 
	 */
	@Test
	public void testClearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId() throws SQLException {
		messageDao.clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(long, long)}.
	 * @throws SQLException 
	 */
	@Test
	public void testDeleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId() throws SQLException {
		messageDao.deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#deleteMessageReplyByIdAndReceiverId(long, long)}.
	 * @throws SQLException 
	 */
	@Test
	public void testDeleteMessageReplyByIdAndReceiverId() throws SQLException {
		messageDao.deleteMessageReplyByIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#decrMessageReplyNumberByMessageIdAndReceiverId(long, long)}.
	 * @throws SQLException 
	 */
	@Test
	public void testDecrMessageReplyNumberByMessageIdAndReceiverId() throws SQLException {
		messageDao.decrMessageReplyNumberByMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.MessageDaoImpl#incrAllMessageReplyNumberByMessageId(long)}.
	 */
	@Test
	public void testIncrAllMessageReplyNumberByMessageId() {
	}

}
