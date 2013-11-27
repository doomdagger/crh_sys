package sys.crh.web.struts.json.action;

import java.util.Map;

import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;

@SuppressWarnings("rawtypes")
public class MatlabComputeAction {
	public String execute(){
		this.setData(crhService.computeMatlab(crhNo, engineNo, getFileName()));
		
		return Action.SUCCESS;
	}
	
	
	private Map[] data;
	public void setData(Map[] data){
		this.data = data;
	}
	public Map[] getData(){
		return this.data;
	}
	
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String crhNo;
	private String engineNo;

	public String getCrhNo() {
		return crhNo;
	}
	public void setCrhNo(String crhNo) {
		this.crhNo = crhNo;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}


	private CRHService crhService;
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
}
