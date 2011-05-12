/**
 * 
 */
package q.web.message;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import q.commons.pinyin.Pinyin;
import q.dao.PeopleDao;
import q.domain.People;
import q.util.CollectionKit;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public class GetMessageNew extends AddMessage {
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
		long receiverId = context.getIdLong("receiverId");
		long replyMessageId = context.getIdLong("replyMessageId");

		People receiver = peopleDao.getPeopleById(receiverId);
		context.setModel("receiver", receiver);
		context.setModel("replyMessageId", replyMessageId);
		long loginPeopleId = context.getCookiePeopleId();

		List<Long> followingIds = peopleDao.getAllFollowingId(loginPeopleId);
		List<People> followings = peopleDao.getPeoplesByIds(followingIds);
		String peoplesHintJson = this.getPeoplesHintJson(followings);
		context.setModel("peoplesHintJson", peoplesHintJson);
	}

	/**
	 * [{ value: 'jin zi cheng', img: '1', name: '金梓成'},{ value: 'han han', img: '1', name: '韩寒'}]
	 * 
	 * @param peoples
	 * @return
	 */
	private String getPeoplesHintJson(List<People> peoples) {
		if (CollectionKit.isEmpty(peoples)) {
			return "[]";
		}
		StringBuilder buffer = new StringBuilder(peoples.size() * 32);
		buffer.append("[");
		Iterator<People> iterator = peoples.iterator();
		People first = iterator.next();
		appendPeople(buffer, first);
		while (iterator.hasNext()) {
			People people = iterator.next();
			if (people != null) {
				buffer.append(',');
				appendPeople(buffer, people);
			}
		}
		buffer.append("]");
		return buffer.toString();
	}

	private void appendPeople(StringBuilder buffer, People people) {
		buffer.append("{value:\"");
		Set<String> pinyins = Pinyin.getPinyin(people.getRealName());
		if (CollectionKit.isNotEmpty(pinyins)) {
			buffer.append(StringUtils.join(pinyins, ' '));
		}
		buffer.append(people.getUsername());
		buffer.append("\",");
		buffer.append("avatarPath:\"");
		buffer.append(people.getAvatarPath());
		buffer.append("\",");
		buffer.append("username:\"");
		buffer.append(people.getUsername());
		buffer.append("\",");
		buffer.append("realName:\"");
		buffer.append(people.getRealName());
		buffer.append("\",");
		buffer.append("id:\"");
		buffer.append(people.getId());
		buffer.append("\"}");
	}

}
