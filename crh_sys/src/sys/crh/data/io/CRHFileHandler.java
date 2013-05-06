package sys.crh.data.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.ServletContextResource;

import sys.crh.data.model.RealTimeData;


public class CRHFileHandler {	
	private File root;
	private static final String MARK_SUFFIX = ".mark";

	
	public CRHFileHandler(ServletContext context){
		ServletContextResource resource = new ServletContextResource(context, "/res/recordFile");
		try {
			this.root = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<RealTimeData> loadNextRecordFile(){
		
		
		
		return null;
	}
	
	public File findNextFile(){
		Date date = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String folderName = "";
		folderName += calendar.get(Calendar.YEAR)+".";
		int month = calendar.get(Calendar.MONTH)+1;
		folderName += ((month>9)?month+"":"0"+month)+".";
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		folderName += ((day>9)?day+"":"0"+day);
		File dayRoot = new File(root,folderName);
		if(!dayRoot.exists())
			return null;
		
		
		return null;
	}
	
	public static File nextFile(){
		File rootFolder = new File("dataFile");
		
		File dateFolder = new File(rootFolder,folderNameMaker(new Date()));
		if(!dateFolder.exists()){
			throw new RuntimeException("当天没有采集到数据");
		}
		FileFilter filter = new FileFilter(){
			public boolean accept(File file) {
				if(qualifyTimeFileName(file.getName()))
					return true;
				return false;
			}
		};
		
		File[] fileList = dateFolder.listFiles(filter);
		for(File f : fileList){
			if(!isReadBefore(f))
				return f;
		}
		return null;
	}
	
	public static String folderNameMaker(Date date){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH)+1;
		int day = gc.get(Calendar.DAY_OF_MONTH);
		
		return year+"-"+((month<10)?"0"+month:month+"")+"-"+((day<10)?"0"+day:day+"");
	}
	public static String folderNameofLatestDay(String name,int flag){
		String temp = name.substring(name.lastIndexOf("-")+1);
		String date = name.substring(0,name.lastIndexOf("-")+1);
		int day = Integer.valueOf(temp)+flag;
		if(day<1){
			day=1;
		}
		return date+day;
	}
	public static boolean qualifyTimeFileName(String name){
		String[] compo = name.split("\\.");
		if(compo.length!=2)
			return false;
		String fileName = compo[0];
		String[] temp = fileName.split("-");
		if(temp.length==3){
			for(String s : temp){
				for(int i = 0; i < s.length(); i++)
					if(!Character.isDigit(s.charAt(i)))
						return false;
			}
			return true;
		}
		return false;
	}
	public static boolean isReadBefore(final File file){
		File folder = file.getParentFile();
		FileFilter filter = new FileFilter(){
			public boolean accept(File f){
				if((file.getName()+MARK_SUFFIX).equals(f.getName())){
					return true;
				}
				return false;
			}
		};
		File[] result = folder.listFiles(filter);
		if(result.length==1){
			return true;
		}else if(result.length==0){
			return false;
		}
		throw new RuntimeException("文件命名出现冲突");
	}
	
	public static void createMarkFile(File file){
		File mark = new File(file.getParentFile(),file.getName()+MARK_SUFFIX);
		if(!mark.exists()){
			try {
				mark.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
