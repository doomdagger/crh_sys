package sys.crh.data.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.Date;

import sys.crh.data.model.RealTimeData;
import sys.crh.data.model.Crh;
import sys.crh.data.model.Engine;

public class CRHDao {
	public static final String queryForCrhNoWithCrhIdSQL = "";
	public static final String queryForCrhIdWithCrhNoSQL = "";
	public static final String queryForCrhWithCrhIdSQL = "";
	public static final String queryForEngineNoWithEngineIdSQL = "";
	public static final String queryForEngineIdWithEngineNoSQL = "";
	public static final String queryForEngineWithEngineIdSQL = "";
	public static final String addRealTimeDataSQL = "";
	public static final String queryForRealTimeDataSQL = "";
	public static final String queryForRealTimeDataWithCrhNoSQL = "";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoSQL = "";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateSQL = "";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL = "";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL = "";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL = "";


	
	private NamedParameterJdbcTemplate template;
	public void setTemplate(NamedParameterJdbcTemplate template){
		this.template = template;
	}
	
	/*
	 * util:get crhNo With crhId and inverse
	 * util:also the crh object
	 */
	public String queryForCrhNoWithCrhId(long crhId){
		
		return null;
	}
	public long queryForCrhIdWithCrhNo(String crhNo){
		
		return 0;
	}
	public Crh queryForCrhWithCrhId(long crhId){
		
		return null;
	}
	
	/*
	 * util:get engineNo with engineId and inverse
	 * util: also the engine object
	 */
	public String queryForEngineNoWithEngineId(long engineId){
		
		return null;
	}
	public long queryForEngineIdWithEngineNo(String engineNo){
		
		return 0;
	}
	public Engine queryForEngineWithEngineId(long engineId){
		
		return null;
	}
	
	
	
	
	/*
	 * first model: real time data model
	 * second model: real curve data model
	 */
	public boolean addRealTimeData(RealTimeData relData){
		
		return false;
	}
	
	public boolean addBatchRealTimeData(ArrayList<RealTimeData> relDatas){
		
		return false;
	}
	
	/*
	 * third model: history data model
	 */
	public ArrayList<RealTimeData> queryForRealTimeData(){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNo(String crhNo){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNoAndEngineNo(String crhNo, String engineNo){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNoAndStartDate(String crhNo, Date startDate){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNoAndStartDateAndEndDate(String crhNo, Date startDate){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNoAndEngineNoAndStartDate(String crhNo, String engineNo, Date startDate){
		
		return null;
	}
	public ArrayList<RealTimeData> queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDate(String crhNo,String engineNo, Date startDate,Date endDate){
		
		return null;
	}
}



































