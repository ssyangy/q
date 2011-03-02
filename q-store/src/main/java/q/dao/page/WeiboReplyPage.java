/**
 * 
 */
package q.dao.page;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboReplyPage extends Page implements Serializable {
	private static final long serialVersionUID = -3159535885864621611L;

	private Long quoteWeiboId;

	public Long getQuoteWeiboId() {
		return quoteWeiboId;
	}

	public void setQuoteWeiboId(Long quoteWeiboId) {
		this.quoteWeiboId = quoteWeiboId;
	}

}
