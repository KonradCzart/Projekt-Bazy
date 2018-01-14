package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowAnnouncementWindowController {
	Announcement announcement;
	
	void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
		fillFormsWithData();
	}
	
	@FXML
	private Button mainPage;
	
	@FXML
	private void mainPageActivated(ActionEvent event) {
		Stage stage = (Stage) mainPage.getScene().getWindow();

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
	
	@FXML
	private TextField titleField;
	
	@FXML
	private TextField productNameField;
	
	@FXML
	private TextField categoryField;
	
	@FXML
	private TextField subcategoryField;
	
	@FXML
	private GridPane attributesGrid;
	
	@FXML
	private TextArea descriptionTextArea;
	
	@FXML
	private void initialize() {
		
	}
	
	void fillFormsWithData() {
		titleField.setText(announcement.getTitle());
		productNameField.setText(announcement.getProductName());
		categoryField.setText(announcement.getCategory());
		subcategoryField.setText(announcement.getSubcategory());
		//fill attribute grid
		descriptionTextArea.setText(announcement.getDescription());
	}
}
