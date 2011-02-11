package q.domain;

import java.util.Date;
import java.io.Serializable;
public class People implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5592959607374663712L;

	// ===== db properties =====
	private int id;

	private String username;

	private String password;

	private String realName;

	private String email;

	private long mobile;

	private int year;// xxxx - 1900

	private byte month;// 1~12

	private byte day;// 1~31

	private int countryCode;// contry telephone code

	private int districtId;// people residence disctrictId

	private byte genderId;

	private byte bloodTypeId;

	private byte educationId;

	private byte weiboLimitRoleId;

	private byte status;

	private Date created;

	private Date modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return 1900 + year;
	}

	public void setYear(int year) {
		if (year > 1900) {
			year -= 1900;
		}
		this.year = year;
	}

	public byte getMonth() {
		return month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	public byte getDay() {
		return day;
	}

	public void setDay(byte day) {
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

	public byte getGenderId() {
		return genderId;
	}

	public void setGenderId(byte genderId) {
		this.genderId = genderId;
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

	public byte getWeiboLimitRoleId() {
		return weiboLimitRoleId;
	}

	public void setWeiboLimitRoleId(byte weiboLimitRoleId) {
		this.weiboLimitRoleId = weiboLimitRoleId;
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

	// =======================================

	public String getBirthdayString() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}

}
