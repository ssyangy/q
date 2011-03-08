package q.serialize.mapping;

import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.album.Album;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.SetMapping;
import q.serialize.mapping.UnsupportSourceTypeException;


// TODO: Auto-generated Javadoc
/**
 * The Class SetMappingTest.
 */
public class SetMappingTest extends MappingTestBase{
	
	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test mapping album set.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMappingAlbumSet() throws MappingException, OperationCodeException {
		SetMapping<Map<Object, Object>> listm = new SetMapping<Map<Object, Object>>(
				getAlbumMapping(null));

		Album album = getAlbum();
		Set<Album> albums = new HashSet<Album>();
		albums.add(album);
		albums.add(album);

		// 测试转换对象列表
		Set<Map<Object, Object>> rs = listm.mapping(albums, null, false);
		for (Map<Object, Object> item : rs) {
			checkAlbum2Map(item);
		}

		// 测试转换数组
		Album[] albumArr = new Album[2];
		albumArr[0] = album;
		albumArr[1] = album;
		rs = listm.mapping(albums, null, false);
		for (Map<Object, Object> item : rs) {
			checkAlbum2Map(item);
		}
	}
	
	/**
	 * Test mapping set string.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMappingString() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);set.add(2);set.add(3);set.add(4);
		Set<Integer> rs = listm.mapping("1,2,3,4", null, false);
		Assert.assertEquals(set.size(), rs.size());
		for(Integer i : set) {
			Assert.assertTrue(rs.contains(i));
		}
	}
	
	@Test
	public void testMappingArray() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);set.add(2);set.add(3);set.add(4);
		Set<Integer> rs = listm.mapping(new Integer[]{1, 2, 3, 4}, null, false);
		Assert.assertEquals(set.size(), rs.size());
		for(Integer i : set) {
			Assert.assertTrue(rs.contains(i));
		}
	}
	
	@Test
	public void testMappingUnsupport() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		try {
		listm.mapping(new HashMap<String, String>(), null, false);
		} catch (UnsupportSourceTypeException e) {
			return;
		}
		fail();
	}
	
	@Test
	public void testMappingEmptySet() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> rs = listm.mapping(new HashSet<Integer>(), null, false);
		Assert.assertEquals(0, rs.size());
	}
	
	@Test
	public void testMappingEmptyList() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> rs = listm.mapping(new ArrayList<Integer>(), null, false);
		Assert.assertEquals(0, rs.size());
	}
	
	@Test
	public void testMappingEmptyArray() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> rs = listm.mapping(new String[]{}, null, false);
		Assert.assertEquals(0, rs.size());
	}
	
	@Test
	public void testMappingEmptyString() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> rs = listm.mapping("", null, false);
		Assert.assertEquals(null, rs);
	}
	
	@Test
	public void testMappingBlankString() throws MappingException, OperationCodeException {
		SetMapping<Integer> listm = new SetMapping<Integer>(im);
		Set<Integer> rs = listm.mapping("  ", null, false);
		Assert.assertEquals(1, rs.size());
	}
}
