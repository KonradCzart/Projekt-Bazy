package gui;
import java.io.IOException;

import Message.*;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MySettingsWindowController {
	
	private Client client;
	public MySettingsWindowController ()
	{
		client = Window.client;
		client.setSettingWindow(this);
		
		SettingWindowMessage newSetting = new SettingWindowMessage();
		try {
			client.sendMessage(newSetting);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	@FXML
	private TextField phoneNumberField;
	
	@FXML
	private TextField locationField;
	
	@FXML
	private Button saveContactData;
	
	@FXML
	private PasswordField currentPasswordField;
	
	@FXML
	private PasswordField newPasswordField;
	
	@FXML
	private PasswordField newPasswordField2;
	
	@FXML
	private Button savePassword;
	
	@FXML
	private TextField userLoginField;
	
	@FXML
	private TextField userNameField;
	
	@FXML
	private TextField userSurnameField;
	
	@FXML
	private Button announcementsButton;

	@FXML
	void announcementsButtonActivated(ActionEvent event) {

		Stage stage = (Stage) announcementsButton.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyAnnouncementsWindow.fxml"));

		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// SignInOrRegisterController controller =
		// loader.<SignInOrRegisterController>getController();
		stage.show();

	}
	
	@FXML
	private void mainPageButtonActivated(ActionEvent event) {
		Stage stage = (Stage) announcementsButton.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// SignInOrRegisterController controller =
		// loader.<SignInOrRegisterController>getController();
		stage.show();
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void setUserInformatorListener(String login, String firstName, String lastName)
	{
		userLoginField.setText(login);
		userNameField.setText(firstName);
		userSurnameField.setText(lastName);
		
	}

	
}
