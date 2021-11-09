package Tests;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

public class Test1 {

	public static void main(String[] args) {

		System.out.println("Hello");
		
		try
		{
			AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			
			DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
			if(!AudioSystem.isLineSupported(dataInfo))
			{
				System.out.println("Not supported");
			}
			
			TargetDataLine targetLine = (TargetDataLine)AudioSystem.getLine(dataInfo);
			targetLine.open();
			
			JOptionPane.showMessageDialog(null, "Hit ok to start");
			targetLine.start();
			
			Thread audioRecorderThread = new Thread()
			{
				@Override public void run() {
					AudioInputStream recordingStream = new AudioInputStream(targetLine);
					File outputFile = new File("C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordAudio\\record_ymy4.wav");
					
					try {
						AudioSystem.write(recordingStream, AudioFileFormat.Type.WAVE, outputFile);
					}
					catch (IOException ex) {
						System.out.println(ex);
					}
					System.out.println("Stopped recording");
				}
			};
			
			audioRecorderThread.start();
			JOptionPane.showMessageDialog(null, "hit ok to stop");
			targetLine.stop();
			targetLine.close();
					
		}
		
		catch(Exception e) 
		{
			System.out.println(e);
		}

	}
	
	
		

}
