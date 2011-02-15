/**
 * 
 */
package q.dao;
import java.sql.SQLException;
import q.domain.Group;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public interface GroupDao {

	void addGroup(Group p) throws SQLException;

	Group getGroupById(int gid) throws SQLException;
}
