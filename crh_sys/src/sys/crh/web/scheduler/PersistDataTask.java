package sys.crh.web.scheduler;

import sys.crh.data.service.CRHService;

public class PersistDataTask{
	private CRHService crhService;
	private boolean idleForFirstStart = true;
	public void setCrhService(CRHService crhService){
		this.crhService = crhService;
	}
	
	public void run(){
		if(idleForFirstStart){
			idleForFirstStart = false;
			return;
		}
		boolean flag = crhService.fetchAndPersistFileData();
		if(!flag){
			System.out.println("Have no available data to persist...waiting for the next time");
		}
	}
}
