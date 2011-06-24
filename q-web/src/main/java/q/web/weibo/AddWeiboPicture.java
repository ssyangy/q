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
import q.util.StringKit;
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
		List fileItems = pictureService.getUploadParameter().parseRequest(request);
		Iterator iter = fileItems.iterator();
		for (; iter.hasNext();) {
			FileItem fileItem = (FileItem) iter.next();
			if (fileItem.isFormField()) {
				// 当前是一个表单项
			} else {
				// 当前是一个上传的文件
				String fileName = fileItem.getName();
				String typeString = fileName.substring(fileName.lastIndexOf(".") + 1);

				if(fileItem.getSize()>2097152l){
                       context.setModel("value", "上传图片的体积超过2M");
				}else{
					String type = pictureService.getType(typeString);
					 if(StringKit.isBlank(type)){
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
