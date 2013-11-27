package sys.crh.servlet.test;

import java.io.IOException;

import sys.crh.mail.LVMMailSender;

public class Test {
	public static void main(String[] args) throws IOException{
		/*
		Date date = new Date();
		String str = DataGenerator.generateTimeString(date);
		System.out.println(str);
		File text = new File(new File("/home/lihe"), DataGenerator.generateTimeString(date)+".txt");
		text.createNewFile();
		DataGenerator.writeDataToFile(DataGenerator.generateDatas(),text);
		*/
		//System.out.println(getStatus("011"));	
		
		LVMMailSender sender = new LVMMailSender();
		System.out.println(sender.printDetails());
		sender.setContent("Hello, Good morning");
		sender.send();
	}
	
	public static int getStatus(String str){
		
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
}
