package q.web.people;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import q.biz.PictureService;
import q.commons.image.ImageKit;
import q.util.StringKit;
import q.web.DefaultResourceContext;
import q.web.Resource;
import q.web.ResourceContext;

public class AddAvatar extends Resource {
	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		HttpServletRequest request = ((DefaultResourceContext) context).getRequest();
		List fileItems = pictureService.getUploadParameter().parseRequest(request);
		Iterator iter = fileItems.iterator();
		for (; iter.hasNext();) {
			FileItem fileItem = (FileItem) iter.next();
			if (fileItem.isFormField()) {
				// 当前是一个表单项
			} else {
				if(fileItem.getSize()>2097152l){
                    context.setModel("value", "上传图片的体积超过2M");
				}
				else{
				long peopleId = context.getCookiePeopleId();
				long dir = peopleId % 10000;
				// 当前是一个上传的文件
				String fileName = fileItem.getName();
				String typeString = fileName.substring(fileName.lastIndexOf(".") + 1);
				String type = pictureService.getType(typeString);
				 if(StringKit.isBlank(type)){
					context.setModel("isImg", false);
				}
				if (ImageKit.isImage(fileItem.getInputStream())) {
					context.setModel("isImg", true);
					long size=fileItem.getSize();
					String sb=pictureService.uploadAvatar(fileItem.getInputStream(), peopleId, size, type);
					String[] data = sb.split(";");
					String place = data[1].substring(data[1].indexOf(":") + 1);
					context.setModel("imgHeight", ImageKit.load(fileItem.getInputStream()).getHeight());
					context.setModel("imgWidth", ImageKit.load(fileItem.getInputStream()).getWidth());
					context.setModel("imgPath", this.pictureService.getImageUrl() + place);
					context.setModel("value", "上传成功");
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

	@Override
	public void validate(ResourceContext context) throws Exception {

	}
}
