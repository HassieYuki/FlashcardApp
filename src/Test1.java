import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

	public static void main(String[] args) {

		String storedPath = "C:\\Users\\g84oo\\Desktop\\Chinese";

	
		System.out.println(listOfLists);
		
		File folder  = new File("C:\\Users\\g84oo\\Desktop\\Chinese");
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			System.out.println(storedPath + "\\" + listOfFiles[i].getName());
			String newPath = storedPath + "\\" + listOfFiles[i].getName();
			textReadAdd(newPath,listOfLists);
		}
		
		System.out.println(listOfLists);

	}
	
	// take path and list of list
		public static void textReadAdd(String storedPath,
				List<ArrayList<String>> listOfLists) {
			
			String line = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(storedPath));

				while((line = br.readLine()) != null) {
					String[] values = line.split(",");
					
					ArrayList<String> addList = new ArrayList<String>();
					for(String value: values ) {
						addList.add(value);
					}
					
					listOfLists.add((ArrayList<String>) addList);
				}
				//System.out.println(listOfLists);

				br.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

}
