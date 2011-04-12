/**
 *
 */
package q.web.weibo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import q.biz.SearchService;
import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.http.JdkHttpClient;
import q.log.Logger;
import q.util.IdCreator;
import q.util.ImageKit;
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
 * @author bububut
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class AddWeibo extends Resource {
	private WeiboDao weiboDao;
	private String imageUploadUrl;
	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
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
		String picturePath=context.getString("picPath");
		weibo.setPicturePath(picturePath);
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
		searchService.updateWeibo(weibo);

		String upimgfix=context.getString("upimgfix");
		int fix=Integer.parseInt(upimgfix);
        while(fix-360>-1){
        	fix=fix-360;
        }
		if(fix>0){
		BufferedImage originImage;
		BufferedImage originImage160;
		BufferedImage originImage320;

		URL temp = new URL(picturePath);
		HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
		InputStream imagetemp;
		try {
			imagetemp = JdkHttpClient.getMultipart(con);
			originImage = ImageKit.load(imagetemp);
		} finally {
			JdkHttpClient.releaseUrlConnection(con);
		}
		URL temp1 = new URL(picturePath+"-160");
		HttpURLConnection con1 = JdkHttpClient.getHttpConnection(temp1, 100000, 100000);
		InputStream imagetemp1;
		try {
			imagetemp1 = JdkHttpClient.getMultipart(con1);
			originImage160 = ImageKit.load(imagetemp1);
		} finally {
			JdkHttpClient.releaseUrlConnection(con1);
		}
		URL temp2 = new URL(picturePath+"-320");
		HttpURLConnection con2 = JdkHttpClient.getHttpConnection(temp2, 100000, 100000);
		InputStream imagetemp2;
		try {
			imagetemp2 = JdkHttpClient.getMultipart(con2);
			originImage320 = ImageKit.load(imagetemp2);
		} finally {
			JdkHttpClient.releaseUrlConnection(con2);
		}
        originImage=ImageKit.rotate(originImage, fix);
        originImage160=ImageKit.rotate(originImage160, fix);
        originImage320=ImageKit.rotate(originImage320, fix);
        BufferedImage[] images = new BufferedImage[3];
		images[0] = originImage;
		images[1] = originImage160;
		images[2] = originImage320;
		URL tempt = new URL(this.imageUploadUrl);
		String sb;
		int di=picturePath.lastIndexOf("/");
		String name=picturePath.substring(di);
		picturePath=picturePath.substring(0, di);
		String dir=picturePath.substring(picturePath.lastIndexOf("/"));
		sb = JdkHttpClient.postPictures(tempt,dir ,name, images);


		}
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
