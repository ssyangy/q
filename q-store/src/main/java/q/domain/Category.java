package q.domain;

import java.io.Serializable;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public class Category extends AbstractDomain implements Serializable {
	private static final long serialVersionUID = 1611557472302730738L;

	private String name;

	private String intro;

	private String status;

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

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", " + "intro=" + intro + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}
}
