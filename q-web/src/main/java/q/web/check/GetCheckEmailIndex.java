package q.web.check;

import q.dao.PeopleDao;
import q.web.Resource;
import q.web.ResourceContext;

public class GetCheckEmailIndex extends Resource{
	private PeopleDao peopleDao;
	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		String email=context.getString("email");
		log.debug("check email exists?",email);
		long result=this.peopleDao.getPeopleIdByEmail(email);
		if(result>0){
		context.setModel("exist", 1);
		}
		else{
	    context.setModel("exist", 0);	
		}
	}
   
}
