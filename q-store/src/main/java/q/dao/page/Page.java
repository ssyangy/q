/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class Page implements Serializable{
	private static final long serialVersionUID = -4271255854510793304L;

	private Date startCreated;

	private Integer startIndex = 0;

	private Integer startId;

	private Integer size = 20;

	public Date getStartCreated() {
		return startCreated;
	}

	public void setStartCreated(Date startCreated) {
		this.startCreated = startCreated;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getStartId() {
		return startId;
	}

	public void setStartId(Integer startId) {
		this.startId = startId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
