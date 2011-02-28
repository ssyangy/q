/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboJoinGroup;
import q.domain.WeiboReply;
import q.util.CollectionKit;
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
	public List<Weibo> getPageGroupWeibo(WeiboPage page) throws SQLException {
		List<Long> ids = (List<Long>) this.sqlMapClient.queryForList("selectPageGroupWeiboIds", page);
		if (CollectionKit.isEmpty(ids)) {
			return null;
		}
		return (List<Weibo>) this.sqlMapClient.queryForList("selectWeibosByIds", ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Weibo> getPageWeibo(WeiboPage page) throws SQLException {
		return (List<Weibo>) this.sqlMapClient.queryForList("selectPageWeibos", page);
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

	@Override
	public int getPeopleWeiboNumByPeopleId(long peopleId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("getPeopleWeiboNumByPeopleId", peopleId);
	}

	@Override
	public WeiboReply getWeiboReplyById(long weiboReplyId) throws SQLException {
		return (WeiboReply) this.sqlMapClient.queryForObject("selectWeiboReplyById", weiboReplyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.WeiboDao#getWeiboJoinGroupByWeiboIdAndGroupId(long, long)
	 */
	@Override
	public WeiboJoinGroup getWeiboJoinGroupByWeiboIdAndGroupId(long weiboId, long groupId) throws SQLException {
		WeiboJoinGroup join = new WeiboJoinGroup();
		join.setWeiboId(weiboId);
		join.setGroupId(groupId);
		return (WeiboJoinGroup) this.sqlMapClient.queryForObject("selectWeiboJoinGroupByWeiboIdGroupId", join);
	}

	@Override
	public WeiboJoinGroup getWeiboJoinGroupByWeiboId(long weiboId) throws SQLException {
		return (WeiboJoinGroup) this.sqlMapClient.queryForObject("selectWeiboJoinGroupByWeiboId", weiboId);
	}

}
