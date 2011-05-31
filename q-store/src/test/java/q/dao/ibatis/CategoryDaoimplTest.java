package q.dao.ibatis;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.dao.CategoryDao;
import q.domain.Category;

public class CategoryDaoimplTest {

	private static CategoryDao categoryDao = new CategoryDaoImpl();

	@BeforeClass
	public static void setUpBeforeClass() {
		categoryDao = (CategoryDao)TestSupport.getBean("categoryDao");
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

	@Test
	public void testUpdateCategorySortOrder() throws SQLException {
		Category category = new Category();
		category.setId(12686176455000011L);
		category.setSortOrder(10);
		categoryDao.updateCategorySortOrder(category);
	}


}
