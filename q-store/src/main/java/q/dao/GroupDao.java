/**
 * 
 */
package q.dao;
import java.sql.SQLException;
import q.domain.Group;
import q.domain.GroupJoinCategory;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public interface GroupDao {

	void addGroup(Group p) throws SQLException;

	Group getGroupById(long gid) throws SQLException;

	void addGroupCategory(GroupJoinCategory gc)throws SQLException;
}
