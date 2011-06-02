/**
 * 
 */
package q.biz.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import q.biz.NotifyService;
import q.biz.SearchService;
import q.biz.ShortUrlService;
import q.biz.WeiboService;
import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboJoinGroupPage;
import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboReply;
import q.util.CollectionKit;
import q.util.IdCreator;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 * 
 */
public class DefaultWeiboService implements WeiboService {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private NotifyService notifyService;

	public void setNotifyService(NotifyService notifyService) {
		this.notifyService = notifyService;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private ShortUrlService shortUrlService;

	public void setShortUrlService(ShortUrlService shortUrlService) {
		this.shortUrlService = shortUrlService;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeiboReply(q.domain.WeiboReply)
	 */
	@Override
	public void addWeiboReply(WeiboReply reply) throws SQLException {
		reply.setContent(StringUtils.trim(reply.getContent()));
		reply.setContent(this.shortUrlService.urlFilter(reply.getContent()));
		this.weiboDao.addWeiboReply(reply);
		this.weiboDao.incrWeiboReplyNumByReplyId(reply.getQuoteWeiboId());
		this.notifyService.notifyWeiboReply(reply);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeibo(q.domain.Weibo)
	 */
	@Override
	public void addWeibo(Weibo weibo, long groupId) throws Exception {
		this.addWeiboCommon(weibo, groupId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addWeiboRetweet(q.domain.Weibo)
	 */
	@Override
	public void addWeiboRetweet(Weibo retweet) throws Exception {
		this.addWeiboCommon(retweet, -1);
		this.weiboDao.incrWeiboRetweetNumByWeiboId(retweet.getQuoteWeiboId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.WeiboService#addReplyRetweet(q.domain.Weibo)
	 */
	@Override
	public void addReplyRetweet(Weibo retweet) throws Exception {
		this.addWeiboCommon(retweet, -1);
	}

	private void addWeiboCommon(Weibo weibo, long groupId) throws Exception {
		weibo.setContent(StringUtils.trim(weibo.getContent()));
		weibo.setContent(this.shortUrlService.urlFilter(weibo.getContent()));
		this.weiboDao.addWeibo(weibo);
		this.peopleDao.incrPeopleWeiboNumberByPeopleId(weibo.getSenderId());
		if (IdCreator.isValidIds(groupId)) {
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(), groupId);
		}
		this.searchService.updateWeibo(weibo); // FIXME will remove here, sean
		this.notifyService.notifyWeibo(weibo);
		if (IdCreator.isValidIds(groupId)) {
			this.notifyService.notifyGroupWeibo(groupId, weibo);
		}

	}

	@Override
	public Map<String, Object> getPeopleWeiboPagination(long peopleId, long loginPeopleId, int size, long startId, int type) throws SQLException {
		int asc = 1;
		WeiboPage page = new WeiboPage();
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		if (startId > 0) {
			page.setStartId(startId);
		}
		page.setSenderId(peopleId);
		List<Weibo> weibos = weiboDao.getWeibosByPage(page);
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(weibos)) {
			if (weibos.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				weibos.remove(weibos.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				Weibo[] array = weibos.toArray(new Weibo[weibos.size()]);
				CollectionUtils.reverseArray(array);
				weibos = Arrays.asList(array);
			}
			DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
			if (loginPeopleId > 0) {
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
			}
			api.put("weibos", weibos);
		}
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		return api;
	}

	@Override
	public Map<String, Object> getGroupWeiboPagination(long groupId, long loginPeopleId, int size, long startId, int type) throws Exception {
		int asc = 1;
		WeiboJoinGroupPage page = new WeiboJoinGroupPage();
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		page.setGroupId(groupId);
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<Long> weiboIds = weiboDao.getWeiboIdsByJoinGroupPage(page);
		List<Weibo> weibos = weiboDao.getWeibosByIds(weiboIds, true);
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(weibos)) {
			if (weibos.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				weibos.remove(weibos.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				Weibo[] array = weibos.toArray(new Weibo[weibos.size()]);
				CollectionUtils.reverseArray(array);
				weibos = Arrays.asList(array);
			}
			DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
			if (loginPeopleId > 0) {
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
			}
			injectWeiboWithBiaoqingImage(weibos);
			api.put("weibos", weibos);
		}
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		return api;
	}

	@Override
	public Map<String, Object> getWeiboReplyPagination(long weiboId, long loginPeopleId, int size, long startId, int type) throws Exception {
		WeiboReplyPage page = new WeiboReplyPage();
		page.setQuoteWeiboId(weiboId);
		int asc = 1;
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<WeiboReply> replies = weiboDao.getWeiboRepliesByPage(page);
		if (CollectionKit.isNotEmpty(replies)) {
			if (replies.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				replies.remove(replies.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				WeiboReply[] array = replies.toArray(new WeiboReply[replies.size()]);
				CollectionUtils.reverseArray(array);
				replies = Arrays.asList(array);
			}
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, replies);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, replies);
			if (loginPeopleId > 0) {
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, replies, loginPeopleId);
			}
		}

		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(replies)) {
			api.put("replies", replies);
		}

		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		return api;
	}

	@Override
	public Map<String, Object> getReplyReceivedPagination(long loginPeopleId, int size, long startId, int type) throws Exception {
		int asc = 1;
		WeiboReplyPage page = new WeiboReplyPage();
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		if (startId > 0) {
			page.setStartId(startId);
		}
		page.setQuoteSenderId(loginPeopleId);
		List<WeiboReply> replies = weiboDao.getWeiboRepliesByPage(page);
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(replies)) {
			if (replies.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				replies.remove(replies.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				WeiboReply[] array = replies.toArray(new WeiboReply[replies.size()]);
				CollectionUtils.reverseArray(array);
				replies = Arrays.asList(array);
			}
			DaoHelper.injectWeiboReplyWithReplyOrQuote(weiboDao, replies);
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, replies);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, replies);
			DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, replies, loginPeopleId);
			api.put("replies", replies);
		}
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		return api;
	}
	
	@Override
	public Map<String, Object> getReplySendedPagination(long loginPeopleId, int size, long startId, int type) throws Exception {
		int asc = 1;
		WeiboReplyPage page = new WeiboReplyPage();
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		if (startId > 0) {
			page.setStartId(startId);
		}
		page.setSenderId(loginPeopleId);
		List<WeiboReply> replies = weiboDao.getWeiboRepliesByPage(page);
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(replies)) {
			if (replies.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				replies.remove(replies.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			if (type == asc) { // reverse asc to desc
				WeiboReply[] array = replies.toArray(new WeiboReply[replies.size()]);
				CollectionUtils.reverseArray(array);
				replies = Arrays.asList(array);
			}
			DaoHelper.injectWeiboReplyWithReplyOrQuote(weiboDao, replies);
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, replies);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, replies);
			DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, replies, loginPeopleId);
			api.put("replies", replies);
		}
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		return api;
	}


	private static final Map<String, Integer> biaoqingMap = new HashMap<String, Integer>();

	private static final String[] biaoqings = new String[] { "微笑", "撇嘴", "色", "发呆", "得意", "流泪", "害羞", "闭嘴", "睡", "大哭", "尴尬", "生气", "调皮", "呲牙", "惊讶", "难过", "酷", "冷汗", "抓狂", "吐", "偷笑", "可爱", "白眼", "傲慢", "饿", "困", "惊恐", "汗", "大笑", "大兵", "奋斗", "咒骂", "疑问", "嘘...", "晕", "折磨", "哀", "骷髅", "再见", "擦汗", "抠鼻", "鼓掌", "糗大了", "坏笑", "吓", "左哼哼", "右哼哼", "哈欠", "鄙视", "委屈", "快哭了", "阴险", "亲亲", "可怜", "玫瑰", "凋谢", "示爱", "爱心", "心碎", "蛋糕", "闪电", "炸弹", "便便", "啤酒", "咖啡", "饭", "抱抱", "强", " 弱", "握手", "胜利", "佩服", "勾引", "拳头", "差劲", "爱你", "NO", "OK", "猪头", "月亮", "太阳" };

	static {
		for (int i = 0; i < biaoqings.length; i++) {
			biaoqingMap.put(biaoqings[i], i + 1);
		}
	}

	/**
	 * @param weibos
	 */
	private void injectWeiboWithBiaoqingImage(List<Weibo> weibos) {
		for (Weibo weibo : weibos) {
			String content = weibo.getContent();
			char[] charArray = content.toCharArray();
			StringBuilder sb = new StringBuilder(content.length() * 2);
			int start = 0;
			int end = 0;
			boolean startMatched = false;
			for (int i = 0; i < charArray.length; i++) {
				char cha = charArray[i];
				if (cha == '[') {
					start = i;
					startMatched = true;
				} else if (cha == ']') {
					if (startMatched) {

					}
				}

			}
		}

	}
}
