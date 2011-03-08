package q.serialize.album;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * 相册测试对象.
 * 
 * @author jing.jiaoj
 */
//@MappingName("album")
public class Album implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4926421898183710433L;

	/** 相册是否有效. */
	private boolean valid;

	/** 相册id. */
	private int id;

	/** 用户id. */
	private long userId;

	/** 相册名称. */
	private String name;

	/** 照片数量. */
	private Integer picNum;

	/** 创建时间. */
	private Date created;

	/** The ints. */
	private int[] ints;

	/** 空值测试专用. */
	private String nullStr;

	/** int. */
	private int intt;

	/** long list. */
	private List<Long> list_long;

	/** float list. */
	private List<Float> list_float;

	/** double list. */
	private List<Double> list_double;

	/** float 数组. */
	private float[] floats;

	/** The picture. */
	//不建议使用此
//	@MappingName("picture_intt")
//	@Name("picture.intt")
//	@MappingType(String.class)
	private Picture picture;
	
//	@MappingName("picture_intts")
//	@Name("picture.intt")
//	@MappingType(String.class)
	private Picture pictures;

	/** double 数组. */
	private double[] doubles;

	/** float convert to double. */
	private float f;

	/** The strs. */
	private String[] strs;

	/** List<List<Picture[]>>. */
	private List<List<Picture[]>> testPics;

	private Album2 album_object;
	
	/**
	 * Gets the intt.
	 * 
	 * @return the intt
	 */
	public int getIntt() {
		return intt;
	}

	public Album2 getAlbum_object() {
		return album_object;
	}

	public void setAlbum_object(Album2 albumObject) {
		album_object = albumObject;
	}

	/**
	 * Sets the intt.
	 * 
	 * @param intt
	 *            the new intt
	 */
	public void setIntt(int intt) {
		this.intt = intt;
	}

	/**
	 * Gets the null str.
	 * 
	 * @return the nullStr
	 */
	public String getNullStr() {
		return nullStr;
	}

	/**
	 * Sets the null str.
	 * 
	 * @param nullStr
	 *            the nullStr to set
	 */
	public void setNullStr(String nullStr) {
		this.nullStr = nullStr;
	}

	/**
	 * Gets the ints.
	 * 
	 * @return the ints
	 */
	public int[] getInts() {
		return ints;
	}

	/**
	 * Sets the ints.
	 * 
	 * @param ints
	 *            the ints to set
	 */
	public void setInts(int[] ints) {
		this.ints = ints;
	}

	/**
	 * Gets the strs.
	 * 
	 * @return the strs
	 */
	public String[] getStrs() {
		return strs;
	}

	/**
	 * Sets the strs.
	 * 
	 * @param strs
	 *            the strs to set
	 */
	public void setStrs(String[] strs) {
		this.strs = strs;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the user id.
	 * 
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the pic num.
	 * 
	 * @return the picNum
	 */
	public Integer getPicNum() {
		return picNum;
	}

	/**
	 * Sets the pic num.
	 * 
	 * @param picNum
	 *            the picNum to set
	 */
	public void setPicNum(Integer picNum) {
		this.picNum = picNum;
	}

	/**
	 * Gets the created.
	 * 
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 * 
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Checks if is valid.
	 * 
	 * @return the invalid
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Sets the valid.
	 * 
	 * @param invalid
	 *            the invalid to set
	 */
	public void setValid(boolean invalid) {
		this.valid = invalid;
	}

	/**
	 * Gets the picture.
	 * 
	 * @return the picture
	 */
	public Picture getPicture() {
		return picture;
	}

	/**
	 * Sets the picture.
	 * 
	 * @param picture
	 *            the new picture
	 */
	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	/**
	 * Gets the list_long.
	 * 
	 * @return the list_long
	 */
	public List<Long> getList_long() {
		return list_long;
	}

	/**
	 * Sets the list_long.
	 * 
	 * @param list_long
	 *            the new list_long
	 */
	public void setList_long(List<Long> list_long) {
		this.list_long = list_long;
	}

	/**
	 * Gets the list_float.
	 * 
	 * @return the list_float
	 */
	public List<Float> getList_float() {
		return list_float;
	}

	/**
	 * Sets the list_float.
	 * 
	 * @param list_float
	 *            the new list_float
	 */
	public void setList_float(List<Float> list_float) {
		this.list_float = list_float;
	}

	/**
	 * Gets the list_double.
	 * 
	 * @return the list_double
	 */
	public List<Double> getList_double() {
		return list_double;
	}

	/**
	 * Sets the list_double.
	 * 
	 * @param list_double
	 *            the new list_double
	 */
	public void setList_double(List<Double> list_double) {
		this.list_double = list_double;
	}

	/**
	 * Gets the floats.
	 * 
	 * @return the floats
	 */
	public float[] getFloats() {
		return floats;
	}

	/**
	 * Sets the floats.
	 * 
	 * @param floats
	 *            the new floats
	 */
	public void setFloats(float[] floats) {
		this.floats = floats;
	}

	/**
	 * Gets the doubles.
	 * 
	 * @return the doubles
	 */
	public double[] getDoubles() {
		return doubles;
	}

	/**
	 * Sets the doubles.
	 * 
	 * @param doubles
	 *            the new doubles
	 */
	public void setDoubles(double[] doubles) {
		this.doubles = doubles;
	}

	/**
	 * Gets the f.
	 * 
	 * @return the f
	 */
	public float getF() {
		return f;
	}

	/**
	 * Sets the f.
	 * 
	 * @param f
	 *            the new f
	 */
	public void setF(float f) {
		this.f = f;
	}

	/**
	 * Gets the test pics.
	 * 
	 * @return the test pics
	 */
	public List<List<Picture[]>> getTestPics() {
		return testPics;
	}

	/**
	 * Sets the test pics.
	 * 
	 * @param testPics
	 *            the new test pics
	 */
	public void setTestPics(List<List<Picture[]>> testPics) {
		this.testPics = testPics;
	}

	public Picture getPictures() {
		return pictures;
	}

	public void setPictures(Picture pictures) {
		this.pictures = pictures;
	}
}
