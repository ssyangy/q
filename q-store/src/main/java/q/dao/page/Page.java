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

	private Integer startIndex;

	private Long startId;

	private Integer size;

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

	public Long getStartId() {
		return startId;
	}

	public void setStartId(Long startId) {
		this.startId = startId;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
