
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDictonary {

	public String chnName;
	public String pinyinName;
	public String meanName;
	public String myDate;


	MyDictonary(String chnName, String pinyinName, String meanName){
		// time
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		this.myDate = dateFormat.format(currentDate) + "_" +
				timeFormat.format(currentDate);

		this.chnName = chnName;
		this.pinyinName  = pinyinName;
		this.meanName = meanName;
	}


	public String toString() {
		return myDate + ": " + chnName + " " + pinyinName + " " + meanName;
	}


	public List<String> createList(){
		List<String> myList = new ArrayList<>();
		myList.add(myDate);
		myList.add(chnName);
		myList.add(pinyinName);
		myList.add(meanName);

		return myList;
	}

}
