package sys.crh.matlab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import freemarker.log.Logger;

public class LVMAlgorithm  implements ServletContextAware{
	private Logger logger;
	private boolean log_f = false;
	private ServletContext context;
	private File root;
	
	public LVMAlgorithm(){
		logger = Logger.getLogger("LVMAlgorithm");
		//log_f = true;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
		root = new File(context.getRealPath("/res/tmp"));
	}
	public ServletContext getServletContext(){
		return this.context;
	}
	
	public String compute(String lvmPath, String uniqueFlag){
		String result = "";
		
		if(log_f)logger.info(lvmPath);
		
		File handleRoot = new File(root, uniqueFlag);
		if(log_f)logger.info(handleRoot.getAbsolutePath());
		if(!handleRoot.exists()){
			handleRoot.mkdirs();
			if(log_f)logger.info("The path does not exist! A new one has created...(exist:)"+handleRoot.exists());
		}
		
		
		try {
			if(log_f)logger.info("Start to transfer pictures");
			this.transformPics(handleRoot);
			result = this.handleResult(new float[]{0.02f,0.51f,0.98f});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if(log_f)logger.info("All things have been settled, return the result!");
		return result;
	}
	
	public String handleResult(float[] result){
		int flag = 0;
		
		for(int i = 0; i < result.length; i++){
			if(result[i]>=0.8){
				flag += 1 * ((int)Math.pow(10, (result.length-i-1)));
			}else if(result[i]>=0.1){
				flag += 5 * ((int)Math.pow(10, (result.length-i-1)));
			}else {
				flag += 0 * ((int)Math.pow(10, (result.length-i-1)));
			}
		}
		
		String temp = flag+"";
		for(int i = temp.length(); i < 3; i++){
			temp = '0' + temp;
		}
		
		return temp;
	}
	
	public int getStatus(String str){
		
		if(str.charAt(0)=='1'){
			return 0;
		}else{
			if(str.charAt(1)=='0'){
				char tmp = str.charAt(2);
				if(tmp=='5'){
					return 1;
				}else{
					return 2;
				}
			}else if(str.charAt(1)=='1'){
				return 2;
			}else{
				char tmp = str.charAt(2);
				if(tmp=='5'){
					return 1;
				}else if(tmp=='0'){
					return 1;
				}else{
					return 2;
				}
			}
		}
	}
	
	public void transformPics(File destination) throws IOException{
		File sample = new File(root, "samplePic");
		File[] list = sample.listFiles();
		if(log_f)logger.info("The samplePic folder has "+list.length+" files...");
		for(int i = 0; i < list.length; i++){
			String name = list[i].getName();
			if(log_f)logger.info("File("+(i+1)+")'s name is "+name+"!");
			FileInputStream in = new FileInputStream(list[i]);
			File outpic = new File(destination, name);
			outpic.createNewFile();
			if(log_f)logger.info("The destination file "+outpic.getAbsolutePath()+" has succeed!("+outpic.exists()+")");
			FileOutputStream out = new FileOutputStream(outpic);
			int b;
			while((b=in.read())!=-1){
				out.write(b);
			}
			in.close();
			out.close();
			if(log_f)logger.info("File("+(i+1)+") has been transmitted...");
		}
	}
	
	
	public float[] realMatlabAlgorithm(String lvmFilePath, String picDestination){
		
		return new float[]{0.02f,0.51f,0.98f};
	}
}
