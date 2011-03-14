
package q.dao;

import java.sql.SQLException;

public interface AuthcodeDao {


	String getValueById(long authcodeId) throws SQLException;


	void updateValueById(long authcodeId) throws SQLException;
}
