import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LaunchGUI implements ActionListener {

	public String filetitle = "_test.txt";
	public List<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

	private static JLabel chnLabel;
	private static JTextField chnText;
	private static JLabel pinyinLabel;
	private static JTextField pinyinText;
	private static JLabel meanLabel;
	private static JTextField meanText;

	private static JButton checkButton;
	private static JButton enterButton;
	private static JButton resetButton;
	private static JButton deleteButton;
	private static JButton searchButton;
	private static JButton recordButton;
	
	private static JLabel success;

	LaunchGUI() {
		
		//GUI
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

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
		meanText.setBounds(100,80,550,25);
		panel.add(meanText);
		
		// check button
		checkButton = new JButton("check in dict");
		checkButton.setBounds(250,20,180,25);
		checkButton.addActionListener(this);
		checkButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(checkButton);


		// enter button
		enterButton = new JButton("添加");
		enterButton.setBounds(10,110,100,45);
		enterButton.addActionListener(this);
		enterButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(enterButton);
		
		// record button
		recordButton = new JButton("record");
		recordButton.setBounds(480,30,100,45);
		recordButton.addActionListener(this);
		recordButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(recordButton);

		// reset button
		resetButton = new JButton("清除");
		resetButton.setBounds(210,110,100,45);
		resetButton.addActionListener(this);
		resetButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(resetButton);

		// delete button
		deleteButton = new JButton("delete 归零");
		deleteButton.setBounds(410,110,100,45);
		deleteButton.addActionListener(this);
		deleteButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(deleteButton);

		// search button
		searchButton = new JButton("搜");
		searchButton.setBounds(210,210,100,45);
		searchButton.addActionListener(this);
		searchButton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(searchButton);

		// success message
		success = new JLabel("");
		success.setForeground(cl);
		success.setBounds(10,180,300,25);
		panel.add(success);

		frame.setVisible(true);


	}

	public String createDate() {
		Date currentDate = new Date();
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		String myDate =  dateFormat.format(currentDate) + "_" +
				timeFormat.format(currentDate);
		return myDate;
	}

	// create current month
	public String createMonth() {
		String myMonth = createDate().substring(0, 6); // get yyyyMM		
		return myMonth;
	}

	// create path
	public String createPath(String title) {
		// file path
		// save in desktop
		String filePath = "C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordText\\"
				+ createMonth() + "" + title;

		return filePath;
	}

	// take path and list of list
	public void textReadAdd(String storedPath,
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


	// take path and delete previous data
	public static void deletePreWord(String storedFile) throws IOException {
		RandomAccessFile f = new RandomAccessFile(storedFile, "rw");
		long length = f.length() - 1;
		byte b;
		do {                     
			length -= 1;
			f.seek(length);
			b = f.readByte();
		} while(b != 10 && length > 0);
		if (length == 0) { 
			f.setLength(length);
		} else {
			f.setLength(length + 1);
		}
		f.close();
		System.out.println("Delete last line in "+storedFile);
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

				String storedDate = si.substring(0, 4) + "/" + si.substring(4, 6);				
				String storedTime = si.substring(9,11) + ":" + si.substring(11,13) + ":" + si.substring(13,15);

				success.setText(chinese + " exists on " + storedDate + ". Added at " + storedTime);

				// add new word
			} else {

				MyDictonary d = new MyDictonary(chinese, pinyin, meaning); 

				// write file
				List<String> txtData = d.createList();

				try (
						OutputStream os = new FileOutputStream(createPath(filetitle),true);
						PrintWriter p = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
						){

					// iterate list
					for (int i=0; i<txtData.size()-1; i++) {
						p.print(txtData.get(i));
						p.print(",");
					}
					p.println(txtData.get(txtData.size()-1));

					success.setText("OK");

					System.out.println(txtData);

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

		if(e.getSource()==deleteButton) {
			//Delete test
			try {
				deletePreWord(createPath(filetitle));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getSource()==searchButton) {
			// open search window
			SearchWindow searchWindow = new SearchWindow(listOfLists);
			System.out.println("open "+searchWindow.getClass());
		}
		
		if(e.getSource()==recordButton) {
			String chinese = chnText.getText();
			if(chinese.isBlank()) {
				System.out.println("Chinese blank error");
				success.setText("Enter chinese");
			}
			else {
				String record_filetitle = createDate()+"_"+chinese+".wav";
				String record_path = "C:\\Users\\g84oo\\GoogleDrive\\Eclipse\\Chinese\\recordAudio-test\\"+record_filetitle;
				WavRecord.record(record_path);
			}
		}
		
		if(e.getSource()==checkButton) {
			String chinese = chnText.getText();
			if(chinese.isBlank()) {
				System.out.println("Chinese blank error");
				success.setText("Enter chinese");
			}
			else {
				ScrapeDict nihao = new ScrapeDict(chinese);
				String[] nihao_dict = nihao.createList();
				
				if(nihao_dict[0].isEmpty()) {
					success.setText("not in the dictionary");
				}
				else if(nihao_dict[1].isEmpty()) {
					success.setText("no pinyin");
				}
				else if(nihao_dict[2].isEmpty()) {
					success.setText("no meaning");
				}
				
				// show result in text field
				else {
					
					pinyinText.setText(nihao_dict[1]);
					meanText.setText(nihao_dict[2]);
				}	
			}
		}
	}



}
