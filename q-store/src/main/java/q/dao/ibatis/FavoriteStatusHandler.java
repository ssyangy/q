/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.domain.FavoriteStatus;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 27, 2011
 * 
 */
public class FavoriteStatusHandler implements TypeHandlerCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
	 */
	@Override
	public void setParameter(ParameterSetter setter, Object parameter) throws SQLException {
		setter.setInt(parameter == null ? 0 : ((FavoriteStatus) parameter).getValue());
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
		return FavoriteStatus.convertValue(getter.getInt());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
	 */
	@Override
	public Object valueOf(String s) {
		return FavoriteStatus.convertValue(Integer.valueOf(s));
	}

}
