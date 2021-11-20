import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		// read all data from a text file
		String textStoredPath = "C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordText"; // folder path to the txt file

		String textFolderPath = textStoredPath+ "\\";		
		LaunchGUI launchPage = new LaunchGUI(textFolderPath);
		List<ArrayList<String>> listOfLists = launchPage.listOfLists;

		File folder  = new File(textStoredPath);
		File[] listOfFiles = folder.listFiles();


		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println("Read " + listOfFiles[i].getName());
			String newPath = textFolderPath + listOfFiles[i].getName();
			launchPage.textReadAdd(newPath,listOfLists);
		}
		
		System.out.println(listOfLists);

	}



}
