/**
 * 
 */
package q.dao.page;

/**
 * @author seanlinwang at gmail dot com
 * @date May 27, 2011
 * 
 */
public class WeiboJoinGroupPage extends Page {

	private static final long serialVersionUID = 2575101320215950768L;

	private Long weiboId;

	private Integer newStatus;

	private Integer oldStatus;

	public Long getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(Long weiboId) {
		this.weiboId = weiboId;
	}

	public Integer getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	public Integer getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}

}
