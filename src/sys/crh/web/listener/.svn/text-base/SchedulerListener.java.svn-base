package sys.crh.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SchedulerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void contextInitialized(ServletContextEvent evt) {
		Map<String, Long> uuids = new HashMap<String, Long>();
		evt.getServletContext().setAttribute("uuid", uuids);
		
		/*
		Map[] lvm = new HashMap[0];
		evt.getServletContext().setAttribute("lvm", lvm);
		*/
	}

}
