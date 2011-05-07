/**
 * 
 */
package q.biz.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import q.biz.ShortUrlService;
import q.commons.http.JdkHttpClient;
import q.commons.http.UrlKit;
import q.log.Logger;
import q.util.Replace;

/**
 * @author seanlinwang at gmail dot com
 * @date Apr 21, 2011
 *
 */
public class DefaultShortUrlService implements ShortUrlService{
	protected static final Logger log = Logger.getLogger();
	
	private String putShortUrl;

	public void setPutShortUrl(String putShortUrl) {
		if (putShortUrl.endsWith("/")) {
			this.putShortUrl = putShortUrl;
		} else {
			this.putShortUrl = putShortUrl + '/';
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

	@Override
	public String urlFilter(String content) {
		return UrlKit.replaceUrl(content, new Replace() {

			@Override
			public String replace(String lurl) {
				Map<String, CharSequence> param = new HashMap<String, CharSequence>();
				param.put("url", lurl);
				HttpURLConnection con = null;
				String surl = lurl;
				try {
					con = JdkHttpClient.getHttpConnection(new URL(putShortUrl), 100000, 100000);
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

}
