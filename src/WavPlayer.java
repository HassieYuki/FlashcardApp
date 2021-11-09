import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WavPlayer {
	
	private static Clip clip;
	
	WavPlayer(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		File file = new File(filename);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);

	}

	void start() {
		clip.start();
	}
	
	void stop() {
		clip.stop();
	}
}
