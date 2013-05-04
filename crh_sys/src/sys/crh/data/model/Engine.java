package sys.crh.data.model;

import java.util.*;

public class Engine {
	private long id;
	private String engineId;
	private long crhId;
	private String setupType;
	private String suitTrain;
	private int power;
	private int voltage;
	private int number;
	private String lineType;
	private String jyRank;
	private String coolType;
	private int weight;
	private int maxRevolution;
	private String workType;
	private String tfQuality;
	private String diaTemper;
	private String diaSpeed;
	private String produceFactory;
	private Date produceDate;
	
	public Engine() {}
	
	public Engine(long id, String engineId, long crhId, String setupType,
			String suitTrain, int power, int voltage, int number,
			String lineType, String jyRank, String coolType, int weight,
			int maxRevolution, String workType, String tfQuality,
			String diaTemper, String diaSpeed, String produceFactory,
			Date produceDate) {
		this.id = id;
		this.engineId = engineId;
		this.crhId = crhId;
		this.setupType = setupType;
		this.suitTrain = suitTrain;
		this.power = power;
		this.voltage = voltage;
		this.number = number;
		this.lineType = lineType;
		this.jyRank = jyRank;
		this.coolType = coolType;
		this.weight = weight;
		this.maxRevolution = maxRevolution;
		this.workType = workType;
		this.tfQuality = tfQuality;
		this.diaTemper = diaTemper;
		this.diaSpeed = diaSpeed;
		this.produceFactory = produceFactory;
		this.produceDate = produceDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	public long getCrhId() {
		return crhId;
	}
	public void setCrhId(long crhId) {
		this.crhId = crhId;
	}
	public String getSetupType() {
		return setupType;
	}
	public void setSetupType(String setupType) {
		this.setupType = setupType;
	}
	public String getSuitTrain() {
		return suitTrain;
	}
	public void setSuitTrain(String suitTrain) {
		this.suitTrain = suitTrain;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getVoltage() {
		return voltage;
	}
	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLineType() {
		return lineType;
	}
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	public String getJyRank() {
		return jyRank;
	}
	public void setJyRank(String jyRank) {
		this.jyRank = jyRank;
	}
	public String getCoolType() {
		return coolType;
	}
	public void setCoolType(String coolType) {
		this.coolType = coolType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getMaxRevolution() {
		return maxRevolution;
	}
	public void setMaxRevolution(int maxRevolution) {
		this.maxRevolution = maxRevolution;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getTfQuality() {
		return tfQuality;
	}
	public void setTfQuality(String tfQuality) {
		this.tfQuality = tfQuality;
	}
	public String getDiaTemper() {
		return diaTemper;
	}
	public void setDiaTemper(String diaTemper) {
		this.diaTemper = diaTemper;
	}
	public String getDiaSpeed() {
		return diaSpeed;
	}
	public void setDiaSpeed(String diaSpeed) {
		this.diaSpeed = diaSpeed;
	}
	public String getProduceFactory() {
		return produceFactory;
	}
	public void setProduceFactory(String produceFactory) {
		this.produceFactory = produceFactory;
	}
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	
	
	
	
}
