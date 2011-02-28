/**
 * 
 */
package q.web.message;

import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 *
 */
public class GetMessageNew extends Resource {
	private PeopleDao peopleDao;
	
	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long senderId = context.getLoginPeopleId();
		if(senderId < 0 ) {
			throw new IllegalStateException();
		}
		long receiverId = context.getIdLong("receiverId");
		People receiver = peopleDao.getPeopleById(receiverId);
		context.setModel("receiver", receiver);
	}

}
