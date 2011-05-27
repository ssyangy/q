/**
 * 
 */
package q.serialize.mapping;

import java.io.Serializable;
import java.util.Map;

/**
 * The Interface Switcher.
 * 
 * @author alin
 * @date May 22, 2009
 */
public interface Switcher extends Serializable {

	/**
	 * Gets the cases.
	 * 
	 * @return the cases
	 */
	Map<Object, Object> getCases();

	/**
	 * Gets the default target.
	 * 
	 * @return the default target
	 */
	Object getDefaultTarget();

	/**
	 * Switch case.
	 * 
	 * @param src
	 *            the src
	 * 
	 * @return the object
	 */
	Object switchCase(Object src);

}
