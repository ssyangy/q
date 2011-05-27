/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.domain.Area;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 12, 2011
 * 
 *       Converter between db tables and area.
 */
public class AreaHandler implements TypeHandlerCallback {

	/**
	 * convert db result to area
	 */
	public Object getResult(ResultGetter getter) throws SQLException {

		if (getter.getObject() == null) {
			return null;
		}

		return Area.getAreaById(getter.getInt());
	}

	/**
	 * convert area to db
	 */
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		setter.setLong(parameter == null ? 0 : ((Area) parameter).getId());
	}

	/**
	 * convert nullValue to gender if db result is null
	 */
	public Object valueOf(String value) {
		return Area.getAreaById(Long.valueOf(value));
	}

}