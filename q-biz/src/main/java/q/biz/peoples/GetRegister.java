package q.biz.peoples;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;


import org.apache.commons.validator.GenericValidator;

import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
public class GetRegister extends Resource{
private final static Logger log = Logger.getLogger();
	
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
	String uid = context.getResourceLastId();
   // People people=new People();
  //people.setEmail(context.getString("email"));
 // people.setPassword(context.getString("password"));
  //people.setUsername(context.getString("username"));
 // people.setRealName(context.getString("real_name"));
  //log.debug("get person's age:%s", people.getYear());
	//peopleDao.addPeople(people);
  /*people.setEmail("iceball8@163.com");
    people.setPassword("12345");
    people.setUsername("iceball");
    people.setRealName("赵哲豪");
    people.setId(3);
    people.setYear(1989);
    people.setMonth(3);
    people.setDay(17);
    people.setGender(Gender.MALE);
    people.setBloodTypeId(Byte.parseByte("1"));
    people.setEducationId(Byte.parseByte("1"));
    people.setRoleId(Byte.parseByte("1"));
    people.setCountryCode(132);
    people.setMobile(Long.parseLong("13501949215"));
    people.setLoginToken("xxxx");
    people.setStatus(Byte.parseByte("0"));
    people.setCreated(new Date());
    people.setModified(new Date());
    */
    
}
}
