package gui;

import client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Application {
	
	public static boolean isLoggedIn = false;
	public static Client client = new Client();

	@Override
	public void start(Stage primaryStage) throws Exception {
		client.connectServer();
		client.startServerListener();
		Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("CzaKi Trade");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
