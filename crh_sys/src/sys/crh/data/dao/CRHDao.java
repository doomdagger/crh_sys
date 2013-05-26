package sys.crh.data.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import sys.crh.data.model.Crh;
import sys.crh.data.model.Engine;
import sys.crh.data.model.MTrain;
import sys.crh.data.model.RealTimeData;

public class CRHDao {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	
	public static final String queryForCrhNoWithCrhIdSQL = "SELECT FCRHNo FROM t_crh WHERE ID=:crhId";
	public static final String queryForCrhIdWithCrhNoSQL = "SELECT ID FROM t_crh WHERE FCRHNo=:crhNo";
	public static final String queryForCrhWithCrhIdSQL = "SELECT * FROM t_crh WHERE ID=:crhId";
	public static final String queryForAllSimpleCrh = "SELECT ID,FCRHNo FROM t_crh";
	public static final String queryForEngineNoWithEngineIdSQL = "SELECT FEngineID FROM t_engine WHERE ID=:engineId";
	public static final String queryForEngineIdWithEngineNoSQL = "SELECT ID FROM t_engine WHERE FEngineID=:engineNo AND id_t_crh=:crhId";
	public static final String queryForEngineWithEngineIdSQL = "SELECT * FROM t_engine WHERE ID=:engineId";
	public static final String queryForAllSimpleEngineWithCrhId = "SELECT ID,FEngineID FROM t_engine WHERE id_t_crh = :crhId";
	public static final String addRealTimeDataSQL = "INSERT INTO t_realtimedata (`idt_engine`, `idt_crh`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime`) " +
			"VALUES (:engineId,:crhId,:ybdianya,:ybdianliu,:kzdianya,:zjdianya,:djdianliu,:djpinlv,:qyzhidongli,:speed,:jiasudu,:temperature,:zdxinhao,:dzdianliu,:dateTime)";
	public static final String queryForRealTimeDataSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID";
	public static final String queryForRealTimeDataWithCrhNoSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND idt_engine = :engineId";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND FDatatime >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND FDatatime >= :startDate AND FDatatime <= :endDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND idt_engine = :engineId AND FDatatime >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND idt_engine = :engineId AND FDatatime >= :startDate AND FDatatime <= :endDate";
	
	public static final String queryForRealTimeDataWithStartDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND FDatatime >= :startDate";
	public static final String queryForRealTimeDataWithEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND FDatatime <= :endDate";
	public static final String queryForRealTimeDataWithStartDateAndEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND FDatatime >= :startDate AND FDatatime <= :endDate";
	
	public static final String queryForRealTimeDataWithCrhNoAndEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND FDatatime <= :endDate";
	
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndEndDateSQL = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND idt_engine = :engineId AND FDatatime <= :endDate";
	
	public static final String addCrhSQL="INSERT INTO `t_crh` (`FCRHNo`, `FCRHModelType`, `FCRHType`, `FFactory`, `FProduceDate`, `FRouteline`, `FRemark`, `FCreatetime`, `FEdittime`, `FCreator`, `FEditor`) " +
			"VALUES (:crhNo, :crhModelType, :crhType, :factory, :produceDate, :routeLine, :remark, :createTime, :editTime, :creator, :editor)";
	public static final String addEngineSQL="INSERT INTO `train_sys`.`t_engine` (`FEngineID`, `id_t_crh`, `FSetupType`, `FSuitTrain`, `FPower`, `Fvoltage`, `FNumber`, `FLineType`, `FJYRank`, `FCoolType`, `FWeight`, `FMaxrevolution`, `FWorkType`, `FTFQuality`, `FDiaTemper`, `FDiaSpeed`, `FProduceFac`, `FProduceDate`) " +
			"VALUES (:engineNo, :crhId, :setupType, :suitTrain, :power, :voltage, :number, :lineType, :jyRank, :coolType, :weight, :maxRevolution, :workType, :tfQuality, :diaTemper, :diaSpeed, :produceFactory, :produceDate)";
	
	
	public static final String fetchRealTimeDataWithCrhId = "SELECT t_realtimedata.ID,`FEngineID`, `FCRHNo`, `Fyb_dianya`, `Fyb_dianliu`, `Fkz_dianya`, `Fzj_dianya`, `Fdj_dianliu`, `Fdj_pinlv`, `Fzdxinhao`, `Fdzdianliu`, `Fqy_kongzhili`, `FSpeed`, `Fjiasudu`, `Ftemperature`, `FDatatime` FROM t_realtimedata,t_crh,t_engine WHERE t_realtimedata.idt_engine=t_engine.ID AND t_realtimedata.idt_crh=t_crh.ID AND idt_crh = :crhId AND FDatatime = (SELECT max(FDatatime) FROM t_realtimedata WHERE idt_crh = :crhId) ORDER BY t_realtimedata.ID DESC";
	
	//I have to add some methods for obtaining m_train
	public static final String queryForMTrainSQL = "SELECT * FROM m_train";
	public static final String queryForMTrainWithCrhType = "SELECT * FROM m_train WHERE FCRHType = :crhType";
	public static final String queryForAllCrhNo = "SELECT FCRHNo FROM t_crh";
	public static final String queryForAllEngineNoWithCrhId = "SELECT FEngineID FROM t_engine WHERE id_t_crh = :crhId";
	
	private NamedParameterJdbcTemplate template;
	public void setTemplate(NamedParameterJdbcTemplate template){
		this.template = template;
	}
	
	
	/*
	 * add train and engine
	 */
	public boolean addCrh(Crh crh){
		SqlParameterSource sps = new BeanPropertySqlParameterSource(crh);
		template.update(addCrhSQL, sps);
		return true;
	}
	
	public boolean addEngine(Engine engine){
		SqlParameterSource sps = new BeanPropertySqlParameterSource(engine);
		template.update(addEngineSQL, sps);
		return true;
	}
	
	/*
	 * serve for combo Box
	 */
	public List<String> queryForAllCrhNo(){
		SqlParameterSource sps = new MapSqlParameterSource();
		return template.queryForList(queryForAllCrhNo, sps, String.class);
	}
	
	public List<Crh> queryForAllSimpleCrh(){
		SqlParameterSource sps = new MapSqlParameterSource();
		return template.query(queryForAllSimpleCrh, sps, new RowMapper<Crh>(){
			@Override
			public Crh mapRow(ResultSet rs, int index) throws SQLException {
				Crh crh = new Crh();
				crh.setId(rs.getLong("ID"));
				crh.setCrhNo(rs.getString("FCRHNo"));
				return crh;
			}
			
		});
	}
	
	public List<String> queryForAllEngineNoWithCrhId(long crhId){
		SqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.queryForList(queryForAllEngineNoWithCrhId, sps, String.class);
	}
	
	public List<Engine> queryForAllSimpleEngineWithCrhId(long crhId){
		SqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.query(queryForAllSimpleEngineWithCrhId, sps, new RowMapper<Engine>(){

			@Override
			public Engine mapRow(ResultSet rs, int index) throws SQLException {
				Engine engine = new Engine();
				engine.setId(rs.getLong("ID"));
				engine.setEngineNo(rs.getString("FEngineID"));
				return engine;
			}
			
		});
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
	public long queryForEngineIdWithEngineNoAndCrhId(String engineNo,long crhId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("engineNo", engineNo).addValue("crhId", crhId);
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
	
	public boolean addBatchRealTimeData(List<RealTimeData> relDatas){
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
	public List<RealTimeData> queryForRealTimeData(){
		return template.query(queryForRealTimeDataSQL, new MapSqlParameterSource(), new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithStartDate(Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", start);
		return template.query(queryForRealTimeDataWithStartDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithEndDate(Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", end);
		return template.query(queryForRealTimeDataWithEndDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithStartDateAndEndDate(Date startDate, Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", start).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithStartDateAndEndDateSQL, sps, new RealTimeDataRowMapper());
	}

	public List<RealTimeData> queryForRealTimeDataWithCrhId(long crhId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.query(queryForRealTimeDataWithCrhNoSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineId(long crhId, long engineId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndStartDate(long crhId, Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start);
		return template.query(queryForRealTimeDataWithCrhNoAndStartDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndStartDateAndEndDate(long crhId, Date startDate, Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndStartDate(long crhId, long engineId, Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndStartDateAndEndDate(long crhId,long engineId, Date startDate,Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("engineId", engineId).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEndDate(long crhId, Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndEndDateSQL, sps, new RealTimeDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndEndDate(long crhId, long engineId, Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("endDate", end).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndEndDateSQL, sps, new RealTimeDataRowMapper());
	}
	
	
	public List<RealTimeData> fetchRealTimeDataWithCrhId(long crhId){
		int size = this.queryForAllEngineNoWithCrhId(crhId).size();
		String tempSQL = CRHDao.fetchRealTimeDataWithCrhId+" LIMIT "+size;
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.query(tempSQL, sps, new RealTimeDataRowMapper());
	}

	
	private class RealTimeDataRowMapper implements RowMapper<RealTimeData>{
		public RealTimeData mapRow(ResultSet rs, int index) throws SQLException{
			RealTimeData rel = new RealTimeData();
			rel.setId(rs.getLong("ID"));
			rel.setEngineNo(rs.getString("FEngineID"));
			rel.setCrhNo(rs.getString("FCRHNo"));
			rel.setYbdianya(rs.getDouble("Fyb_dianya"));
			rel.setYbdianliu(rs.getDouble("Fyb_dianliu"));
			rel.setKzdianya(rs.getDouble("Fkz_dianya"));
			rel.setZjdianya(rs.getDouble("Fzj_dianya"));
			rel.setDjdianliu(rs.getDouble("Fdj_dianliu"));
			rel.setDjpinlv(rs.getDouble("Fdj_pinlv"));
			rel.setZdxinhao(rs.getDouble("Fzdxinhao"));
			rel.setDzdianliu(rs.getDouble("Fdzdianliu"));
			rel.setQyzhidongli(rs.getDouble("Fqy_kongzhili"));
			rel.setSpeed(rs.getDouble("FSpeed"));
			rel.setJiasudu(rs.getDouble("Fjiasudu"));
			rel.setTemperature(rs.getDouble("Ftemperature"));
			rel.setDateTime(rs.getString("FDatatime"));
			return rel;
		}
	}
	
	public List<MTrain> queryForMTrain(){
		SqlParameterSource sps = new MapSqlParameterSource();
		return template.query(queryForMTrainSQL, sps, new RowMapper<MTrain>(){
			public MTrain mapRow(ResultSet rs, int index) throws SQLException{
				MTrain mtrain = new MTrain();
				mtrain.setId(rs.getLong("ID"));
				mtrain.setCrhType(rs.getString("FCRHType"));
				mtrain.setCrhModelType(rs.getString("FCRHModelType"));
				String temp = "Cart";
				short[] carts = new short[16];
				for(int i = 1; i <= 16; i++ ){
					carts[i-1] = (short)rs.getInt(temp+i);
				}
				mtrain.setCarts(carts);
				return mtrain;
			}
		});
	}
	
	public MTrain queryForMTrainWithCrhType(String crhType){
		SqlParameterSource sps = new MapSqlParameterSource().addValue("crhType", crhType);
		return template.queryForObject(queryForMTrainWithCrhType, sps, new RowMapper<MTrain>(){
			public MTrain mapRow(ResultSet rs, int index) throws SQLException{
				MTrain mtrain = new MTrain();
				mtrain.setId(rs.getLong("ID"));
				mtrain.setCrhType(rs.getString("FCRHType"));
				mtrain.setCrhModelType(rs.getString("FCRHModelType"));
				String temp = "Cart";
				short[] carts = new short[16];
				for(int i = 1; i <= 16; i++ ){
					carts[i-1] = (short)rs.getInt(temp+i);
				}
				mtrain.setCarts(carts);
				return mtrain;
			}
		});
	}
}

