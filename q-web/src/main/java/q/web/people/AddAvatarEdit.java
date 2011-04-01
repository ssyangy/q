package q.web.people;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

	@Override
	public void execute(ResourceContext context) throws Exception {

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		double x1 = Double.parseDouble(context.getString("realx1"));
		double x2 = Double.parseDouble(context.getString("realx2"));
		double y1 = Double.parseDouble(context.getString("realy1"));
		double y2 = Double.parseDouble(context.getString("realy2"));
		if (y2 <= 0 || x2 <= 0) {
			throw new RequestParameterInvalidException("value:图片参数出错了。");
		}
		long peopleId = context.getCookiePeopleId();
		long dir = peopleId % 10000;
		URL temp = new URL(imageUrl + "/a/" + String.valueOf(dir) + "/" + String.valueOf(peopleId));
		HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
		InputStream imagetemp;
		BufferedImage cutImage;
		try {
			imagetemp = JdkHttpClient.getMultipart(con);
			BufferedImage originImage = ImageKit.load(imagetemp);
			cutImage = ImageKit.cutTo(originImage, x1, y1, x2, y2);
		} finally {
			JdkHttpClient.releaseUrlConnection(con);
		}

		BufferedImage image128 = ImageKit.zoomTo(cutImage, 128, 128);
		BufferedImage image48 = ImageKit.zoomTo(cutImage, 48, 48);
		BufferedImage image24 = ImageKit.zoomTo(cutImage, 24, 24);
		BufferedImage[] images = new BufferedImage[3];
		images[0] = image128;
		images[1] = image48;
		images[2] = image24;
		URL url = new URL(this.imageUploadUrl);

		boolean sb;

		sb = JdkHttpClient.postPictures(url, peopleId, images);

		if (sb == false) {
			throw new RequestParameterInvalidException("value:服务器忙。");
		}
	}
}
