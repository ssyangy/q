/**
 * 
 */
package q.dao.page;

import java.util.List;

/**
 * @author seanlinwang at gmail dot com
 * @date May 16, 2011
 * 
 */
public class GroupJoinCategoryPage extends Page {

	private static final long serialVersionUID = 8186458581959280224L;

	private List<Long> categoryIds;

	/**
	 * @param categoryIds
	 *            the catIds to set
	 */
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	/**
	 * @return the catIds
	 */
	public List<Long> getCategoryIds() {
		return categoryIds;
	}

}
