package client;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import DataBase.AccountsStatus;
import Message.*;
import gui.*;


public class Client
{
	private Socket socket;
	private ServerListener serverLisener;
	private ObjectOutputStream outStream;
	private String localhost;
	private int port;

	private MainWindowController mainWindow;
	private MyAnnouncementsWindowController announcementWindow;
	private MySettingsWindowController settingWindow;
	private SignInOrRegisterController registerWindow;
	private SignInOrRegisterController2 registerToBasaWindow;
	private AddAnnouncementWindowController addAnnouncementWindow;
	private AccountsStatus accountStatus;
	private ShowAnnouncementWindowController showWindow;

	private static final int DEFAULT_PORT = 8189;
	private static final String DEFAULT_HOSTNAME = "localhost";

	public Client()
	{
		localhost = DEFAULT_HOSTNAME;
		port = DEFAULT_PORT;
		accountStatus = AccountsStatus.USER;
	}

	public Client(String localhost, int adress)
	{
		this.localhost = localhost;
		this.port = adress;
		accountStatus = AccountsStatus.USER;
	}
	public void connectServer() throws UnknownHostException, IOException
	{
		socket = new Socket(localhost, port);
		outStream = new ObjectOutputStream ( socket.getOutputStream());
	}

	public void startServerListener()
	{
		serverLisener = new ServerListener(socket,Client.this);

		Runnable r = serverLisener;
		Thread t = new Thread(r);
		t.start();
	}

	public void sendMessage(Message newMessage) throws IOException
	{
		outStream.writeObject(newMessage);
	}

		
	
	
	public AccountsStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountsStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getLocalhost()
	{
		return localhost;
	}

	public int getPort()
	{
		return port;
	}
	
	
	public AddAnnouncementWindowController getAddAnnouncementWindow() {
		return addAnnouncementWindow;
	}

	public void setAddAnnouncementWindow(AddAnnouncementWindowController addAnnouncementWindow) {
		this.addAnnouncementWindow = addAnnouncementWindow;
	}

	public MainWindowController getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindowController mainWindow) {
		this.mainWindow = mainWindow;
	}

	public MyAnnouncementsWindowController getAnnouncementWindow() {
		return announcementWindow;
	}

	public void setAnnouncementWindow(MyAnnouncementsWindowController announcementWindow) {
		this.announcementWindow = announcementWindow;
	}

	public MySettingsWindowController getSettingWindow() {
		return settingWindow;
	}

	public void setSettingWindow(MySettingsWindowController settingWindow) {
		this.settingWindow = settingWindow;
	}

	public SignInOrRegisterController getRegisterWindow() {
		return registerWindow;
	}

	public void setRegisterWindow(SignInOrRegisterController registerWindow) {
		this.registerWindow = registerWindow;
	}
	
	public SignInOrRegisterController2 getRegisterToBasaWindow() {
		return registerToBasaWindow;
	}

	public void setRegisterToBasaWindow(SignInOrRegisterController2 registerToBasaWindow) {
		this.registerToBasaWindow = registerToBasaWindow;
	}

	public ShowAnnouncementWindowController getShowWindow() {
		return showWindow;
	}

	public void setShowWindow(ShowAnnouncementWindowController showWindow) {
		this.showWindow = showWindow;
	}
	
}
