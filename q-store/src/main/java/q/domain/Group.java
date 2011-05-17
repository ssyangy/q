package q.domain;

import java.io.Serializable;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public class Group extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -471923896513393314L;

	private long creatorId;

	private String name;

	private String intro;

	private int status;

	private int joinNum;

	private String avatarPath;

	private boolean joined;

	private long categoryId;
	
	private People creator;

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryId
	 */
	public long getCategoryId() {
		return categoryId;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNumber) {
		this.joinNum = joinNumber;
	}

	private double latitude;

	private double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public boolean hasAvatar() {
		if (avatarPath == null || avatarPath.endsWith("-def")) {
			return false;
		}
		return true;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param joined
	 *            the joined to set
	 */
	public void setJoined(boolean joined) {
		this.joined = joined;
	}

	/**
	 * @return the joined
	 */
	public boolean isJoined() {
		return joined;
	}


	/**
	 * @param creator the creator to set
	 */
	public void setCreator(People creator) {
		this.creator = creator;
	}

	/**
	 * @return the creator
	 */
	public People getCreator() {
		return creator;
	}
	
	@Override
	public String toString() {
		return "Group [id=" + id + ", creatorId=" + creatorId + ", name=" + name + ", intro=" + intro + ", longitude=" + longitude + ", latitude=" + latitude + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}
}
