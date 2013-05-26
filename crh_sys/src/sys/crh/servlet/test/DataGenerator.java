package sys.crh.servlet.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sys.crh.data.model.GroupRealTimeData;
import sys.crh.data.model.RealTimeData;

public class DataGenerator {
	private static String[] crhNos = {"CRH1-004A","CRH1-006A","CRH1-020A","CRH2-120B","CRH2-122B","CRH2-124B"};
	private static double[] factors = {10.0,100.0};
	private static Random random = new Random();
	public static void main(String[] args) throws IOException{
		writeDataToFile(generateDatas(),new File("/home/lihe/Desktop"));
	}
	
	public static void writeDataToFile(ArrayList<GroupRealTimeData> data, File root) throws IOException{
		File path = new File(root,"/test.txt");
		if(!path.exists()){
			path.createNewFile();
		}
		PrintWriter writer = new PrintWriter(path);
		for(GroupRealTimeData group : data){
			List<RealTimeData> list = group.getDatas();
			writer.println(list.get(0).getCrhNo());
			for(RealTimeData rel : list){
				String line = rel.getEngineNo()+" ";
				line += rel.getYbdianya()+","+rel.getYbdianliu()+","+rel.getKzdianya()+","+rel.getZjdianya()+","+rel.getDjdianliu()+","+
				rel.getDjpinlv()+","+rel.getZdxinhao()+","+rel.getDzdianliu()+","+rel.getQyzhidongli()+","+rel.getSpeed()+","+rel.getJiasudu()+","+
						rel.getTemperature();
				writer.println(line);
			}
			writer.println();
		}
		writer.close();
	}
	
	public static ArrayList<GroupRealTimeData> generateDatas(){
		ArrayList<GroupRealTimeData> datas = new ArrayList<GroupRealTimeData>();
		for(int i = 0; i < 10; i++){
			int index = random.nextInt(6);
			int engineNum = (index>2)?16:10;
			ArrayList<RealTimeData> data = new ArrayList<RealTimeData>();
			Date tempDate = new Date();
			for(int j = 0; j < engineNum; j++){
				RealTimeData rel = new RealTimeData();
				rel.setCrhNo(crhNos[index]);
				rel.setEngineNo("M"+j);
				rel.setYbdianya((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setYbdianliu((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setKzdianya((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setZjdianya((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setDjdianliu((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setDjpinlv((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setZdxinhao((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setDzdianliu((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setQyzhidongli((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setSpeed((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setJiasudu((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setTemperature((int)(Math.random()*10000)/factors[random.nextInt(2)]);
				rel.setDateTime(new Timestamp(tempDate.getTime()));
				data.add(rel);
			}
			GroupRealTimeData group = new GroupRealTimeData();
			group.setDatas(data);
			datas.add(group);
		}
		return datas;
	}
	
}
