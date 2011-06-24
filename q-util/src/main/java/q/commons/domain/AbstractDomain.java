/**
 * 
 */
package q.commons.domain;

import java.util.Date;

import q.util.DateKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 26, 2011
 * 
 */
public abstract class AbstractDomain {
	protected long id;

	protected Date created;

	protected Date modified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	/**
	 * TODO bad performance
	 * @return
	 */
	public String getTime(){
		return DateKit.date2SinaStyle(created, new Date());
	}
	
	public String getCreatedTime(){
		return DateKit.date2Ymdhms(created);
	}
}
