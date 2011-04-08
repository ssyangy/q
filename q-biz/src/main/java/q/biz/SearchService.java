package q.biz;

import java.util.List;

import q.domain.Group;
import q.domain.People;
import q.domain.Weibo;

public interface SearchService {

	public List<Long> searchWeibo(String query) throws Exception;

	public void updateWeibo(Weibo data)throws Exception;

	public List<Long> searchPeople(String query) throws Exception;

	public void updatePeople(People data)throws Exception;

	public void updateGroup(Group data) throws Exception;

	public List<Long> searchGroup(String query) throws Exception;
}
