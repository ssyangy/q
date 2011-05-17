/**
 * 
 */
package q.dao.page;

/**
 * @author seanlinwang at gmail dot com
 * @date May 16, 2011
 * 
 */
public class GroupRecommendPage extends Page {

	private static final long serialVersionUID = 7544344452448879536L;

	private Long peopleId;

	/**
	 * @param peopleId
	 *            the peopleId to set
	 */
	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	/**
	 * @return the peopleId
	 */
	public Long getPeopleId() {
		return peopleId;
	}

}
