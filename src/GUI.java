import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
		
		button = new JButton("添加");
		button.setBounds(10,110,180,25);
		button.addActionListener(new GUI());
		panel.add(button);
		
		success = new JLabel("");
		success.setForeground(cl);
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
		System.out.println(MyDictonary.filePath);
		

		// write file
		List<String> txtData = MyDictonary.createList();
				
		try (FileWriter f = new FileWriter(MyDictonary.filePath,true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);){
			
			// iterate list
			for (int i=0; i<txtData.size()-1; i++) {
				p.print(txtData.get(i));
				p.print(",");
			}
			p.println(txtData.get(txtData.size()-1));
			
			success.setText("OK");
			
		} catch (IOException i) {
			i.printStackTrace();
		}
		


	}
	
	

}
