package sys.crh.servlet.test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws IOException{
		Date date = new Date();
		String str = DataGenerator.generateTimeString(date);
		System.out.println(str);
		File text = new File(new File("/home/lihe"), DataGenerator.generateTimeString(date)+".txt");
		text.createNewFile();
		DataGenerator.writeDataToFile(DataGenerator.generateDatas(),text);
	}
}
