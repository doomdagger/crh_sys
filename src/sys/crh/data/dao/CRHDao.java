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
	
	
	public static final String queryForCrhNoWithCrhIdSQL = "SELECT PLT_FCRHNO FROM PLT_DTU_RUSHTRAIN WHERE ID=:crhId";
	public static final String queryForCrhIdWithCrhNoSQL = "SELECT ID FROM PLT_DTU_RUSHTRAIN WHERE PLT_FCRHNO=:crhNo";
	public static final String queryForCrhWithCrhIdSQL = "SELECT * FROM PLT_DTU_RUSHTRAIN WHERE ID=:crhId";
	public static final String queryForAllSimpleCrh = "SELECT ID,PLT_FCRHNO FROM PLT_DTU_RUSHTRAIN";
	public static final String queryForEngineNoWithEngineIdSQL = "SELECT PLT_FENGINEID FROM PLT_DTU_ENGINE WHERE ID=:engineId";
	public static final String queryForEngineIdWithEngineNoSQL = "SELECT ID FROM PLT_DTU_ENGINE WHERE PLT_FENGINEID=:engineNo AND PLT_ID_T_CRH=:crhId";
	public static final String queryForEngineWithEngineIdSQL = "SELECT * FROM PLT_DTU_ENGINE WHERE ID=:engineId";
	public static final String queryForAllSimpleEngineWithCrhId = "SELECT ID,PLT_FENGINEID FROM PLT_DTU_ENGINE WHERE PLT_ID_T_CRH = :crhId";
	public static final String addRealTimeDataSQL = "INSERT INTO PLT_DTU_REALTIMEDATA (`PLT_IDT_ENGINE`, `PLT_IDT_CRH`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME`) " +
			"VALUES (:engineId,:crhId,:ybdianya,:ybdianliu,:kzdianya,:zjdianya,:djdianliu,:djpinlv,:qyzhidongli,:speed,:jiasudu,:temperature,:zdxinhao,:dzdianliu,:dateTime)";
	public static final String queryForRealTimeDataSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID LIMIT 400";
	public static final String queryForRealTimeDataWithCrhNoSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId LIMIT 400";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_IDT_ENGINE = :engineId";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_FDATATIME >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_FDATATIME >= :startDate AND PLT_FDATATIME <= :endDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_IDT_ENGINE = :engineId AND PLT_FDATATIME >= :startDate";
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_IDT_ENGINE = :engineId AND PLT_FDATATIME >= :startDate AND PLT_FDATATIME <= :endDate";
	
	public static final String queryForRealTimeDataWithStartDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_FDATATIME >= :startDate";
	public static final String queryForRealTimeDataWithEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_FDATATIME <= :endDate";
	public static final String queryForRealTimeDataWithStartDateAndEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_FDATATIME >= :startDate AND PLT_FDATATIME <= :endDate";
	
	public static final String queryForRealTimeDataWithCrhNoAndEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_FDATATIME <= :endDate";
	
	public static final String queryForRealTimeDataWithCrhNoAndEngineNoAndEndDateSQL = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_IDT_ENGINE = :engineId AND PLT_FDATATIME <= :endDate";
	
	public static final String addCrhSQL="INSERT INTO `PLT_DTU_RUSHTRAIN` (`PLT_FCRHNO`, `PLT_FCRHMODELTYPE`, `PLT_FCRHTYPE`, `PLT_FFACTORY`, `PLT_FPRODUCEDATE`, `PLT_FROUTELINE`, `PLT_FREMARK`, `PLT_FCREATETIME`, `PLT_FEDITTIME`, `PLT_FCREATOR`, `PLT_FEDITOR`) " +
			"VALUES (:crhNo, :crhModelType, :crhType, :factory, :produceDate, :routeLine, :remark, :createTime, :editTime, :creator, :editor)";
	public static final String addEngineSQL="INSERT INTO `PLT_DTU_ENGINE` (`PLT_FENGINEID`, `PLT_ID_T_CRH`, `PLT_FSETUPTYPE`, `PLT_FSUITTRAIN`, `PLT_FPOWER`, `PLT_FVOLTAGE`, `PLT_FNUMBER`, `PLT_FLINETYPE`, `PLT_FJYRANK`, `PLT_FCOOLTYPE`, `PLT_FWEIGHT`, `PLT_FMAXREVOLUTION`, `PLT_FWORKTYPE`, `PLT_FTFQUALITY`, `PLT_FDIATEMPER`, `PLT_FDIASPEED`, `PLT_FPRODUCEFAC`, `PLT_FPRODUCEDATE`) " +
			"VALUES (:engineNo, :crhId, :setupType, :suitTrain, :power, :voltage, :number, :lineType, :jyRank, :coolType, :weight, :maxRevolution, :workType, :tfQuality, :diaTemper, :diaSpeed, :produceFactory, :produceDate)";
	
	
	public static final String fetchRealTimeDataWithCrhId = "SELECT PLT_DTU_REALTIMEDATA.ID,`PLT_FENGINEID`, `PLT_FCRHNO`, `PLT_FYB_DIANYA`, `PLT_FYB_DIANLIU`, `PLT_FKZ_DIANYA`, `PLT_FZJ_DIANYA`, `PLT_FDJ_DIANLIU`, `PLT_FDJ_PINLV`, `PLT_FZDXINHAO`, `PLT_FDZDIANLIU`, `PLT_FQY_KONGZHILI`, `PLT_FSPEED`, `PLT_FJIASUDU`, `PLT_FTEMPERATURE`, `PLT_FDATATIME`, `PLT_FSTATUS` FROM PLT_DTU_REALTIMEDATA,PLT_DTU_RUSHTRAIN,PLT_DTU_ENGINE WHERE PLT_DTU_REALTIMEDATA.PLT_IDT_ENGINE=PLT_DTU_ENGINE.ID AND PLT_DTU_REALTIMEDATA.PLT_IDT_CRH=PLT_DTU_RUSHTRAIN.ID AND PLT_IDT_CRH = :crhId AND PLT_FDATATIME = (SELECT max(PLT_FDATATIME) FROM PLT_DTU_REALTIMEDATA WHERE PLT_IDT_CRH = :crhId) ORDER BY PLT_DTU_REALTIMEDATA.ID DESC";
	
	//I have to add some methods for obtaining PLT_DTU_CRHTYPE
	public static final String queryForMTrainSQL = "SELECT * FROM PLT_DTU_CRHTYPE";
	public static final String queryForMTrainWithCrhType = "SELECT * FROM PLT_DTU_CRHTYPE WHERE PLT_FCRHTYPE = :crhType";
	public static final String queryForAllCrhNo = "SELECT PLT_FCRHNO FROM PLT_DTU_RUSHTRAIN";
	public static final String queryForAllEngineNoWithCrhId = "SELECT PLT_FENGINEID FROM PLT_DTU_ENGINE WHERE PLT_ID_T_CRH = :crhId";
	
	
	public static final String updateEngineStatusWithCrhNoAndEngineNoSQL = "UPDATE PLT_DTU_ENGINE SET `PLT_FSTATUS`=:status,`PLT_FSTATUSCODE`=:statusCode,`PLT_FSTATUSDESCRIPTION`=:statusDescription WHERE `ID`=:engineId";
	
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
				crh.setCrhNo(rs.getString("PLT_FCRHNO"));
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
				engine.setEngineNo(rs.getString("PLT_FENGINEID"));
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
				crh.setCrhNo(rs.getString("PLT_FCRHNO"));
				crh.setCrhModelType(rs.getString("PLT_FCRHMODELTYPE"));
				crh.setCrhType(rs.getString("PLT_FCRHTYPE"));
				crh.setFactory(rs.getString("PLT_FFACTORY"));
				crh.setProduceDate(rs.getDate("PLT_FPRODUCEDATE"));
				crh.setRouteLine(rs.getString("PLT_FROUTELINE"));
				crh.setRemark(rs.getString("PLT_FREMARK"));
				crh.setCreateTime(rs.getDate("PLT_FCREATETIME"));
				crh.setEditTime(rs.getDate("PLT_FEDITTIME"));
				crh.setCreator(rs.getLong("PLT_FCREATOR"));
				crh.setEditor(rs.getLong("PLT_FEDITOR"));
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
				engine.setEngineNo(rs.getString("PLT_FENGINEID"));
				engine.setCrhId(rs.getLong("PLT_ID_T_CRH"));
				engine.setSetupType(rs.getString("PLT_FSETUPTYPE"));
				engine.setSuitTrain(rs.getString("PLT_FSUITTRAIN"));
				engine.setPower(rs.getInt("PLT_FPOWER"));
				engine.setVoltage(rs.getInt("PLT_FVOLTAGE"));
				engine.setNumber(rs.getInt("PLT_FNUMBER"));
				engine.setLineType(rs.getString("PLT_FLINETYPE"));
				engine.setJyRank(rs.getString("PLT_FJYRANK"));
				engine.setCoolType(rs.getString("PLT_FCOOLTYPE"));
				engine.setWeight(rs.getInt("PLT_FWEIGHT"));
				engine.setMaxRevolution(rs.getInt("PLT_FMAXREVOLUTION"));
				engine.setWorkType(rs.getString("PLT_FWORKTYPE"));
				engine.setTfQuality(rs.getString("PLT_FTFQUALITY"));
				engine.setDiaTemper(rs.getString("PLT_FDIATEMPER"));
				engine.setDiaSpeed(rs.getString("PLT_FDIASPEED"));
				engine.setProduceFactory(rs.getString("PLT_FPRODUCEFAC"));
				engine.setProduceDate(rs.getDate("PLT_FPRODUCEDATE"));
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
		return template.query(queryForRealTimeDataSQL, new MapSqlParameterSource(), new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithStartDate(Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", start);
		return template.query(queryForRealTimeDataWithStartDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithEndDate(Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", end);
		return template.query(queryForRealTimeDataWithEndDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithStartDateAndEndDate(Date startDate, Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("startDate", start).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithStartDateAndEndDateSQL, sps, new HistoryDataRowMapper());
	}

	public List<RealTimeData> queryForRealTimeDataWithCrhId(long crhId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		return template.query(queryForRealTimeDataWithCrhNoSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineId(long crhId, long engineId){
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndStartDate(long crhId, Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start);
		return template.query(queryForRealTimeDataWithCrhNoAndStartDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndStartDateAndEndDate(long crhId, Date startDate, Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndStartDateAndEndDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndStartDate(long crhId, long engineId, Date startDate){
		String start = format.format(startDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndStartDateAndEndDate(long crhId,long engineId, Date startDate,Date endDate){
		String start = format.format(startDate);
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("startDate", start).addValue("engineId", engineId).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndStartDateAndEndDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEndDate(long crhId, Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("endDate", end);
		return template.query(queryForRealTimeDataWithCrhNoAndEndDateSQL, sps, new HistoryDataRowMapper());
	}
	public List<RealTimeData> queryForRealTimeDataWithCrhIdAndEngineIdAndEndDate(long crhId, long engineId, Date endDate){
		String end = format.format(endDate);
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId).addValue("endDate", end).addValue("engineId", engineId);
		return template.query(queryForRealTimeDataWithCrhNoAndEngineNoAndEndDateSQL, sps, new HistoryDataRowMapper());
	}
	
	
	public List<RealTimeData> fetchRealTimeDataWithCrhId(long crhId){
		int size = this.queryForAllEngineNoWithCrhId(crhId).size();
		String tempSQL = CRHDao.fetchRealTimeDataWithCrhId+" LIMIT "+size;
		MapSqlParameterSource sps = new MapSqlParameterSource().addValue("crhId", crhId);
		List<RealTimeData> list = template.query(tempSQL, sps, new RealTimeDataRowMapper());
		ArrayList<RealTimeData> result = new ArrayList<RealTimeData>();
		for(RealTimeData rel : list){
			result.add(0, rel);
		}
		return result;
	}

	
	private class RealTimeDataRowMapper implements RowMapper<RealTimeData>{
		public RealTimeData mapRow(ResultSet rs, int index) throws SQLException{
			RealTimeData rel = new RealTimeData();
			rel.setId(rs.getLong("ID"));
			rel.setEngineNo(rs.getString("PLT_FENGINEID"));
			rel.setCrhNo(rs.getString("PLT_FCRHNO"));
			rel.setYbdianya(rs.getDouble("PLT_FYB_DIANYA"));
			rel.setYbdianliu(rs.getDouble("PLT_FYB_DIANLIU"));
			rel.setKzdianya(rs.getDouble("PLT_FKZ_DIANYA"));
			rel.setZjdianya(rs.getDouble("PLT_FZJ_DIANYA"));
			rel.setDjdianliu(rs.getDouble("PLT_FDJ_DIANLIU"));
			rel.setDjpinlv(rs.getDouble("PLT_FDJ_PINLV"));
			rel.setZdxinhao(rs.getDouble("PLT_FZDXINHAO"));
			rel.setDzdianliu(rs.getDouble("PLT_FDZDIANLIU"));
			rel.setQyzhidongli(rs.getDouble("PLT_FQY_KONGZHILI"));
			rel.setSpeed(rs.getDouble("PLT_FSPEED"));
			rel.setJiasudu(rs.getDouble("PLT_FJIASUDU"));
			rel.setTemperature(rs.getDouble("PLT_FTEMPERATURE"));
			rel.setDateTime(rs.getString("PLT_FDATATIME"));
			rel.setStatus(rs.getInt("PLT_FSTATUS"));
			return rel;
		}
	}
	
	private class HistoryDataRowMapper implements RowMapper<RealTimeData>{
		public RealTimeData mapRow(ResultSet rs, int index) throws SQLException{
			RealTimeData rel = new RealTimeData();
			rel.setId(rs.getLong("ID"));
			rel.setEngineNo(rs.getString("PLT_FENGINEID"));
			rel.setCrhNo(rs.getString("PLT_FCRHNO"));
			rel.setYbdianya(rs.getDouble("PLT_FYB_DIANYA"));
			rel.setYbdianliu(rs.getDouble("PLT_FYB_DIANLIU"));
			rel.setKzdianya(rs.getDouble("PLT_FKZ_DIANYA"));
			rel.setZjdianya(rs.getDouble("PLT_FZJ_DIANYA"));
			rel.setDjdianliu(rs.getDouble("PLT_FDJ_DIANLIU"));
			rel.setDjpinlv(rs.getDouble("PLT_FDJ_PINLV"));
			rel.setZdxinhao(rs.getDouble("PLT_FZDXINHAO"));
			rel.setDzdianliu(rs.getDouble("PLT_FDZDIANLIU"));
			rel.setQyzhidongli(rs.getDouble("PLT_FQY_KONGZHILI"));
			rel.setSpeed(rs.getDouble("PLT_FSPEED"));
			rel.setJiasudu(rs.getDouble("PLT_FJIASUDU"));
			rel.setTemperature(rs.getDouble("PLT_FTEMPERATURE"));
			rel.setDateTime(rs.getString("PLT_FDATATIME"));
			return rel;
		}
	}
	
	
	public List<MTrain> queryForMTrain(){
		SqlParameterSource sps = new MapSqlParameterSource();
		return template.query(queryForMTrainSQL, sps, new RowMapper<MTrain>(){
			public MTrain mapRow(ResultSet rs, int index) throws SQLException{
				MTrain mtrain = new MTrain();
				mtrain.setId(rs.getLong("ID"));
				mtrain.setCrhType(rs.getString("PLT_FCRHTYPE"));
				mtrain.setCrhModelType(rs.getString("PLT_FCRHMODELTYPE"));
				String temp = "PLT_CART";
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
				mtrain.setCrhType(rs.getString("PLT_FCRHTYPE"));
				mtrain.setCrhModelType(rs.getString("PLT_FCRHMODELTYPE"));
				String temp = "PLT_CART";
				short[] carts = new short[16];
				for(int i = 1; i <= 16; i++ ){
					carts[i-1] = (short)rs.getInt(temp+i);
				}
				mtrain.setCarts(carts);
				return mtrain;
			}
		});
	}
	
	public boolean updateEngineStatusWithCrhNoAndEngineNo(String crhNo, String engineNo, String status, int resultStatus){
		try{
			long crhId = this.queryForCrhIdWithCrhNo(crhNo);
			long engineId = this.queryForEngineIdWithEngineNoAndCrhId(engineNo, crhId);
			String statusDescription = "";
			
			char one = status.charAt(0);
			char two = status.charAt(1);
			char three = status.charAt(2);
			
			if(one=='1'){
				statusDescription = "这个电机运行良好。";
			}else if(two=='0'&&three=='1'){
				statusDescription = "电机出现B故障。";
			}else if(two=='0'&&three=='5'){
				statusDescription = "电机已有损耗，出现B故障趋势";
			}else if(two=='1'&&three=='0'){
				statusDescription = "电机出现A故障。";
			}else if(two=='1'&&three=='1'){
				statusDescription = "电机出现A,B故障。";
			}else if(two=='1'&&three=='5'){
				statusDescription = "电机出现A故障。且电机已有出现B故障趋势。";
			}else if(two=='5'&&three=='0'){
				statusDescription = "电机已有损耗，出现A故障趋势。";
			}else if(two=='5'&&three=='1'){
				statusDescription = "电机出现B故障。且电机已有出现A故障趋势。";
			}else if(two=='5'&&three=='5'){
				statusDescription = "电机已有损耗，出现A,B故障趋势。";
			}
			
			SqlParameterSource sps = new MapSqlParameterSource().addValue("status", resultStatus).addValue("statusCode", status).addValue("statusDescription", statusDescription).addValue("engineId", engineId);
			template.update(updateEngineStatusWithCrhNoAndEngineNoSQL, sps);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
}
