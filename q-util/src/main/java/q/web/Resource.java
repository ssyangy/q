package q.web;

import q.log.Logger;

public abstract class Resource {
	protected final Logger log = Logger.getLogger();

	private String name;

	public abstract void execute(ResourceContext context) throws Exception;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	private String redirectPath;

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}

	public boolean validate(ResourceContext context) {
		return true; // default validatation is successful.
	}

}
