package sys.crh.data.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import sys.crh.data.model.RealTimeData;
import sys.crh.data.model.Crh;
import sys.crh.data.model.Engine;

public class CRHDao {
	public static final String queryForCrhNoWithCrhIdSQL = "SELECT FCRHNo FROM t_crh WHERE ID=:crhId";
	public static final String queryForCrhIdWithCrhNoSQL = "SELECT ID FROM t_crh WHERE FCRHNo=:crhNo";
	public static final String queryForCrhWithCrhIdSQL = "SELECT * FROM t_crh WHERE ID=:crhId";
	public static final String queryForEngineNoWithEngineIdSQL = "SELECT FEngineID FROM t_engine WHERE ID=:engineId";
	public static final String queryForEngineIdWithEngineNoSQL = "SELECT ID FROM t_engine WHERE FEngineID=:engineNo";
	public static final String queryForEngineWithEngineIdSQL = "SELECT * FROM t_engine WHERE ID=:engineId";
	public static final String addRealTimeDataSQL = "INSERT INTO t_realtimedata (`idt_engine`, `idt_crh`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime`) " +
			"VALUES (:engineId,:crhId,:ybdianya,:ybdianliu,:kzdianya,:zjdianya,:djdianliu,:djpinlv,:qyzhidongli,:speed,:jiasudu,:temperature,:zdxinhao,:dzdianliu,:dateTime);";
	public static final String queryForRealTimeDataSQL = "SELECT * FROM t_realtimedata";
	public static final String queryForRealTimeDataWithCrhNoSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId AND idt_engine = :engineId";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId AND FDatatime >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId AND FDatatime >= :startDate AND FDatatime <= :endDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId AND idt_engine = :engineId AND FDatatime >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL = "SELECT * FROM t_realtimedata WHERE idt_crh = :crhId AND idt_engine = :engineId AND FDatatime >= :startDate AND FDatatime <= :endDate";

	public static final String addCrhSQL="";
	
	private NamedParameterJdbcTemplate template;
	public void setTemplate(NamedParameterJdbcTemplate template){
		this.template = template;
	}
	
	/*
	 * util:get crhNo With crhId and inverse
	 * util:also the crh object
	 */
	public String queryForCrhNoWithCrhId(long crhId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.queryForObject(queryForCrhNoWithCrhIdSQL, sps, String.class);
	}
	public long queryForCrhIdWithCrhNo(String crhNo){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhNo", crhNo);
		return template.queryForLong(queryForCrhIdWithCrhNoSQL, sps);
	}
	public Crh queryForCrhWithCrhId(long crhId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.queryForObject(queryForCrhWithCrhIdSQL, sps, new RowMapper<Crh>(){
			public Crh mapRow(ResultSet rs, int index) throws SQLException {
				Crh crh = new Crh();
				crh.setId(rs.getLong("ID"));
				crh.setCrhNo(rs.getString("FCRHNo"));
				crh.setCrhModelType(rs.getString("FCRHModelType"));
				crh.setCrhType(rs.getString("FCRHType"));
				crh.setFactory(rs.getString("FFactory"));
				crh.setProduceDate(rs.getDate("FProduceDate"));
				crh.setRouteLine(rs.getString("FRouteline"));
				crh.setRemark(rs.getString("FRemark"));
				crh.setCreateTime(rs.getDate("FCreatetime"));
				crh.setEditTime(rs.getDate("FEdittime"));
				crh.setCreator(rs.getLong("FCreator"));
				crh.setEditor(rs.getLong("FEditor"));
				return crh;
			}
		});
	}
	
	/*
	 * util:get engineNo with engineId and inverse
	 * util: also the engine object
	 */
	public String queryForEngineNoWithEngineId(long engineId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("engineId", engineId);
		return template.queryForObject(queryForEngineNoWithEngineIdSQL, sps, String.class);
	}
	public long queryForEngineIdWithEngineNo(String engineNo){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("engineNo", engineNo);
		return template.queryForLong(queryForEngineIdWithEngineNoSQL, sps);
	}
	public Engine queryForEngineWithEngineId(long engineId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("engineId", engineId);
		return template.queryForObject(queryForEngineWithEngineIdSQL, sps, new RowMapper<Engine>(){
			public Engine mapRow(ResultSet rs, int index) throws SQLException {
				Engine engine = new Engine();
				engine.setId(rs.getLong("ID"));
				engine.setEngineNo(rs.getString("FEngineID"));
				engine.setCrhId(rs.getLong("id_t_crh"));
				engine.setSetupType(rs.getString("FSetupType"));
				engine.setSuitTrain(rs.getString("FSuitTrain"));
				engine.setPower(rs.getInt("FPower"));
				engine.setVoltage(rs.getInt("Fvoltage"));
				engine.setNumber(rs.getInt("FNumber"));
				engine.setLineType(rs.getString("FLineType"));
				engine.setJyRank(rs.getString("FJYRank"));
				engine.setCoolType(rs.getString("FCoolType"));
				engine.setWeight(rs.getInt("FWeight"));
				engine.setMaxRevolution(rs.getInt("FMaxrevolution"));
				engine.setWorkType(rs.getString("FWorkType"));
				engine.setTfQuality(rs.getString("FTFQuality"));
				engine.setDiaTemper(rs.getString("FDiaTemper"));
				engine.setDiaSpeed(rs.getString("FDiaSpeed"));
				engine.setProduceFactory(rs.getString("FProduceFac"));
				engine.setProduceDate(rs.getDate("FProduceDate"));
				return engine;
			}
		});
	}
	
	
	
	
	/*
	 * first model: real time data model
	 * second model: real curve data model
	 */
	public boolean addRealTimeData(RealTimeData relData){
		SqlParameterSource sps = new BeanPropertySqlParameterSource(relData);
		template.update(addRealTimeDataSQL, sps);
		return true;
	}
	
	public boolean addBatchRealTimeData(ArrayList<RealTimeData> relDatas){
		ArrayList<SqlParameterSource> list = new ArrayList<SqlParameterSource>();
		for(RealTimeData rel : relDatas){
			SqlParameterSource sps = new BeanPropertySqlParameterSource(rel);
			list.add(sps);
		}
		template.batchUpdate(addRealTimeDataSQL, list.toArray(new SqlParameterSource[0]));
		return true;
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



































