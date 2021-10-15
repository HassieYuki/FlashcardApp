
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

	MyDictonary(){
		// time
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		MyDictonary.myDate = dateFormat.format(currentDate) + "_" +
				timeFormat.format(currentDate);
		MyDictonary.myMonth = MyDictonary.myDate.substring(0, 6); // get yyyyMM

		// file path
		// save in desktop
		MyDictonary.filePath = "C:\\Users\\g84oo\\Desktop\\Chinese\\"
				+ myMonth + "_test.txt";
	}


	MyDictonary(String chnName, String pinyinName, String meanName){
		MyDictonary.chnName = chnName;
		MyDictonary.pinyinName  = pinyinName;
		MyDictonary.meanName = meanName;
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
