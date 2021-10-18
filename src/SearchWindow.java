import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchWindow implements ActionListener{

	
	private List<ArrayList<String>> listOfLists;
	
	private static JLabel srchLabel;
	private static JTextField srchText;
	
	private static JButton searchButton;
	
	private static JLabel message;
	
	SearchWindow(List<ArrayList<String>> listOfLists){
		this.listOfLists = listOfLists;
		
		JPanel panel = new JPanel();
		
		JFrame frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/3-frame.getSize().height/3);
		
		frame.setTitle("Search word");
		frame.setSize(620,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		
		panel.setLayout(null);
		
		// Searching word
		srchLabel = new JLabel("Search word");
		srchLabel.setBounds(100,20,200,45);
		srchLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(srchLabel);
		
		srchText = new JTextField(35);
		srchText.setBounds(100, 80, 200, 45);
		srchText.setFont(new Font("TimesRoman", Font.BOLD, 30));
		panel.add(srchText);

		// enter button
		searchButton = new JButton("决定");
		searchButton.setBounds(200,150,100,45);
		searchButton.addActionListener(this);
		searchButton.setFont(new Font("TimesRoman", Font.BOLD, 15));
		panel.add(searchButton);

		// message
		message = new JLabel("");
		message.setBounds(10,220,500,45);
		message.setFont(new Font("TimesRoman", Font.BOLD, 20));
		panel.add(message);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==searchButton) {
			System.out.println("hi");
			String srchWord = srchText.getText();
			
			// check the list
			for(int i=0; i<listOfLists.size(); i++) {
				if(listOfLists.get(i).contains(srchWord)) {
					System.out.println(srchWord+" is in "+listOfLists.get(i).get(0));
					
					message.setText(listOfLists.get(i).get(0)+": "+listOfLists.get(i).get(1)
							+" "+listOfLists.get(i).get(2)+" "+listOfLists.get(i).get(3));
					
					break;
				}
			}
		
		}
		
	}

}
