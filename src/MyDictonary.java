
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDictonary {

	public static String chnName;
	public static String pinyinName;
	public static String meanName;
	public static String myDate;
	public static String myMonth;
	public static String filePath;

	MyDictonary(String chnName, String pinyinName, String meanName){
		this.chnName = chnName;
		this.pinyinName  = pinyinName;
		this.meanName = meanName;

		// time
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		this.myMonth = dateFormat.format(currentDate);
		this.myDate = dateFormat.format(currentDate) + "_" +
				timeFormat.format(currentDate);

		// file path
		this.filePath = "C:\\Users\\g84oo\\Desktop\\Chinese\\"
				+ myMonth + ".txt";
	}
	
	public String toString() {
		return myDate + ": " + chnName + " " + pinyinName + " " + meanName;
	}
	

	public static List<String> createList(){
		List<String> myList = new ArrayList<>();
		myList.add(myDate);
		myList.add(chnName);
		myList.add(pinyinName);
		myList.add(meanName);
		
		
		return myList;
	}
	
}
