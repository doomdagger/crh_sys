package sys.crh.web.scheduler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class LVMFileRefreshTask implements ServletContextAware{
	private Logger logger;
	private boolean log_f = false;
	private ServletContext context;
	private File root;
	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		root = new File(context.getRealPath("/res/lvmFile"));
	}
	
	public LVMFileRefreshTask(){
		logger = Logger.getLogger("LVMFileRefreshTask");
		//log_f = true;
	}
	@SuppressWarnings("rawtypes")
	public void run(){
		Map[] maps;
		
		if(log_f)logger.info("Start to refresh the list of LVM files...");
		File[] list = root.listFiles();
		maps = new Map[list.length];
		
		
		int count = 0;
		for(File file : list){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("filePath", file.getAbsolutePath());
			map.put("fileName", file.getName());
			maps[count++] = map;
			if(log_f)logger.info(file.getAbsolutePath());
		}
		
		context.setAttribute("lvm", maps);
		if(log_f)logger.info("End to refresh the list of LVM files...");
	}

}
