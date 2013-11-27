package sys.crh.web.scheduler;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class TmpPicOutFileClearanceTask implements ServletContextAware{
	private Logger logger;
	private boolean log_f = false;
	private ServletContext context;
	private File root;
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		root = new File(context.getRealPath("/res/tmp"));
	}
	
	public TmpPicOutFileClearanceTask(){
		logger = Logger.getLogger("TmpPicOutFileClearanceTask");
		//log_f = true;
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		long passed = System.currentTimeMillis();
		if(log_f) logger.info("Prepare to clean the context");
		Map<String, Long> uuid = (Map<String, Long>)context.getAttribute("uuid");
		if(uuid==null){
			return;
		}
		for(Entry<String, Long> en : uuid.entrySet()){
			if(log_f) logger.info("key: "+en.getKey()+", value: "+en.getValue()+"...");
			if(passed-en.getValue()>30000){
				if(log_f) logger.info("This entry has been out of date!!! Prepared to Clean it!!!");
				File file = new File(root,en.getKey());
				if(log_f) logger.info("Folder "+file.getAbsolutePath()+" is about to be removed!!!");
				if(file.exists()){
					file.delete();
				}
				uuid.remove(en.getKey());
			}
			if(log_f) logger.info("Next Entry!!");
		}
		
		if(log_f) logger.info("Finish clearing the folders... Sleep....");
	}
}
