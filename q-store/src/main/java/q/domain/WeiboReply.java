/**
 * 
 */
package q.domain;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboReply extends WeiboModel implements Serializable {

	private static final long serialVersionUID = -3572302391963822701L;

	/* (non-Javadoc)
	 * @see q.domain.WeiboModel#getModelName()
	 */
	@Override
	public String getViewName() {
		return "reply";
	}




}
