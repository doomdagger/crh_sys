package sys.crh.web.struts.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;

import sys.crh.data.model.MTrain;
import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;

public class TestAction implements ApplicationAware{
	public String execute(){
		this.str = (String)application.get("message");
		trainInfo = crhService.getMTrainWithCrhNo("CRH380A-6601L");
		return Action.SUCCESS;
	}
	
	//Spring auto-injection service object
	private CRHService crhService;
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
	
	private Map<String, Object> application;
	private String str;
	public MTrain trainInfo;
	
	public MTrain getTrainInfo() {
		return trainInfo;
	}
	public void setTrainInfo(MTrain trainInfo) {
		this.trainInfo = trainInfo;
	}

	@SuppressWarnings("rawtypes")
	private List list;
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	
}
