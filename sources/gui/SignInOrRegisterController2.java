package gui;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignInOrRegisterController2 {

	@FXML
	private Button logInButton;
	

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
}
