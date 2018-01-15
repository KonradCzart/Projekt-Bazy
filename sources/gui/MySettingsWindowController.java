package gui;
import java.io.IOException;
import java.io.InputStream;

import DataBase.AccountsStatus;
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
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MySettingsWindowController {
	
	private Client client;
	private String oldSalt;
	private AccountsStatus accountStatus;
	
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
	private TitledPane backupRestoreSection;
	
	@FXML
	private Button makeBackup;
	
	
	public boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {
		 
        String executeCmd = "mysqldump --routines -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + path;
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe","/c", executeCmd});
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                successBackupRestore("Stworzono backup");
                return true;
            } else {
                errorBackupRestore("Nie uda³o siê stworzyæ backupu");
                InputStream errorStream = runtimeProcess.getErrorStream();
                byte[] buffer = new byte[errorStream.available()];
                errorStream.read(buffer);

                String str = new String(buffer);
                System.out.println(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
	
	
	@FXML
	private void makeBackupActivated(ActionEvent event) {
		backupDB("czaki", "admin", "admin123", " > plik.sql");
	}
	
	@FXML
	private Button restoreDatabase;
	
	
	public boolean restoreDB(String dbUserName, String dbPassword, String source) {
		 
        String[] executeCmd = new String[]{"cmd.exe","/c","mysql", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source "+source};
 
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                successBackupRestore("Przywrócono bazê");
                return true;
            } else {
            	errorBackupRestore("Nie uda³o siê przywróciæ");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return false;
    }
	
	
	@FXML
	private void restoreDatabaseActivated(ActionEvent event) {
		restoreDB("admin", "admin123", "plik.sql");
	}
	
	@FXML
	private void initialize() {
//		accountStatus = AccountsStatus.USER;
//		if(accountStatus != AccountsStatus.ADMIN) {
//			backupRestoreSection.setCollapsible(false);
//			backupRestoreSection.setText("");
//		}
	}

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
	
	@FXML
	private void saveContactDataActivated(ActionEvent event) {
		
	}
	
	@FXML
	private void savePasswordActivated(ActionEvent event) {
		
		String oldPassword = currentPasswordField.getText();
		String newPassword1 = newPasswordField.getText();
		String newPassword2 = newPasswordField2.getText();
		
		if(newPassword1.equals(newPassword2))
		{
			PasswordHash secure = new PasswordHash();
			String salt = secure.getNextSalt();
			System.out.println(oldPassword);
			String hashPasswordOld = secure.hashPassword(oldPassword, oldSalt);
			System.out.println(hashPasswordOld);
			System.out.println(oldSalt);
			String hashPasswordNew = secure.hashPassword(newPassword1, salt);
			
			ChangeSettings setting = new ChangeSettings();
			setting.setNewHashPassword(hashPasswordNew);
			setting.setOldHashPassword(hashPasswordOld);
			setting.setNewSalt(salt);
			
			try {
				client.sendMessage(setting);
			} catch (IOException e) {
				System.out.print("nie wyslano!");
			}
		}
		else
			this.errorDialogListener("Nowe has³a musz¹ byæ identyczne!");
		
	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	public void setUserInformatorListener(String login, String firstName, String lastName, String salt, AccountsStatus status)
	{
		userLoginField.setText(login);
		userNameField.setText(firstName);
		userSurnameField.setText(lastName);
		this.oldSalt = salt;
		accountStatus = status;
		if(accountStatus != AccountsStatus.ADMIN) {
			backupRestoreSection.setCollapsible(false);
			backupRestoreSection.setText("");
		}
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
	
	public void successDialogListener(String successMessage, String salt)
	{
		this.oldSalt = salt;
		this.newPasswordField.setText("");
		this.newPasswordField2.setText("");
		this.currentPasswordField.setText("");
		
		Platform.runLater(() -> {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("");
			alert.setContentText(successMessage);

			alert.showAndWait();
		});

	}
	
	public void errorBackupRestore(String errorMessage)
	{
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("");
			alert.setContentText(errorMessage);

			alert.showAndWait();
		});
	}
	
	public void successBackupRestore(String successMessage)
	{
		Platform.runLater(() -> {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("");
			alert.setContentText(successMessage);

			alert.showAndWait();
		});

	}
}
