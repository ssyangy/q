/**
 * 
 */
package q.serialize.album;

// TODO: Auto-generated Javadoc
/**
 * 相册服务演示.
 * 
 * @version 2009-2-16
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public interface AlbumService {

	/**
	 * 根据相册id获取相册.
	 * 
	 * @param albumId the album id
	 * @param ver the ver
	 * 
	 * @return the album
	 */
	Album getAlbum(int albumId, Integer ver);

	/**
	 * 根据相册拥有人昵称获取相册分页列表.
	 * 
	 * @param loginNick 登录的人
	 * @param nick 相册的主人
	 * @param pageSize the page size
	 * @param pageNo the page no
	 * 
	 * @return the albums
	 */
	AlbumSet getAlbums(String loginNick, String nick, int pageSize,
			int pageNo);

	/**
	 * 添加相册.
	 * 
	 * @param album the album
	 * @param albumId the album id
	 * 
	 * @return the album result
	 */
	AlbumResult addAlbum(Album album, int albumId,String sessionId);

	/**
	 * Adds the picture.
	 * 
	 * @param albumId the album id
	 * @param pic the pic
	 */
	void addPicture(int albumId, byte[] pic);

	/**
	 * 相册是否有效.
	 * 
	 * @param albumId the album id
	 * 
	 * @return true, if valid
	 */
	boolean valid(int albumId);

}
