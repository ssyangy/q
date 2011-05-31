package q.web.group;

import q.biz.PictureService;
import q.dao.GroupDao;
import q.domain.Group;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

public class AddGroupEdit extends Resource {
	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}



	@Override
	public void validate(ResourceContext context) throws Exception {

		double x1 = Double.parseDouble(context.getString("realx1"));
		double x2 = Double.parseDouble(context.getString("realx2"));
		double y1 = Double.parseDouble(context.getString("realy1"));
		double y2 = Double.parseDouble(context.getString("realy2"));
		Group group = new Group();
		if (y2 <= 0 || x2 <= 0) {
			throw new RequestParameterInvalidException("value:图片参数出错了。");
		}

		String SId = context.getString("groupId");

		long Id = 0;

		if (!StringKit.isBlank(SId)) {
			Id = Long.valueOf(SId);
			long dir = Id % 10000;
			String avatarPath = this.pictureService.getImageUrl()+ "/g/" + dir + "/" + String.valueOf(Id);
			boolean sb = pictureService.editGroupAvatar(x1, x2, y1, y2, avatarPath);
			if (sb == true) {
				if (Id != 0) {
					group.setAvatarPath(avatarPath);
					group.setId(Id);
					groupDao.updateGroup(group);

				}

			}

			else {
				throw new RequestParameterInvalidException("value:服务器忙。");
			}
		} else {
			throw new RequestParameterInvalidException("value:服务器忙。");
		}

	}
}
