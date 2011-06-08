/**
 *
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.page.PeoplePage;
import q.dao.page.PeopleRelationPage;
import q.domain.Area;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;

/**
 * @author alin
 * @date Feb 10, 2011
 *
 */
public interface PeopleDao {

	/**
	 * @param p
	 * @throws SQLException
	 */
	void addPeople(People p) throws SQLException;

	/**
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	People getPeopleById(long pid) throws SQLException;

	/**
	 * @param p
	 * @throws SQLException
	 */
	void updatePeopleById(People p) throws SQLException;

	/**
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	People getPeopleByUsername(String username) throws SQLException;

	/**
	 * @param senderIds
	 * @return
	 */
	Map<Long, String> getIdRealNameMapByIds(Set<Long> senderIds) throws SQLException;

	/**
	 * @param relation
	 */
	void addPeopleRelation(PeopleRelation relation) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<PeopleRelation> getPeopleRelationsByPage(PeopleRelationPage page) throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 * @throws SQLException
	 */
	List<Long> getAllFollowingId(long peopleId) throws SQLException;

	/**
	 * @param fromPeopleId
	 * @param toPeopleId
	 * @return
	 */
	PeopleRelation getPeopleRelationByFromIdToId(long fromPeopleId, long toPeopleId) throws SQLException;

	/**
	 * @param relationStatus
	 * @param relationId
	 * @param newRelation
	 * @return
	 */
	int updatePeopleRelationStatusById(PeopleRelationStatus newStatus, PeopleRelationStatus oldStatus, long relationId) throws SQLException;

	/**
	 * @param stranger
	 * @param fromPeopleId
	 * @param toPeopleId
	 * @return
	 */
	int updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus newStatus, PeopleRelationStatus oldStatus, long fromPeopleId, long toPeopleId) throws SQLException;

	/**
	 * @param email
	 * @return
	 */
	People getPeopleByEmail(String email) throws SQLException;

	/**
	 * @param area
	 * @param limit
	 * @return
	 */
	List<People> getHotPeoplesByArea(Area area, int limit) throws SQLException;

	/**
	 * @param ids
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	List<People> getPeoplesByIds(List<Long> ids) throws SQLException;

	/**
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	People getInterestById(long pid) throws SQLException;

	/**
	 * @param p
	 * @throws SQLException
	 */
	void updateInterestById(People p) throws SQLException;

	/**
	 * @param p
	 * @throws SQLException
	 */
	String selectPasswordById(long pid) throws SQLException;

	/**
	 * @param senderId
	 */
	int decrPeopleWeiboNumberByPeopleId(long senderId) throws SQLException;

	/**
	 * @param senderId
	 * @return
	 * @throws SQLException
	 */
	int incrPeopleWeiboNumberByPeopleId(long senderId) throws SQLException;

	/**
	 * @param fromPeopleId
	 */
	int incrPeopleFollowingNumberByPeopleId(long fromPeopleId) throws SQLException;

	/**
	 * @param toPeopleId
	 */
	int incrPeopleFollowerNumberByPeopleId(long toPeopleId) throws SQLException;

	/**
	 * @param fromPeopleId
	 */
	int decrPeopleFollowingNumberByPeopleId(long fromPeopleId) throws SQLException;

	/**
	 * @param toPeopleId
	 * @return
	 */
	int decrPeopleFollowerNumberByPeopleId(long toPeopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @param newPassword
	 */
	int decrPeopleGroupNumberByPeopleId(long PeopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @param newPassword
	 */
	int incrPeopleGroupNumberByPeopleId(long PeopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @param newPassword
	 */
	int updatePasswordByPeopleId(long peopleId, String newPassword) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<People> getPeoplesByPage(PeoplePage page) throws SQLException;

}
