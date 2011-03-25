package q.web.people;

import q.web.Resource;
import q.web.ResourceContext;

public class AddAvatar extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		context.setModel("imgHeight", 100);
		context.setModel("imgWidth", 100);
		context.setModel("imgPath", "http://www.q.net/a/1.png");
        context.setModel("isImg", true);
	}

}
