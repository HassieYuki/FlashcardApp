import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		LaunchGUI launchPage = new LaunchGUI();
		List<ArrayList<String>> listOfLists = launchPage.listOfLists;

		// read all data from a text file
		String storedPath = "C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordText"; // folder path to the txt file

		File folder  = new File(storedPath);
		File[] listOfFiles = folder.listFiles();


		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println("Read " + listOfFiles[i].getName());
			String newPath = storedPath + "\\" + listOfFiles[i].getName();
			launchPage.textReadAdd(newPath,listOfLists);
		}
		
		ScrapeDict nihao = new ScrapeDict("厲害");
		String[] nihao_dict = nihao.createList();
		
		
		if(nihao_dict[0].isEmpty()) {
			System.out.println("not in the dictionary");
		}
		else if(nihao_dict[1].isEmpty()) {
			System.out.println("no pinyin");
		}
		else if(nihao_dict[2].isEmpty()) {
			System.out.println("no meaning");
		}
		else {
			System.out.println(nihao_dict[0]);
			System.out.println(nihao_dict[1]);
			System.out.println(nihao_dict[2]);
		}	

	}



}
