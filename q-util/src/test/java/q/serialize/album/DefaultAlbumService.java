/**
 * 
 */
package q.serialize.album;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultAlbumService.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author jiangyongyuan.tw : 用于Annotation测试
 */
public class DefaultAlbumService implements AlbumService {

	public AlbumResult addAlbum(Album album, int albumId, String sessionid) {
		AlbumResult r = new AlbumResult();
		album.setCreated(new Date());
		r.setItem(album);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.config.AlbumService#getAlbum(int)
	 */
	public Album getAlbum(int albumId, Integer ver) {
		Album a = new Album();
		return a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.config.AlbumService#getAlbums(java.lang
	 * .String, java.lang.String, int, int)
	 */
	public AlbumSet getAlbums(String loginNick, String nick, int pageSize,
			int pageNo) {
		AlbumSet aqr = new AlbumSet();
		return aqr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.config.AlbumService#isAvailable(int)
	 */
	public boolean valid(int albumId) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.album.AlbumService#addPicture(int, byte[])
	 */
	public void addPicture(int albumId, byte[] pic) {
		return;
	}

}
