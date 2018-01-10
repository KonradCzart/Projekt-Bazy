import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowController {

	@FXML
	private Button myAccountButton;

	@FXML
	private Button registerButton;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		Stage stage = (Stage)myAccountButton.getScene().getWindow();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInOrRegisterWindow.fxml"));
		
		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//SignInOrRegisterController controller = loader.<SignInOrRegisterController>getController();
		stage.show();
		
	}
	
	@FXML
	private void handleRegisterAction(ActionEvent event) {
		
		
	}
}
