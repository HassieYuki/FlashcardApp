import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchWindow implements ActionListener {

	private String recordDataPath = "C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordAudio-test";
	private String matchFile;
	private List<ArrayList<String>> listOfLists;

	private static JLabel srchLabel;
	private static JTextField srchText;

	private static JButton searchButton;
	private static JButton playButton;

	private static JLabel message;
	private static JLabel message2;

	SearchWindow(List<ArrayList<String>> listOfLists) {
		this.listOfLists = listOfLists;

		JPanel panel = new JPanel();

		JFrame frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 3 - frame.getSize().height / 3);

		frame.setTitle("Search word");
		frame.setSize(620, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);

		panel.setLayout(null);

		// Searching word
		srchLabel = new JLabel("Search word");
		srchLabel.setBounds(100, 20, 200, 45);
		srchLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(srchLabel);

		srchText = new JTextField(35);
		srchText.setBounds(100, 80, 200, 45);
		srchText.setFont(new Font("TimesRoman", Font.BOLD, 30));
		panel.add(srchText);

		// enter button
		searchButton = new JButton("决定");
		searchButton.setBounds(200, 150, 100, 45);
		searchButton.addActionListener(this);
		searchButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
		panel.add(searchButton);

		// play button
		playButton = new JButton("play");
		playButton.setBounds(100, 320, 100, 45);
		playButton.addActionListener(this);
		playButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
		playButton.setVisible(false);
		panel.add(playButton);

		// message
		message = new JLabel("");
		message.setBounds(10, 220, 500, 45);
		message.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(message);

		// message2
		message2 = new JLabel("");
		message2.setBounds(10, 270, 500, 45);
		message2.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(message2);	

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String srchWord = srchText.getText();

			// if the box is empty
			if(srchWord.isBlank()) {
				message.setText("Fill in the blank");
			} 
			
			else {		
			// visible audio play button
			File folder = new File(recordDataPath);
			File[] listOfFiles = folder.listFiles();

			int containAudioIdxNo = -1;
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].getName().contains(srchWord)) {
					containAudioIdxNo = i;
					System.out.println("File: " + listOfFiles[i].getName());
					matchFile = listOfFiles[i].getName();
					playButton.setVisible(true);
					message2.setText(matchFile);
					break;
				}		
			}
			if(containAudioIdxNo == -1) {
				System.out.println("Not in audio file");
				playButton.setVisible(false);
				message2.setText("Not in audio file");
			}

			// check the list
			int containWordIdxNo = -1;
			for (int i = 0; i < listOfLists.size(); i++) {
				if (listOfLists.get(i).contains(srchWord)) {
					containWordIdxNo = i;
					System.out.println(srchWord + " is in " + listOfLists.get(i).get(0));

					message.setText(listOfLists.get(i).get(0) + ": " + listOfLists.get(i).get(1) + " "
							+ listOfLists.get(i).get(2) + " " + listOfLists.get(i).get(3));
					break;
				}

			}
			// not in the list
			if(containWordIdxNo == -1) {
				message.setText("Not in my falsh card");
			}
			}

		}
		
		if(e.getSource()==playButton) {
			WavPlayer wavplay;
			String wavFilePath = recordDataPath+"\\"+matchFile;
			try {
				wavplay = new WavPlayer(wavFilePath);
				wavplay.start();
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}

	}

}
