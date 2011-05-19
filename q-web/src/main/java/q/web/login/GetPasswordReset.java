/**
 * 
 */
package q.web.login;

import q.biz.CacheService;
import q.biz.PasswordResetToken;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 *
 */
public class GetPasswordReset extends Resource {
	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("key", context.getResourceId());
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		String key = context.getResourceId();
		PasswordResetToken token = this.cacheService.getPasswordResetToken(key);
		if(token == null) {
			context.redirectServletPath("/password/reset/invalid");
		}

	}

}
