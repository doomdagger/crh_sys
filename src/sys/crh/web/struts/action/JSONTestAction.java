package sys.crh.web.struts.action;

import com.opensymphony.xwork2.Action;

public class JSONTestAction {
	public String execute(){
		this.setName("LIHE");
		this.setAge(19);
		return Action.SUCCESS;
	}
	
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
