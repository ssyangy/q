package q.biz;

import java.io.InputStream;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
public interface PictureService {

	public boolean rotate(String url, int rotate) throws Exception;

	public String uploadWeiboPictures(InputStream picture,String type) throws Exception;

	public String uploadAvatar(InputStream picture, long peopleId, long size, String type) throws Exception;

	public String uploadGroupPicture(InputStream picture, long peopleId, long size, String type) throws Exception;

	public boolean editAvatar(double x1, double x2, double y1, double y2, long peopleId) throws Exception;

	public boolean editGroupAvatar(double x1, double x2, double y1, double y2,String path)throws Exception;

	public boolean hasAvatar(long peopleId);

    public ServletFileUpload getUploadParameter();

	public String getImageUrl();

	public String getImageUploadUrl();

	public String getType(String type);
	/**
	 * @return
	 */
	public String getDefaultFemaleAvatarPath();

	/**
	 * @return
	 */
	public String getDefaultMaleAvatarPath();

	/**
	 * @return
	 */
	public String getDefaultGroupAvatarPath();

	/**
	 * @return
	 */
	public String getDefaultCategoryAvatarPath();

	/**
	 * @param content
	 * @return
	 */
	String replaceBiaoqing(String content);
}
