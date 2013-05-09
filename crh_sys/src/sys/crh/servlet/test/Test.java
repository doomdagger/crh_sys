package sys.crh.servlet.test;

public class Test {
	public static void main(String[] args){
		String temp = "1,2,3,4,,6,7,8,9,10";
		String[] temps = temp.split(",");
		for(String s : temps){
			System.out.println("value="+s);
		}
	}
}
