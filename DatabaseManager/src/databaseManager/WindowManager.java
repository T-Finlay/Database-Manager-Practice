package databaseManager;

import java.awt.Dimension;
import javax.swing.JFrame;

public class WindowManager {
	
	public static void start() {
		//Create an object of the class StartMenuWin and configure the Window
		StartMenuWin sWin = new StartMenuWin();
		sWin.setVisible(true);
		sWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sWin.setResizable(false);
	}
	
	public static void newTable() {
		//this method creates an object of the class newTable win and subsiquently configures the window created by the constuctor
		NewTableWin nTWin = new NewTableWin();
		nTWin.setVisible(true);
		nTWin.setSize(new Dimension(400,400));
		nTWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nTWin.setResizable(false);
	}

	public static void login() {
		//this method creates an object of the class AdminLoginWin and subsiquently configures the window created by the constructor.
		AdminLoginWin aLWin = new AdminLoginWin();
		aLWin.setVisible(true);
		aLWin.setSize(new Dimension(400,400));
		aLWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aLWin.setResizable(false);
	}
	
	public static void mainMenu(String tabName) {
		//this method creates an object of the class MainMenuWin and subsiquently configures the window created by the constructor.
		MainMenuWin mMWin = new MainMenuWin();
		mMWin.setVisible(true);
		mMWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMWin.setResizable(false);
		mMWin.tableName = tabName;
	}
	
	public static void newUser(String tabName) {
		//this method creates an object of the class NewUserWin and subsiquently configures the window created by the constructor.
		NewUserWin nUWin = new NewUserWin(tabName);
		nUWin.setVisible(true);
		nUWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nUWin.pack();
		nUWin.setResizable(false);
	}
	
	public static void updateUser(String tabName) {
		//this method creates an object of the class UpdateUserWin and subsiquently configures the window created by the constructor
		UpdateUserWin uUWin = new UpdateUserWin(tabName);
		uUWin.setVisible(true);
		uUWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uUWin.pack();
		uUWin.setResizable(false);
	}
	
	public static void searchUser(String tabName) {
		//this method creates an object of the class SearchUserWin and subsiquently configures the window created by the constructor
		SearchUserWin sUWin = new SearchUserWin(tabName);
		sUWin.setVisible(true);
		sUWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sUWin.pack();
		sUWin.setResizable(false);
	}
	
	public static void deleteUser(String tabName) {
		//this method creates an object of the class DeleteUserWin and subsiquently configures the window created by the constructor
		DeleteUserWin dUWin = new DeleteUserWin(tabName);
		dUWin.setVisible(true);
		dUWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dUWin.pack();
		dUWin.setResizable(false);
	}
}
