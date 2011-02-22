/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.util.CollectionUtils;

import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboJoinGroup;
import q.domain.WeiboReply;
import q.util.IdCreator;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class WeiboDaoImpl extends AbstractDaoImpl implements WeiboDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.WeiboDao#addWeibo(q.domain.Weibo)
	 */
	@Override
	public void addWeibo(Weibo weibo) throws SQLException {
		weibo.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertWeibo", weibo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.WeiboDao#addWeiboJoinGroup(q.domain.Weibo)
	 */
	@Override
	public void addWeiboJoinGroup(long weiboId, long groupId) throws SQLException {
		WeiboJoinGroup join = new WeiboJoinGroup();
		join.setWeiboId(weiboId);
		join.setGroupId(groupId);
		join.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertWeiboJoinGroup", join);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.WeiboDao#getPageGroupWeibo(q.domain.WeiboPage)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Weibo> getPageWeibo(WeiboPage page) throws SQLException {
		List<Long> ids = (List<Long>) this.sqlMapClient.queryForList("selectPageWeiboIds", page);
		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}
		return (List<Weibo>) this.sqlMapClient.queryForList("selectWeibosByIds", ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.WeiboDao#getWeiboById(long)
	 */
	@Override
	public Weibo getWeiboById(long weiboId) throws SQLException {
		return (Weibo) this.sqlMapClient.queryForObject("selectWeiboById", weiboId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WeiboReply> getPageWeiboReply(WeiboReplyPage page) throws SQLException {
		return (List<WeiboReply>) this.sqlMapClient.queryForList("selectPageWeiboReply", page);
	}

	@Override
	public void addWeiboReply(WeiboReply reply) throws SQLException {
		reply.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertWeiboReply", reply);
	}

}
