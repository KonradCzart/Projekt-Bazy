package gui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInOrRegisterController2 {

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

		//NACIŒNIETO ZAREJESTRUJ (ten na dole)

	}
}
