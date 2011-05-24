package q.web.group;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 *
 */
import q.biz.PictureService;
import q.biz.SearchService;
import q.dao.GroupDao;
import q.domain.Group;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * AddGroup action
 *
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 19, 2011
 *
 */
public class AddGroup extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		Group group = new Group();
		group.setId(IdCreator.getLongId());
		long loginId = context.getCookiePeopleId();
		group.setCreatorId(loginId); // set group creator id from cookie
		group.setName(context.getString("name"));
		group.setIntro(context.getString("intro"));
		if (context.getString("latitude") != null && context.getString("longitude") != null) {
			group.setLatitude(Double.parseDouble(context.getString("latitude")));
			group.setLongitude(Double.parseDouble(context.getString("longitude")));
		}
		group.setAvatarPath(pictureService.getDefaultGroupAvatarPath());

		groupDao.addGroup(group); // create group
		groupDao.addGroupJoinCategory(group.getId(), context.getIdLong("categoryId")); // set group category
		groupDao.addPeopleJoinGroup(group.getCreatorId(), group.getId());
		groupDao.incrGroupJoinNumByGroupId(group.getId());
		searchService.updateGroup(group);

		if(context.isApiRequest()){
			context.setModel("group", group);
		} else {
			context.redirectServletPath("/group/" + group.getId());
		}

	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long categoryId = context.getIdLong("categoryId");
		if(IdCreator.isNotValidId(categoryId)) {
			throw new RequestParameterInvalidException("category:invalid");
		}

	}
}