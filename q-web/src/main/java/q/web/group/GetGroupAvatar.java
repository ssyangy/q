package q.web.group;

import java.util.List;

import q.dao.GroupDao;
import q.domain.Group;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class GetGroupAvatar extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		// 传递一些大图，中图，小图地址的参数
		// 传递那张修改中的大图地址的参数

	}
	@Override
	public void validate(ResourceContext context) throws Exception {

		long groupId = context.getResourceIdLong();
		Group group=groupDao.getGroupById(groupId);
		if( group.hasAvatar()){
			context.setModel("avatarExists", true);
		}
		else{
		    context.setModel("avatarExists", false);
		}
		context.setModel("avatarPath", group.getAvatarPath());
		context.setModel("group", group);
		long loginId = context.getCookiePeopleId();
		if (loginId > 0) {
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(loginId);
			context.setModel("groups", groups);
		}
	}
}
