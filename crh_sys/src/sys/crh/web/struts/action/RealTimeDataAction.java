package sys.crh.web.struts.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;

import sys.crh.data.model.GroupRealTimeData;
//import sys.crh.data.model.RealTimeData;
import sys.crh.data.service.CRHService;

@SuppressWarnings("rawtypes")
public class RealTimeDataAction implements ApplicationAware{
	public static final String GROUP_DATA = "groupData";
	public static final String CRH_TYPE = "crhType";
	
	//action method one
	public String groupData(){
		//this.setJsonModel(((GroupRealTimeData)(application.get("onData"))).getDatas().toArray(new RealTimeData[0]));
		this.setData(crhService.fetchRealTimeDataWithCrhId(crhId));
		this.setCrhNo(((GroupRealTimeData)(application.get("onData"))).getDatas().get(0).getCrhNo());
		this.setModelType(crhService.getMTrainWithCrhNo(this.getCrhNo()).getCarts());
		return GROUP_DATA;
	}
	
	//action method two
	public String crhType(){
		//this.setJsonModel(crhService.getMTrainWithCrhNo(this.getCrhNo()));
		return CRH_TYPE;
	}

	
	private Map[] data;	
	public Map[] getData() {
		return data;
	}
	public void setData(Map[] data) {
		this.data = data;
	}

	private short[] modelType;	
	public short[] getModelType() {
		return modelType;
	}

	public void setModelType(short[] modelType) {
		this.modelType = modelType;
	}


	//crhNo, receive this value to determine the crhType
	private String crhNo;
	public void setCrhNo(String crhNo){
		this.crhNo = crhNo;
	}
	public String getCrhNo(){
		return crhNo;
	}
	
	private long crhId;
	public void setCrhId(long crhId){
		this.crhId = crhId;
	}
	
	//business object
	private Map<String, Object> application;
	private CRHService crhService;
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
	
	//jsonModel, for result
	/*
	private Object jsonModel;
	public void setJsonModel(Object jsonModel){
		this.jsonModel = jsonModel;
	}
	public Object getJsonModel(){
		return jsonModel;
	}
	*/
	
}
