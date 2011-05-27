package q.web;

import q.log.Logger;

public abstract class Resource {
	protected static final Logger log = Logger.getLogger();

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

	public abstract void validate(ResourceContext context) throws Exception;

}
