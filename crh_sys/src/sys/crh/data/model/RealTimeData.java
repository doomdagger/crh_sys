package sys.crh.data.model;
  
import java.util.*;

public class RealTimeData {
	private long id;
	private long engineId;
	private String crhNo;
	private double ybdianya;
	private double ybdianliu;
	private double kzdianya;
	private double zjdianya;
	private double djdianliu;
	private double djpinlv;
	private double qyzhidongli;
	private double speed;
	private double jiasudu;
	private double temperature;
	private double zdxinhao;
	private double dzdianliu;
	private Date dateTime;
	
	public RealTimeData(){}
	public RealTimeData(long id, double temperature, double speed){
		this.id = id;
		this.temperature = temperature;
		this.speed = speed;
	}
	public RealTimeData(long id, long engineId, String crhNo, double ybdianya, double ybdianliu, double kzdianya, double zjdianya,
			double djdianliu, double djpinlv, double qyzhidongli, double speed, double jiasudu, 
			double temperature, double zdxinhao, double dzdianliu, Date dateTime){
		this.id = id;
		this.engineId = engineId;
		this.crhNo = crhNo;
		this.ybdianya = ybdianya;
		this.ybdianliu = ybdianliu;
		this.kzdianya = kzdianya;
		this.zjdianya = zjdianya;
		this.djdianliu = djdianliu;
		this.djpinlv = djpinlv;
		this.qyzhidongli = qyzhidongli;
		this.jiasudu = jiasudu;
		this.temperature = temperature;
		this.speed = speed;
		this.zdxinhao = zdxinhao;
		this.dzdianliu = dzdianliu;
		this.dateTime = dateTime;
	}
	
	public void setDateTime(Date dateTime){
		this.dateTime = dateTime;
	}
	public Date getDateTime(){
		return this.dateTime;
	}
	
	public long getEngineId(){
		return this.engineId;
	}
	
	public void setEngineId(long engineId){
		this.engineId = engineId;
	}
	
	public String getCrhNo(){
		return this.crhNo;
	}
	public void setCrhNo(String crhNo){
		this.crhNo = crhNo;
	}
	
	public void setZdxinhao(double zdxinhao){
		this.zdxinhao = zdxinhao;
	}
	public double getZdxinhao(){
		return this.zdxinhao;
	}
	public void setDzdianliu(double dzdianliu){
		this.dzdianliu = dzdianliu;
	}
	public double getDzdianliu(){
		return dzdianliu;
	}
	
	public void setYbdianya(double ybdianya){
		this.ybdianya = ybdianya;
	}
	public double getYbdianya(){
		return ybdianya;
	}
	
	public void setYbdianliu(double ybdianliu){
		this.ybdianliu = ybdianliu;
	}
	public double getYbdianliu(){
		return ybdianliu;
	}
	
	public void setKzdianya(double kzdianya){
		this.kzdianya = kzdianya;
	}
	public double getKzdianya(){
		return kzdianya;
	}
	
	public void setZjdianya(double zjdianya){
		this.zjdianya = zjdianya;
	}
	public double getZjdianya(){
		return zjdianya;
	}
	
	public void setDjdianliu(double djdianliu){
		this.djdianliu = djdianliu;
	}
	public double getDjdianliu(){
		return djdianliu;
	}
	
	public void setDjpinlv(double djpinlv){
		this.djpinlv = djpinlv;
	}
	public double getDjpinlv(){
		return djpinlv;
	}
	
	public void setQyzhidongli(double qyzhidongli){
		this.qyzhidongli = qyzhidongli;
	}
	public double getQyzhidongli(){
		return qyzhidongli;
	}
	
	public void setJiasudu(double jiasudu){
		this.jiasudu = jiasudu;
	}
	public double getJiasudu(){
		return jiasudu;
	}
	
	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return id;
	}
	public void setTemperature(double temperature){
		this.temperature = temperature;
	}
	public double getTemperature(){
		return temperature;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	public double getSpeed(){
		return speed;
	}
	
}
