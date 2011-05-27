/**
 * 
 */
package q.serialize.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;

import q.serialize.album.Album;
import q.serialize.album.Album2;
import q.serialize.album.AlbumResult;
import q.serialize.album.AlbumSet;
import q.serialize.album.Picture;
import q.serialize.config.MappingConfigReader;
import q.serialize.mapping.ArrayMapping;
import q.serialize.mapping.DoubleMapping;
import q.serialize.mapping.FloatMapping;
import q.serialize.mapping.IntMapping;
import q.serialize.mapping.IntegerMapping;
import q.serialize.mapping.ListMapping;
import q.serialize.mapping.LongMapping;
import q.serialize.mapping.MapMapping;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.ObjectMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.PrimitiveDoubleMapping;
import q.serialize.mapping.PrimitiveFloatMapping;
import q.serialize.mapping.PrimitiveLongMapping;
import q.serialize.mapping.StringMapping;


// TODO: Auto-generated Javadoc
/**
 * The Class MappingTestBase.
 * 
 * @version 2009-2-26
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MappingTestBase {

	/** The dcr. */
	private MappingConfigReader dcr = new MappingConfigReader();

	/**
	 * Gets the file is.
	 * 
	 * @param filename
	 *            the filename
	 * 
	 * @return the file is
	 * 
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	protected FileInputStream getFileIS(String filename)
			throws FileNotFoundException {
		return new FileInputStream(
				"src/test/resources/q/serialize/album/" + filename);
	}

	/**
	 * Gets the gets the albums method mapping.
	 * 
	 * @return the gets the albums method mapping
	 */
	protected MethodMapping<Object> getGetAlbumsMethodMapping() {
		return getMethodMapping("api-mapping-Album-getAlbums.xml");
	}

	/**
	 * Gets the gets the album method mapping.
	 * 
	 * @return the gets the album method mapping
	 */
	protected MethodMapping<Object> getGetAlbumMethodMapping() {
		return getMethodMapping("api-mapping-Album-getAlbum.xml");
	}

	protected MethodMapping<Object> getGetAlbumMethodMappingWithChineseCommnets() {
		return getMethodMapping("api-mapping-Album-getAlbum_ChineseComments.xml");
	}

	/**
	 * Gets the gets the album method mapping.
	 * 
	 * @return the gets the album method mapping
	 */
	protected MethodMapping<Object> getMappingFile() {
		return getMethodMapping("mapping.xml");
	}

	/**
	 * Gets the album valid method mapping.
	 * 
	 * @return the album valid method mapping
	 */
	protected MethodMapping<Object> getAlbumValidMethodMapping() {
		return getMethodMapping("api-mapping-Album-valid.xml");
	}

	protected MethodMapping<Object> getEmptyMethodMapping() {
		return getMethodMapping("api-mapping-empty.xml");
	}

	/**
	 * Gets the adds the picture method mapping.
	 * 
	 * @return the adds the picture method mapping
	 */
	protected MethodMapping<Object> getAddPictureMethodMapping() {
		return getMethodMapping("api-mapping-Album-addPicture.xml");
	}

	/**
	 * Gets the adds the album method mapping.
	 * 
	 * @return the adds the album method mapping
	 */
	protected MethodMapping<Object> getAddAlbumMethodMapping() {
		return getMethodMapping("api-mapping-Album-addAlbum.xml");
	}

	/**
	 * Gets the album2 map member mapping.
	 * 
	 * @return the album2 map member mapping
	 */
	protected MemberMapping<Object> getAlbum2MapMemberMapping() {
		return getMemberMapping("mapping-album2map.xml");
	}

	/**
	 * Gets the method mapping.
	 * 
	 * @param configFile
	 *            the config file
	 * 
	 * @return the method mapping
	 */
	protected MethodMapping<Object> getMethodMapping(String configFile) {
		MethodMapping<Object> mm = null;
		try {
			mm = dcr.read(getFileIS(configFile));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return mm;
	}

	/**
	 * Gets the member mapping.
	 * 
	 * @param configFile
	 *            the config file
	 * 
	 * @return the member mapping
	 */
	protected MemberMapping<Object> getMemberMapping(String configFile) {
		MemberMapping<Object> mm = null;
		try {
			mm = dcr.readMemberMapping(getFileIS(configFile));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return mm;
	}

	/** The sm. */
	protected StringMapping sm = new StringMapping();

	/** The im. */
	protected IntegerMapping im = new IntegerMapping();

	/** The lm. */
	private LongMapping lm = new LongMapping();

	/** The pim. */
	private IntMapping pim = new IntMapping();

	/** The fm. */
	private FloatMapping fm = new FloatMapping();

	/** The dm. */
	private DoubleMapping dm = new DoubleMapping();

	/** The pfm. */
	private PrimitiveFloatMapping pfm = new PrimitiveFloatMapping();

	/** The pdm. */
	private PrimitiveDoubleMapping pdm = new PrimitiveDoubleMapping();

	/** The plm. */
	private PrimitiveLongMapping plm = new PrimitiveLongMapping();

	/** The slm. */
	private ListMapping<String> slm = new ListMapping<String>(sm);

	/** The ilm. */
	private ListMapping<Integer> ilm = new ListMapping<Integer>(im);

	/** The llm. */
	private ListMapping<Long> llm = new ListMapping<Long>(lm);

	/** The flm. */
	private ListMapping<Float> flm = new ListMapping<Float>(fm);

	/** The dlm. */
	private ListMapping<Double> dlm = new ListMapping<Double>(dm);

	/** The llim. */
	private ListMapping<List<Integer>> llim = new ListMapping<List<Integer>>(
			ilm);

	/** The sam. */
	private ArrayMapping<String[], String> sam = new ArrayMapping<String[], String>(
			sm);

	/** The iam. */
	private ArrayMapping<int[], Integer> iam = new ArrayMapping<int[], Integer>(
			pim);

	/** The fam. */
	private ArrayMapping<float[], Float> fam = new ArrayMapping<float[], Float>(
			pfm);

	/** The dam. */
	private ArrayMapping<double[], Double> dam = new ArrayMapping<double[], Double>(
			pdm);

	/** The lam. */
	private ArrayMapping<long[], Long> lam = new ArrayMapping<long[], Long>(plm);

	/**
	 * Gets the album.
	 * 
	 * @return the album
	 */
	protected <E> Album getAlbum() {
		Album album = new Album();
		album.setId(123);
		album.setName("007");
		album.setCreated(new Date());
		album.setPicNum(100);
		album.setUserId(1234L);
		album.setStrs(new String[] { "<>1&'\"", "2" });
		album.setInts(new int[] { 1, 2 });
		List<Long> long_list = new LinkedList<Long>();
		long_list.add(100L);
		long_list.add(200L);
		album.setList_long(long_list);
		List<Float> float_list = new LinkedList<Float>();
		float_list.add(2.5F);
		float_list.add(1F);
		album.setList_float(float_list);
		List<Double> double_list = new LinkedList<Double>();
		double_list.add(1.0D);
		double_list.add(3.1415926575859133456D);
		album.setList_double(double_list);
		album.setFloats(new float[] { 0.1f, 0.2f });
		album.setDoubles(new double[] { 1.0, 2.123456789 });
		album.setF(0.123456789f);
		Picture p = new Picture();
		p.setIntt(2);
		p.setPic_name("jyyPicName");
		Picture[] ps = new Picture[] { p };
		album.setPicture(p);
		album.setPictures(p);
		List<Picture[]> list_ps = new LinkedList<Picture[]>();
		list_ps.add(ps);
		List<List<Picture[]>> list_list_ps = new LinkedList<List<Picture[]>>();
		list_list_ps.add(list_ps);
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("a", 1);
		// map.put("b", "hello");
		// map.put("p", ps);
		album.setTestPics(list_list_ps);
		Album2 album2 = new Album2();
		album2.setCid("1234");
		album2.setYear("1998");
		album.setAlbum_object(album2);
		return album;
	}

	/**
	 * Gets the success album result.
	 * 
	 * @return the success album result
	 */
	protected AlbumResult getSuccessAlbumResult() {
		AlbumResult ar = new AlbumResult();
		ar.setSuccess(true);
		ar.setItem(getAlbum());
		return ar;
	}

	/**
	 * Gets the fail album result.
	 * 
	 * @return the fail album result
	 */
	protected AlbumResult getFailAlbumResult() {
		AlbumResult ar = new AlbumResult();
		ar.setCode("400");
		ar.setMsg("error detail");
		ar.setSuccess(false);
		ar.setItem(getAlbum());
		return ar;
	}

	/**
	 * Gets the fail album result exception.
	 * 
	 * @return the fail album result exception
	 */
	protected OperationCodeException getFailAlbumResultException() {
		return new OperationCodeException("400", "error detail");
	}

	/**
	 * Gets the output album map.
	 * 
	 * @return the output album map
	 */
	protected Map<String, Object> getOutputAlbumMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "007");
		map.put("pic_num", new Integer(100));
		map.put("user_id", new Long(1234));
		map.put("strs_name", Arrays.asList(new String[] { "<>1&'\"", "2" }));
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		list2.add(4);
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		lists.add(list);
		lists.add(list2);
		map.put("ints_name", lists);
		map.put("floats_name", Arrays.asList(new Float[] { 0.1f, 0.2f }));
		map.put("doubles_name",
				Arrays.asList(new Double[] { 1.0, 2.123456789 }));
		List<Long> long_list = new LinkedList<Long>();
		long_list.add(100L);
		long_list.add(200L);
		List<Float> float_list = new LinkedList<Float>();
		float_list.add(2.5F);
		float_list.add(1F);
		List<Double> double_list = new LinkedList<Double>();
		double_list.add(1.0D);
		double_list.add(3.1415926575859133456D);
		map.put("long_list", long_list);
		map.put("float_list", float_list);
		map.put("double_list", double_list);
		map.put("f_name", new Double(0.123456789));
		Picture p = new Picture();
		p.setIntt(2);
		Picture[] ps = new Picture[] { p };
		map.put("picture", p);
		List<Picture[]> list_ps = new LinkedList<Picture[]>();
		list_ps.add(ps);
		List<List<Picture[]>> list_list_ps = new LinkedList<List<Picture[]>>();
		list_list_ps.add(list_ps);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("a", 1);
		m.put("b", "hello");
		m.put("p", ps);
		map.put("testPics", list_list_ps);
		map.put("map", m);
		return map;
	}

	/**
	 * Gets the input album map.
	 * 
	 * @return the input album map
	 */
	protected Map<String, Object> getInputAlbumMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "007");
		map.put("pic_num", "100");
		map.put("aid", "123");
		map.put("user_id", "1234");
		map.put("strs_name", "<>1&'\",2");
		map.put("ints_name", "1,2");
		map.put("floats_name", "0.1,0.2");
		map.put("doubles_name", "1.0, 2.123456789");
		map.put("long_list", "100,200");
		map.put("float_list", "2.5,1");
		map.put("double_list", "1.0,3.1415926575859133456");
		map.put("f_name", "0.123456789");
		map.put("picture_intt", "1");
		map.put("album_object.cid", "1234");
		return map;
	}

	/**
	 * Gets the album query result.
	 * 
	 * @return the album query result
	 */
	protected AlbumSet getAlbumQueryResult() {
		List<Album> albums = new ArrayList<Album>();
		Album album = getAlbum();
		albums.add(album);
		albums.add(album);
		AlbumSet aqr = new AlbumSet();
		aqr.setCount(100);
		aqr.setPages(albums);
		return aqr;
	}

	/**
	 * Gets the output album query result map.
	 * 
	 * @return the output album query result map
	 */
	protected Map<String, Object> getOutputAlbumQueryResultMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalResults", 100);
		List<Map<String, Object>> albums = new ArrayList<Map<String, Object>>();
		Map<String, Object> album = getInputAlbumMap();
		albums.add(album);
		albums.add(album);
		map.put("albums", albums);
		return map;
	}

	/**
	 * Gets the input album query result map.
	 * 
	 * @return the input album query result map
	 */
	protected Map<String, Object> getInputAlbumQueryResultMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalResults", "100");
		List<Map<String, Object>> albums = new ArrayList<Map<String, Object>>();
		Map<String, Object> album = getInputAlbumMap();
		albums.add(album);
		albums.add(album);
		map.put("albums", albums);
		return map;
	}

	/**
	 * Gets the album mapping.
	 * 
	 * @param mappingName
	 *            the mapping name
	 * 
	 * @return the album mapping
	 */
	protected MapMapping getAlbumMapping(String mappingName) {
		MapMapping mm = new MapMapping(mappingName);
		mm.addMemberMapping(new MemberMapping<String>("name", "title", sm));
		mm.addMemberMapping(new MemberMapping<Integer>("picNum", "pic_num", im));
		mm.addMemberMapping(new MemberMapping<Integer>("id", "aid", pim));
		mm.addMemberMapping(new MemberMapping<Long>("userId", "user_id", lm));
		mm.addMemberMapping(new MemberMapping<List<String>>("strs",
				"strs_name", slm));
		mm.addMemberMapping(new MemberMapping<List<List<Integer>>>("ints",
				"ints_name", llim));
		mm.addMemberMapping(new MemberMapping<String>("nullStr", "null_str", sm));
		mm.addMemberMapping(new MemberMapping<List<Float>>("floats",
				"floats_name", flm));
		mm.addMemberMapping(new MemberMapping<List<Double>>("doubles",
				"doubles_name", dlm));
		mm.addMemberMapping(new MemberMapping<long[]>("list_long", "long_list",
				lam));
		mm.addMemberMapping(new MemberMapping<List<Float>>("list_float",
				"float_list", flm));
		mm.addMemberMapping(new MemberMapping<List<Double>>("list_double",
				"double_list", dlm));
		mm.addMemberMapping(new MemberMapping<Double>("f", "f_name", dm));
		return mm;
	}

	/**
	 * Gets the 2 album mapping.
	 * 
	 * @return the 2 album mapping
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	protected ObjectMapping<Album> get2AlbumMapping() throws SecurityException,
			NoSuchMethodException {
		ObjectMapping<Album> om = new ObjectMapping<Album>(Album.class);
		om.addMemberMapping(new MemberMapping<String>("title", "name", sm));
		om.addMemberMapping(new MemberMapping<Integer>("pic_num", "picNum", im));
		om.addMemberMapping(new MemberMapping<Integer>("aid", "id", pim));
		om.addMemberMapping(new MemberMapping<Long>("user_id", "userId", plm));
		om.addMemberMapping(new MemberMapping<String[]>("strs_name", "strs",
				sam));
		om.addMemberMapping(new MemberMapping<int[]>("ints_name", "ints", iam));
		om.addMemberMapping(new MemberMapping<String>("null_str", "nullStr", sm));
		om.addMemberMapping(new MemberMapping<float[]>("floats_name", "floats",
				fam));
		om.addMemberMapping(new MemberMapping<double[]>("doubles_name",
				"doubles", dam));
		om.addMemberMapping(new MemberMapping<List<Long>>("long_list",
				"list_long", llm));
		om.addMemberMapping(new MemberMapping<List<Float>>("float_list",
				"list_float", flm));
		om.addMemberMapping(new MemberMapping<List<Double>>("double_list",
				"list_double", dlm));
		om.addMemberMapping(new MemberMapping<Float>("f_name", "f", pfm));

		// 设置内部MemberMapping是对象
		ObjectMapping<Album2> get2Album2Mapping = get2Album2Mapping();

		// //设置前缀，自动会读取mappingName，由于这里是手工写,无法获得
		// get2Album2Mapping.setMappingPreFix("album_object");

		// 设置一些必须数据
		MemberMapping<Album2> mem = new MemberMapping<Album2>("album_object",
				get2Album2Mapping);
		mem.setName("album_object");
		mem.setMappingName("album_object");

		om.addMemberMapping(mem);
		return om;
	}

	/**
	 * get album2 mapping
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	protected ObjectMapping<Album2> get2Album2Mapping()
			throws SecurityException, NoSuchMethodException {
		ObjectMapping<Album2> om = new ObjectMapping<Album2>(Album2.class);
		om.addMemberMapping(new MemberMapping<String>("cid", "cid", sm));
		return om;
	}

	/**
	 * Gets the album query result mapping.
	 * 
	 * @return the album query result mapping
	 */
	protected MapMapping getAlbumQueryResultMapping() {
		MapMapping mm = new MapMapping();
		ListMapping<Map<Object, Object>> listm = new ListMapping<Map<Object, Object>>(
				getAlbumMapping("album"));
		mm.addMemberMapping(new MemberMapping<Integer>("count", "totalResults",
				im));
		mm.addMemberMapping(new MemberMapping<List<Map<Object, Object>>>(
				"pages", "albums", listm));
		return mm;
	}

	/**
	 * Gets the 2 album query result mapping.
	 * 
	 * @return the 2 album query result mapping
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	protected ObjectMapping<AlbumSet> get2AlbumQueryResultMapping()
			throws SecurityException, NoSuchMethodException {
		ObjectMapping<AlbumSet> aqm = new ObjectMapping<AlbumSet>(
				AlbumSet.class);
		ListMapping<Album> listm = new ListMapping<Album>(get2AlbumMapping());
		aqm.addMemberMapping(new MemberMapping<Integer>("totalResults",
				"count", pim));
		aqm.addMemberMapping(new MemberMapping<List<Album>>("albums", "pages",
				listm));
		return aqm;
	}

	/**
	 * Check album2 map.
	 * 
	 * @param actual
	 *            the actual
	 */
	protected void checkAlbum2Map(Map<Object, Object> actual) {
		Map<String, Object> expect = this.getOutputAlbumMap();
		long[] longs = (long[]) actual.get("long_list");
		List<Long> long_list = this.getList(longs);
		// class修改为1
		// assertEquals(expect.get("title"), actual.get("title"));
		assertEquals(expect.get("pic_num"), actual.get("pic_num"));
		assertEquals(expect.get("user_id"), actual.get("user_id"));
		// assertArrayEquals(
		// ((List<String>)
		// expect.get("strs_name")).toArray(ArrayUtils.EMPTY_STRING_ARRAY),
		// (String[])actual.get("strs_name"));

		// TODO List与[]数组比较
		// int[][] obj = (int[][]) actual.get("ints_name");
		// if (obj.getClass().isArray()) {
		// Object args = ((List) expect.get("ints_name")).toArray();
		// assertEquals(args, obj);
		// // this.checkInts((List) expect.get("ints_name"), (List[]) obj);
		// } else {
		// this.checkList((List) expect.get("ints_name"), (List) actual
		// .get("ints_name"));
		// }
		// assertArrayEquals(((List<Float>) expect.get("floats_name"))
		// .toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY),
		// ((List<Float>) actual.get("floats_name"))
		// .toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY));
		// assertArrayEquals(((List<Double>) expect.get("doubles_name"))
		// .toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY),
		// ((List<Double>) actual.get("doubles_name"))
		// .toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY));
		// assertArrayEquals(((List<Long>) expect.get("long_list"))
		// .toArray(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY), long_list
		// .toArray(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY));
		// assertArrayEquals(((List<Float>) expect.get("float_list"))
		// .toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY),
		// ((List<Float>) actual.get("float_list"))
		// .toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY));
		// assertArrayEquals(((List<Double>) expect.get("double_list"))
		// .toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY),
		// ((List<Double>) actual.get("double_list"))
		// .toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY));
		// assertEquals((Double) expect.get("f_name"), (Double) actual
		// .get("f_name"), 0.0000001F);
	}

	/**
	 * Check album2 map in class.
	 * 
	 * @param actual
	 *            the actual
	 */
	protected void checkAlbum2MapInClass(Map<String, ?> actual) {
		Map<String, Object> expect = this.getOutputAlbumMap();
		// long[] longs = (long[]) actual.get("list_long");
		List<Long> long_list = (List<Long>) actual.get("list_long");
		assertEquals(expect.get("title"), actual.get("name"));
		assertEquals(expect.get("pic_num"), actual.get("picNum"));
		assertEquals(expect.get("user_id"), actual.get("userId"));
		assertArrayEquals(
				((List<String>) expect.get("strs_name"))
						.toArray(ArrayUtils.EMPTY_STRING_ARRAY),
				(String[]) actual.get("strs"));
		Object obj = actual.get("ints");
		Object o = expect.get("ints_name");
		if (obj.getClass().isArray()) {
			this.checkInts((List) o, (int[][]) obj);
		} else {
			this.checkList((List) expect.get("ints_name"), (List) obj);
		}
		assertArrayEqualsInClass(
				((List<Float>) expect.get("floats_name"))
						.toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY),
				((float[]) actual.get("floats")));
		assertArrayEqualsInClass(
				((List<Double>) expect.get("doubles_name"))
						.toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY),
				((double[]) actual.get("doubles")));
		assertArrayEquals(
				((List<Long>) expect.get("long_list"))
						.toArray(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY),
				long_list.toArray(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY));
		assertArrayEquals(
				((List<Float>) expect.get("float_list"))
						.toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY),
				((List<Float>) actual.get("list_float"))
						.toArray(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY));
		assertArrayEquals(
				((List<Double>) expect.get("double_list"))
						.toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY),
				((List<Double>) actual.get("list_double"))
						.toArray(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY));
		assertEquals((Double) expect.get("f_name"),
				((Float) actual.get("f")).doubleValue(), 0.0000001F);
	}

	/**
	 * Assert array equals in class.
	 * 
	 * @param array
	 *            the array
	 * @param fs
	 *            the fs
	 */
	private void assertArrayEqualsInClass(Float[] array, float[] fs) {
		assertEquals(array.length, fs.length);
		if (array.length == fs.length) {
			for (int i = 0; i < fs.length; i++) {
				assertEquals(array[i], (Float) fs[i]);
			}
		}
	}

	/**
	 * Assert array equals in class.
	 * 
	 * @param array
	 *            the array
	 * @param fs
	 *            the fs
	 */
	private void assertArrayEqualsInClass(Double[] array, double[] fs) {
		assertEquals(array.length, fs.length);
		if (array.length == fs.length) {
			for (int i = 0; i < fs.length; i++) {
				assertEquals(array[i], (Double) fs[i]);
			}
		}
	}

	/**
	 * Gets the list.
	 * 
	 * @param longs
	 *            the longs
	 * 
	 * @return the list
	 */
	private List<Long> getList(long[] longs) {
		List<Long> list = new ArrayList<Long>();
		for (long t : longs) {
			list.add(t);
		}
		return list;
	}

	/**
	 * Check ints.
	 * 
	 * @param list1
	 *            the list1
	 * @param list2
	 *            the list2
	 */
	private void checkInts(List list1, int[][] list2) {
		assertEquals(list1.size(), list2.length);
		if (list1.size() == list2.length) {
			for (int i = 0; i < list1.size(); i++) {
				List<Integer> o = (List<Integer>) list1.get(i);
				int[] o2 = list2[i];
				assertEquals(o.size(), o2.length);
				for (int j = 0; j < o2.length; j++) {
					assertEquals(o.get(j), (Integer) o2[j]);
				}
			}
		}
	}

	/**
	 * Check ints.
	 * 
	 * @param list1
	 *            the list1
	 * @param list2
	 *            the list2
	 */
	private void checkInts(List list1, List[] list2) {
		assertEquals(list1.size(), list2.length);
		if (list1.size() == list2.length) {
			for (int i = 0; i < list1.size(); i++) {
				Object o = list1.get(i);
				Object o2 = list2[i];
				assertArrayEquals(
						((List<Integer>) list1.get(i))
								.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY),
						((List) list2[i])
								.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY));
			}
		}
	}

	/**
	 * Check list.
	 * 
	 * @param lists1
	 *            the lists1
	 * @param lists2
	 *            the lists2
	 */
	private void checkList(List lists1, List lists2) {
		Iterator iter1 = lists1.iterator(), iter2 = lists2.iterator();
		int num1 = 0, num2 = 0;
		for (; iter1.hasNext() || iter2.hasNext();) {
			List<Integer> list1 = null;
			List<Integer> list2 = null;
			if (iter1.hasNext()) {
				num1++;
				list1 = (List<Integer>) iter1.next();
			}
			if (iter2.hasNext()) {
				num2++;
				list2 = (List<Integer>) iter2.next();
			}
			assertEquals(num1, num2);
			assertArrayEquals(
					((List<Integer>) list1)
							.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY),
					((List<Integer>) list2)
							.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY));
		}
	}

	/**
	 * Check map2 album.
	 * 
	 * @param actual
	 *            the actual
	 */
	protected void checkMap2Album(Album actual) {
		Album expect = this.getAlbum();
		assertFalse(actual == expect);
		assertEquals(expect.getName(), actual.getName());
		assertEquals(expect.getPicNum(), actual.getPicNum());
		assertEquals(expect.getUserId(), actual.getUserId());
		assertEquals(expect.getId(), actual.getId());
		assertArrayEquals(expect.getStrs(), actual.getStrs());
		assertArrayEquals(expect.getInts(), actual.getInts());
		assertArrayEquals3(expect.getFloats(), actual.getFloats());
		assertArrayEquals3(expect.getDoubles(), actual.getDoubles());
		assertArrayEquals2F(expect.getList_float(), actual.getList_float());
		assertArrayEquals2(expect.getList_long(), actual.getList_long());
		assertArrayEquals2D(expect.getList_double(), actual.getList_double());
		assertEquals((Float) expect.getF(), (Float) actual.getF(), 0.00001F);
		assertEquals(expect.getAlbum_object().getCid(), actual
				.getAlbum_object().getCid());
	}

	/**
	 * Assert array equals2.
	 * 
	 * @param list_float
	 *            the list_float
	 * @param list_float2
	 *            the list_float2
	 */
	private <T> void assertArrayEquals2(List<T> list_float, List<T> list_float2) {
		Iterator<T> iter1 = list_float.iterator();
		Iterator<T> iter2 = list_float2.iterator();
		assertEquals(list_float.size(), list_float2.size());
		if (list_float.size() == list_float2.size()) {
			while (iter1.hasNext() && iter2.hasNext()) {
				T t1 = iter1.next();
				T t2 = iter2.next();
				assertEquals(t1, t2);
			}
		}
	}

	/**
	 * Assert array equals2 f.
	 * 
	 * @param list_float
	 *            the list_float
	 * @param list_float2
	 *            the list_float2
	 */
	private void assertArrayEquals2F(List<Float> list_float,
			List<Float> list_float2) {
		Iterator<Float> iter1 = list_float.iterator();
		Iterator<Float> iter2 = list_float2.iterator();
		assertEquals(list_float.size(), list_float2.size());
		if (list_float.size() == list_float2.size()) {
			while (iter1.hasNext() && iter2.hasNext()) {
				Float t1 = iter1.next();
				Float t2 = iter2.next();
				assertEquals(t1, t2, 0.0001F);
			}
		}
	}

	/**
	 * Assert array equals2 d.
	 * 
	 * @param list_float
	 *            the list_float
	 * @param list_float2
	 *            the list_float2
	 */
	private void assertArrayEquals2D(List<Double> list_float,
			List<Double> list_float2) {
		Iterator<Double> iter1 = list_float.iterator();
		Iterator<Double> iter2 = list_float2.iterator();
		assertEquals(list_float.size(), list_float2.size());
		if (list_float.size() == list_float2.size()) {
			while (iter1.hasNext() && iter2.hasNext()) {
				Double t1 = iter1.next();
				Double t2 = iter2.next();
				assertEquals(t1, t2, 0.0001F);
			}
		}
	}

	/**
	 * Assert array equals3.
	 * 
	 * @param fs
	 *            the fs
	 * @param fs2
	 *            the fs2
	 */
	private void assertArrayEquals3(float[] fs, float[] fs2) {
		assertEquals(fs.length, fs2.length);
		if (fs.length == fs2.length) {
			int length = fs.length;
			for (int i = 0; i < length; i++) {
				assertEquals(fs[i], fs2[i], 0.0001F);
			}
		}
	}

	/**
	 * Assert array equals3.
	 * 
	 * @param fs
	 *            the fs
	 * @param fs2
	 *            the fs2
	 */
	private void assertArrayEquals3(double[] fs, double[] fs2) {
		assertEquals(fs.length, fs2.length);
		if (fs.length == fs2.length) {
			int length = fs.length;
			for (int i = 0; i < length; i++) {
				assertEquals(fs[i], fs2[i], 0.0001F);
			}
		}
	}

	/**
	 * Gets the list.
	 * 
	 * @param ts
	 *            the ts
	 * 
	 * @return the list
	 */
	private <T> List<T> getList(T[] ts) {
		List<T> list = new ArrayList<T>();
		for (T t : ts) {
			list.add(t);
		}
		return list;
	}

	/**
	 * Check map2 album query result.
	 * 
	 * @param actual
	 *            the actual
	 */
	protected void checkMap2AlbumQueryResult(AlbumSet actual) {
		AlbumSet expect = this.getAlbumQueryResult();
		assertFalse(actual == expect);
		assertEquals(expect.getCount(), actual.getCount());
		assertEquals(expect.getPages().size(), actual.getPages().size());
		for (Album item : actual.getPages()) {
			checkMap2Album(item);
		}
	}

	/**
	 * Check with array item name.
	 * 
	 * @param mapping
	 *            the mapping
	 */
	protected void checkWithArrayItemName(Map<Object, Object> mapping) {
		AlbumSet aqr = getAlbumQueryResult();
		assertEquals(aqr.getCount(),
				((Integer) mapping.get("totalResults")).intValue());
		for (Map<String, ?> item : (List<Map<String, ?>>) mapping.get("albums")) {
			Map album = (Map) item;
			checkAlbum2Map(album);
		}
	}

	/**
	 * Check album query result2 map.
	 * 
	 * @param actual
	 *            the actual
	 */
	protected void checkAlbumQueryResult2Map(Map<Object, Object> actual) {
		Map<String, Object> expect = this.getOutputAlbumQueryResultMap();
		assertEquals(expect.get("totalResults"), actual.get("totalResults"));
		for (Map<Object, Object> item : (List<Map<Object, Object>>) actual
				.get("albums")) {
			checkAlbum2Map(item);
		}
	}
}
