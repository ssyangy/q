/**
 *
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.dao.page.MessageJoinPeoplePage;
import q.dao.page.MessageReplyJoinPeoplePage;
import q.domain.MessageJoinPeople;

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
		messageDao = (MessageDaoImpl) TestSupport.getBean("messageDao");
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
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#addMessage(q.domain.Message)}.
	 */
	@Test
	public void testAddMessage() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessagesByPage(q.dao.page.MessagePage)}
	 * .
	 */
	@Test
	public void testGetMessagesByPage() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageRepliesByPage(q.dao.page.MessageReplyPage)}
	 * .
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
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#addMessageJoinPeoples(java.util.List)}
	 * .
	 */
	@Test
	public void testAddMessageJoinPeoples() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#addMessageReplyJoinPeoples(java.util.List)}
	 * .
	 */
	@Test
	public void testAddMessageReplyJoinPeoples() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageReplyById(long)}.
	 */
	@Test
	public void testGetMessageReplyById() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#addMessageReply(q.domain.MessageReply)}
	 * .
	 */
	@Test
	public void testAddMessageReply() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageJoinPeoplesByPage(q.dao.page.MessageJoinPeoplePage)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetMessageJoinPeoplesByPageWhenAddMessageReply() throws SQLException {
		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setMessageId(1L);
		messageDao.getMessageJoinPeoplesByPage(joinPage);

	}

	@Test
	public void testGetMessageJoinPeoplesByPageWhenSelectPeopleMessageIndex() throws SQLException {
		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setReceiverId(1L);
		joinPage.setIgnoreReplyNum(false);
		joinPage.setSize(10);
		joinPage.setStartId(999L);
		messageDao.getMessageJoinPeoplesByPage(joinPage);

	}

	@Test
	public void testGetMessageJoinPeoplesByPageWhenSelectPeopleMessagesAllParticipation() throws SQLException {
		MessageJoinPeoplePage joinPage = new MessageJoinPeoplePage();
		joinPage.setMessageIds(Arrays.asList(1L, 2L));
		messageDao.getMessageJoinPeoplesByPage(joinPage);

	}

	@Test
	public void updateMessageLastReplyByMessageId() throws SQLException {
		MessageJoinPeoplePage updateJoinPage = new MessageJoinPeoplePage();
		updateJoinPage.setMessageId(1L);
		updateJoinPage.setLastReplyId(1L);
		updateJoinPage.setLastReplySenderId(1L);
		messageDao.updateMessageLastReplyByMessageId(updateJoinPage);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageIdsByJoinPage(q.dao.page.MessageJoinPeoplePage)}
	 * .
	 */
	@Test
	public void testGetMessageIdsByJoinPage() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageReplyIdsByJoinPage(q.dao.page.MessageReplyJoinPeoplePage)}
	 * .
	 * @throws SQLException
	 */
	@Test
	public void testGetMessageReplyIdsByJoinPage() throws SQLException {
		MessageReplyJoinPeoplePage joinPage = new MessageReplyJoinPeoplePage();
		joinPage.setQuoteMessageId(1L);
		joinPage.setReceiverId(1L);
		joinPage.setDesc(true);
		joinPage.setSize(10);
		joinPage.setStartId(999L);
		this.messageDao.getMessageReplyIdsByJoinPage(joinPage);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageRepliesByIds(java.util.List)}
	 * .
	 */
	@Test
	public void testGetMessageRepliesByIds() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#getMessageJoinPeopleByMessageIdReceiverIdStatus(long, long, int)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetMessageJoinPeopleByMessageIdReceiverIdStatus() throws SQLException {
		messageDao.getMessageJoinPeopleByMessageIdReceiverIdStatus(0L, 0L, 0);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(long, long)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testClearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId() throws SQLException {
		messageDao.clearMessageJoinPeopleReplyNumberByMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(long, long)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDeleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId() throws SQLException {
		messageDao.deleteAllMessageReplyJoinPeopleByQuoteMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#deleteMessageReplyByIdAndReceiverId(long, long)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDeleteMessageReplyByIdAndReceiverId() throws SQLException {
		messageDao.deleteMessageReplyByIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#decrMessageReplyNumberByMessageIdAndReceiverId(long, long)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDecrMessageReplyNumberByMessageIdAndReceiverId() throws SQLException {
		messageDao.decrMessageReplyNumberByMessageIdAndReceiverId(0L, 0L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.MessageDaoImpl#incrAllMessageReplyNumberByMessageId(long)}
	 * .
	 */
	@Test
	public void testIncrAllMessageReplyNumberByMessageId() {
	}

}
