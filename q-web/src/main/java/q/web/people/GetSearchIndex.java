package q.web.people;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.domain.WeiboModel;
import q.http.JdkHttpClient;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
public class GetSearchIndex extends Resource{
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		String search=context.getString("search");
		List<? extends WeiboModel> weibos = null;
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		if(startId!=0){
			search=search+" "+"AND id:[* TO "+startId+"]";
		}
		if(search!=null&search!=""){
	        List<Long>bs=searchService.searchWeibo(search,size);
	        if(bs!=null){
	        if(bs.size()>0){
	        weibos = weiboDao.getWeibosByIds(bs, true);
	        DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
	        context.setModel("weibos", weibos);

	        }
	        }
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
