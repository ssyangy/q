/**
 *
 */
package q.web.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.dao.DaoHelper;
import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.dao.page.MessageReplyJoinPeoplePage;
import q.domain.MessageReply;
import q.util.CollectionKit;
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
public class GetMessageReply extends Resource {
	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
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
		long mid = context.getResourceIdLong();
		MessageReplyJoinPeoplePage joinPage = new MessageReplyJoinPeoplePage();
		joinPage.setQuoteMessageId(mid);
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId", IdCreator.MAX_ID);
		int type = context.getInt("type", 0);
		int asc = 1;
		if (type == asc) { // 1 indicate asc
			joinPage.setDesc(false);
		} else {
			joinPage.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		joinPage.setSize(fetchSize);
		joinPage.setStartId(startId);
		List<Long> replyIds = this.messageDao.getMessageReplyIdsByJoinPage(joinPage);

		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(replyIds)) {
			if (replyIds.size() == fetchSize) {
				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
				replyIds.remove(replyIds.size() - 1);// remove last one
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId < IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}
			List<MessageReply> replies = this.messageDao.getMessageRepliesByIds(replyIds);
			if (CollectionKit.isNotEmpty(replies)) {
				DaoHelper.injectMessageRepliesWithSenderAndReceivers(peopleDao, replies);
				api.put("replies", replies);
				api.put("hasPrev", hasPrev);
				api.put("hasNext", hasNext);
			}
		}
		context.setModel("api", api);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long mid = context.getResourceIdLong();
		if (IdCreator.isNotValidId(mid)) {
			throw new RequestParameterInvalidException("message:invalid");
		}
	}

}
