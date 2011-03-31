package q.web.people;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import q.util.ImageKit;
import q.http.JdkHttpClient;
import q.log.Logger;
import q.web.DefaultResourceContext;
import q.web.Resource;
import q.web.ResourceContext;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddAvatar extends Resource {

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		HttpServletRequest request = ((DefaultResourceContext) context).getRequest();
		File tempfile = new File(System.getProperty("java.io.tmpdir"));// 采用系统临时文件目录
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		diskFileItemFactory.setRepository(tempfile); // 设置缓冲区目录
		ServletFileUpload fu = new ServletFileUpload(diskFileItemFactory);
		fu.setSizeMax(4194304);
		List fileItems = fu.parseRequest(request);
		Iterator iter = fileItems.iterator();
		for (; iter.hasNext();) {
			FileItem fileItem = (FileItem) iter.next();
			if (fileItem.isFormField()) {
				// 当前是一个表单项
			} else {
				long peopleId = context.getCookiePeopleId();
				long dir = peopleId % 10000;
				// 当前是一个上传的文件
				String fileName = fileItem.getName();
				String typeString = fileName.substring(fileName.lastIndexOf(".") + 1);
				String type = "";
				if (typeString.equals("jpg") || typeString.equals("jpeg")) {
					type = "image/jpeg";
				} else if (typeString.equals("png")) {
					type = "image/png";
				} else if (typeString.equals("gif")) {
					type = "image/gif";
				} else {
					context.setModel("isImg", false);
				}
				if (ImageKit.isImage( fileItem.getInputStream())) {
					context.setModel("isImg", true);
					URL temp = new URL("http://upload.qimg.net/upload.php");
					HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
					String sb;
					try {
						Map<String, CharSequence> payload = new HashMap();
						payload.put("imgdir", "a/" + String.valueOf(dir) + "/");
						sb = JdkHttpClient.postMultipart(con, payload, fileItem.getInputStream(), String.valueOf(peopleId),fileItem.getSize(),type);
					} finally {
						JdkHttpClient.releaseUrlConnection(con);
					}
					String[]data=sb.split(";");
					String place=data[1].substring(data[1].indexOf(":")+1);
					context.setModel("imgHeight", ImageKit.load(fileItem.getInputStream()).getHeight());
					context.setModel("imgWidth", ImageKit.load(fileItem.getInputStream()).getWidth());
					context.setModel("imgPath", "http://qimg.net/"+place);
				} else {
					context.setModel("isImg", false);
				}

				// fileItem.write( new File("/home/zhao/下载/test/"+fileName) );
				// BufferedImage originImage=ImageKit.load(new
				// File("/home/zhao/下载/test/"+fileName));
				// BufferedImage cutImage=ImageKit.cutTo(originImage, 5,
				// 10,105,110);
				// BufferedImage image128=ImageKit.zoomTo(cutImage, 128, 128);
				// BufferedImage image48=ImageKit.zoomTo(cutImage, 48, 48);
				// / BufferedImage image24=ImageKit.zoomTo(cutImage, 24, 24);
				// ImageKit.save(image128, "/home/zhao/下载/test/128.gif","jpg");
				// ImageKit.save(image48, "/home/zhao/下载/test/48.jpg","jpg");
				// ImageKit.save(image24, "/home/zhao/下载/test/24.jpg","jpg");

			}

		}
	}
}
