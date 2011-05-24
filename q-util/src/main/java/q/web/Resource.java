package q.web;

import q.commons.domain.DomainIdAscComparator;
import q.commons.domain.DomainIdDescComparator;
import q.log.Logger;

public abstract class Resource {
	protected static final Logger log = Logger.getLogger();

	protected static final DomainIdDescComparator idDescComparator = new DomainIdDescComparator();

	protected static final DomainIdAscComparator idAscComparator = new DomainIdAscComparator();

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
