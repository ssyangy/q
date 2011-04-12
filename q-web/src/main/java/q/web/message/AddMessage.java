/**
 * 
 */
package q.web.message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import q.dao.MessageDao;
import q.dao.PeopleDao;
import q.domain.Message;
import q.domain.MessageJoinPeople;
import q.domain.People;
import q.util.ArrayKit;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class AddMessage extends Resource {
	protected MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	protected PeopleDao peopleDao;

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
		Message message = new Message();
		message.setId(IdCreator.getLongId());
		message.setContent(context.getString("content"));
		long senderId = context.getCookiePeopleId();
		message.setSenderId(senderId);
		String idsString = context.getString("receiverId");
		String[] receiverStringIds = StringKit.split(idsString, ',');
		Set<Long> receiverIds = IdCreator.convertIfValidIds(receiverStringIds);
		List<MessageJoinPeople> joins = new ArrayList<MessageJoinPeople>();
		for (Long receiverId : receiverIds) {
			MessageJoinPeople join = new MessageJoinPeople();
			join.setId(IdCreator.getLongId());
			join.setSenderId(senderId);
			join.setMessageId(message.getId());
			join.setReceiverId(receiverId);
			joins.add(join);
		}
		messageDao.addMessage(message);
		messageDao.addMessageJoinPeoples(joins);

		String from = context.getString("from");
		if (from != null) {
			context.redirectContextPath(from);
		}

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long senderId = context.getCookiePeopleId();
		if (senderId == 0)
			throw new RequestParameterInvalidException("loginId invalid");
		String[] receiverStringIds = StringKit.split(context.getString("receiverId"), ',');
		if (ArrayKit.isEmpty(receiverStringIds)) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		Set<Long> receiverIds = null;
		try {
			receiverIds = IdCreator.convertIfValidIds(receiverStringIds);
		} catch (Exception e) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		if (CollectionKit.isEmpty(receiverIds)) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}
		HashSet<Long> idSet = new HashSet<Long>(receiverIds);
		List<People> receivers = peopleDao.getPeoplesByIds(new ArrayList<Long>(idSet));
		if (CollectionKit.isEmpty(receivers) || receivers.size() != idSet.size()) {
			throw new RequestParameterInvalidException("receiver:invalid");
		}

	}

}
