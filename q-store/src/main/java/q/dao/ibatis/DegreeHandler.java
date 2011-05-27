/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.domain.Degree;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 12, 2011
 * 
 *       Converter between db tables and pojo gender.
 */
public class DegreeHandler implements TypeHandlerCallback {

	/**
	 * convert db result to gender
	 */
	public Object getResult(ResultGetter getter) throws SQLException {

		if (getter.getObject() == null) {
			return null;
		}

		return Degree.convertValue(getter.getInt());
	}

	/**
	 * convert gender to db
	 */
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		setter.setInt(parameter == null ? 0 : ((Degree) parameter).getValue());
	}

	/**
	 * convert nullValue to gender if db result is null
	 */
	public Object valueOf(String value) {
		return Degree.convertValue(Integer.valueOf(value));
	}

}