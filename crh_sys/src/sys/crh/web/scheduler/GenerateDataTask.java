package sys.crh.web.scheduler;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import sys.crh.servlet.test.DataGenerator;

public class GenerateDataTask implements ServletContextAware{
	private Logger logger;
	
	private ServletContext context;
	private String root;
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		root = context.getRealPath("/res/recordFile");
	}
	
	public GenerateDataTask(){
		logger = Logger.getLogger("GenerateDataTask");
	}
	
	public void run(){
		Date date = new Date();
		String subFolder = "/"+DataGenerator.generateDateString(date);
		File fullPath = new File(root+subFolder);
		boolean dir = false;
		if(!fullPath.exists()){
			dir = fullPath.mkdir();
		}
		if(!dir){
			logger.info("fail to create folder");
		}
		if(fullPath.exists()){
			File text = new File(fullPath, DataGenerator.generateTimeString(date)+".txt");
			try {
				text.createNewFile();
				logger.info("successfully create the file");
				DataGenerator.writeDataToFile(DataGenerator.generateDatas(),text);
			} catch (IOException e) {
				logger.info("Encounter exception!");
				e.printStackTrace();
			}
		}
	}

}
