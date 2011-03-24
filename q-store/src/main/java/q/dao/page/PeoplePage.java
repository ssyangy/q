/**
 * 
 */
package q.dao.page;

import java.util.List;

import q.domain.Area;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 7, 2011
 * 
 */
public class PeoplePage extends Page {

	private static final long serialVersionUID = 248251005962049033L;

	private Long id;

	private String username;

	private String password;

	private String email;

	private Area area;

	private List<Long> ids;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Area getArea() {
		return area;
	}

	/**
	 * @param area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * @param ids
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
		
	}

	public List<Long> getIds() {
		return ids;
	}

}
