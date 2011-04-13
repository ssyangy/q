package q.biz.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import q.biz.PictureService;
import q.http.JdkHttpClient;
import q.util.IdCreator;
import q.util.ImageKit;

public class ImgService implements PictureService {
	private String imageUrl;

	private String imageUploadUrl;

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setImageUploadUrl(String imageUploadUrl) {
		this.imageUploadUrl = imageUploadUrl;
	}

	@Override
	public boolean rotate(String url, int rotate) throws IOException {
		   int fix=rotate;
		   String sb="";
		   String picturePath=url;
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
			int di=picturePath.lastIndexOf("/");
			String name=picturePath.substring(di);
			picturePath=picturePath.substring(0, di);
			String dir=picturePath.substring(picturePath.lastIndexOf("/"));
			sb = JdkHttpClient.postPictures(tempt,dir ,name, images);
			}
			if(sb.equals("false")){
		        return false;
			}
			return true;
	}

	@Override
	public String uploadWeiboPictures(InputStream picture) throws Exception {
		long picId=IdCreator.getLongId();
		String dir=Long.toString(picId% 10000, Character.MAX_RADIX);
        String name=Long.toString(picId, Character.MAX_RADIX);
		BufferedImage originImage = ImageKit.load(picture);
        BufferedImage image160 = ImageKit.zoomTo(originImage, 160);
        BufferedImage image320 = ImageKit.zoomTo(originImage, 320);
        BufferedImage[] images = new BufferedImage[3];
		images[0] = image160;
		images[1] = image320;
		images[2] = originImage;
    	URL temp = new URL(this.imageUploadUrl);
		String sb;
		sb = JdkHttpClient.postPictures(temp,dir ,name, images);
        if(sb.equals("false")){
        	return "false";
        }
        else{
            return sb;
        }

	}

}
