/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 986692036209440550L;

	private long id;

	private long senderId;

	private String name;

	private String intro;

	private String status;

	private Date created;

	private Date modified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
