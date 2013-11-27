package sys.crh.web.struts.action;

import java.util.Date;
import java.util.List;

import sys.crh.data.dao.CRHDao;
import sys.crh.data.model.RealTimeData;

import com.opensymphony.xwork2.Action;

public class HistoryDataQuery {
	public String execute(){
		
		
		return Action.SUCCESS;
	}
	private String crhNo;
	private String engineNo;
	private Date startDate;
	private Date endDate;
	
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	//result data
	private List<RealTimeData> realTimedatas;
	
	public List<RealTimeData> getRealTimedatas() {
		return realTimedatas;
	}

	public void setRealTimedatas(List<RealTimeData> realTimedatas) {
		this.realTimedatas = realTimedatas;
	}

	//crhDao Data Access Object
	private CRHDao crhDao;

	public CRHDao getCrhDao() {
		return crhDao;
	}

	public void setCrhDao(CRHDao crhDao) {
		this.crhDao = crhDao;
	}
	
}
