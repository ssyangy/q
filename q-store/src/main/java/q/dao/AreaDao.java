/**
 * 
 */
package q.dao;

import q.domain.Area;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 15, 2011
 *
 */
public interface AreaDao {

	/**
	 * @return
	 */
	Area getRootArea();

	/**
	 * @param id
	 * @return
	 */
	Area getAreaById(long id);

}
