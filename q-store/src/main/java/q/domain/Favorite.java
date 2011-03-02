/**
 * 
 */
package q.domain;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class Favorite extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 7035235826504724248L;

	private FavoriteStatus status;

	private FavoriteFromType fromType;

	private long fromId;

	private long creatorId;

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
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

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public boolean isFav() {
		return this.status.isFav();
	}

	public boolean isUnFav() {
		return this.status.isUnFav();
	}

}