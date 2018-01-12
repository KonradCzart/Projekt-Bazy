package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Message.*;
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
					}
				}
				
	
				
			}
			
			
		}
		catch (IOException | ClassNotFoundException e)
		{
			//Platform.runLater(() -> game.lostConnection());
		}
	}

}
