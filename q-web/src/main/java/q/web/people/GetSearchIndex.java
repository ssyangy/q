package q.web.people;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
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

	@Override
	public void execute(ResourceContext context) throws Exception {
		String search=context.getString("search");
		if(search!=null&search!=""){
        String httpUrl="http://192.168.1.100:8080/solr/select/?q="+search+"&wt=json";
        URL temp = new URL(httpUrl);
        HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
        List<Long>bs=getIds(JdkHttpClient.getSearch(con));
        if(bs.size()>0){
        List<Weibo> weibos = weiboDao.getWeibosByIds(bs, true);
        DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
        context.setModel("weibos", weibos);
        }
		}
	}
    public List<Long> getIds(BufferedReader br ) throws IOException{
    	 String line = null;
         StringBuilder sb = new StringBuilder();
         while ((line = br.readLine()) != null) {
             sb.append(line);
         }
    	List<Long>temp=new ArrayList<Long>();
    	JSONObject jsonObject = JSONObject.fromObject(sb.toString());
    	JSONObject inner=jsonObject.getJSONObject("response");
    	JSONArray ids=inner.getJSONArray("docs");
        for(int i=0;i<ids.size();i++){
           JSONObject bs=(JSONObject) ids.get(i);
           String id=bs.getString("id");
           temp.add(Long.valueOf(id));
        }
    	return temp;
    }
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
