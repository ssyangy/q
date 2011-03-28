/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

import q.domain.FavoriteFromType;
import q.domain.FavoriteStatus;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class FavoritePage extends Page implements Serializable {

	private static final long serialVersionUID = 5644575518928001717L;

	private Long id;

	private Long creatorId;
	
	private Long senderId;
	
	private List<Long> senderIds;

	private Long fromId;
	
	private List<Long> fromIds;

	private FavoriteStatus status;

	private FavoriteFromType fromType;


	/**
	 * @param fromIds
	 */
	public void setFromIds(List<Long> fromIds) {
		this.fromIds = fromIds;
	}

	public List<Long> getFromIds() {
		return fromIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public FavoriteStatus getStatus() {
		return status;
	}

	public void setStatus(FavoriteStatus status) {
		this.status = status;
	}

	public FavoriteFromType getFromType() {
		return fromType;
	}

	public void setFromType(FavoriteFromType fromType) {
		this.fromType = fromType;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public List<Long> getSenderIds() {
		return senderIds;
	}

	public void setSenderIds(List<Long> senderIds) {
		this.senderIds = senderIds;
	}

}
