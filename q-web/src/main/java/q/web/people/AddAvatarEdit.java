package q.web.people;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import q.biz.PictureService;
import q.dao.PeopleDao;
import q.domain.People;
import q.http.JdkHttpClient;
import q.util.ImageKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

public class AddAvatarEdit extends Resource {
	private String imageUrl;

	private String imageUploadUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
	}
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		double x1 = Double.parseDouble(context.getString("realx1"));
		double x2 = Double.parseDouble(context.getString("realx2"));
		double y1 = Double.parseDouble(context.getString("realy1"));
		double y2 = Double.parseDouble(context.getString("realy2"));
		People people = new People();
		if (y2 <= 0 || x2 <= 0) {
			throw new RequestParameterInvalidException("value:图片参数出错了。");
		}
		long peopleId = context.getCookiePeopleId();
		boolean sb=pictureService.editAvatar(x1, x2, y1, y2, peopleId);
		if(sb==true){
		long dir = peopleId % 10000;
		String avatarPath=imageUrl + "/a/" + String.valueOf(dir) + "/" + String.valueOf(peopleId);
		people.setAvatarPath(avatarPath);
		people.setId(peopleId);
		peopleDao.updatePeopleById(people);
		}
		if (sb == false) {
			throw new RequestParameterInvalidException("value:服务器忙。");
		}
	}
}
