/**
 * 
 */
package q.serialize.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class DefaultSwitcher.
 * 
 * @author alin
 * @date May 22, 2009
 */
public class DefaultSwitcher implements Switcher {

	private static final long serialVersionUID = -259252976603721977L;

	/** The cases. */
	private Map<Object, Object> cases;

	/** The default target. */
	private Object defaultTarget;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Switcher#getCases()
	 */
	public Map<Object, Object> getCases() {
		return cases;
	}

	/**
	 * Sets the cases.
	 * 
	 * @param cases
	 *            the cases
	 */
	public void setCases(Map<Object, Object> cases) {
		this.cases = cases;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Switcher#getDefaultTarget()
	 */
	public Object getDefaultTarget() {
		return defaultTarget;
	}

	/**
	 * Sets the default target.
	 * 
	 * @param defaultTarget
	 *            the new default target
	 */
	public void setDefaultTarget(Object defaultTarget) {
		this.defaultTarget = defaultTarget;
	}

	/**
	 * Adds the case.
	 * 
	 * @param src
	 *            the src
	 * @param target
	 *            the target
	 */
	public void addCase(Object src, Object target) {
		if (this.cases == null) {
			this.cases = new HashMap<Object, Object>();
		}
		this.cases.put(src, target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Switcher#switchCase(java.lang.Object)
	 */
	public Object switchCase(Object src) {
		if (src == null) {
			return null;
		}
		Object target = null;
		if (this.cases != null) {
			target = this.cases.get(src);
		}
		if (null == target) {
			target = this.defaultTarget;
		}
		return target;
	}
}
