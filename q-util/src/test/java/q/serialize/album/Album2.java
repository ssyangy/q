package q.serialize.album;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * 相册测试对象.
 * 
 * @author jing.jiaoj
 */
//@ExplicitMapping
public class Album2 implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5410697703064612065L;

	/** 相册id. */
	private String cid;
	
	private String year;
	
//	public String getTitle() {
//		return title;z
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	private String title;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}


}
