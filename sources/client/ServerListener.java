package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import Message.*;
import gui.AnnouncementData;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class ServerListener implements Runnable
{
	private Socket currentSocket;
	private Client myClient;
	
	public ServerListener(Socket socket, Client myClient)
	{
		currentSocket = socket;
		this.myClient = myClient;

	}
	@Override
	public void run() {
		
		
		try {
			
			
			ObjectInputStream in = new ObjectInputStream(currentSocket.getInputStream());

			Object tmp;
			while((tmp = in.readObject()) != null)
			{
				
				if(tmp instanceof LoginMessage)
				{
					LoginMessage loginMessage = (LoginMessage) tmp;
					
					if(loginMessage.getFirst())
					{
						
						String salt = loginMessage.getSalt();
						myClient.getRegisterWindow().hashPaswordListener(salt);
					}
					else
					{
						Platform.runLater(() -> {
							myClient.getRegisterWindow().logMeActivatedListener();
						});
					}
				}
				else if(tmp instanceof FailMessage)
				{
					FailMessage fail = (FailMessage) tmp;
					
					int failCode = fail.getCodFail();
					String dectription = fail.getDescription();
					
					if(failCode == 3)
					{
						myClient.getRegisterWindow().errorDialogListener(dectription);
					}
					else if(failCode == 4)
					{
						myClient.getRegisterToBasaWindow().errorDialogListener(dectription);
					}else if(failCode == 5)
					{
						myClient.getSettingWindow().errorDialogListener(dectription);
					}
					else if(failCode == 6)
					{
						myClient.getAddAnnouncementWindow().errorDialogListener(dectription);
					}
				}
				else if(tmp instanceof SuccessMessage)
				{
					SuccessMessage success = (SuccessMessage) tmp;
					
					int successCode = success.getCodSuccess();
					String dectription = success.getDescription();
					
					if(successCode == 5)
					{
						String salt = success.getDescription2();
						myClient.getSettingWindow().successDialogListener(dectription, salt);
					}
					if(successCode == 6)
					{
						myClient.getAddAnnouncementWindow().successDialogListener(dectription);
					}
				}
				else if(tmp instanceof SettingWindowMessage)
				{
					SettingWindowMessage setting = (SettingWindowMessage) tmp;
					String login = setting.getUserName();
					String firstName = setting.getFirstName();
					String lastName = setting.getLastName();
					String salt = setting.getSalt();
					
					Platform.runLater(() -> {
						myClient.getSettingWindow().setUserInformatorListener(login, firstName, lastName, salt);
					});
				}
				else if(tmp instanceof AnnDataMessage)
				{
					AnnDataMessage ann = (AnnDataMessage) tmp;
					ArrayList<AnnouncementData> ad = ann.getArrayAD();
					
					Platform.runLater(() -> {
						myClient.getMainWindow().updateAnnouncementsTable(ad);
					});
				}
				
	
				
			}
			
			
		}
		catch (IOException | ClassNotFoundException e)
		{
			//Platform.runLater(() -> game.lostConnection());
		}
	}

}
