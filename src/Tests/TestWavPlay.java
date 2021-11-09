package Tests;

import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TestWavPlay {	
   public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
	   Scanner scan = new Scanner(System.in);
	   
	   
	   TestPlay wavplay = new TestPlay("C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordAudio\\record_ymy4.wav");
	   wavplay.start();
	
	   scan.nextInt();
}
    
}