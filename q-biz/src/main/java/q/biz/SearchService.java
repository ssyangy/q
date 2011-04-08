package q.biz;

import java.util.List;

import q.domain.Weibo;

public interface SearchService {

	public List<Long> searchWeibo(String query) throws Exception;

	public void updateWeibo(Weibo data)throws Exception;

}
