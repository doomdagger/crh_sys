package sys.crh.web.struts.json.action;

import java.util.Date;
import java.util.Map;

import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;
@SuppressWarnings("rawtypes")
public class HistoryQueryAction {
	public String execute(){
		this.setData(crhService.getHistoryQueryOutput(crhId, engineId, startDate, endDate));
		
		return Action.SUCCESS;
	}
	
	private Map[] data;
	public Map[] getData() {
		return data;
	}
	public void setData(Map[] data) {
		this.data = data;
	}

	private Date startDate;
	private Date endDate;
	private long crhId;
	private long engineId;
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setCrhId(long crhId) {
		this.crhId = crhId;
	}
	public void setEngineId(long engineId) {
		this.engineId = engineId;
	}
	
	private CRHService crhService;
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
	
}
