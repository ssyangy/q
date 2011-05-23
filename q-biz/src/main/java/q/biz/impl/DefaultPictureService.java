package q.biz.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import q.biz.PictureService;
import q.commons.http.JdkHttpClient;
import q.commons.image.ImageKit;
import q.util.IdCreator;

public class DefaultPictureService implements PictureService {
	private String imageUrl;

	private String imageUploadUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getImageUploadUrl() {
		return imageUploadUrl;
	}

	@Override
	public boolean rotate(String url, int rotate) throws IOException {
		int fix = rotate;
		String sb = "";
		String picturePath = url;
		while (fix - 360 > -1) {
			fix = fix - 360;
		}
		if (fix > 0) {
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
			URL temp1 = new URL(picturePath + "-160");
			HttpURLConnection con1 = JdkHttpClient.getHttpConnection(temp1, 100000, 100000);
			InputStream imagetemp1;
			try {
				imagetemp1 = JdkHttpClient.getMultipart(con1);
				originImage160 = ImageKit.load(imagetemp1);
			} finally {
				JdkHttpClient.releaseUrlConnection(con1);
			}
			URL temp2 = new URL(picturePath + "-320");
			HttpURLConnection con2 = JdkHttpClient.getHttpConnection(temp2, 100000, 100000);
			InputStream imagetemp2;
			try {
				imagetemp2 = JdkHttpClient.getMultipart(con2);
				originImage320 = ImageKit.load(imagetemp2);
			} finally {
				JdkHttpClient.releaseUrlConnection(con2);
			}
			originImage = ImageKit.rotate(originImage, fix);
			originImage160 = ImageKit.rotate(originImage160, fix);
			originImage320 = ImageKit.rotate(originImage320, fix);
			BufferedImage[] images = new BufferedImage[3];
			images[0] = originImage;
			images[1] = originImage160;
			images[2] = originImage320;
			URL tempt = new URL(this.imageUploadUrl);
			int di = picturePath.lastIndexOf("/");
			String name = picturePath.substring(di);
			picturePath = picturePath.substring(0, di);
			String dir = picturePath.substring(picturePath.lastIndexOf("/"));
			sb = postPictures(tempt, dir, name, images);
		}
		if (sb.equals("false")) {
			return false;
		}
		return true;
	}

	public static boolean postPictures(URL url, long peopleId, BufferedImage[] images) throws IOException {
		long dir = peopleId % 10000;
		for (int i = 0; i < images.length; i++) {
			BufferedImage temp = images[i];
			Map<String, CharSequence> payload = new HashMap();
			payload.put("imgdir", "a/" + String.valueOf(dir) + "/");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(temp);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			if (i == 0) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				String[] data = JdkHttpClient.postMultipart(connection, payload, is, String.valueOf(peopleId) + "-128", os.size(), "image/jpeg").split(";");
				if (!data[0].equals("success")) {
					return false;
				}
				JdkHttpClient.releaseUrlConnection(connection);
			} else if (i == 1) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				String[] data = JdkHttpClient.postMultipart(connection, payload, is, String.valueOf(peopleId) + "-48", os.size(), "image/jpeg").split(";");
				if (!data[0].equals("success")) {
					return false;
				}
				JdkHttpClient.releaseUrlConnection(connection);
			} else if (i == 2) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				String[] data = JdkHttpClient.postMultipart(connection, payload, is, String.valueOf(peopleId) + "-24", os.size(), "image/jpeg").split(";");
				if (!data[0].equals("success")) {
					return false;
				}

			}
		}
		return true;
	}

	public static String postPictures(URL url, String dir, String name, BufferedImage[] images) throws IOException {
		String toEnd = "false";
		for (int i = 0; i < images.length; i++) {
			BufferedImage temp = images[i];
			Map<String, CharSequence> payload = new HashMap();
			payload.put("imgdir", dir);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(temp);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			if (i == 0) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				String[] data = JdkHttpClient.postMultipart(connection, payload, is, name + "-160", os.size(), "image/jpeg").split(";");
				if (!data[0].equals("success")) {
					return "false";
				}
				JdkHttpClient.releaseUrlConnection(connection);
			} else if (i == 1) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				String[] data = JdkHttpClient.postMultipart(connection, payload, is, name + "-320", os.size(), "image/jpeg").split(";");
				if (!data[0].equals("success")) {
					return "false";
				}
				JdkHttpClient.releaseUrlConnection(connection);
			} else if (i == 2) {
				HttpURLConnection connection = JdkHttpClient.getHttpConnection(url, 100000, 100000);
				toEnd = JdkHttpClient.postMultipart(connection, payload, is, name, os.size(), "image/jpeg");
				String[] data = toEnd.split(";");
				if (!data[0].equals("success")) {
					return "false";
				}

			}
		}
		return toEnd;
	}

	private String getDir(long id) {
		return Long.toString((id / 10000) % 10000, Character.MAX_RADIX);
	}

	@Override
	public String uploadAvatar(InputStream picture, long peopleId, long size, String type) throws Exception {
		long dir = peopleId % 10000;
		URL temp = new URL(this.imageUploadUrl);
		HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
		String sb;
		try {
			Map<String, CharSequence> payload = new HashMap<String, CharSequence>();
			payload.put("imgdir", "a/" + dir + "/");
			sb = JdkHttpClient.postMultipart(con, payload, picture, String.valueOf(peopleId), size, type);
		} finally {
			JdkHttpClient.releaseUrlConnection(con);
		}
		return sb;
	}

	@Override
	public String uploadWeiboPictures(InputStream picture,String type) throws Exception {
		long picId = IdCreator.getLongId();
		String dir = "w/" + Long.toString(picId % 10000, Character.MAX_RADIX) + "/";
		String kind=type.substring(type.indexOf("/")+1, type.length());
		String name = Long.toString(picId, Character.MAX_RADIX)+"."+kind;
		BufferedImage image = ImageKit.load(picture);
		int originWidth = image.getWidth();
		int originHeight = image.getHeight();

		BufferedImage image160 = null;
		BufferedImage image320 = null;
		int longer;
		if (originWidth > originHeight) {
			longer = originWidth;
		} else {
			longer = originHeight;
		}
		if (longer <= 160) {
			image160 = image;
			image320 = image;
		} else if (longer <= 320) {
			image160 = ImageKit.zoomTo(image, 160);
			image320 = image;
		} else {
			image160 = ImageKit.zoomTo(image, 160);
			image320 = ImageKit.zoomTo(image, 320);
		}
		BufferedImage tagImage=image;
		if(kind.equals("png")){
			tagImage = new BufferedImage(originWidth, originHeight, BufferedImage.TYPE_INT_RGB);
			Image temp = image.getScaledInstance(originWidth, originHeight, Image.SCALE_SMOOTH);
			Graphics g = tagImage.getGraphics();
			g.drawImage(temp, 0, 0, null);
			g.dispose();
		}
		BufferedImage[] images = new BufferedImage[3];
		images[0] = image160;
		images[1] = image320;
		images[2] = tagImage;
		URL temp = new URL(this.imageUploadUrl);
		String sb;
		sb = postPictures(temp, dir, name, images);
		if (sb.equals("false")) {
			return "false";
		} else {
			return sb;
		}

	}

	@Override
	public boolean editAvatar(double x1, double x2, double y1, double y2, long peopleId) throws Exception {
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

		sb = postPictures(url, peopleId, images);
		return sb;
	}

	@Override
	public boolean hasAvatar(long peopleId) {
		long dir = peopleId % 10000;
		if (JdkHttpClient.exists(imageUrl + "/a/" + String.valueOf(dir) + "/" + String.valueOf(peopleId))) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getDefaultFemaleAvatarPath() {
		return this.imageUrl + "/default/female-def";
	}

	@Override
	public String getDefaultMaleAvatarPath() {
		return this.imageUrl + "/default/male-def";
	}

	@Override
	public String getDefaultGroupAvatarPath() {
		return this.imageUrl + "/default/group-def";
	}

	@Override
	public String getDefaultCategoryAvatarPath() {
		return this.imageUrl + "/default/cat-def";
	}

}
