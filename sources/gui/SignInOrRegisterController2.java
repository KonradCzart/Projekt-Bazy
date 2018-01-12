package gui;
import java.io.IOException;
import java.util.regex.Pattern;

import Message.RegisterMessage;
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

public class SignInOrRegisterController2 {

	private Client client;
	
	@FXML
	private Button logInButton;
	
	@FXML
	private Button registerMeButton;
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField surnameField;
	
	@FXML
	private TextField phoneNumberField;
	
	@FXML
	private TextField loginField;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private PasswordField password2Field;
	
	String getName() {
		return nameField.getText();
	}
	
	String getSurname() {
		return surnameField.getText();
	}
	
	String getPhoneNumber() {
		return phoneNumberField.getText();
	}
	
	String getLogin() {
		return loginField.getText();
	}
	
	String getPassword() {
		return passwordField.getText();
	}
	
	String getPassword2() {
		return password2Field.getText();
	}

	@FXML
	void handleLogInButtonActive(ActionEvent event) {

		Stage stage = (Stage) logInButton.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInOrRegisterWindow.fxml"));

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
	void handleRegisterMeButtonActive(ActionEvent event) {

		RegisterMessage register = new RegisterMessage();
		register.setFirstName(getName());
		register.setLastName(getSurname());
		register.setUserName(getLogin());
		String phoneNumber = this.getPhoneNumber();
		String password1 = this.getPassword();
		String password2 = this.getPassword2();
		
		if(!Pattern.matches("[0-9]{9}", phoneNumber))
		{
			this.errorDialogListener("Niepoprawny numer telefonu");
		}
		else if(password1.equals(password2))
		{
			//to do haszowanie has³a
			int number = Integer.parseInt(phoneNumber);
			register.setNumber(number);
			
			register.setPasswordHash(password1);
			register.setSalt(password1);
			
			
			try {
				client.sendMessage(register);
				logInButton.setText("");
				registerMeButton.setText("");
				nameField.setText("");
				surnameField.setText("");
				phoneNumberField.setText("");
				loginField.setText("");
				passwordField.setText("");
				password2Field.setText("");
			} catch (IOException e) {
				System.out.println("nie wyslano");
			}
		}
		else
		{
			this.errorDialogListener("Wpisane has³a musz¹ byæ jednakowe!");
		}
		
		

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
