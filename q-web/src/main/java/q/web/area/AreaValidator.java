/**
 * 
 */
package q.web.area;

import q.biz.exception.RequestParameterInvalidException;
import q.domain.Area;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 17, 2011
 *
 */
public class AreaValidator {
	
	public static void check(long provinceId, long cityId, long countyId) throws RequestParameterInvalidException{

		Area province = null;
		Area city = null;
		Area county = null;
		if (provinceId <= 0) {
			throw new RequestParameterInvalidException("province:省份必填");
		}
		province = Area.getAreaById(provinceId);
		if (null == province) {
			throw new RequestParameterInvalidException("province:该省份不存在");
		}
		if (cityId > 0) {
			city = Area.getAreaById(cityId);
			if (null == city) {
				throw new RequestParameterInvalidException("city:该城市不存在");
			}
		}
		if (countyId > 0) {// county, city, province all exsit.
			county = Area.getAreaById(countyId);
			if (null == county) {
				throw new RequestParameterInvalidException("county:该县/区不存在");
			}
		}
		if (city == null && province.hasChilds()) { // need city after province
			throw new RequestParameterInvalidException("city:该省份下必填城市");
		}
		if (county == null && city.hasChilds()) { // need county after city
			throw new RequestParameterInvalidException("county:该城市下必填县/区");
		}
		if (city != null && province != null && !city.isChild(province)) {
			throw new RequestParameterInvalidException("city:该城市不属于该省份");
		}
		if (county != null && city != null && !county.isChild(city)) {
			throw new RequestParameterInvalidException("county:该县/区不属于该城市");
		}
	}

	/**
	 * @return
	 */
	public static int getAreaId(int provinceId, int cityId, int countyId) {
		int areaId = countyId;
		if (areaId <= 0) {
			areaId = cityId; // only province, city
		}
		if (areaId <= 0) {
			areaId = provinceId; // only province
		}
		return areaId;
	}

}
