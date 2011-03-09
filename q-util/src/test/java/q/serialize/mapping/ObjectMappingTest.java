/**
 * 
 */
package q.serialize.mapping;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.album.Album;
import q.serialize.album.AlbumSet;
import q.serialize.mapping.IntMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.ObjectMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.StringMapping;


// TODO: Auto-generated Javadoc
/**
 * The Class ObjectMappingTest.
 * 
 * @author alin mailto:xalinx@gmail.com
 * @date Apr 13, 2009
 */
public class ObjectMappingTest extends MappingTestBase {

	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test single layer mapping.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testSingleLayerMapping() throws OperationCodeException,
			SecurityException, NoSuchMethodException {
		ObjectMapping<Album> map2album = this.get2AlbumMapping();
		try {
			checkMap2Album(map2album.mapping(this.getInputAlbumMap(), null,
					false));
		} catch (MappingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test single layer mapping.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws MappingException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testInnerObject() throws OperationCodeException,
			MappingException, SecurityException, NoSuchMethodException {
		// XXX TODO 重要：ObjectMapping读到的不是别名的Mapping,与get2AlbumMapping不一致
		// MappingClassReader mcr = new
		// MappingClassReader(Utils.UNDERLINE_STYLE);
		// boolean includeSuperClass =false;
		// String name = "album";
		// String mappingName = "album";
		// //(T object,
		// //boolean includeSuperClass, String mappingName, int callFrom)
		//		
		// MemberMapping<Album> objectMapping =
		// mcr.getMemberMappingFromType(Album.class
		// , includeSuperClass, name, mappingName,
		// MappingClassReader.objectCall);
		// ObjectMapping<Album> map2album =
		// (ObjectMapping<Album>)objectMapping.getMapping();

		// XXX TODO 重要：ObjectMapping读到的不是别名的Mapping,与get2AlbumMapping不一致
		ObjectMapping<Album> map2album = this.get2AlbumMapping();
		try {
			checkMap2Album(map2album.mapping(this.getInputAlbumMap(), null,
					false));
		} catch (MappingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test single layer mapping use config file.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testSingleLayerMappingUseConfigFile()
			throws OperationCodeException {
		// get mm from config file
		MethodMapping<Object> mm = getAddAlbumMethodMapping();
		MemberMapping<?> albumMapping = mm.getMethodCallMapping()
				.getParameterMappings().get(0);
		try {
			checkMap2Album((Album) albumMapping.mapping(
					this.getInputAlbumMap(), false));
		} catch (MappingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test multi layer mapping.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void testMultiLayerMapping() throws OperationCodeException,
			SecurityException, NoSuchMethodException {
		ObjectMapping<AlbumSet> map2multi = this.get2AlbumQueryResultMapping();
		try {
			checkMap2AlbumQueryResult(map2multi.mapping(this
					.getInputAlbumQueryResultMap(), null, false));
		} catch (MappingException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testObject2Object() throws MappingException, OperationCodeException {
		ObjectMapping<ObjectTo> aqm = new ObjectMapping<ObjectTo>(
				ObjectTo.class);
		IntMapping im = new IntMapping();
		aqm
				.addMemberMapping(new MemberMapping<Integer>("intFrom",
						"intTo", im));
		StringMapping sm = new StringMapping();
		aqm.addMemberMapping(new MemberMapping<String>("stringFrom", "stringTo", sm));
		StringMapping sm2 = new StringMapping();
		MemberMapping<String> mm = new MemberMapping<String>("map.stringInMap", "stringFromMap", sm2);
		mm.setNames(new String[]{"map", "stringInMap"});
		aqm.addMemberMapping(mm);
		
		
		
		ObjectFrom of = new ObjectFrom();
		of.setIntFrom(1);
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("stringInMap", "test");
		of.setMap(map);
		
		ObjectTo rs = aqm.mapping(of, null, false);
		Assert.assertEquals(of.getIntFrom(), rs.getIntTo());
		Assert.assertEquals(of.getStringFrom(), rs.getStringTo());
		Assert.assertEquals(of.getMap().get("stringInMap"), rs.getStringFromMap());
	}
}

class ObjectFrom {
	private int intFrom;

	public int getIntFrom() {
		return intFrom;
	}

	public void setIntFrom(int intFrom) {
		this.intFrom = intFrom;
	}
	
	private String stringFrom;

	public String getStringFrom() {
		return stringFrom;
	}

	public void setStringFrom(String stringFrom) {
		this.stringFrom = stringFrom;
	}
	
	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
}


class ObjectTo {
	private int intTo;

	public int getIntTo() {
		return intTo;
	}

	public void setIntTo(int intTo) {
		this.intTo = intTo;
	}
	
	private String stringTo;

	public String getStringTo() {
		return stringTo;
	}

	public void setStringTo(String stringTo) {
		this.stringTo = stringTo;
	}
	
	private String stringFromMap;

	public String getStringFromMap() {
		return stringFromMap;
	}

	public void setStringFromMap(String stringFromMap) {
		this.stringFromMap = stringFromMap;
	}
	
	
}

