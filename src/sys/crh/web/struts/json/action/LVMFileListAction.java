package sys.crh.web.struts.json.action;

import java.util.Map;

import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;

@SuppressWarnings("rawtypes")
public class LVMFileListAction {
	public String execute(){
		this.setData(crhService.getLVMFileList());
		
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
