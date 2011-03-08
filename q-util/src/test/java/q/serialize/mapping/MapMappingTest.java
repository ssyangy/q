package q.serialize.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.album.Album;
import q.serialize.album.AlbumResult;
import q.serialize.album.Picture;
import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.convert.XMLConvert;
import q.serialize.mapping.BooleanMapping;
import q.serialize.mapping.IntegerMapping;
import q.serialize.mapping.LongMapping;
import q.serialize.mapping.MapMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodCallMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.PrimitiveMapMaping;
import q.serialize.mapping.StringMapping;


/**
 * The Class MapMappingTest.
 */
public class MapMappingTest extends MappingTestBase {

	/** The sm. */
	private StringMapping sm = new StringMapping();

	/** The im. */
	private IntegerMapping im = new IntegerMapping();

	/** The lm. */
	private LongMapping lm = new LongMapping();

	/** The bm. */
	private BooleanMapping bm = new BooleanMapping();

	/**
	 * Sets the up before class.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Tear down.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test get member mappings.
	 */
	@Test
	public void testGetMemberMappings() {
		MapMapping mm = new MapMapping();
		MemberMapping<String> memm = new MemberMapping<String>("name", "title",
				sm);
		mm.addMemberMapping(memm);
		assertTrue(memm == mm.getMemberMappings().get("name"));
	}

	/**
	 * jiangyy:用户传递location.state,转为Location对象,的state属性,现在不支持这样的功能。
	 * @throws OperationCodeException 
	 * @throws MappingException 
	 */
	@Test
	public void testParameTypeIsClass() throws MappingException, OperationCodeException {
		MethodMapping<Object> itemAddMethodMappin = getAddAlbumMethodMapping();
		String interfaceName = itemAddMethodMappin.getInterfaceName();
		// Item item = new Item();
		// Location lo = new Location();
		// item.setLocation(lo);

		// ApiInput
		Map hm = new HashMap<String, String>();
		hm.put("album_object.id", "1234");
		// hm.put("album_object.title", "title");

		MethodCallMapping methodCallMapping = itemAddMethodMappin
				.getMethodCallMapping();
		List<MemberMapping<?>> parameterMappings = methodCallMapping
				.getParameterMappings();

		for (MemberMapping<?> m : parameterMappings) {
				Object mapping = m.mapping(hm, false);
				if (mapping instanceof Album) {
					Album i = (Album) mapping;
					System.out.println(">>>>>album_object.id:"
							+ i.getAlbum_object().getCid());
				}
		}
	}

	/**
	 * Test mapping.
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testMapping() throws MappingException, OperationCodeException {
		// 目标对象
		Picture pic = new Picture();
		pic.setIntt(12);
		Album album = new Album();
		album.setPicture(pic);
		album.setName("007");
		album.setPicNum(100);
		album.setUserId(1234L);
		album.setCreated(new GregorianCalendar(2008, 9, 1, 23, 24, 25)
				.getTime());
		album.setValid(true);
		// 构造 ObjectMapping & MemberMapping
		MapMapping mm = new MapMapping();
		// int:picture.intt --> string:intt
		MemberMapping<String> picMM = new MemberMapping<String>("picture.intt",
				"inttStr", sm);
		picMM.setNames(new String[] { "picture", "intt" });
		mm.addMemberMapping(picMM);
		// string:name --> string:title
		mm.addMemberMapping(new MemberMapping<String>("name", "title", sm));
		// int:picNum --> int:pic_num
		mm
				.addMemberMapping(new MemberMapping<Integer>("picNum",
						"pic_num", im));
		// date:created --> string:created
		mm
				.addMemberMapping(new MemberMapping<String>("created",
						"created", sm));
		// boolean:valid --> string:valid
		mm.addMemberMapping(new MemberMapping<String>("valid", "valid", sm));
		// 转换
		Map<Object, Object> result = mm.mapping(album, null, false);
		// 依据预设的 member mapping比较获取转换后结果
		assertEquals("12", (String) result.get("inttStr"));
		assertEquals(album.getName(), (String) result.get("title"));
		assertEquals(album.getPicNum(), (Integer) result.get("pic_num"));
		assertEquals("2008-10-01 23:24:25", (String) result.get("created"));
		assertEquals("true", (String) result.get("valid"));
		// 没有设置该member的mapping,所以取该值为空
		assertNull(result.get("user_id"));
		// long:userId --> long:user_id
		mm.addMemberMapping(new MemberMapping<Long>("userId", "user_id", lm));
		result = mm.mapping(album, null, false);
		assertEquals(album.getUserId(), ((Long) result.get("user_id"))
				.longValue());
		// 换一个映射 long:userId --> string:user_id
		mm.addMemberMapping(new MemberMapping<String>("userId", "user_id", sm));
		result = mm.mapping(album, null, false);
		assertEquals(album.getUserId() + "", (String) result.get("user_id"));
		// 换一个映射 boolean:valid --> boolean:valid
		mm.addMemberMapping(new MemberMapping<Boolean>("valid", "valid", bm));
		result = mm.mapping(album, null, false);
		assertTrue(album.isValid() == ((Boolean) result.get("valid"))
				.booleanValue());
	}

	/**
	 * Test mapping success album result use config file.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testMappingSuccessAlbumResultUseConfigFile()
			throws OperationCodeException {
		// get mm from config file
		MethodMapping<Object> mm = getAddAlbumMethodMapping();
		MemberMapping<Object> rspMapping = mm.getMethodResponseMapping()
				.getParameterMapping();
		try {
			Map mapping = (Map) rspMapping.mapping(
					this.getSuccessAlbumResult(), false);
			System.out.println(mapping.toString());
			mapping = (Map) mapping.get("album");
			Object object = mapping.get("created");
			System.out.println(object);
			checkAlbum2Map(mapping);
		} catch (MappingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Test mapping success album result use config file.
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testMemberMappingArrayItemNameString()
			throws OperationCodeException {
		// get mm from config file
		MethodMapping<Object> mm = getAddAlbumMethodMapping();
		MemberMapping<Object> rspMapping = mm.getMethodResponseMapping()
				.getParameterMapping();
		try {
			AlbumResult successAlbumResult = this.getSuccessAlbumResult();
			StringWriter writer = new StringWriter();
			Convert convert = new JSONConvert(writer);
			rspMapping.write(convert, successAlbumResult, false);
			//rspMapping.write(writer, successAlbumResult, "json", false);
			String string = writer.toString();
			System.out.println(string);
			String include = "\"strs_name\":{\"str\":[\"<>1&'\\\"\",\"2\"]}";
			assertTrue(string.indexOf(include) > -1);
		} catch (MappingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	

	/**
	 * Test mapping fail album result use config file.
	 */
	@Test
	public void testMappingFailAlbumResultUseConfigFile() {
		// get mm from config file
		MethodMapping<Object> mm = getAddAlbumMethodMapping();
		MemberMapping<Object> rspMapping = mm.getMethodResponseMapping()
				.getParameterMapping();
		try {
			rspMapping.mapping(this.getFailAlbumResult(), false);
		} catch (MappingException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (OperationCodeException e) {
			OperationCodeException expect = getFailAlbumResultException();
			Assert.assertEquals(expect.getCode(), e.getCode());
			Assert.assertEquals(expect.getMsg(), e.getMsg());
		}
	}

	/**
	 * Test mapping album map.
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testMappingAlbumMap() throws MappingException,
			OperationCodeException {
		PrimitiveMapMaping<String, Map<Object, Object>> listm = new PrimitiveMapMaping<String, Map<Object, Object>>(sm, getAlbumMapping(null));

		Album album = getAlbum();
		Map<String, Album> albums = new HashMap<String, Album>();
		albums.put("1", album);
		albums.put("2", album);

		// 测试转换对象列表
		Map<String, Map<Object, Object>> rs = listm.mapping(albums, null, false);
		checkAlbum2Map(rs.get("1"));
		checkAlbum2Map(rs.get("2"));
	}

	/**
	 * Test mapping string map.
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	public void testMappingStringMap() throws MappingException,
			OperationCodeException {
		PrimitiveMapMaping m = new PrimitiveMapMaping<Object, Object>();
		PrimitiveMapMaping<String, Map> listm = new PrimitiveMapMaping<String, Map>(
				sm, m);
		Map<Object, Object> submap = new HashMap<Object, Object>();
		submap.put(1.0, "hello");
		submap.put("a", 'a');
		Map<String, Object> submap2 = new HashMap<String, Object>();
		submap.put("b", "test");
		submap.put("a", 'a');
		Map<String, Map> map = new HashMap<String, Map>();
		map.put("first", submap);
		map.put("second", submap2);
		Map<String, Map> rs = listm.mapping(map.toString(), null, false);
		Assert.assertEquals(map, rs);
		Assert.assertEquals(map.get("first"), rs.get("first"));
		Assert
				.assertEquals(map.get("first").get(1.0), rs.get("first").get(
						1.0));
		Assert
				.assertEquals(map.get("first").get("a"), rs.get("first").get(
						"a"));
		Assert.assertEquals(map.get("second"), rs.get("second"));
		Assert.assertEquals(map.get("second").get("a"), rs.get("second").get(
				"a"));
		Assert.assertEquals(map.get("second").get("b"), rs.get("second").get(
				"b"));
	}
}
