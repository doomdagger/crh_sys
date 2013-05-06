package sys.crh.data.service;

import sys.crh.data.dao.CRHDao;
import sys.crh.data.io.CRHFileHandler;
import sys.crh.data.model.Crh;
import sys.crh.data.model.Engine;
import sys.crh.data.model.RealTimeData;

public class CRHService {
	private CRHDao dao;
	private CRHFileHandler handler;
	public void setDao(CRHDao dao){
		this.dao = dao;
	}
	public void setHandler(CRHFileHandler handler){
		this.handler = handler;
	}
	
	
}
