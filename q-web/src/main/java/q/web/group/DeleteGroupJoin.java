/**
 *
 */
package q.web.group;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Status;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 18, 2011
 *
 */
public class DeleteGroupJoin extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginId = context.getCookiePeopleId();
		long groupId = context.getResourceIdLong();
		int rowEffected = groupDao.updatePeopleJoinGroupStatusByPeopleIdAndGroupIdAndOldStatus(loginId, groupId, Status.DELETE, Status.COMMON);
		if (rowEffected > 0) {
			groupDao.decrGroupJoinNumByGroupId(groupId);
			peopleDao.decrPeopleGroupNumberByPeopleId(context.getCookiePeopleId());
		}
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
	}

}
