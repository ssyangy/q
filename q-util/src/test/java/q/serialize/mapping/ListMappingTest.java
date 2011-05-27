/**
 * 
 */
package q.serialize.mapping;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.album.Album;
import q.serialize.album.AlbumSet;
import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.ListMapping;
import q.serialize.mapping.MapMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;


/**
 * The Class ListMappingTest.
 * 
 * @version 2009-2-19
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class ListMappingTest extends MappingTestBase {

	
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
	 * Test mapping album query result.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMappingAlbumQueryResult() throws MappingException, OperationCodeException {
		AlbumSet aqr = getAlbumQueryResult();
		MapMapping mm = getAlbumQueryResultMapping();

		Map<Object, Object> result = mm.mapping(aqr, null, false);
		this.checkAlbumQueryResult2Map(result);
	}

	/**
	 * Test mapping album query result with array item name.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMappingAlbumQueryResultWithArrayItemName() throws MappingException, OperationCodeException {
		AlbumSet aqr = getAlbumQueryResult();
		MapMapping mm = getAlbumQueryResultMapping();

		Map<Object, Object> result = mm.mapping(aqr, null, false);
		checkWithArrayItemName(result);
	}
	
	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.ListMapping#mapping(java.lang.Object, ExceptionExpressInfo, boolean)}
	 * .
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMappingAlbumListOrArray() throws MappingException, OperationCodeException {
		ListMapping<Map<Object, Object>> listm = new ListMapping<Map<Object, Object>>(getAlbumMapping(null));

		Album album = getAlbum();
		List<Album> albums = new ArrayList<Album>();
		albums.add(album);
		albums.add(album);

		// 测试转换对象列表
		List<Map<Object, Object>> rs = listm.mapping(albums, null, false);
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
		
		Writer writer = new StringWriter();
		Convert convert = new JSONConvert(writer);
		listm.write(convert, albums, null, "list",false);
		System.out.println(writer.toString());

		System.out.println(writer.toString());
	}
	
	@Test
	public void testMappingType(){
		ListMapping<Integer> lm = new ListMapping<Integer>();
		Assert.assertEquals(lm.getMappingType(), List.class);
	}

}
