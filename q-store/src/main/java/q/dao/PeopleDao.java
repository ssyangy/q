/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	List<PeopleRelation> getPagePeopleRelationWithFromRealName(PeopleRelationPage page) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<PeopleRelation> getPagePeopleRelationWithToRealName(PeopleRelationPage page) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<PeopleRelation> getPagePeopleRelation(PeopleRelationPage page) throws SQLException;

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
	 */
	void updatePeopleRelationStatusById(PeopleRelationStatus relationStatus, long relationId) throws SQLException;

	/**
	 * @param stranger
	 * @param fromPeopleId
	 * @param toPeopleId
	 */
	void updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus stranger, long fromPeopleId, long toPeopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 */
	int getPeopleFollowingNumById(long peopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 */
	int getPeopleFollowerNumById(long peopleId) throws SQLException;

	/**
	 * @param email
	 * @return
	 */
	People getPeopleByEmail(String email) throws SQLException;

	/**
	 * @param areaId
	 * @param i
	 * @return
	 */
	List<People> getHotPeoplesByArea(Area area, int limit) throws SQLException;

}
