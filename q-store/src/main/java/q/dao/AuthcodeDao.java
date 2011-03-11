
package q.dao;

import java.sql.SQLException;
import java.util.List;

public interface AuthcodeDao {


	String getValueById(long authcodeId) throws SQLException;


	void updateValueById(long authcodeId, String value) throws SQLException;
}
