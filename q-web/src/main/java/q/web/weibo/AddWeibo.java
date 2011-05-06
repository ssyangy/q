/**
 *
 */
package q.web.weibo;

import q.biz.PictureService;
import q.biz.WeiboService;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @author bububut
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class AddWeibo extends Resource {
	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	private WeiboService weiboService;
	
	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
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
		weibo.setContent(content);
		String picturePath = context.getString("picPath");
		if (StringKit.isNotEmpty(picturePath)) {
			weibo.setPicturePath(picturePath);
		}
		long groupId = context.getIdLong("groupId");
		if (IdCreator.isValidIds(groupId)) {
			weibo.setFromType(WeiboFromType.GROUP);
			weibo.setFromId(groupId);
		}
		if (context.getString("latitude") != null && context.getString("longitude") != null) {
			weibo.setLatitude(Double.parseDouble(context.getString("latitude")));
			weibo.setLongitude(Double.parseDouble(context.getString("longitude")));
		}
		
		this.weiboService.addWeibo(weibo, groupId);
		
		String upimgfix = context.getString("upimgfix");
		if (StringKit.isNotEmpty(upimgfix)) {
			int fix = Integer.parseInt(upimgfix);
			pictureService.rotate(picturePath, fix);
		}

		String from = context.getString("from");
		if (StringKit.isNotEmpty(from)) {
			context.redirectContextPath(from);
		}
		context.setModel("weibo", weibo);
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
			throw new RequestParameterInvalidException("login:invalid");
		}
		String content = context.getString("content");
		if (null == content) {
			throw new RequestParameterInvalidException("content:invalid");
		}

	}
}
