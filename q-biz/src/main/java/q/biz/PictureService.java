package q.biz;

import java.io.InputStream;

public interface PictureService {

	public boolean rotate(String url,int rotate)throws Exception;

	public String uploadWeiboPictures(InputStream picture)throws Exception;


}
