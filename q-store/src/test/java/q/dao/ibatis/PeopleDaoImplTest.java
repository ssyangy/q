/**
 *
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
import q.domain.Area;
import q.domain.People;
import q.domain.PeopleRelationStatus;

/**
 * @author seanlinwang at gmail dot com
 * @date May 18, 2011
 *
 */
public class PeopleDaoImplTest {

	private static PeopleDao peopleDao = new PeopleDaoImpl();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		peopleDao = (PeopleDao) TestSupport.getBean("peopleDao");
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
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#addPeople(q.domain.People)}.
	 */
	@Test
	public void testAddPeople() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#updatePeopleById(q.domain.People)}.
	 */
	@Test
	public void testUpdatePeopleById() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeopleById(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeopleById() throws SQLException {
		peopleDao.getPeopleById(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeopleByEmail(java.lang.String)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeopleByEmail() throws SQLException {
		peopleDao.getPeopleByEmail("xalinx@gmail.com");
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeopleByUsername(java.lang.String)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeopleByUsername() throws SQLException {
		peopleDao.getPeopleByUsername("xalinx@gmail.com");
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getHotPeoplesByArea(q.domain.Area, int)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetHotPeoplesByArea() throws SQLException {
		peopleDao.getHotPeoplesByArea(Area.getRootArea(), 10);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeoplesByIds(java.util.List)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeoplesByIds() throws SQLException {
		peopleDao.getPeoplesByIds(Arrays.asList(1L, 2L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getIdRealNameMapByIds(java.util.Set)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetIdRealNameMapByIds() throws SQLException {
		peopleDao.getIdRealNameMapByIds(new HashSet<Long>(Arrays.asList(1L, 2L)));
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#addPeopleRelation(q.domain.PeopleRelation)}.
	 */
	@Test
	public void testAddPeopleRelation() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeopleRelationsByPage(q.dao.page.PeopleRelationPage)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeopleRelationsByPage() throws SQLException {
		PeopleRelationPage page = new PeopleRelationPage();
		page.setFromPeopleId(0L);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		page.setDesc(true);
		page.setSize(5);
		page.setStartId(9999999L);
		peopleDao.getPeopleRelationsByPage(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getAllFollowingId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetAllFollowingId() throws SQLException {
		peopleDao.getAllFollowingId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getPeopleRelationByFromIdToId(long, long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetPeopleRelationByFromIdToId() throws SQLException {
		peopleDao.getPeopleRelationByFromIdToId(9473724000010011L, 1298102888539L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#updatePeopleRelationStatusById(q.domain.PeopleRelationStatus, q.domain.PeopleRelationStatus, long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testUpdatePeopleRelationStatusById() throws SQLException {
		peopleDao.updatePeopleRelationStatusById(PeopleRelationStatus.BLOCK, PeopleRelationStatus.DELETE, 1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#updatePeopleRelationStatusByFromIdAndToId(q.domain.PeopleRelationStatus, q.domain.PeopleRelationStatus, long, long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testUpdatePeopleRelationStatusByFromIdAndToId() throws SQLException {
		peopleDao.updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus.BLOCK, PeopleRelationStatus.DELETE, 1L, 2L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#getInterestById(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testGetInterestById() throws SQLException {
		peopleDao.getInterestById(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#updateInterestById(q.domain.People)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testUpdateInterestById() throws SQLException {
		People people = new People();
		people.setBook("book");
		people.setId(1L);
		peopleDao.updateInterestById(people);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#selectPasswordById(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testSelectPasswordById() throws SQLException {
		peopleDao.selectPasswordById(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#decrPeopleWeiboNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDecrPeopleWeiboNumberByPeopleId() throws SQLException {
		peopleDao.decrPeopleWeiboNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#incrPeopleWeiboNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testIncrPeopleWeiboNumberByPeopleId() throws SQLException {
		peopleDao.incrPeopleWeiboNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#incrPeopleFollowingNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testIncrPeopleFollowingNumberByPeopleId() throws SQLException {
		peopleDao.incrPeopleFollowingNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#incrPeopleFollowerNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testIncrPeopleFollowerNumberByPeopleId() throws SQLException {
		peopleDao.incrPeopleFollowerNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#decrPeopleFollowingNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDecrPeopleFollowingNumberByPeopleId() throws SQLException {
		peopleDao.decrPeopleFollowingNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#decrPeopleFollowerNumberByPeopleId(long)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testDecrPeopleFollowerNumberByPeopleId() throws SQLException {
		peopleDao.decrPeopleFollowerNumberByPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.PeopleDaoImpl#updatePasswordByPeopleId(long, java.lang.String)}.
	 *
	 * @throws SQLException
	 */
	@Test
	public void testUpdatePasswordByPeopleId() throws SQLException {
		peopleDao.updatePasswordByPeopleId(1L, "xxx");
	}



}
