import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LaunchGUI launchPage = new LaunchGUI();
		List<ArrayList<String>> listOfLists = launchPage.listOfLists;

		// read all data from a text file
		String storedPath = "C:\\Users\\g84oo\\Desktop\\Chinese\\recordText"; // folder path to the txt file

		File folder  = new File(storedPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println("Read " + listOfFiles[i].getName());
			String newPath = storedPath + "\\" + listOfFiles[i].getName();
			launchPage.textReadAdd(newPath,listOfLists);
		}
		System.out.println(listOfLists);
		

	}
	
	



}
