package DataBase;

import java.sql.*;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DataBaseConnection 
{	
	private String url;
	private String username;
	private String password;
	private Connection myConnection;
	private Boolean isConnection;
	private RowSetFactory factory;
	
	public DataBaseConnection(String url, String username, String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
		isConnection = false;
	}

	public Boolean startConnection()
	{
		try {
			myConnection = DriverManager.getConnection(url, username, password);
			isConnection = true;
			factory = RowSetProvider.newFactory();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public CachedRowSet executeScrolResult(String query) throws SQLException
	{

		Statement myStmt = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet myRs = myStmt.executeQuery(query);
		CachedRowSet csr = factory.createCachedRowSet();
		csr.populate(myRs);
		return csr;
	}
	
	public ArrayList<String> getStringColumnOne(String query) throws SQLException
	{
		Statement myStmt = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet myRs = myStmt.executeQuery(query);
		ArrayList<String> columnString = new ArrayList<String>();
		
		while(myRs.next())
		{	
			String line = myRs.getString(1);
			columnString.add(line);
		}
		
		return columnString;
	}
	
	public void closeConnection()
	{
		try {
			myConnection.close();
			isConnection = false;
		} catch (SQLException e) {
			System.out.println("Erron close connection");
		}
	}
	
	public Boolean getIsConnection()
	{
		return isConnection;
	}
}