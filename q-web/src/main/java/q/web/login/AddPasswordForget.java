/**
 * 
 */
package q.web.login;

import java.util.UUID;

import q.biz.CacheService;
import q.biz.MailService;
import q.dao.PeopleDao;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class AddPasswordForget extends Resource {

	private MailService mailService;

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		String email = context.getString("email");
		People people = null;
		people = this.peopleDao.getPeopleByEmail(email);
		if (people == null) { // email not exists
			throw new RequestParameterInvalidException("email:该邮箱地址不存在");
		}
		this.mailService.sendEmail(email, "重新设置您的q.com.cn密码", "请点击以下链接重置您在q.com.cn的密码(链接48小时内有效):<br/>" + createPasswordResetUrl(context.getUrlPrefix(), people.getId(), System.currentTimeMillis()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		String email = context.getString("email");
		if (StringKit.isEmpty(email)) {
			throw new RequestParameterInvalidException("email:invalid");
		}
	}

	/**
	 * @param urlPrefix
	 * @return
	 */
	private String createPasswordResetUrl(String urlPrefix, long peopleId, long timestamp) {
		String token = UUID.randomUUID().toString();
		this.cacheService.putPasswordResetToken(token, peopleId, timestamp);
		return urlPrefix + "/password/" + token + "/reset";
	}

}
