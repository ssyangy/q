package q.web.people;

import q.web.Resource;
import q.web.ResourceContext;

public class GetProfileAvatar extends Resource {

	@Override
	public void execute(ResourceContext context) throws Exception {
		// 传递一些大图，中图，小图地址的参数
		// 传递那张修改中的大图地址的参数

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		String temp = loginPeopleId % 10000 + "/" + loginPeopleId;
		context.setModel("avatarPath", temp);
	}

}
