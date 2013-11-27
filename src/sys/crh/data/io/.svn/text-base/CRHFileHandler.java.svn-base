package sys.crh.data.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResource;

public class CRHFileHandler implements ServletContextAware{	
	private File root;
	private ServletContext context;
	private static final String MARK_SUFFIX = ".mark";

	
	public CRHFileHandler(){}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public ServletContext getServletContext(){
		return this.context;
	}
	
	public void init(){
		ServletContextResource resource = new ServletContextResource(context, "/res/recordFile");
		context.setAttribute("message", "This is a trick");
		try {
			this.root = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File nextFile(){		
		File dateFolder = new File(root,folderNameMaker(new Date()));
		if(!dateFolder.exists()){
			throw new RuntimeException("Don't have today's Data File Folder!");
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
	
	public String folderNameMaker(Date date){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH)+1;
		int day = gc.get(Calendar.DAY_OF_MONTH);
		
		return year+"-"+((month<10)?"0"+month:month+"")+"-"+((day<10)?"0"+day:day+"");
	}
	
	public boolean qualifyTimeFileName(String name){
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
	public boolean isReadBefore(final File file){
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
		throw new RuntimeException("Files have same name conflict");
	}
	
	public void createMarkFile(File file){
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
