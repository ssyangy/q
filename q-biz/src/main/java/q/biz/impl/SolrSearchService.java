package q.biz.impl;

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
import q.http.JdkHttpClient;

public class SolrSearchService implements SearchService {
	private String searchUrl;


	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}
	@Override
	public List<Long> searchWeibo(String query)throws Exception {
		 String httpUrl=searchUrl+"/solr/select/?q="+query+"&wt=json";
	        URL temp = new URL(httpUrl);
	        HttpURLConnection con=null;
	        List<Long>bs;
	        try{
	        con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
	        bs=getIds(JdkHttpClient.getSearch(con));
	        }
	        finally{
					JdkHttpClient.releaseUrlConnection(con);
	        }
            return bs;
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
}
