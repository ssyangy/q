package q.biz;

import java.util.List;

import q.domain.Group;
import q.domain.People;
import q.domain.Weibo;

public interface SearchService {

	public List<Long> searchPeople(String query, int type, int size) throws Exception;

	public List<Long> searchGroup(String query, int type, int size) throws Exception;

	public List<Long> searchWeibo(String query, int size) throws Exception;

	public void updateWeibo(Weibo data) throws Exception;

	public void updatePeople(People data) throws Exception;

	public void updateGroup(Group data);

	public void deleteWeiboById(Long id) throws Exception;

}
