package gui;
import java.io.IOException;

import DataBase.PasswordHash;
import Message.*;
import client.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInOrRegisterController {
	
	
	private Client client;
	private String userName;
	
	@FXML
	private Button registerButton;
	
	@FXML
	private Button logMeInButton;
	
	@FXML
	private TextField userNameTextField;
	
	@FXML 
	private PasswordField passwordTextField;
	
	String getUserName() {
		return userNameTextField.getText();
	}
	
	String getPassword() {
		return passwordTextField.getText();
	}

	@FXML
	void handleRegisterAction(ActionEvent event) {

		Stage stage = (Stage) registerButton.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInOrRegisterWindow2.fxml"));

		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SignInOrRegisterController2 controller = loader.<SignInOrRegisterController2>getController();
		controller.setClient(client);
		client.setRegisterToBasaWindow(controller);
		stage.show();

	}
	
	@FXML
	private void logMeInButtonActivated(ActionEvent event) {

		LoginMessage newMessage = new LoginMessage(true);
		userName = getUserName();
		newMessage.setUserName(userName);
		
		try {
			client.sendMessage(newMessage);
		} catch (IOException e) {
			System.out.println("Nie udalo sie wyslac wiadomosci");
		}

	}
	
	// metoda wywolywana przez server
	public void hashPaswordListener(String salt)
	{
		// TO DO zrobic haszowanie hasla przy pomocy otrzymanego stringa salt
		PasswordHash secure = new PasswordHash();
		String password = getPassword();
		String passwordHash = secure.hashPassword(password, salt);
		System.out.println(salt);
		LoginMessage newMessage = new LoginMessage(false);
		newMessage.setUserName(userName);
		newMessage.setHashPassword(passwordHash);
		userNameTextField.setText("");
		passwordTextField.setText("");
		try {
			client.sendMessage(newMessage);
		} catch (IOException e) {
			System.out.println("Nie udalo sie wyslac wiadomosci");
		}
		
	}
	
	// metoda wywolywana przez server
	public void logMeActivatedListener()
	{
		Window.isLoggedIn = true;
		Stage stage = (Stage) registerButton.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyAnnouncementsWindow.fxml"));

		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		stage.show();

	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void errorDialogListener(String errorMessage)
	{
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("");
			alert.setContentText(errorMessage);

			alert.showAndWait();
		});
	}
}
