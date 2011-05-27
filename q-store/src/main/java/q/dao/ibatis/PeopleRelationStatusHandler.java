/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.domain.PeopleRelationStatus;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 * 
 */
public class PeopleRelationStatusHandler implements TypeHandlerCallback {

	/*
	 * java to db
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
	 */
	@Override
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		setter.setInt(parameter == null ? 0 : ((PeopleRelationStatus) parameter).getValue());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
	 */
	@Override
	public Object getResult(ResultGetter getter) throws SQLException {
		if (getter.getObject() == null) {
			return null;
		}

		return PeopleRelationStatus.convertValue(getter.getInt());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
	 */
	@Override
	public Object valueOf(String s) {
		return PeopleRelationStatus.convertValue(Integer.valueOf(s));
	}

}
