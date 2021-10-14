import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI implements ActionListener {
	
	private static JLabel chnLabel;
	private static JTextField chnText;
	private static JLabel pinyinLabel;
	private static JTextField pinyinText;
	private static JLabel meanLabel;
	private static JTextField meanText;
	
	
	private static JButton button;
	private static JLabel success;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		// Chinese
		chnLabel = new JLabel("中文");
		chnLabel.setBounds(10,20,80,25); //(x,y,width,height) 
		panel.add(chnLabel);
		
		chnText = new JTextField(20);
		chnText.setBounds(100,20,80,25);
		panel.add(chnText);
		
		// Pinyin
		pinyinLabel = new JLabel("拼音");
		pinyinLabel.setBounds(10,50,80,25);
		panel.add(pinyinLabel);
		
		pinyinText = new JTextField(20);
		pinyinText.setBounds(100,50,160,25);
		panel.add(pinyinText);
		
		// Meaning
		meanLabel = new JLabel("意味");
		meanLabel.setBounds(10,80,80,25);
		panel.add(meanLabel);
		
		meanText = new JTextField(20);
		meanText.setBounds(100,80,360,25);
		panel.add(meanText);
		
		button = new JButton("Add this word");
		button.setBounds(10,110,180,25);
		button.addActionListener(new GUI());
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10,140,300,25);
		panel.add(success);
		
		
		frame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String chinese = chnText.getText();
		String pinyin = pinyinText.getText();
		String meaning = meanText.getText();
		
		MyDictonary d = new MyDictonary(chinese, pinyin, meaning);
		System.out.println(d);
		System.out.println(d.filePath);
		

		// write file
		List<String> txtData = d.createList();
		
		FileWriter fileWriter = null;
		try {
		      fileWriter = new FileWriter(d.filePath);


		     for (int i=0; i<txtData.size()-1; i++) {
		        fileWriter.append(txtData.get(i));
		        fileWriter.append(",");	     	        
		     }
		     fileWriter.append(txtData.get(txtData.size()-1)); // last element
		     fileWriter.append("\n");
		     System.out.println("TXT Recorded");


		    } catch (Exception e1) {
		      e1.printStackTrace();
		    } finally {
		      try {
		        fileWriter.flush();
		        fileWriter.close();
		      } catch (IOException e1) {
		        e1.printStackTrace();
		      }

		    }
		
		
		

	}
	
	

}
