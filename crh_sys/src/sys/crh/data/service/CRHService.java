package sys.crh.data.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sys.crh.data.dao.CRHDao;
import sys.crh.data.io.CRHFileHandler;
import sys.crh.data.model.GroupRealTimeData;
import sys.crh.data.model.MTrain;
import sys.crh.data.model.RealTimeData;

public class CRHService {
	private CRHDao dao;
	private CRHFileHandler handler;
	
	private List<MTrain> mtrains;
	
	public void setDao(CRHDao dao){
		this.dao = dao;
	}
	public void setHandler(CRHFileHandler handler){
		this.handler = handler;
	}
	
	public void init(){
		this.mtrains = dao.queryForMTrain();
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
		
		while((line = reader.readLine()) != null){
			GroupRealTimeData group = new GroupRealTimeData();
			ArrayList<RealTimeData> subdatas = new ArrayList<RealTimeData>();
			String crhNo = line.trim();
			while(!(line = reader.readLine().trim()).equals("")){
				RealTimeData data = new RealTimeData();
				data.setCrhNo(crhNo);
				data.setEngineNo(line.substring(0, line.indexOf(' ')));
				String[] values = line.substring(line.indexOf(' ')+1).split(",");
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
				data.setDateTime(new Date());
				subdatas.add(data);
			}
			group.setDatas(subdatas);
			datas.add(group);
		}
		reader.close();
		
		return datas;
	}
	
	public RealTimeData prepareRealTimeDataForPersist(RealTimeData data){
		data.setCrhId(dao.queryForCrhIdWithCrhNo(data.getCrhNo()));
		data.setEngineId(dao.queryForEngineIdWithEngineNo(data.getEngineNo()));
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
}
