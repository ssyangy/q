package q.web.people;

import q.dao.PeopleDao;
import q.domain.People;
import q.http.JdkHttpClient;
import q.web.Resource;
import q.web.ResourceContext;

public class GetProfileAvatar extends Resource {
	private String imageUrl;
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		// 传递一些大图，中图，小图地址的参数
		// 传递那张修改中的大图地址的参数

	}
	@Override
	public void validate(ResourceContext context) throws Exception {

		long loginPeopleId = context.getCookiePeopleId();
		People people=peopleDao.getPeopleById(loginPeopleId);
		long peopleId = context.getCookiePeopleId();
		long dir = peopleId % 10000;
		if( people.hasAvatar()){
			context.setModel("avatarExists", true);
		}
		else{
		    context.setModel("avatarExists", false);
		}
		context.setModel("avatarPath", people.getAvatarPath());
	}

}
