/**
 * 
 */
package q.commons.domain;

import java.util.Comparator;


/**
 * @author seanlinwang at gmail dot com
 * @date May 24, 2011
 * 
 */
public class DomainIdDescComparator implements Comparator<AbstractDomain> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(AbstractDomain o1, AbstractDomain o2) {
		long cha = o1.id - o2.id;
		if (cha > 0) {
			return -1;
		} else if (cha < 0) {
			return 1;
		} else { // cha == 0
			return 0;
		}
	}

}
