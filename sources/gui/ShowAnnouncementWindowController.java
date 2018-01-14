package gui;

import java.io.IOException;
import java.util.Map;

import DataBase.AccountsStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowAnnouncementWindowController {
	Announcement announcement;
	AccountsStatus accountStatus;
	
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
	private Button editButton;
	
	@FXML
	private void initialize() {
		accountStatus = AccountsStatus.USER;
		if(accountStatus == AccountsStatus.MOD || accountStatus == AccountsStatus.ADMIN) {
			editButton.setDisable(false);
			editButton.setText("Edytuj");
		}
		else {
			editButton.setDisable(true);
		}
	}
	
	@FXML
	private void editButonActivated(ActionEvent event) {
		
	}
	
	void fillFormsWithData() {
		titleField.setText(announcement.getTitle());
		productNameField.setText(announcement.getProductName());
		categoryField.setText(announcement.getCategory());
		subcategoryField.setText(announcement.getSubcategory());
		displayAttributes();
		descriptionTextArea.setText(announcement.getDescription());
	}
	
	void displayAttributes() {
		TextField tf;
		Label lb;
		int i = 0;
		for(Map.Entry<String, String> a : announcement.getAttributes().entrySet()) {
			lb = new Label();
			lb.setText(a.getKey());
			lb.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			tf = new TextField();
			tf.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			tf.setText(a.getValue());
			tf.setEditable(false);
			
			attributesGrid.add(lb, i, 0);
			attributesGrid.add(tf, i, 1);
			i++;
		}
	}
}
