/**
 *
 */
package q.web.weibo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import q.dao.WeiboDao;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboFromType;
import q.http.JdkHttpClient;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class AddWeibo extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		Weibo weibo = new Weibo();
		weibo.setStatus(Status.COMMON.getValue());
		long senderId = context.getCookiePeopleId();
		weibo.setSenderId(senderId);
		String content = context.getString("content");
		weibo.setContent(content);
		long groupId = context.getIdLong("groupId");
		if (IdCreator.isValidIds(groupId)) {
			weibo.setFromType(WeiboFromType.GROUP);
			weibo.setFromId(groupId);
		}
		this.weiboDao.addWeibo(weibo);

		if (IdCreator.isValidIds(groupId)) {
			this.weiboDao.addWeiboJoinGroup(weibo.getId(), weibo.getSenderId(), groupId);
		}

		String from = context.getString("from");
/*
		URL temp = new URL("http://192.168.1.100:8080/solr/update");
		HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000);
		JdkHttpClient.postString(con,newXml(weibo));*/
		if (from != null) {
			context.redirectContextPath(from);
		} else {
			context.redirectServletPath("/weibo/" + weibo.getId());
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
	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (senderId <= 0) {
			throw new RequestParameterInvalidException("senderId");
		}

	}
}
