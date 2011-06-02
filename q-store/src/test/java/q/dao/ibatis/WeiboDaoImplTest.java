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

import q.dao.page.WeiboReplyPage;

/**
 * @author seanlinwang at gmail dot com
 * @date May 27, 2011
 *
 */
public class WeiboDaoImplTest {
	private static WeiboDaoImpl weiboDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		weiboDao = (WeiboDaoImpl) TestSupport.getBean("weiboDao");
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
	 * {@link q.dao.ibatis.WeiboDaoImpl#addWeibo(q.domain.Weibo)}.
	 */
	@Test
	public void testAddWeibo() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#addWeiboJoinGroup(long, long, long)}.
	 */
	@Test
	public void testAddWeiboJoinGroup() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeiboIdsByJoinPage(q.dao.page.WeiboPage)}
	 * .
	 */
	@Test
	public void testGetWeiboIdsByJoinPage() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getHotGroupWeibosByGroupId(long, int, int)}
	 * .
	 */
	@Test
	public void testGetHotGroupWeibosByGroupId() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getHotWeibosByGroupIds(java.util.List, int, int)}
	 * .
	 */
	@Test
	public void testGetHotWeibosByGroupIds() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeibosByIds(java.util.List, boolean)}
	 * .
	 */
	@Test
	public void testGetWeibosByIds() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeiboRepliesByIds(java.util.List, boolean)}
	 * .
	 */
	@Test
	public void testGetWeiboRepliesByIds() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeibosByPage(q.dao.page.WeiboPage)}.
	 */
	@Test
	public void testGetWeibosByPage() {

	}

	/**
	 * Test method for {@link q.dao.ibatis.WeiboDaoImpl#getHotWeibos(int)}.
	 */
	@Test
	public void testGetHotWeibos() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getFollowingWeibosByPage(q.dao.page.WeiboPage)}
	 * .
	 */
	@Test
	public void testGetFollowingWeibosByPage() {

	}

	/**
	 * Test method for {@link q.dao.ibatis.WeiboDaoImpl#getWeiboById(long)}.
	 */
	@Test
	public void testGetWeiboById() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeiboRepliesByPage(q.dao.page.WeiboReplyPage)}
	 * .
	 * @throws SQLException
	 */
	@Test
	public void testGetWeiboRepliesByPageForReplyReceived() throws SQLException {
		WeiboReplyPage page = new WeiboReplyPage();
		page.setDesc(false);
		page.setSize(10);
		page.setStartId(10L);
		page.setQuoteSenderId(1L);
		weiboDao.getWeiboRepliesByPage(page);

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#addWeiboReply(q.domain.WeiboReply)}.
	 */
	@Test
	public void testAddWeiboReply() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getPeopleWeiboNumByPeopleId(long)}.
	 */
	@Test
	public void testGetPeopleWeiboNumByPeopleId() {

	}

	/**
	 * Test method for {@link q.dao.ibatis.WeiboDaoImpl#getWeiboReplyById(long)}
	 * .
	 */
	@Test
	public void testGetWeiboReplyById() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeiboJoinGroupByWeiboIdAndGroupId(long, long)}
	 * .
	 */
	@Test
	public void testGetWeiboJoinGroupByWeiboIdAndGroupId() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#getWeiboJoinGroupByWeiboId(long)}.
	 */
	@Test
	public void testGetWeiboJoinGroupByWeiboId() {

	}

	/**
	 * Test method for {@link q.dao.ibatis.WeiboDaoImpl#deleteWeiboById(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDeleteWeiboById() throws SQLException {
		weiboDao.deleteWeiboById(1L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#deleteWeiboJoinGroupsByWeiboId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDeleteWeiboJoinGroupsByWeiboId() throws SQLException {
		weiboDao.deleteWeiboJoinGroupsByWeiboId(1L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#deleteWeiboReplyBySenderIdAndReplyId(long, long)}
	 * .
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDeleteWeiboReplyBySenderIdAndReplyId() throws SQLException {
		weiboDao.deleteWeiboReplyBySenderIdAndReplyId(1L, 1L);
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#incrWeiboRetweetNumByWeiboId(long)}.
	 */
	@Test
	public void testIncrWeiboRetweetNumByWeiboId() {

	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#decrWeiboRetweetNumberByWeiboId(long)}.
	 */
	@Test
	public void testDecrWeiboRetweetNumberByWeiboId() {
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#incrWeiboReplyNumByReplyId(long)}.
	 */
	@Test
	public void testIncrWeiboReplyNumByReplyId() {
	}

	/**
	 * Test method for
	 * {@link q.dao.ibatis.WeiboDaoImpl#decrWeiboReplyNumByWeiboId(long)}.
	 */
	@Test
	public void testDecrWeiboReplyNumByWeiboId() {
	}

}
