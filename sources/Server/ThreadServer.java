
package Server;

import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.CachedRowSet;

import DataBase.AccountsStatus;
import DataBase.BadLoginException;
import DataBase.DataBaseConnection;
import Message.*;

/** 
* @author Konrad Czart
* Server class with multi thread connection in client
*/ 
public class ThreadServer implements Runnable {
	private ArrayList<ClientHandler> client;
	private static ThreadServer instance;
	private boolean serverRun;
	private int port;
	private String hostname;

	private static final int DEFAULT_PORT = 8189;
	private static final String DEFAULT_HOSTNAME = "localhost";
	
	private DataBaseConnection adminConnection;
	private DataBaseConnection userConnection;

	private ThreadServer()
	{
		adminConnection = new DataBaseConnection("admin", "admin123");
		userConnection = new DataBaseConnection("user", "user12345");
		startDataBaseConnection();
		
		client = new ArrayList<ClientHandler>();
		hostname = DEFAULT_HOSTNAME;
		port = DEFAULT_PORT;
	}

	private ThreadServer(String hostname, int port)
	{
		this.hostname = hostname;

			if(port > 0 && port <= 65535)
				this.port = port;
			else
				this.port = DEFAULT_PORT;

		client = new ArrayList<ClientHandler>();
		adminConnection = new DataBaseConnection("admin", "admin123");
		userConnection = new DataBaseConnection("user", "user12345");
		startDataBaseConnection();
	}

	/**
	 * @return instance ThreadedServer (Singleton)
	 */
	public static ThreadServer getInstance(String hostname, int port)
	{
		 if(instance == null)
		 {
			 instance = new ThreadServer(hostname, port);
	     }
		 return instance;
	}
	private void startDataBaseConnection()
	{
		adminConnection.startConnection();
		userConnection.startConnection();
	}
	/**
	 * @return instance ThreadedServer (Singleton)
	 */
	public static ThreadServer getInstance()
	{
		if(instance == null)
		{
			instance = new ThreadServer();
		}
		return instance;
	}

	/**
	 *
	 * @return Status is started server
	 */
	public boolean isThreadedServerRun()
	{
		return serverRun;
	}
	
	/**
	 * close connection with database
	 */
	public void closeDataBaseConnection()
	{
		adminConnection.closeConnection();
		userConnection.closeConnection();
	}
	
	public int getPort() {
		return port;
	}

	public String getHostname() {
		return hostname;
	}


	/**
	 * @return ArrayList<String> with name client connect with server
	 */
	public ArrayList getClientName()
	{
		ArrayList<String> clientName = new ArrayList<String>();

		for (ClientHandler tmp : client)
		{
			clientName.add(tmp.getName());
		}
		return clientName;
	}


	@Override
	/**
	 * Run thread server
	 */
	public void run()
	{
		try
		{
			ServerSocket server = new ServerSocket(port, 0, InetAddress.getByName(hostname));
			serverRun = true;
			int i = 1;

			while(true)
			{
				Socket coming = server.accept();

				ClientHandler newClient = new ClientHandler(coming, "usr " + i);
				client.add(newClient);
				Runnable r = newClient;
				Thread t = new Thread(r);
				t.start();
				i++;
			}

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	
	private class ClientHandler implements Runnable
	{

		private Socket coming;
		private String name;
		private ObjectOutputStream outStream;
		private ObjectInputStream inStream;
		private AccountsStatus myStatus;

		public ClientHandler(Socket coming, String name)
		{
			myStatus = AccountsStatus.USER;
			this.coming = coming;
			this.name = name;
			try {
				outStream = new ObjectOutputStream ( coming.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public ObjectOutputStream getObjectOutputStream()
		{
			return outStream;
		}

		public Socket getSocket()
		{
			return coming;
		}

		public String getName()
		{
			return name;
		}

		/**
		 * Send message to all client
		 * @param newMessage
		 * @throws IOException
		 */
		public void sendMessageToAllClient(Message newMessage) throws IOException
		{
			for (ClientHandler tmp : client)
			{
				ObjectOutputStream outS;
				outS = tmp.getObjectOutputStream();

				outS.writeObject(newMessage);
			}
		}

		@Override
		public void run() {


			try
			{

				Object objectMessage;
				inStream = new ObjectInputStream(coming.getInputStream());

				while((objectMessage = inStream.readObject()) != null)
				{
					if(objectMessage instanceof LoginMessage)
					{
						LoginMessage loginMessage = (LoginMessage) objectMessage;
						
						if(loginMessage.getFirst())
						{
							String login = loginMessage.getUserName();
							String salt = "";
							String query = "Select PasswordSalt From logdata where UserName = ?";
							try {
								PreparedStatement ps = userConnection.createOnePreparedStatement(query,login);
								salt = adminConnection.executeOneString(ps);
								loginMessage.setSalt(salt);
								outStream.writeObject(loginMessage);
							} catch (SQLException e) {
								FailMessage fail = new FailMessage(3, "Nieprawid³owa nazwa u¿ytkownika lub has³o!");
								outStream.writeObject(fail);
							}
						}
						else
						{
							String login = loginMessage.getUserName();
							String passwordHash = loginMessage.getHashPassword();
							
							try {
								System.out.println(login);
								System.out.println(passwordHash);
								myStatus = adminConnection.loginToDataBase(login, passwordHash);
								loginMessage.setFirst(false);
								outStream.writeObject(loginMessage);
								this.name = login;
							} catch (BadLoginException e) 
							{
								FailMessage fail = new FailMessage(3, "Nieprawid³owa nazwa u¿ytkownika lub has³o!");
								outStream.writeObject(fail);
							
							} catch (SQLException e) {
								System.out.println("cos nie tak z baza");
							}
						}
						
					}
					else if(objectMessage instanceof RegisterMessage)
					{
						RegisterMessage register = (RegisterMessage) objectMessage;
						
						String query = "Select * From logdata ";
						Boolean tmpB = true;
						try {
							ArrayList<String> name = adminConnection.getStringColumnOne(query);
							String login = register.getUserName();
							String hashPassword = register.getPasswordHash();
							String salt = register.getSalt();
							String firstName = register.getFirstName();
							String lastName = register.getLastName();
							int number = register.getNumber();
							for(String tmp: name)
							{
								if(login.equals(tmp))
									tmpB = false;
							}
							
							if(tmpB)
							{
								adminConnection.registerInsert(login, hashPassword, salt, firstName, lastName, number);
							}
							else
							{
								FailMessage fail = new FailMessage(4, "Nazwa u¿ytkownika jest zajêta");
								outStream.writeObject(fail);
							}
						} catch (SQLException e) {
							e.printStackTrace();
							
						}
						
					}
					else if(objectMessage instanceof SettingWindowMessage)
					{
						String query = "Select FirstName, LastName, PasswordSalt From users inner join logdata on users.UserName = logdata.UserName " +
								"where users.UserName = ?";
						CachedRowSet csr;
						try {
							PreparedStatement ps = userConnection.createOnePreparedStatement(query,name);
							csr = userConnection.executePreparedStatement(ps);
							if(csr.next())
							{
								String firstName = csr.getString(1);
								String lastName = csr.getString(2);
								String salt = csr.getString(3);
								SettingWindowMessage settingMessage = new SettingWindowMessage();
								settingMessage.setUserName(name);
								settingMessage.setFirstName(firstName);
								settingMessage.setLastName(lastName);
								settingMessage.setSalt(salt);
								outStream.writeObject(settingMessage);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					else if(objectMessage instanceof ChangeSettings)
					{
						ChangeSettings settings = (ChangeSettings) objectMessage;
						String oldPassword = settings.getOldHashPassword();
						String newPassword = settings.getNewHashPassword();
						String newSalt = settings.getNewSalt();
						System.out.println(oldPassword);
						try {
							adminConnection.changePassword(name, oldPassword, newPassword, newSalt);
							SuccessMessage success = new SuccessMessage(5, "Has³o zosta³o zmienione!");
							success.setDescription2(newSalt);
							
							outStream.writeObject(success);
						} catch (SQLException e) {
							FailMessage fail = new FailMessage(5, "Nie udalo siê zmieniæ has³a!");
							outStream.writeObject(fail);
							e.printStackTrace();
						}
					}
					//To dooooooooooooooooo
				}
			}
			catch (IOException e)
			{
				client.remove(this);
				System.out.println("client disconnected");
			} catch (ClassNotFoundException e) {
				System.out.println("fail server 2");
			}
		}
	}
}
