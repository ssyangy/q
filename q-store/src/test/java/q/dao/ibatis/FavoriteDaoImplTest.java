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

import q.dao.page.FavoritePage;
import q.domain.FavoriteStatus;

/**
 * @author seanlinwang at gmail dot com
 * @date May 25, 2011
 * 
 */
public class FavoriteDaoImplTest {
	private static FavoriteDaoImpl favoriteDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		favoriteDao = (FavoriteDaoImpl) TestSupport.getBean("favoriteDao");
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
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#addReplyFavorite(long, long, long)}.
	 */
	@Test
	public void testAddReplyFavorite() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#addWeiboFavorite(long, long, long)}.
	 */
	@Test
	public void testAddWeiboFavorite() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#getPageFavorites(q.dao.page.FavoritePage)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetPageFavorites() throws SQLException {
		FavoritePage page = new FavoritePage();
		page.setDesc(false);
		page.setStatus(FavoriteStatus.FAV);
		page.setSize(2);
		page.setCreatorId(1L);
		page.setStartId(99999L);
		favoriteDao.getPageFavorites(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#getFavoriteIdsByFromIdsAndCreatorId(java.util.List, long)}.
	 */
	@Test
	public void testGetFavoriteIdsByFromIdsAndCreatorId() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#getReplyFavoriteByReplyIdAndCreatorId(long, long)}.
	 */
	@Test
	public void testGetReplyFavoriteByReplyIdAndCreatorId() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#getWeiboFavoriteByWeiboIdAndCreatorId(long, long)}.
	 */
	@Test
	public void testGetWeiboFavoriteByWeiboIdAndCreatorId() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#unFavReplyFavorite(long, long)}.
	 */
	@Test
	public void testUnFavReplyFavorite() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#unFavWeiboFavorite(long, long)}.
	 */
	@Test
	public void testUnFavWeiboFavorite() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#favReplyById(long)}.
	 */
	@Test
	public void testFavReplyById() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.FavoriteDaoImpl#favWeiboById(long)}.
	 */
	@Test
	public void testFavWeiboById() {
	}

}
