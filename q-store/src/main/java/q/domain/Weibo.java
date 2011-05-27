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
public class Weibo extends WeiboModel implements Serializable {

	private static final long serialVersionUID = 224241669909941892L;

	public boolean isInGroup() { // in group now, not retweet from group
		return isFromGroup() && this.getQuoteWeiboId() == 0;
	}

	@Override
	public String getViewName() {
		return "weibo";
	}

}
