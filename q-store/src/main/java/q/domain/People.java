package q.domain;

import java.io.Serializable;

public class People extends AbstractDomain implements Serializable {
	private static final long serialVersionUID = -5592959607374663712L;

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

	private int areaId;// people residence disctrictId
	
	private Area province;
	
	private Area city;
	
	private Area county;

	private Gender gender;

	private int bloodTypeId;

	private Degree degree;

	private int roleId;

	private int status;

	private int friendNum;

	private int followerNum;

	private int followingNum;

	private int weiboNum;

	private String intro;

	// =========================================

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

	public int getBloodTypeId() {
		return bloodTypeId;
	}

	public void setBloodTypeId(int bloodTypeId) {
		this.bloodTypeId = bloodTypeId;
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getFollowerNum() {
		return followerNum;
	}

	public void setFollowerNum(int followerNum) {
		this.followerNum = followerNum;
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
		return getYear() + 1900 + "-" + getMonth() + "-" + getDay();
	}

	public Area getProvince() {
		return province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public Area getCounty() {
		return county;
	}

	public void setCounty(Area county) {
		this.county = county;
	}

	@Override
	public String toString() {
		return "People [id=" + id + ", username=" + username + ", password=" + password + ", loginToken=" + loginToken + ", realName=" + realName + ", email=" + email + ", mobile=" + mobile + ", year=" + year + ", month=" + month + ", day=" + day + ", countryCode=" + countryCode + ", districtId=" + areaId + ", gender=" + gender + ", bloodTypeId=" + bloodTypeId + ", degree=" + degree + ", roleId=" + roleId + ", status=" + status + ", created=" + created + ", modified=" + modified + ", friendNum=" + friendNum + ", followNum=" + followerNum + ", followingNum=" + followingNum + ", weiboNum=" + weiboNum + ", intro=" + intro + "]";
	}

}
