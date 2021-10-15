import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {

	public static List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
	
	private static JLabel chnLabel;
	private static JTextField chnText;
	private static JLabel pinyinLabel;
	private static JTextField pinyinText;
	private static JLabel meanLabel;
	private static JTextField meanText;

	private static JButton enterButton;
	private static JButton resetButton;
	private static JLabel success;

	public static void main(String[] args) {
		// read all data from a text file
		String storedPath = "C:\\Users\\g84oo\\Desktop\\Chinese\\202110_test.txt";

		textReadAdd(storedPath,listOfLists);
		System.out.println(listOfLists);


		JPanel panel = new JPanel();
		panel.setBackground(Color.black);

		JFrame frame = new JFrame();
		frame.setTitle("My Flashcard");
		frame.setSize(700, 300); // initial size of fame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application


		frame.add(panel);

		panel.setLayout(null);

		Color cl = new Color(200,50,50);
		// Chinese
		chnLabel = new JLabel("中文");
		chnLabel.setForeground(cl);
		chnLabel.setBounds(10,20,80,25); //(x,y,width,height)
		panel.add(chnLabel);

		chnText = new JTextField(20);
		chnText.setBounds(100,20,80,25);
		panel.add(chnText);

		// Pinyin
		pinyinLabel = new JLabel("拼音");
		pinyinLabel.setForeground(cl);
		pinyinLabel.setBounds(10,50,80,25);
		panel.add(pinyinLabel);

		pinyinText = new JTextField(20);
		pinyinText.setBounds(100,50,160,25);
		panel.add(pinyinText);

		// Meaning
		meanLabel = new JLabel("意思");
		meanLabel.setForeground(cl);
		meanLabel.setBounds(10,80,80,25);
		panel.add(meanLabel);

		meanText = new JTextField(20);
		meanText.setBounds(100,80,360,25);
		panel.add(meanText);

		// enter button
		enterButton = new JButton("添加");
		enterButton.setBounds(10,110,100,45);
		enterButton.addActionListener(new GUI());
		enterButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(enterButton);

		// reset button
		resetButton = new JButton("删除");
		resetButton.setBounds(210,110,100,45);
		resetButton.addActionListener(new GUI());
		resetButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(resetButton);

		// success message
		success = new JLabel("");
		success.setForeground(cl);
		success.setBounds(10,180,300,25);
		panel.add(success);

		frame.setVisible(true);

				
	}

	// create path
	public static String createPath() {
		// time
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String myDate = dateFormat.format(currentDate) + "_" +
				timeFormat.format(currentDate);
		String myMonth = myDate.substring(0, 6); // get yyyyMM

		// file path
		// save in desktop
		String filePath = "C:\\Users\\g84oo\\Desktop\\Chinese\\"
				+ myMonth + "_test.txt";
		
		return filePath;
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
	
	public static String storedInfo(String chn_word, List<ArrayList<String>> listOfLists) {
		
		String st = "";

		for(int i=0; i<listOfLists.size(); i++) {
			if(chn_word.equals(listOfLists.get(i).get(1))) {
				
				st = listOfLists.get(i).get(0);
				System.out.println("Recorded in "+ st);
				break;
			}
		}
			
		return st;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==enterButton) {
			String chinese = chnText.getText();
			String pinyin = pinyinText.getText();
			String meaning = meanText.getText();
			
	
			String si = storedInfo(chinese,listOfLists);
			
			// when there is empty box
			if(chinese.isEmpty() || pinyin.isEmpty() || meaning.isEmpty()) {
				success.setText("Empty box!");
			
				// stored before => storedInfo has String => isBlank=false
			} else if(!si.equals("")){
				
				String storedDate = si.substring(0, 4) + "/" + si.substring(6, 8);				
				String storedTime = si.substring(9,11) + ":" + si.substring(11,13) + ":" + si.substring(13,15);
				
				success.setText("This word exists on " + storedDate + ". Added at " + storedTime);
			
				// add new word
			} else {

				MyDictonary d = new MyDictonary(chinese, pinyin, meaning); 

				// write file
				List<String> txtData = d.createList();
				

				try (FileWriter f = new FileWriter(createPath(),true);
						BufferedWriter b = new BufferedWriter(f);
						PrintWriter p = new PrintWriter(b);){

					// iterate list
					for (int i=0; i<txtData.size()-1; i++) {
						p.print(txtData.get(i));
						p.print(",");
					}
					p.println(txtData.get(txtData.size()-1));

					success.setText("OK");

					// clear text box
					chnText.setText("");
					pinyinText.setText("");
					meanText.setText("");


				} catch (IOException i) {
					i.printStackTrace();
				}

			}
		}

		if(e.getSource()==resetButton) {
			chnText.setText("");
			pinyinText.setText("");
			meanText.setText("");
		}

	}



}
