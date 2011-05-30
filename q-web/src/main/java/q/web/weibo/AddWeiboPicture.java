package q.web.weibo;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import q.biz.PictureService;
import q.commons.image.ImageKit;
import q.web.DefaultResourceContext;
import q.web.Resource;
import q.web.ResourceContext;

public class AddWeiboPicture extends Resource{
	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
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
				// 当前是一个上传的文件
				String fileName = fileItem.getName();
				String typeString = fileName.substring(fileName.lastIndexOf(".") + 1);
				String type = "";
				if(fileItem.getSize()>2907152l){
                       context.setModel("value", "上传图片的体积超过2M");
				}else{
				if (typeString.equals("jpg") || typeString.equals("jpeg")|| typeString.equals("JPEG")|| typeString.equals("JPG")) {
					type = "image/jpeg";
				} else if (typeString.equals("png")|| typeString.equals("PNG")) {
					type = "image/png";
				} else if (typeString.equals("gif")|| typeString.equals("GIF")) {
					type = "image/gif";
				} else {

					context.setModel("isImg", false);
				}
				if (ImageKit.isImage(fileItem.getInputStream())) {

					String result=pictureService.uploadWeiboPictures(fileItem.getInputStream(),type);
                    if(result.equals("false")){
                    	context.setModel("value", "服务器忙");
                    }
                    else{
					String[] data = result.split(";");
					String place = data[1].substring(data[1].indexOf(":") + 1);
					context.setModel("imgHeight", ImageKit.load(fileItem.getInputStream()).getHeight());
					context.setModel("imgWidth", ImageKit.load(fileItem.getInputStream()).getWidth());
					context.setModel("imgPath", this.pictureService.getImageUrl() + place);
					context.setModel("value", "上传成功");
                    }
				}
				else{

				}

			}
			}
		}
	}
}
