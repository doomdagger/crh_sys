package sys.crh.data.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import sys.crh.data.dao.CRHDao;
import sys.crh.data.io.CRHFileHandler;
import sys.crh.data.model.Crh;
import sys.crh.data.model.Engine;
import sys.crh.data.model.GroupRealTimeData;
import sys.crh.data.model.MTrain;
import sys.crh.data.model.RealTimeData;

public class CRHService  implements ServletContextAware{
	private CRHDao dao;
	private CRHFileHandler handler;
	private ServletContext context;
	
	private List<MTrain> mtrains;
	
	public void setDao(CRHDao dao){
		this.dao = dao;
	}
	public void setHandler(CRHFileHandler handler){
		this.handler = handler;
	}
	
	public void init(){
		this.mtrains = dao.queryForMTrain();
		try {
			context.setAttribute("onData", this.loadFile().get(0));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("CRHService : In Init method, load method throws IOExceptione");
		}
		this.fetchAndPersistFileData();
	}
	
	public boolean fetchAndPersistFileData(){
		List<GroupRealTimeData> datas = null;
		try {
			datas =  this.loadFile();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		if(datas == null){
			return false;
		}
		
		long crhId;
		long engineId;
		
		Iterator<GroupRealTimeData> iter = datas.iterator();
		while(iter.hasNext()){
			GroupRealTimeData group = iter.next();
			List<RealTimeData> data = group.getDatas();
			
			
			crhId = dao.queryForCrhIdWithCrhNo(data.get(0).getCrhNo());
			engineId = dao.queryForEngineIdWithEngineNoAndCrhId(data.get(0).getEngineNo(),crhId);
			
			Iterator<RealTimeData> iter2 = data.iterator();
			while(iter2.hasNext()){
				RealTimeData relTemp = iter2.next();
				relTemp.setCrhId(crhId);
				relTemp.setEngineId(engineId);
			}
			
			dao.addBatchRealTimeData(data);
		}
		
		return true;
	}
	
	public List<GroupRealTimeData> loadFile() throws IOException{
		File file = handler.nextFile();
		if(file == null){
			System.err.println("CRHService["+(new Date())+"] : No File To Load!");
			return null;
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		
		ArrayList<GroupRealTimeData> datas = new ArrayList<GroupRealTimeData>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

		while((line = reader.readLine()) != null){
			GroupRealTimeData group = new GroupRealTimeData();
			ArrayList<RealTimeData> subdatas = new ArrayList<RealTimeData>();
			String crhNo = line.trim();
			Date tempDate = new Date();
			String tempStr = format.format(tempDate);
			while(!(line = reader.readLine().trim()).equals("")){
				RealTimeData data = new RealTimeData();
				data.setCrhNo(crhNo);
				data.setEngineNo(line.substring(0, line.indexOf(' ')));
				String[] values = line.substring(line.indexOf(' ')+1).split(",");
				for(int n = 0; n < values.length; n++){
					if(values[n]==null || values[n].equals(""))
						values[n] = "0";
				}
				data.setYbdianya(Double.valueOf(values[0]));
				data.setYbdianliu(Double.valueOf(values[1]));
				data.setKzdianya(Double.valueOf(values[2]));
				data.setZjdianya(Double.valueOf(values[3]));
				data.setDjdianliu(Double.valueOf(values[4]));
				data.setDjpinlv(Double.valueOf(values[5]));
				data.setZdxinhao(Double.valueOf(values[6]));
				data.setDzdianliu(Double.valueOf(values[7]));
				data.setQyzhidongli(Double.valueOf(values[8]));
				data.setSpeed(Double.valueOf(values[9]));
				data.setJiasudu(Double.valueOf(values[10]));
				data.setTemperature(Double.valueOf(values[11]));
				data.setDateTime(tempStr);
				subdatas.add(data);
			}
			group.setDatas(subdatas);
			datas.add(group);
		}
		reader.close();
		
		return datas;
	}
	
	public RealTimeData prepareRealTimeDataForPersist(RealTimeData data){
		long crhId = dao.queryForCrhIdWithCrhNo(data.getCrhNo());
		data.setCrhId(crhId);
		data.setEngineId(dao.queryForEngineIdWithEngineNoAndCrhId(data.getEngineNo(),crhId));
		return data;
	}
	
	public MTrain getMTrainWithCrhNo(String crhNo){
		String type = crhNo.substring(0, crhNo.indexOf('-'));
		if(type.length() == 4){
			type += crhNo.charAt(crhNo.length()-1);
		}else{
			if(Character.isLetter(crhNo.charAt(crhNo.length()-1)))
				type += crhNo.charAt(crhNo.length()-1);
		}
		
		for(MTrain mtrain : mtrains){
			if(mtrain.getCrhType().equals(type))
				return mtrain;
		}
		
		return null;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public ServletContext getServletContext(){
		return this.context;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] tranverseRealTimeDatasToJSONType(List<RealTimeData> datas){
		
		Map[] maps = new HashMap[datas.size()];
		int i  = 0;
		for(RealTimeData data : datas){
			Map map = new HashMap();
			map.put("engineNo", data.getEngineNo());
			map.put("crhNo", data.getCrhNo());
			map.put("ybdianya", data.getYbdianya());
			map.put("ybdianliu", data.getYbdianliu());
			map.put("kzdianya", data.getKzdianya());
			map.put("zjdianya", data.getZjdianya());
			map.put("djdianliu", data.getDjdianliu());
			map.put("djpinlv", data.getDjpinlv());
			map.put("qyzhidongli", data.getQyzhidongli());
			map.put("speed", data.getSpeed());
			map.put("jiasudu", data.getJiasudu());
			map.put("temperature", data.getTemperature());
			map.put("zdxinhao", data.getZdxinhao());
			map.put("dzdianliu", data.getDzdianliu());
			maps[i++] = map;
		}
		return maps;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getCrhList(){
		List<Crh> list = dao.queryForAllSimpleCrh();
		Map[] maps = new HashMap[list.size()];
		int i = 0;
		for(Crh crh : list){
			Map temp = new HashMap();
			temp.put("crhId", crh.getId());
			temp.put("crhNo", crh.getCrhNo());
			maps[i++] = temp;
		}
		return maps;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map[] getEngineList(long crhId){
		List<Engine> list = dao.queryForAllSimpleEngineWithCrhId(crhId);
		Map[] maps = new HashMap[list.size()];
		int i = 0;
		for(Engine engine : list){
			Map temp = new HashMap();
			temp.put("id", engine.getId());
			temp.put("engineNo", engine.getEngineNo());
			maps[i++] = temp;
		}
		return maps;
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] getHistoryQueryOutput(long crhId, long engineId, Date startDate, Date endDate){
		List<RealTimeData> list = null;
		if(crhId == 0 && engineId ==0 && startDate == null && endDate == null){
			list = dao.queryForRealTimeData();
		}
		else if(crhId == 0 && engineId ==0 && startDate != null && endDate == null){
			list = dao.queryForRealTimeDataWithStartDate(startDate);
		}
		else if(crhId == 0 && engineId ==0 && startDate == null && endDate != null){
			list = dao.queryForRealTimeDataWithEndDate(endDate);
		}
		else if(crhId == 0 && engineId ==0 && startDate != null && endDate != null){
			list = dao.queryForRealTimeDataWithStartDateAndEndDate(startDate, endDate);
		}
		else if(crhId != 0 && engineId ==0 && startDate == null && endDate == null){
			list = dao.queryForRealTimeDataWithCrhId(crhId);
		}
		else if(crhId != 0 && engineId !=0 && startDate == null && endDate == null){
			list = dao.queryForRealTimeDataWithCrhIdAndEngineId(crhId, engineId);
		}
		else if(crhId != 0 && engineId ==0 && startDate != null && endDate == null){
			list = dao.queryForRealTimeDataWithCrhIdAndStartDate(crhId, startDate);
		}
		else if(crhId != 0 && engineId ==0 && startDate != null && endDate != null){
			list = dao.queryForRealTimeDataWithCrhIdAndStartDateAndEndDate(crhId, startDate, endDate);
		}
		else if(crhId != 0 && engineId !=0 && startDate != null && endDate == null){
			list = dao.queryForRealTimeDataWithCrhIdAndEngineIdAndStartDate(crhId, engineId, startDate);
		}
		else if(crhId != 0 && engineId !=0 && startDate != null && endDate != null){
			list = dao.queryForRealTimeDataWithCrhIdAndEngineIdAndStartDateAndEndDate(crhId, engineId, startDate, endDate);
		}
		else if(crhId != 0 && engineId ==0 && startDate == null && endDate != null){
			list = dao.queryForRealTimeDataWithCrhIdAndEndDate(crhId, endDate);
		}
		else if(crhId != 0 && engineId !=0 && startDate == null && endDate != null){
			list = dao.queryForRealTimeDataWithCrhIdAndEngineIdAndEndDate(crhId, engineId, endDate);
		}


		return this.tranverseRealTimeDatasToJSONType(list);
	}
	
	@SuppressWarnings("rawtypes")
	public Map[] fetchRealTimeDataWithCrhId(long crhId){
		return this.tranverseRealTimeDatasToJSONType(dao.fetchRealTimeDataWithCrhId(crhId));
	}
}
