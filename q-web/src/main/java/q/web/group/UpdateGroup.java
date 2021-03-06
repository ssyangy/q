package q.web.group;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 *
 */
import java.util.ArrayList;
import java.util.List;

import q.biz.PictureService;
import q.biz.SearchService;
import q.dao.CategoryDao;
import q.dao.GroupDao;
import q.domain.Group;
import q.domain.GroupJoinCategory;
import q.domain.Status;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;
import q.web.exception.RequestParameterInvalidException;

/**
 * AddGroup action
 *
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 19, 2011
 *
 */
public class UpdateGroup extends Resource {
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

	/**
	 * @param categoryDao
	 *            the categoryDao to set
	 */
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * @return the categoryDao
	 */
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	private CategoryDao categoryDao;

	@Override
	public void execute(ResourceContext context) throws Exception {
		long groupId = context.getResourceIdLong();
		Group group = new Group();
		group.setId(groupId);
		group.setName(context.getString("name"));
		group.setIntro(context.getString("intro"));
		groupDao.updateGroup(group); // create group
		long categoryId = context.getIdLong("categoryId");
		List<GroupJoinCategory> joins = groupDao.getGroupJoinCategoriesByGroupIdAndStatus(groupId, null); // get joins ignore status
		boolean hasCat = false; // weather selected category is already exists
		GroupJoinCategory hasCatDeleted = null;
		List<Long> deleteJoinIds = new ArrayList<Long>();
		for (GroupJoinCategory join : joins) {
			if (join.getCategoryId() == categoryId) {
				hasCat = true;
				if (join.getStatus() == Status.DELETE.getValue()) {
					hasCatDeleted = join;
				}
			} else if (join.getStatus() == Status.COMMON.getValue()) {
				deleteJoinIds.add(join.getId());
			}
		}
		if (!hasCat) { // add new join
			groupDao.addGroupJoinCategory(group.getId(), categoryId);
		} else if (hasCatDeleted != null) { // reopen deleted and selected join
			groupDao.updateGroupJoinCategoryStatusByIdAndOldStatus(hasCatDeleted.getId(), Status.COMMON, Status.DELETE);
		}
		if (CollectionKit.isNotEmpty(deleteJoinIds)) {
			groupDao.deleteGroupJoinCategoriesByjoinIdsAndGroupId(group.getId(), deleteJoinIds); // delete old joins
		}

		searchService.updateGroup(group); // update search

		context.setModel("group", group);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long groupId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(groupId)) {
			throw new RequestParameterInvalidException("group:invalid");
		}
		Group group = groupDao.getGroupById(groupId);
		if (group == null) {
			throw new RequestParameterInvalidException("group:invalid");
		}
		if (group.getCreatorId() != loginPeopleId) {
			throw new PeopleNotPermitException();
		}
		GroupValidator.validateGroupName(context.getString("name"));
		GroupValidator.validateIntro(context.getString("intro"));

		long categoryId = context.getIdLong("categoryId");
		if (IdCreator.isNotValidId(categoryId)) {
			throw new RequestParameterInvalidException("category:invalid");
		}
		if (getCategoryDao().getCategoryById(categoryId) == null) {
			throw new RequestParameterInvalidException("category:invalid");
		}
	}

}