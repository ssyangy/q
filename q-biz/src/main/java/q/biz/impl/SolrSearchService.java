package q.biz.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import q.biz.SearchService;
import q.domain.Weibo;
import q.http.JdkHttpClient;
import q.log.Logger;

public class SolrSearchService implements SearchService {
	protected final Logger log = Logger.getLogger();

	private String searchUrl;

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	@Override
	public List<Long> searchWeibo(String query) throws Exception {
		String urlTemp=URLEncoder.encode(query, "UTF-8");
		String httpUrl = searchUrl + "/solr/qweibo/select/?q=" + urlTemp + "&wt=json";
		URL temp = new URL(httpUrl);
		HttpURLConnection con = null;
		List<Long> bs = null;
		try {
			con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
			BufferedReader br=JdkHttpClient.getSearch(con);
			if(br!=null){
			bs = getIds(br);
			}
		} catch (Exception e) {
			log.error("search engine fail:", e);
		} finally {
			JdkHttpClient.releaseUrlConnection(con);
		}
		return bs;
	}

	public List<Long> getIds(BufferedReader br) throws IOException {
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		List<Long> temp = new ArrayList<Long>();
		JSONObject jsonObject = JSONObject.fromObject(sb.toString());
		JSONObject inner = jsonObject.getJSONObject("response");
		JSONArray ids = inner.getJSONArray("docs");
		for (int i = 0; i < ids.size(); i++) {
			JSONObject bs = (JSONObject) ids.get(i);
			String id = bs.getString("id");
			if(StringUtils.isNumeric(id)){
			temp.add(Long.valueOf(id));
			}
		}
		return temp;
	}

	@Override
	public void updateWeibo(Weibo data) throws Exception {
		try{
			String httpUrl = searchUrl + "/solr/qweibo/update?commit=true";
			URL temp = new URL(httpUrl);
			HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
			JdkHttpClient.postString(con,newXml(data));
			}
			 catch(IOException e){
		            Logger.getLogger().error("Search Engine is out of use and the weibo "+data.getId());
		    }
	}
	public ArrayList<String> people(String Content){
		 ArrayList<String>names=new ArrayList<String>();
		 int start=0;
		 while(true){
          int tempStart=Content.indexOf("@",start);
          if(tempStart<0){
          	break;
          }
          int tempEnd=Content.indexOf(" ",tempStart);
          if(tempEnd<0){
          	names.add(Content.substring(tempStart));
          	break;
          }
          names.add(Content.substring(tempStart, tempEnd));
          start=tempEnd;
		 }
		 return names;
	}
	public String newXml(Weibo weibo){
	     ArrayList<String>people=people(weibo.getContent());
	     Object[]temp2=people.toArray();
	     int x=temp2.length;
	     String []temp1=new String[2+x];
	     temp1[0]=String.valueOf(weibo.getId());
	     temp1[1]=weibo.getContent();
	     System.arraycopy(temp2, 0, temp1, 2, temp2.length);
	     String format="<add><doc><field name=\"id\">%s</field><field name=\"qcontent\">%s</field>";
	     for(int i=0;i<x;i++){
	    	  format=format+ "<field name=\"qrelated\">%s</field>";
	     }
	     format=format+"</doc></add>";
	     return String.format(format, temp1);
	    }
}
