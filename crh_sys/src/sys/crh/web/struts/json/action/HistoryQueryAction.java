package sys.crh.web.struts.json.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import sys.crh.data.service.CRHService;

import com.opensymphony.xwork2.Action;
@SuppressWarnings("rawtypes")
public class HistoryQueryAction {
	public String execute(){
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		System.out.println(startDate + "  " + endDate);
		try {
			this.setData(crhService.getHistoryQueryOutput(crhId, engineId, format.parse(startDate), format.parse(endDate)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	private Map[] data;
	public Map[] getData() {
		return data;
	}
	public void setData(Map[] data) {
		this.data = data;
	}

	private String startDate;
	private String endDate;
	private long crhId;
	private long engineId;
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
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
