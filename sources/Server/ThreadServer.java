
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
import gui.Announcement;
import gui.AnnouncementData;
import gui.AnnouncementInfo;

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
					else if(objectMessage instanceof Announcement)
					{
						Announcement newAnnouncement = (Announcement) objectMessage;
						String localization = "pusta";
						String description = newAnnouncement.getDescription();
						String title = newAnnouncement.getTitle();
						String productName = newAnnouncement.getProductName();
						String subcategory = newAnnouncement.getSubcategory();
						int year = 2018;
						String useProduct = "u¿ywany";
						double price = 0;
						
						Map<String, String> attributes = newAnnouncement.getAttributes();
						

						
						try {
							
							Set<String> keys = attributes.keySet();
							for(String key : keys)
							{
								String value = attributes.get(key);
								System.out.println(key + "  " + value);
				        		if(key.equals("rok produkcji"))
				        			year = Integer.parseInt(value);
				        		else if(key.equals("stan"))
				        			useProduct = value;
				        		else if(key.equals("cena"))
				        			price = Double.parseDouble(value);

				        	}
							int productID = adminConnection.productAdd(name, localization, title, productName, description, useProduct,subcategory, price, year);
							for(String key : keys)
							{
								String value = attributes.get(key);
				        		
				        		if(key.equals("rok produkcji"))
				        			continue;
				        		else if(key.equals("stan"))
				        			continue;
				        		else if(key.equals("cena"))
				        			continue;
				        		System.out.println(key + "  " + value);
				        		adminConnection.addAttribute(productID, key, value);
				        		//System.out.println(key + "  " + value);
				        		
							}
							
							SuccessMessage success = new SuccessMessage(6, "Og³oszenie zosta³o dodane!");
							outStream.writeObject(success);
						} catch (SQLException e) {
							FailMessage fail = new FailMessage(6, "Nieuda³o siê dodaæ og³oszenia!");
							outStream.writeObject(fail);
						} catch (NumberFormatException e)
						{
							FailMessage fail = new FailMessage(6, "Niepoprawne dane og³oszenia!");
							outStream.writeObject(fail);
						}
			        	
						
			        	//System.out.println(price + "---" + year +"---" + title + "---" + useProduct + "---" + productName);
					}
					else if(objectMessage instanceof ShowProductMessage)
					{
						ShowProductMessage showProduct = (ShowProductMessage) objectMessage;
						Map<String, String> attributes = showProduct.getAttributes();
						//Map<String, String> attributes = new HashMap<String,String>();
						//attributes.put("rodzaj", "damskie");
						String subcategory = showProduct.getSubcategory();
						int minYear = Integer.MIN_VALUE;
						int maxYear = Integer.MAX_VALUE;
						double minPrice = Double.MIN_VALUE;
						double maxPrice = Double.MAX_VALUE;
						
						ArrayList<AnnouncementData> ad = new ArrayList<AnnouncementData>();
						AnnouncementData tmp;
						
						if(attributes != null)
						{
							String query = "SELECT p.ID, a.TitleName, a.BeginDate, p.Name, p.Price from announcements as a inner join product as p on a.ProductID = p.ID " +  
										"inner join productcategory as pc ON p.ID = pc.ProductID inner join subcategory as s on s.ID = pc.SubcategoryID inner join productattribute as pa " +
										"ON p.ID = pa.ProductID inner JOIN attributes as att on pa.AttributeID = att.ID where ";
							String addAttribute = "att.Value = ";
							Set<String> keys = attributes.keySet();
							for(String key : keys)
							{
								
								String value = attributes.get(key);
								if(key.equals("cena od"))
								{
									minPrice = Double.parseDouble(value);
									continue;
								}
								else if(key.equals("cena do"))
								{
									maxPrice = Double.parseDouble(value);
									continue;
								}
								else if(key.equals("rok prod. od"))
								{
									minYear = Integer.parseInt(value);
									continue;
								}
								else if(key.equals("rok prod. do"))
								{
									maxYear = Integer.parseInt(value);
									continue;
								}
								query = query + addAttribute+ "'" + value +"'"  + " and ";
				        	}
							query = query + "p.ProductYear between " + minYear + " and " + maxYear + " and ";
							query = query + "p.Price between " + minPrice + " and " + maxPrice + " and ";
							query = query + " s.Name = " +  "'" +subcategory+ "'";
							System.out.println(query);
							try {
								
								CachedRowSet crs = adminConnection.executeScrolResult(query);
								while(crs.next())
								{
									String id = crs.getString(1);
									String title = crs.getString(2);
									String date = crs.getString(3);
									String productName = crs.getString(4);
									String sPrice = crs.getString(5);
									double price = Double.parseDouble(sPrice);
									
									tmp = new AnnouncementData(id, date, productName, title, price);
									ad.add(tmp);
									
									System.out.println(id + "  " + title + "  " + date + "  " + productName + "  " + price);
								}
								
								AnnDataMessage dataMessage = new AnnDataMessage();
								dataMessage.setArrayAD(ad);
								
								outStream.writeObject(dataMessage);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							String query = "SELECT p.ID, a.TitleName, a.BeginDate, p.Name, p.Price from announcements as a inner join product as p on a.ProductID = p.ID "+
									"inner join productcategory as pc ON p.ID = pc.ProductID inner join subcategory as s on s.ID = pc.SubcategoryID where s.Name = ?";
							try {
								PreparedStatement stm = adminConnection.createOnePreparedStatement(query, subcategory);
								CachedRowSet crs = adminConnection.executePreparedStatement(stm);
								while(crs.next())
								{
									String id = crs.getString(1);
									String title = crs.getString(2);
									String date = crs.getString(3);
									String productName = crs.getString(4);
									String sPrice = crs.getString(5);
									double price = Double.parseDouble(sPrice);
									tmp = new AnnouncementData(id, date, productName, title, price);
									ad.add(tmp);
									System.out.println(id + "  " + title + "  " + date + "  " + productName + "  " + price);
								}
								
								AnnDataMessage dataMessage = new AnnDataMessage();
								dataMessage.setArrayAD(ad);
								
								outStream.writeObject(dataMessage);
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
