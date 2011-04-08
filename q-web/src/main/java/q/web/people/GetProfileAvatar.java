package q.web.people;

import q.http.JdkHttpClient;
import q.web.Resource;
import q.web.ResourceContext;

public class GetProfileAvatar extends Resource {
	private String imageUrl;
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		// 传递一些大图，中图，小图地址的参数
		// 传递那张修改中的大图地址的参数

	}
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		String temp = loginPeopleId % 10000 + "/" + loginPeopleId;
		long peopleId = context.getCookiePeopleId();
		long dir = peopleId % 10000;
		if(JdkHttpClient.exists(imageUrl+ "/a/" + String.valueOf(dir) + "/" + String.valueOf(peopleId))){
			context.setModel("avatarExists", true);
		}
		else{
		    context.setModel("avatarExists", false);
		}
		context.setModel("avatarPath", temp);
	}

}
