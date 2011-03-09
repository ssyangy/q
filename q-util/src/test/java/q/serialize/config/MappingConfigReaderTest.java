/**
 * 
 */
package q.serialize.config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.album.Album;
import q.serialize.config.MappingFormatException;
import q.serialize.convert.Convert;
import q.serialize.convert.XMLConvert;
import q.serialize.mapping.ArrayMapping;
import q.serialize.mapping.ListMapping;
import q.serialize.mapping.MapMapping;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MappingTestBase;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodResponseMapping;
import q.serialize.mapping.ObjectMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.Switcher;

/**
 * Test for q.serialize.config.MappingConfigReaderTest.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MappingConfigReaderTest extends MappingTestBase {

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
	 * Test get album.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	@Test
	public void testGetAlbum() throws IOException, MappingFormatException {
		MethodMapping<Object> mm = getGetAlbumMethodMapping();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("getAlbum", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.get", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();
		MemberMapping<?> pm = pms.get(0);
		assertEquals(int.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("aid", pm.getName());
		pm = pms.get(1);
		assertEquals(Integer.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("version", pm.getName());
		// assert methodResponse
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		MemberMapping<Object> rm = mrm.getParameterMapping();
		assertEquals("album", rm.getMappingName());
		Mapping<Object> mapping = rm.getMapping();
		assertEquals(java.util.Map.class, mapping.getMappingType());
		Map<String, MemberMapping<?>> memberMappings = mapping.getMemberMappings();
		MemberMapping<?> id = memberMappings.get("id");
		assertEquals("id", id.getName());
		assertEquals("aid", id.getMappingName());
		assertEquals(String.class, id.getMapping().getMappingType());
		MemberMapping<?> picNum = memberMappings.get("picNum");
		assertEquals("picNum", picNum.getName());
		assertEquals("pic_num", picNum.getMappingName());
		assertEquals(int.class, picNum.getMapping().getMappingType());
	}

	/**
	 * Test get album.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	@Test
	public void testGetAlbum_ChineseComments() throws IOException, MappingFormatException {
		MethodMapping<Object> mm = getGetAlbumMethodMappingWithChineseCommnets();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("getAlbum", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.get", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();
		MemberMapping<?> pm = pms.get(0);
		assertEquals(int.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("aid", pm.getName());
		pm = pms.get(1);
		assertEquals(Integer.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("version", pm.getName());
		// assert methodResponse
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		MemberMapping<Object> rm = mrm.getParameterMapping();
		assertEquals("album", rm.getMappingName());
		Mapping<Object> mapping = rm.getMapping();
		assertEquals(java.util.Map.class, mapping.getMappingType());
		Map<String, MemberMapping<?>> memberMappings = mapping.getMemberMappings();
		MemberMapping<?> id = memberMappings.get("id");
		assertEquals("id", id.getName());
		assertEquals("aid", id.getMappingName());
		assertEquals(String.class, id.getMapping().getMappingType());
		MemberMapping<?> picNum = memberMappings.get("picNum");
		assertEquals("picNum", picNum.getName());
		assertEquals("pic_num", picNum.getMappingName());
		assertEquals(int.class, picNum.getMapping().getMappingType());
	}

	// @Test
	public void testGetMappingFile() {
		MethodMapping<Object> mappingFile = getMappingFile();
		System.out.println(mappingFile.toString());
	}

	/**
	 * Test get albums.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	public void testGetAlbums() throws IOException, MappingFormatException {
		MethodMapping<Object> mm = getGetAlbumsMethodMapping();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("getAlbums", mm.getMethodCallMapping().getName());
		assertEquals("taobao.albums.get", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();
		MemberMapping<?> pm = pms.get(0);
		assertEquals(String.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("session_nick", pm.getName());
		pm = pms.get(1);
		assertEquals(String.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("nick", pm.getName());
		pm = pms.get(2);
		assertEquals(int.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("page_size", pm.getName());
		pm = pms.get(3);
		assertEquals(int.class, pm.getMapping().getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("page_no", pm.getName());
		// assert methodResponse
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		MemberMapping<Object> rm = mrm.getParameterMapping();
		assertEquals(null, rm.getMappingName());
		Mapping<Object> mapping = rm.getMapping();
		assertEquals(java.util.Map.class, mapping.getMappingType());
		Map<String, MemberMapping<?>> memberMappings = mapping.getMemberMappings();
		MemberMapping<?> count = memberMappings.get("count");
		assertEquals("count", count.getName());
		assertEquals("totalResults", count.getMappingName());
		assertEquals(int.class, count.getMapping().getMappingType());
		MemberMapping<?> pages = memberMappings.get("pages");
		assertEquals("pages", pages.getName());
		assertEquals("albums", pages.getMappingName());
		assertEquals(List.class, pages.getMapping().getMappingType());
		assertEquals("album", ((MapMapping) ((ListMapping) pages.getMapping()).getComponentMapping()).getMappingName());
		memberMappings = pages.getMapping().getMemberMappings();
		MemberMapping<?> id = memberMappings.get("id");
		assertEquals("id", id.getName());
		assertEquals("aid", id.getMappingName());
		assertEquals(String.class, id.getMapping().getMappingType());
		MemberMapping<?> picNum = memberMappings.get("picNum");
		assertEquals("picNum", picNum.getName());
		assertEquals("pic_num", picNum.getMappingName());
		assertEquals(int.class, picNum.getMapping().getMappingType());
		MemberMapping<?> strs = memberMappings.get("strs");
		assertEquals("strs", strs.getName());
		assertEquals("strs_name", strs.getMappingName());
		// MappingConfigWriter mw = new MappingConfigWriter();
		// File file = new File(
		// "src/test/resources/com/taobao/top/traffic/album/generate.xml");
		// FileWriter fw = new FileWriter(file, true);
		// try {
		// mw.writeMethodMapping(fw, mm);
		// } catch (MappingException e) {
		// e.printStackTrace();
		// }
		// fw.flush();
		// fw.close();
	}

	/**
	 * Test album valid.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	@Test
	public void testAlbumValid() throws IOException, MappingFormatException {
		MethodMapping<Object> mm = getAlbumValidMethodMapping();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("valid", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.valid", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();
		MemberMapping<?> pm = pms.get(0);
		assertEquals(int.class, pm.getMapping().getMappingType());
		assertEquals(int.class.getName(), pm.getMappingType());
		assertEquals(null, pm.getMappingName());
		assertEquals("aid", pm.getName());
		// assert methodResponse
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		MemberMapping<Object> memm = mrm.getParameterMapping();
		assertEquals("ok", memm.getMappingName());
		assertEquals(boolean.class, memm.getMapping().getMappingType());
	}

	@Test
	public void testConfigEmpty() throws IOException, MappingFormatException {
		MethodMapping<Object> mm = getEmptyMethodMapping();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("empty", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.empty", mm.getMethodCallMapping().getMappingName());
		// List<MemberMapping<?>> pms = mm.getMethodCallMapping()
		// .getParameterMappings();
		// MemberMapping<?> pm = pms.get(0);
		// assertEquals(int.class, pm.getMapping().getMappingType());
		// assertEquals(int.class.getName(), pm.getMappingType());
		// assertEquals(null, pm.getMappingName());
		// assertEquals("aid", pm.getName());
		// // assert methodResponse
		// MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		// MemberMapping<Object> memm = mrm.getParameterMapping();
		// assertEquals("ok", memm.getMappingName());
		// assertEquals(boolean.class, memm.getMapping().getMappingType());
	}

	/**
	 * Test add picture.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testAddPicture() throws FileNotFoundException, IOException, MappingFormatException, MappingException, OperationCodeException {
		MethodMapping<Object> mm = getAddPictureMethodMapping();
		// assert interface
		assertEquals("q.serialize.mapping.config.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// assert methodCall
		assertEquals("addPicture", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.pic.add", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();
		MemberMapping<?> pm = pms.get(0);
		assertEquals(null, pm.getMappingName());
		assertEquals("aid", pm.getName());
		assertEquals(int.class.getName(), pm.getMappingType());
		assertEquals(int.class, pm.getMapping().getMappingType());
		pm = pms.get(1);
		assertEquals("pic", pm.getName());
		assertEquals(null, pm.getMappingName());
		assertEquals(byte[].class.getName(), pm.getMappingType());
		assertEquals(byte.class, ((ArrayMapping<byte[], Byte>) pm.getMapping()).getComponentMapping().getMappingType());
		byte[] srcBytes = new byte[] { 1, 2 };
		byte[] targetBytes = (byte[]) pm.getMapping().mapping(srcBytes, null, false);
		assertArrayEquals(srcBytes, targetBytes);
		// assert methodResponse
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		assertNull(mrm.getParameterMapping());
	}

	/**
	 * Test add album.
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	@Test
	public void testAddAlbum() throws FileNotFoundException, IOException, MappingFormatException {
		MethodMapping<Object> mm = getAddAlbumMethodMapping();
		// assert interface
		assertEquals("q.serialize.album.AlbumService", mm.getInterfaceName());
		assertEquals("1.0.0", mm.getInterfaceVersion());
		// ================= methodCall ===================
		assertEquals("addAlbum", mm.getMethodCallMapping().getName());
		assertEquals("taobao.album.add", mm.getMethodCallMapping().getMappingName());
		List<MemberMapping<?>> pms = mm.getMethodCallMapping().getParameterMappings();

		// ---- pm 0
		MemberMapping<?> pm = pms.get(0);
		assertEquals(null, pm.getMappingName());
		assertEquals(null, pm.getName());
		assertEquals("q.serialize.album.Album", pm.getMappingType());
		assertEquals(ObjectMapping.class, pm.getMapping().getClass());
		assertEquals(Album.class, pm.getMapping().getMappingType());
		Map<String, MemberMapping<?>> memberMappings = pm.getMapping().getMemberMappings();

		// aid
		MemberMapping<?> aid = memberMappings.get("aid");
		assertEquals("aid", aid.getName());
		assertEquals("id", aid.getMappingName());
		assertEquals(int.class.getName(), aid.getMappingType());
		assertEquals(int.class, aid.getMapping().getMappingType());

		// pic_num
		MemberMapping<?> picNum = memberMappings.get("pic_num");
		assertEquals("pic_num", picNum.getName());
		assertEquals("picNum", picNum.getMappingName());
		assertEquals(Integer.class.getName(), picNum.getMappingType());
		assertEquals(Integer.class, picNum.getMapping().getMappingType());

		// ints
		MemberMapping<?> ints = memberMappings.get("ints_name");
		assertEquals("ints_name", ints.getName());
		assertEquals("ints", ints.getMappingName());
		assertEquals(int[].class.getName(), ints.getMappingType());
		assertEquals(int[].class, ints.getMapping().getMappingType());

		// intt
		MemberMapping<?> intt = memberMappings.get("intt_name");
		assertEquals("intt_name", intt.getName());
		assertEquals("intt", intt.getMappingName());
		assertEquals(int.class.getName(), intt.getMappingType());
		assertEquals(int.class, intt.getMapping().getMappingType());
		Switcher sw = intt.getSwitcher();
		assertEquals(0, sw.switchCase("c_seller"));
		assertEquals(1, sw.switchCase("b_seller"));
		assertEquals(2, sw.switchCase(0.2f));
		assertEquals(3, sw.switchCase(0.3d));
		assertEquals(-1, sw.switchCase("default seller"));

		// ---- pm 1
		pm = pms.get(1);
		assertEquals("aid", pm.getName());
		assertEquals(null, pm.getMappingName());
		assertEquals(int.class.getName(), pm.getMappingType());

		// ---- pm 3
		pm = pms.get(3);
		assertEquals(null, pm.getName());
		assertEquals(null, pm.getMappingName());
		assertEquals(null, pm.getMapping());
		// aid
		aid = memberMappings.get("aid");
		assertEquals("aid", aid.getName());
		assertEquals("id", aid.getMappingName());
		assertEquals(int.class.getName(), aid.getMappingType());
		assertEquals(int.class, aid.getMapping().getMappingType());

		// ================ response ====================
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		MemberMapping<Object> rspMapping = mrm.getParameterMapping();
		Map<String, MemberMapping<?>> rspMemMappings = rspMapping.getMapping().getMemberMappings();
		MemberMapping<?> itemMapping = rspMemMappings.get("item");
		assertEquals("album", itemMapping.getMappingName());
		Map<String, MemberMapping<?>> itemMemMappings = itemMapping.getMapping().getMemberMappings();
		MemberMapping<?> inttMapping = itemMemMappings.get("intt");
		Switcher inttSw = inttMapping.getSwitcher();
		assertEquals("c_seller", inttSw.switchCase(0));
		assertEquals("b_seller", inttSw.switchCase(1));
		assertEquals("d_seller", inttSw.switchCase(1L));
		assertEquals("e_seller", inttSw.switchCase(2L));
		assertEquals("other", inttSw.switchCase(99999));

	}

	/**
	 * Test album2 map mapping.
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	@Test
	public void testAlbum2MapMapping() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = this.getAlbum2MapMemberMapping();
		MemberMapping<?> itemMapping = rspMapping.getMapping().getMemberMappings().get("item");
		assertEquals("album", itemMapping.getMappingName());
		Map<String, MemberMapping<?>> itemMemMappings = itemMapping.getMapping().getMemberMappings();
		MemberMapping<?> inttMapping = itemMemMappings.get("intt");
		Switcher inttSw = inttMapping.getSwitcher();
		assertEquals("c_seller", inttSw.switchCase(0));
		assertEquals("b_seller", inttSw.switchCase(1));
		assertEquals("d_seller", inttSw.switchCase(1L));
		assertEquals("e_seller", inttSw.switchCase(2L));
		assertEquals("other", inttSw.switchCase(99999));
		MemberMapping<?> pictureInttMM = itemMemMappings.get("picture.intt");
		assertEquals("picture_intt", pictureInttMM.getMappingName());
		StringWriter sw = new StringWriter();
		Album source = getAlbum();
		Convert convert = new XMLConvert(sw);
		itemMapping.write(convert, source, false);
		assertEquals("<album>" +
				"<aid>123</aid>" +
				"<ints_name list=\"true\">" +
				"</ints_name>" +
				"<intt_name>c_seller</intt_name>" +
				"<title>007</title>" +
				"<pic_num>100</pic_num>" +
				"<picture_intt>2</picture_intt>" +
				"<strs_name list=\"true\">&lt;&gt;1&amp;&apos;&quot;2</strs_name>" +
				"<user_id>1234</user_id>" +
				"<valid>false</valid>" +
				"</album>", sw.toString());
	}
}
