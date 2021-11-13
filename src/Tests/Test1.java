package Tests;

import java.io.File;

public class Test1 {

	public static void main(String[] args) {

		
		File folder = new File("C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordAudio-test");
		File[] listOfFiles = folder.listFiles();

		String word = "天才";
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].getName().contains(word)) {
				System.out.println("match!");
				System.out.println("File " + listOfFiles[i].getName());
			}
			
		}
		
	}
	

}
