package q.serialize.tools;

import java.io.Serializable;

/**
 * 地址信息DO
 * 
 * @author moling
 * @since 1.0, 2009-12-1
 */
public class Location implements Serializable {

	private static final long serialVersionUID = -770423312890516334L;

	/**
	 * 邮编
	 */
	private String zip;
	public static final String ZIP = "zip";

	/**
	 * 城市
	 */
	private String city;
	public static final String CITY = "city";

	/**
	 * 省份
	 */
	private String state;
	public static final String STATE = "state";

	/**
	 * 国家
	 */
	private String country;
	public static final String COUNTRY = "country";

	/**
	 * 时区
	 */
	private String timeZone;
	public static final String TIME_ZONE = "time_zone";

	/**
	 * 地址
	 */
	private String address;
	public static final String ADDRESS = "address";
	
	/**
	 * 区 
	 */
	private String district;
	public static final String DISTRICT = "district";

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
}
