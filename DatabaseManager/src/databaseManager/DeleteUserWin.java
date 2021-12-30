package databaseManager;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DeleteUserWin extends JFrame{
	
	JTextField idF;
	JLabel idL;
	JTextArea instructions;
	JButton submitB,closeB;
	String tableName;
	
	public DeleteUserWin(String name) {
		super("Delete User");
		tableName = name;
		
		//initialise a JPanel, add it to the JFrame and set it to use a GridBagLayout.
		JPanel p0 = new JPanel();
		getContentPane().add(p0);
		p0.setLayout(new GridBagLayout());
		
		//set up constraints and padding for grid bag layout
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5,5,5,5);
		cons.gridx = 0;
		cons.gridy = 0;
		
		//initialise components
		submitB = new JButton("Submit");
		closeB = new JButton("Cancel");
		idF = new JTextField(15);
		idL = new JLabel("User ID:");
		instructions = new JTextArea("Input the ID of the user you want to delete.\n Then click submit.");
		
		//add components at relivant positions on the GridBagLayout.
		p0.add(instructions,cons);
		instructions.setEditable(false);
		cons.gridy = 1;
		p0.add(idL,cons);
		cons.gridx = 1;
		p0.add(idF,cons);
		cons.gridx = 0;
		cons.gridy = 2;
		p0.add(closeB,cons);
		cons.gridx = 1;
		p0.add(submitB,cons);
		
		//add the actionListener to each of the buttons
		submitB.addActionListener(new ButListener());
		closeB.addActionListener(new ButListener());
	}
	
	private void close() {
		//close this window
		this.dispose();
	}
	
	private class ButListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == closeB) {
				
				//close the window and return to the main menu.
				WindowManager.mainMenu(tableName);
				close();
			} else if (e.getSource() == submitB) {
				
				//prevent the user from deleting the admin user.
				if (idF.getText().equals("1")) {
					JOptionPane.showMessageDialog(null, "Sorry. You cannot delete the admin user of any table.", "Sorry." , JOptionPane.INFORMATION_MESSAGE);
					idF.setText("");
				} else {
					SQL dbTool = new SQL();
					try {
						// attempt to SQL select the ID given
						String[] data = dbTool.selectById(Integer.parseInt(idF.getText()), tableName);
						
						//if such a user exists, ask the admin for confirmation.
						if (JOptionPane.showConfirmDialog(null, "Are you sure you want to perminantly delete s" + data[0] +
								"?","Confirm",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							dbTool.deleteByID(tableName,Integer.parseInt(idF.getText()));
							
							//inform the admin that the user has been successfully deleted and return to the main menu.
							JOptionPane.showMessageDialog(null, "Success!","Success!",JOptionPane.INFORMATION_MESSAGE);
							WindowManager.mainMenu(tableName);
							close();
						}
						
					} catch (Exception exe) {
						
						//if the ID was invalid, clear the text field and notify the user.
						JOptionPane.showMessageDialog(null, "ERROR: NO USER WITH GIVEN ID COULD BE FOUND.", "ERROR." ,JOptionPane.ERROR_MESSAGE);
						idF.setText("");
					}
				}
			}
		}
	}
}
