package q.web.people;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import q.dao.PeopleDao;
import q.http.JdkHttpClient;
import q.util.ImageKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleAlreadyExistException;
import q.web.exception.RequestParameterInvalidException;

public class AddAvatarEdit extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		double x1=Double.parseDouble(context.getString("realx1"));
		double x2=Double.parseDouble(context.getString("realx2"));
		double y1=Double.parseDouble(context.getString("realy1"));
		double y2=Double.parseDouble(context.getString("realy2"));
		if(y2<=0||x2<=0){
			throw new RequestParameterInvalidException("value:图片参数出错了。");
		}
		long peopleId = context.getCookiePeopleId();
		long dir = peopleId % 10000;
		URL temp = new URL("http://qimg.net/a/"+String.valueOf(dir)+"/"+String.valueOf(peopleId));
		HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
		InputStream imagetemp;
		BufferedImage cutImage;
		try {
			imagetemp = JdkHttpClient.getMultipart(con);
			BufferedImage originImage=ImageKit.load(imagetemp);
			cutImage=ImageKit.cutTo(originImage, x1, y1,x2,y2);
		} finally {
			JdkHttpClient.releaseUrlConnection(con);
		}

		BufferedImage image128=ImageKit.zoomTo(cutImage, 128, 128);
		BufferedImage image48=ImageKit.zoomTo(cutImage, 48, 48);
		BufferedImage image24=ImageKit.zoomTo(cutImage, 24, 24);
            BufferedImage[]images=new BufferedImage[3];
            images[0]=image128;
            images[1]=image48;
            images[2]=image24;
        	URL url = new URL("http://upload.qimg.net/upload.php");

			boolean sb;

				sb = JdkHttpClient.postPictures(url, peopleId, images);

          if(sb==false){
        	  throw new RequestParameterInvalidException("value:服务器忙。");
          }
	}
}
