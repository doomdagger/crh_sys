package sys.crh.web.struts.json.action;

import java.util.Date;

import com.opensymphony.xwork2.Action;

public class HistoryQueryAction {
	public String execute(){
		if(crhId==0){
			
		}
		
		
		return Action.SUCCESS;
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
	
}
