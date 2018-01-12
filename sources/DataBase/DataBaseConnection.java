package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
	private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/czaki";

	
	public DataBaseConnection(String url, String username, String password)
	{
		this.url = url;
		this.username = username;
		this.password = password;
		isConnection = false;

	}
	public DataBaseConnection(String username, String password)
	{
		this.url = DEFAULT_URL;
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
	
	public CachedRowSet executePreparedStatement(PreparedStatement stm) throws SQLException
	{
		ResultSet myRs = stm.executeQuery();
		CachedRowSet csr = factory.createCachedRowSet();
		csr.populate(myRs);
		return csr;
	}
	
	public Boolean insertToDataBase(String query)
	{
		ArrayList<String> badSqlRegex = new ArrayList<String>();
		String line;
		badSqlRegex.add(".*(?i)drop(?-i).*");
		badSqlRegex.add(".*(?i)delete(?-i).*");
		badSqlRegex.add(".*(?i)update(?-i).*");

		Statement stmt;
		Boolean badQuery = false;
		try {
			stmt = myConnection.createStatement();
			for(int i = 0; i < badSqlRegex.size(); i++)
			{
				line = badSqlRegex.get(i);
				badQuery = Pattern.matches(line, query);
				if(badQuery)
				{
					System.out.println("zle");
					return false;
				}
			}
			Pattern.matches("regex", "ci¹g do sprawdzenia");
			stmt.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public Boolean updateToDataBase(String query)
	{
		ArrayList<String> badSqlRegex = new ArrayList<String>();
		String line;
		badSqlRegex.add(".*(?i)drop(?-i).*");
		badSqlRegex.add(".*(?i)delete(?-i).*");
		badSqlRegex.add(".*(?i)insert(?-i).*");
		Statement stmt;
		Boolean badQuery = false;
		try {
			stmt = myConnection.createStatement();
			for(int i = 0; i < badSqlRegex.size(); i++)
			{
				line = badSqlRegex.get(i);
				badQuery = Pattern.matches(line, query);
				if(badQuery)
				{
					System.out.println("zle");
					return false;
				}
			}
			Pattern.matches("regex", "ci¹g do sprawdzenia");
			stmt.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			return false;
		}
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
	
	public Connection getConnection()
	{
		return myConnection;
	}
	
	public PreparedStatement createPreparedStatement(String query, int argCount, ArrayList<String> arg) throws SQLException
	{
		PreparedStatement newStatement = myConnection.prepareStatement(query);
		for(int i = 1; i <= argCount; i++)
		{
			String line = arg.get(i - 1);
			newStatement.setString(i, line);
		}
		
		return newStatement;
	}

	public AccountsStatus loginToDataBase(String login, String password) throws BadLoginException, SQLException
	{		    

				CallableStatement stmt= myConnection.prepareCall ("{call ValidataLog(?, ?, ?)}");
		    	
		    	
		    	stmt.setString(1, login);       
			    stmt.setString(2,password);
				stmt.execute();
				stmt.registerOutParameter (3, Types.INTEGER);
				int output = stmt.getInt (3);
				if(output == 1)
				{
					String account = "";
					String query = "Select AccountType From Users AS U INNER JOIN Accounts AS A ON U.AccountID = A.ID " +
									"WHERE U.UserName = ?";
					PreparedStatement newStatement = myConnection.prepareStatement(query);
					newStatement.setString(1, login);
					ResultSet rs = newStatement.executeQuery();
					if(rs.next())
						account = rs.getString(1);
					
					if(account.equals("Administrator"))
						return AccountsStatus.ADMIN;
					else if(account.equals("Moderator"))
						return AccountsStatus.MOD;
					else if (account.equals("User"))
						return AccountsStatus.USER;
					else
						throw new BadLoginException();			
					
				}
				else
					throw new BadLoginException();

		    
	}
}