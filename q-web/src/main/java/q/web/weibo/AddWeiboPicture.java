package q.web.weibo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import q.http.JdkHttpClient;
import q.util.IdCreator;
import q.util.ImageKit;
import q.web.DefaultResourceContext;
import q.web.Resource;
import q.web.ResourceContext;

public class AddWeiboPicture extends Resource{
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
				long picId=IdCreator.getLongId();
				String dir=Long.toString(picId% 10000, Character.MAX_RADIX);
                String name=Long.toString(picId, Character.MAX_RADIX);				// 当前是一个上传的文件
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
				if (ImageKit.isImage(fileItem.getInputStream())) {
					context.setModel("isImg", true);
					BufferedImage originImage = ImageKit.load(fileItem.getInputStream());
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
                    	context.setModel("value", "服务器忙");
                    }
                    else{
					String[] data = sb.split(";");
					String place = data[1].substring(data[1].indexOf(":") + 1);
					context.setModel("imgHeight", ImageKit.load(fileItem.getInputStream()).getHeight());
					context.setModel("imgWidth", ImageKit.load(fileItem.getInputStream()).getWidth());
					context.setModel("imgPath", imageUrl + place);
					context.setModel("value", "上传成功");
                    }
				}
				else{

				}

			}
			}

	}
}
