package sys.crh.web.struts.json.action;

import java.util.Map;

import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;

@SuppressWarnings("rawtypes")
public class CrhListAction {
	public String execute(){
		this.setData(crhService.getCrhList());
		
		return Action.SUCCESS;
	}
	
	
	private Map[] data;
	public void setData(Map[] data){
		this.data = data;
	}
	public Map[] getData(){
		return this.data;
	}
	
	
	private CRHService crhService;
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
}
