package databaseManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenuWin extends JFrame {
	
	JButton newTableB, loginB;
	
	//This method is a constructor, it is used to create the window and is run when an instance of this class is created.
	public StartMenuWin () {
		super("Start Menu.");
		setLayout(new FlowLayout());
		
		//initialise the buttons
		newTableB = new JButton("Create new table");
		loginB = new JButton("Login as admin");
		
		// add the buttons to the JFrame
		add(newTableB);
		add(loginB);
		
		// add the actionListeners to the JButtons
		newTableB.addActionListener(new Blistener());
		loginB.addActionListener(new Blistener());
		
		// use imported method to properly size and fit the windows 
		pack();
	}
	
	private void closeWin() {
		this.dispose();
	}
	
	private class Blistener implements ActionListener { //action listener class

		public void actionPerformed(ActionEvent e) { 
			if (e.getSource() == newTableB) {
				
				WindowManager.newTable();
				closeWin();
			} else if (e.getSource() == loginB) {
				
				WindowManager.login();
				closeWin();
			}
		}
	}
}
