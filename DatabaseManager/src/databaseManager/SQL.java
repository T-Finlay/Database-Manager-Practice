package databaseManager;

import java.sql.*;

public class SQL {

	//connect to a locally hosted SQL server by the address provided and return the connection.
	public static Connection connectionMethod() {
		try {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","password");
			return c;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	//select a user from a table by ID and return an array of their username and password
	public String[] selectById (int id, String tabName) throws SQLException {
		Connection c = connectionMethod();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM " + tabName + " WHERE id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String[] data = {rs.getString("username"),rs.getString("password")};
		c.close();
		return data;
	}
	
	//check if a table exists by getting a result set of metadata of the table and testing to see if there are any records in it.
	public boolean checkTable(String tabName) throws SQLException {
		DatabaseMetaData md = connectionMethod().getMetaData();
		ResultSet rs = md.getTables(null, null, tabName, null);
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	//this method creates a new table in the database and adds an admin user to the table
	public void createTable(String tabName, String adminUN, String adminPW) throws SQLException {
		Connection c = connectionMethod();
		PreparedStatement ps = c.prepareStatement("CREATE TABLE " + tabName + "(id int NOT NULL AUTO_INCREMENT, username varchar(255), password varchar(255),PRIMARY key(id))");
		ps.executeUpdate();
		ps.close();
		c.close();
		insert(tabName,adminUN,adminPW);
	}
	
	//this method adds a new user to a given table.
	public void insert(String tabName, String un , String pw) throws SQLException {
		Connection c = connectionMethod();
		PreparedStatement ps = c.prepareStatement("INSERT INTO " + tabName + " (username,password) VALUES (?,?)");
		ps.setString(1,un);
		ps.setString(2,pw);
		ps.executeUpdate();
		ps.close();
		c.close();
	}
	
	//update a given user by ID in a given table.
	public void update(String tabName, String un , String pw, int id) throws SQLException {
		Connection c = connectionMethod();
		//get data of current username and password
		String[] current = selectById(id,tabName);
		
		//if either new username or password is empty then set them to the current username/password
		if (un.isEmpty()) {
			un = current[0];
		}
		if (pw.isEmpty()) {
			pw = current[1];
		}
		
		//update the server setting username to the given new username and password of the given new password to the relivant ID
		PreparedStatement ps = c.prepareStatement("UPDATE "+tabName+" SET username = ?, password = ? WHERE id = ?");
		ps.setString(1,un);
		ps.setString(2, pw);
		ps.setInt(3, id);
		ps.executeUpdate();
		ps.close();
		c.close();
		
	}
	
	//delete a given user by id on the database

	public void deleteByID(String tabName, int id ) throws SQLException {
		Connection c = connectionMethod();
		PreparedStatement ps = c.prepareStatement("DELETE FROM " + tabName + " WHERE id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		c.close();
	}
}
