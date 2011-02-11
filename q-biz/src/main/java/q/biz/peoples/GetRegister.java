package q.biz.peoples;
import java.io.*;


import org.apache.commons.validator.GenericValidator;

import q.web.Resource;
import q.web.ResourceContext;
public class GetRegister extends Resource{

@Override
public void execute(ResourceContext context) {
	String uid = context.getResourceLastId();
	String time = "1";
	context.setModel("time", time);	
}
}
