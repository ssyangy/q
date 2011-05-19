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

import q.dao.page.GroupPage;
import q.dao.page.GroupRecommendPage;
import q.dao.page.PeopleJoinGroupPage;
import q.domain.Group;
import q.domain.Status;

/**
 * @author seanlinwang at gmail dot com
 * @date May 18, 2011
 * 
 */
public class GroupDaoImplTest {
	private static GroupDaoImpl groupDao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		groupDao = (GroupDaoImpl) TestSupport.getBean("groupDao");
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
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#addGroup(q.domain.Group)}.
	 */
	@Test
	public void testAddGroup() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#addGroupJoinCategory(long, long)}.
	 */
	@Test
	public void testAddGroupJoinCategory() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#addPeopleJoinGroup(long, long)}.
	 */
	@Test
	public void testAddPeopleJoinGroup() {
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupById(long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupById() throws SQLException {
		groupDao.getGroupById(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleByGroupIdPeopleId(long, long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleByGroupIdPeopleId() throws SQLException {
		groupDao.getJoinPeopleByGroupIdPeopleId(1L, 2L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleIdsByGroupId(long, int, int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleIdsByGroupId() throws SQLException {
		groupDao.getJoinPeopleIdsByGroupId(1L, 1, 1);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleIdsByGroupIds(java.util.List, int, int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleIdsByGroupIds() throws SQLException {
		groupDao.getJoinPeopleIdsByGroupIds(Arrays.asList(1L), 1, 2);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleIdsByJoinPage(q.dao.page.PeopleJoinGroupPage)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleIdsByJoinPage() throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setDesc(false);
		page.setGroupId(1L);
		page.setStartId(1000L);
		page.setSize(10);
		groupDao.getJoinPeopleIdsByJoinPage(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupsByJoinPeopleId(long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupsByJoinPeopleId() throws SQLException {
		groupDao.getGroupsByJoinPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupIdsByJoinPeopleId(long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupIdsByJoinPeopleId() throws SQLException {
		groupDao.getGroupIdsByJoinPeopleId(1L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupIdsByJoinPeopleIdAndGroupIds(long, java.util.List)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupIdsByJoinPeopleIdAndGroupIds() throws SQLException {
		groupDao.getGroupIdsByJoinPeopleIdAndGroupIds(1L, Arrays.asList(1L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupIdsByPeopleJoinGroupPage(q.dao.page.PeopleJoinGroupPage)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupIdsByPeopleJoinGroupPage() throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		groupDao.getGroupIdsByPeopleJoinGroupPage(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleIdsByHotAndGroupId(long, int, int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleIdsByHotAndGroupId() throws SQLException {
		groupDao.getJoinPeopleIdsByHotAndGroupId(1L, 1, 2);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getJoinPeopleIdsByHotAndGroupIds(java.util.List, int, int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetJoinPeopleIdsByHotAndGroupIds() throws SQLException {
		groupDao.getJoinPeopleIdsByHotAndGroupIds(Arrays.asList(1L), 1, 2);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#unjoinPeopleJoinGroup(long, long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testUnjoinPeopleJoinGroup() throws SQLException {
		groupDao.unjoinPeopleJoinGroup(1L, 2L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#rejoinPeopleJoinGroup(long, long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testRejoinPeopleJoinGroup() throws SQLException {
		groupDao.rejoinPeopleJoinGroup(1L, 2L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getAllGroupsByCatId(long)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetAllGroupsByCatId() throws SQLException {
		groupDao.getAllGroupsByCatId(0L);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getExsitGroupIdsByIds(java.util.List)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetExsitGroupIdsByIds() throws SQLException {
		groupDao.getExsitGroupIdsByIds(Arrays.asList(1L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupsByIds(java.util.List)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupsByIds() throws SQLException {
		groupDao.getGroupsByIds(Arrays.asList(0L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupsByLocation(q.domain.Group)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupsByLocation() throws SQLException {
		Group temp = new Group();
		temp.setLatitude(1.0);
		temp.setLongitude(2.0);
		groupDao.getGroupsByLocation(temp);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getNewGroups(int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetNewGroups() throws SQLException {
		groupDao.getNewGroups(1);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getHotGroups(int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetHotGroups() throws SQLException {
		groupDao.getHotGroups(1);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupIdNameMapByIds(java.util.Set)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupIdNameMapByIds() throws SQLException {
		groupDao.getGroupIdNameMapByIds(new HashSet<Long>(Arrays.asList(1L)));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getAllPromotedGroups(java.util.List)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetAllPromotedGroups() throws SQLException {
		groupDao.getAllPromotedGroups(Arrays.asList(1L, 2L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupsByPage(q.dao.page.GroupPage)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupsByPage() throws SQLException {
		GroupPage page = new GroupPage();
		page.setPeopleId(1L);
		groupDao.getGroupsByPage(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getRecommendGroupsByPage(q.dao.page.GroupRecommendPage)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetRecommendGroupsByPage() throws SQLException {
		GroupRecommendPage page = new GroupRecommendPage();
		page.setPeopleId(1L);
		groupDao.getRecommendGroupsByPage(page);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupJoinCategoriesByGroupIdAndStatus(long, Status)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupJoinCategoriesByGroupIdAndStatusWithCommonStatus() throws SQLException {
		groupDao.getGroupJoinCategoriesByGroupIdAndStatus(0L, Status.COMMON);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#getGroupJoinCategoriesByGroupIdAndStatus(long, Status)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetGroupJoinCategoriesByGroupIdAndStatusWithNullStatus() throws SQLException {
		groupDao.getGroupJoinCategoriesByGroupIdAndStatus(0L, null);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#deleteGroupJoinCategoriesByjoinIdsAndGroupId(long, java.util.List)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testDeleteGroupJoinCategoriesByjoinIdsAndGroupId() throws SQLException {
		groupDao.deleteGroupJoinCategoriesByjoinIdsAndGroupId(1L, Arrays.asList(1L, 2L));
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#updateGroup(q.domain.Group)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testUpdateGroup() throws SQLException {
		Group group = new Group();
		group.setName("xxx");
		groupDao.updateGroup(group);
	}

	/**
	 * Test method for {@link q.dao.ibatis.GroupDaoImpl#updateGroupJoinCategoryStatus(long, int, int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testupdateGroupJoinCategoryStatus() throws SQLException {
		groupDao.updateGroupJoinCategoryStatus(1L, Status.COMMON.getValue(), Status.DELETE.getValue());
	}

}
