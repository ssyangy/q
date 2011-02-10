package q.web;


public abstract class Resource {

	private String name;

	public abstract void execute(ResourceContext context);
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean validate(ResourceContext context) {
		return true; //default validatation is successful.
	}
	
}
