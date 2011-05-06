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
import q.commons.http.JdkHttpClient;
import q.domain.Group;
import q.domain.People;
import q.domain.Weibo;
import q.log.Logger;

public class SolrSearchService implements SearchService {
	protected final Logger log = Logger.getLogger();

	private String searchUrl;

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	@Override
	public List<Long> searchWeibo(String query,int size) throws Exception {
		String urlTemp=URLEncoder.encode(query, "UTF-8");
		String sort=URLEncoder.encode("id desc", "UTF-8");
		String fq=URLEncoder.encode("id:[* TO ", "UTF-8");
		String httpUrl;
         httpUrl = searchUrl + "/solr/qweibo/select/?q=" + urlTemp + "&wt=json&sort="+sort+"&rows="+size;


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
			try{
			JdkHttpClient.releaseUrlConnection(con);
			}
			catch(Exception e){
				Logger.getLogger().error(e);
			}
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
	public String newXml(People people){
	     String []temp1=new String[3];
	     temp1[0]=String.valueOf(people.getId());
	     temp1[1]=String.valueOf(people.getUsername());
	     temp1[2]=String.valueOf(people.getRealName());
	     String format="<add><doc><field name=\"id\">%s</field><field name=\"name\">%s</field><field name=\"name\">%s</field></doc></add>";
	     return String.format(format, temp1);
	    }
	public String newXml(Group group){
	     String []temp1=new String[3];
	     temp1[0]=String.valueOf(group.getId());
	     temp1[1]=String.valueOf(group.getName());
	     temp1[2]=String.valueOf(group.getIntro());
	     String format="<add><doc><field name=\"id\">%s</field><field name=\"groupname\">%s</field><field name=\"intro\">%s</field></doc></add>";
	     return String.format(format, temp1);
	    }
	@Override
	public List<Long> searchPeople(String query) throws Exception {
		String urlTemp=URLEncoder.encode(query, "UTF-8");
		String httpUrl = searchUrl + "/solr/quser/select/?q=" + urlTemp + "&wt=json";
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
			try{
			JdkHttpClient.releaseUrlConnection(con);
			}
			catch(Exception e){
				Logger.getLogger().error(e);
			}
		}
		return bs;
	}

	@Override
	public void updatePeople(People data) throws Exception {
		try{
			String httpUrl = searchUrl + "/solr/quser/update?commit=true";
			URL temp = new URL(httpUrl);
			HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
			String message=newXml(data);
			JdkHttpClient.postString(con,message);
			}
			 catch(IOException e){
		            Logger.getLogger().error("Search Engine is out of use and the people "+data.getId());
		    }

	}
	@Override
	public void updateGroup(Group data) throws Exception {
		try{
			String httpUrl = searchUrl + "/solr/qgroup/update?commit=true";
			URL temp = new URL(httpUrl);
			HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
			String message=newXml(data);
			JdkHttpClient.postString(con,message);
			}
			 catch(IOException e){
		            Logger.getLogger().error("Search Engine is out of use and the group "+data.getId());
		    }

	}

	@Override
	public List<Long> searchGroup(String query) throws Exception {
		String urlTemp=URLEncoder.encode(query, "UTF-8");
		String httpUrl = searchUrl + "/solr/qgroup/select/?q=" + urlTemp + "&wt=json&qt=all";
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
			try{
			JdkHttpClient.releaseUrlConnection(con);
			}
			catch(Exception e){
				Logger.getLogger().error(e);
			}
		}
		return bs;
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
			try{
			JdkHttpClient.releaseUrlConnection(con);
			}
			catch(Exception e){
				Logger.getLogger().error(e);
			}
		}
		return bs;
	}
}
