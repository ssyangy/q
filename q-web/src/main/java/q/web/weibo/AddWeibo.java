/**
 *
 */
package q.web.weibo;

import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.util.IdCreator;
import q.util.Replace;
import q.util.UrlKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;
import q.http.JdkHttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import java.net.URL;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
/**
 * @author bububut
 *
 */
/**
 * @author bububut
 *
 */
public class AddWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private String putShortUrl;

	public void setPutShortUrl(String putShortUrl) {
		if (putShortUrl.endsWith("/")) {
			this.putShortUrl = putShortUrl;
		} else {
			this.putShortUrl = putShortUrl;
		}
	}

	private String getShortUrl;

	public void setGetShortUrl(String getShortUrl) {
		if (getShortUrl.endsWith("/")) {
			this.getShortUrl = getShortUrl;
		} else {
			this.getShortUrl = getShortUrl + '/';
		}

	}

	private String urlFilter(String content) {
		return UrlKit.replaceUrl(content, new Replace() {

			@Override
			public String replace(String lurl) {
				Map<String, CharSequence> param = new HashMap<String, CharSequence>();
				param.put("url", lurl);
				HttpURLConnection con = null;
				String surl = lurl;
				try {
					con = JdkHttpClient.getHttpConnection(new URL(putShortUrl),
							100000, 100000);
					surl = JdkHttpClient.post(con, param);
					surl = getShortUrl + surl;
				} catch (IOException e) {
					log.error("", e);
				} finally {
					try {
						JdkHttpClient.releaseUrlConnection(con);
					} catch (IOException e) {
						log.error("", e);
					}
				}
				return surl;
			}
		});

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
		content = urlFilter(content);
		weibo.setContent(content);
		long groupId = context.getIdLong("groupId");
		if (IdCreator.isValidIds(groupId)) {
			weibo.setFromType(WeiboFromType.GROUP);
			weibo.setFromId(groupId);
		}
		this.weiboDao.addWeibo(weibo);

		if (IdCreator.isValidIds(groupId)) {
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(),
					groupId);
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
