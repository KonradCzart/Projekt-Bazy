package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import Message.*;
import javafx.application.Platform;

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
				
//				if(tmp instanceof ChatMessage)
//				{
//					
//
//				}
				
	
				
			}
			
			
		}
		catch (IOException | ClassNotFoundException e)
		{
			//Platform.runLater(() -> game.lostConnection());
		}
	}

}
