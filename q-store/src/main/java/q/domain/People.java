package q.domain;

import java.util.Date;
import java.io.Serializable;

public class People implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5592959607374663712L;

	private long id;

	private String username;

	private String password;

	private String loginToken; // md5(username+password)

	private String realName;

	private String email;

	private long mobile;

	private int year;// xxxx - 1900

	private int month;// 1~12

	private int day;// 1~31

	private int countryCode;// contry telephone code

	private int districtId;// people residence disctrictId

	private Gender gender;

	private byte bloodTypeId;

	private byte educationId;

	private byte roleId;

	private byte status;

	private Date created;

	private Date modified;

	private int friendNum;

	private int followNum;

	private int followingNum;

	private int weiboNum;

	private String intro;

	// =========================================

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year > 1900) {
			year -= 1900;
		}
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public byte getBloodTypeId() {
		return bloodTypeId;
	}

	public void setBloodTypeId(byte bloodTypeId) {
		this.bloodTypeId = bloodTypeId;
	}

	public byte getEducationId() {
		return educationId;
	}

	public void setEducationId(byte educationId) {
		this.educationId = educationId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public byte getRoleId() {
		return this.roleId;
	}

	public void setRoleId(byte roleId) {
		this.roleId = roleId;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
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

	public String getLoginToken() {
		return loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public int getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(int friendNum) {
		this.friendNum = friendNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getFollowingNum() {
		return followingNum;
	}

	public void setFollowingNum(int followingNum) {
		this.followingNum = followingNum;
	}

	public int getWeiboNum() {
		return weiboNum;
	}

	public void setWeiboNum(int weiboNum) {
		this.weiboNum = weiboNum;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBirthdayString() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", username=" + username + ", password=" + password + ", loginToken=" + loginToken + ", realName=" + realName + ", email=" + email + ", mobile=" + mobile + ", year=" + year + ", month=" + month + ", day=" + day + ", countryCode=" + countryCode + ", districtId=" + districtId + ", gender=" + gender + ", bloodTypeId=" + bloodTypeId + ", educationId=" + educationId + ", roleId=" + roleId + ", status=" + status + ", created=" + created + ", modified=" + modified + ", friendNum=" + friendNum + ", followNum=" + followNum + ", followingNum=" + followingNum + ", weiboNum=" + weiboNum + ", intro=" + intro + "]";
	}

}