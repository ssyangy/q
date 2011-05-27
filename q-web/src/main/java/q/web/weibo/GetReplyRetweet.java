/**
 * 
 */
package q.web.weibo;

import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.People;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 27, 2011
 * 
 */
public class GetReplyRetweet extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long replyId = context.getResourceIdLong();
		WeiboReply reply = this.weiboDao.getWeiboReplyById(replyId);
		DaoHelper.injectWeiboReplyWithSenderRealNameAndFrom(peopleDao, groupDao, reply);
		context.setModel("reply", reply);

		People sender = peopleDao.getPeopleById(reply.getSenderId());
		context.setModel("sender", sender);

		context.setModel("from", context.getString("from"));
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
