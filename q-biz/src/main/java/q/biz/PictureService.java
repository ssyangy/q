package q.biz;

import java.io.InputStream;

public interface PictureService {

	public boolean rotate(String url, int rotate) throws Exception;

	public String uploadWeiboPictures(InputStream picture) throws Exception;

	public String uploadAvatar(InputStream picture, long peopleId, long size, String type) throws Exception;

	public boolean editAvatar(double x1, double x2, double y1, double y2, long peopleId) throws Exception;

	public boolean hasAvatar(long peopleId);

	public String getImageUrl();

	public String getImageUploadUrl();

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
}
