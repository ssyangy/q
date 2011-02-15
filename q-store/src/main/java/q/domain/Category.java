package q.domain;
import java.util.Date;
import java.io.Serializable;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public class Category implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1611557472302730738L;
	private long id;
	private String name;
	private String intro;	
	private String status;
	private Date created;
	private Date modified;
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name+ ", intro=" + intro+ ", status=" + status+ ", created=" + created+ ", modified=" + modified+"]";
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
