/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.Date;

import q.commons.domain.AbstractDomain;
import q.util.DateKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class Event extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = 986692036209440550L;

	private long creatorId;

	private String creatorRealName;

	private long groupId;

	private String groupName;

	private String name;

	private String intro;

	private Area area;

	private String address;

	private Date started;

	private Date ended;

	private String cost;

	private int number;// people number

	private int status;

	private int joinNum;
	
	private People people;

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStarted() {
		return started;
	}

	public String getStartedMd() {
		return DateKit.date2Md(started);
	}

	public String getStartedMdhm() {
		return DateKit.date2Mdhm(started);
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnded() {
		return ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCreatorRealName() {
		return creatorRealName;
	}

	public void setCreatorRealName(String creatorRealName) {
		this.creatorRealName = creatorRealName;
	}

	public int getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(int joinNum) {
		this.joinNum = joinNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "Event [creatorId=" + creatorId + ", groupId=" + groupId + ", name=" + name + ", intro=" + intro + ", area=" + area + ", address=" + address + ", started=" + started + ", ended=" + ended + ", cost=" + cost + ", number=" + number + ", status=" + status + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

}
