/**
 * 
 */
package q.web.weibo;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		Weibo weibo = new Weibo();
		weibo.setStatus(Status.COMMON.getValue());
		long senderId = context.getCookiePeopleId();
		weibo.setSenderId(senderId);
		String content = context.getString("content");
		content = StringUtils.replaceChars(content, "\r\n", "");
		content = StringEscapeUtils.escapeHtml(content);
		weibo.setContent(content);
		this.weiboDao.addWeibo(weibo);
		
		long groupId = context.getIdLong("groupId");
		if (IdCreator.isValidIds(groupId)) {
			weibo.setFromType(WeiboFromType.GROUP);
			weibo.setFromId(groupId);
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(), groupId);
		}

		String from = context.getString("from");
		if (from != null) {
			context.redirectContextPath(from);
		} else {
			context.redirectServletPath("/weibo/" + weibo.getId());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (senderId <= 0) {
			throw new RequestParameterInvalidException("senderId");
		}

	}
}
