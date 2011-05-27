/**
 * 
 */
package q.serialize.album;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Picture.
 * 
 * @author xalinx at gmail dot com
 * @date Jul 22, 2009
 */
public class Picture implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5073607470957541309L;
	
	/** The intt. */
	private int intt;
	
	public String getPic_name() {
		return pic_name;
	}

	public void setPic_name(String picName) {
		pic_name = picName;
	}

	private String pic_name;

	/**
	 * Gets the intt.
	 * 
	 * @return the intt
	 */
	public int getIntt() {
		return intt;
	}

	/**
	 * Sets the intt.
	 * 
	 * @param intt the new intt
	 */
	public void setIntt(int intt) {
		this.intt = intt;
	}
	
}
