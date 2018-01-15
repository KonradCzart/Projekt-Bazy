package gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import client.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AddAnnouncementWindowController {
	@FXML
	private GridPane attributesGrid;
	private Client client;
	
	public AddAnnouncementWindowController()
	{
		this.client = Window.client;
		this.client.setAddAnnouncementWindow(this);
	}

	TextField price;
	ComboBox<String> conditionBox;
	Map<String, String> attributes;
	Announcement newAnnouncement;

	@FXML
	private ComboBox<String> categoryBox;

	@FXML
	private ComboBox<String> subcategoryBox;

	@FXML
	private TextField titleField;

	@FXML
	private TextField productName;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Button addAnnouncement;

	@FXML
	private Button mainPage;

	@FXML
	private void addAnnouncementActivated(ActionEvent event) {
		String title = titleField.getText();
		String product = productName.getText();
		String description = descriptionTextArea.getText();
		if(!title.isEmpty() && !product.isEmpty() && !description.isEmpty() && !subcategoryBox.getSelectionModel().isEmpty()) 
		{
			String category = categoryBox.getValue();
			String subcategory = subcategoryBox.getValue();
	        attributes = getAttributes();
	        if(attributes != null) {
	        	newAnnouncement = new Announcement(product, title,  category, subcategory, attributes, description);
	        	//DATA I CENA NULLAMI
	        	
	        	try {
					client.sendMessage(newAnnouncement);
					clearAnnouncement();
				} catch (IOException e) {
					System.out.println("Nie wys³ano!");
					e.printStackTrace();
				}
	        	
	        }
	        else
	        	System.out.println("Nie wszystkie argumenty!");
		}
	}
	
	void clearAnnouncement() {
		titleField.clear();
		productName.clear();
		price.clear();
		categoryBox.getSelectionModel().clearSelection();
		subcategoryBox.getSelectionModel().clearSelection();
		attributesGrid.getChildren().clear();
		descriptionTextArea.clear();
	}

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

	Map<String, String> getAttributes() {
		HashMap<String, String> attributes = new HashMap<>();
		TextField tf;
		ComboBox<String> cb;
		for (Node node : attributesGrid.getChildren()) {
			if (node instanceof TextField) {
				tf = (TextField) node;
				
				if(tf.getText().trim().isEmpty())
					return null;
				
				attributes.put(tf.getPromptText(), tf.getText().trim());
			} else {
				cb = (ComboBox<String>) node;
				
				if(cb.getSelectionModel().isEmpty())
					return null;
				
				attributes.put(cb.getPromptText(), cb.getValue());
			}
		}
		return attributes;
	}
	
	void setAttribute(String name, String value) {
		attributes.put(name, value);
	}

	@FXML
	private void initialize() {
		price = new TextField();
		price.setPromptText("cena");
		price.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		conditionBox = new ComboBox<>();
		conditionBox.getItems().setAll(Resources.getCondition().get(0), Resources.getCondition().get(1));
		conditionBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		conditionBox.setPromptText("stan");

		categoryBox.getItems().setAll(FXCollections.observableArrayList(Resources.getCategories()));
		categoryBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
				subcategoryBox.getItems().setAll(Resources.getSubcategories(arg2));
				subcategoryBox.setValue(Resources.getSubcategories(arg2).get(0));}
				catch(Exception e) {
					
				}
			}
		});

		subcategoryBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					updateAttributes(newValue);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
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
	
	public void successDialogListener(String successMessage)
	{
		Platform.runLater(() -> {

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText("");
			alert.setContentText(successMessage);

			alert.showAndWait();
		});

	}
	private void updateAttributes(String subcategory) {
		if (subcategory.equals("Samochody osobowe")) {
			ComboBox<String> makesBox = new ComboBox<>();
			makesBox.setPromptText("marka");
			makesBox.getItems().setAll(Resources.getCarMakes());
			makesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			TextField year = new TextField();
			year.setPromptText("rok produkcji");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> milageBox = new ComboBox<>();
			milageBox.setPromptText("przebieg");
			milageBox.getItems().setAll(Resources.getMilageRanges());
			milageBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> fuelBox = new ComboBox<>();
			fuelBox.setPromptText("paliwo");
			fuelBox.getItems().setAll(FXCollections.observableArrayList(Resources.getFuelTypes()));
			fuelBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(makesBox, 1, 0);
			attributesGrid.add(fuelBox, 1, 1);
			attributesGrid.add(year, 2, 0);
			attributesGrid.add(milageBox, 2, 1);

		} else if (subcategory.equals("Samochody ciê¿arowe")) {
			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(Resources.getCarTypes()));
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			typeBox.setPromptText("typ");

			TextField year = new TextField();
			year.setPromptText("rok produkcji");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> milageBox = new ComboBox<>();
			milageBox.setPromptText("przebieg");
			milageBox.getItems().setAll(Resources.getMilageRanges());
			milageBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> fuelBox = new ComboBox<>();
			fuelBox.setPromptText("paliwo");
			fuelBox.getItems().setAll(FXCollections.observableArrayList(Resources.getFuelTypes()));
			fuelBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(typeBox, 1, 0);
			attributesGrid.add(fuelBox, 1, 1);
			attributesGrid.add(year, 2, 0);
			attributesGrid.add(milageBox, 2, 1);

		} else if (subcategory.equals("Motocykle")) {
			
			ComboBox<String> makesBox = new ComboBox<>();
			makesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getMotorcycleMakes()));
			makesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			makesBox.setPromptText("marka");

			TextField year = new TextField();
			year.setPromptText("rok produkcji");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(year, 1, 0);
			attributesGrid.add(makesBox, 1, 1);
			
		} else if (subcategory.equals("Czêœci")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
		} else if (subcategory.equals("Komputery")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(Resources.getComputerManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);

		} else if (subcategory.equals("Telefony")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(Resources.getPhonesManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);
		} else if (subcategory.equals("Sprzêt AGD")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
		} else if (subcategory.equals("Telewizory")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(Resources.getTVManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> screenSizeBox = new ComboBox<>();
			screenSizeBox.setPromptText("przek¹tna");
			screenSizeBox.getItems().setAll(Resources.getScreenSizeRanges());
			screenSizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);
			attributesGrid.add(screenSizeBox, 1, 1);
		} else if (subcategory.equals("Ubrania")) {
			ComboBox<String> sizeBox = new ComboBox<>();
			sizeBox.getItems().setAll(FXCollections.observableArrayList(Resources.getClothesSizes()));
			sizeBox.setPromptText("rozmiar");
			sizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(Resources.getClothesTypes()));
			typeBox.setPromptText("rodzaj");
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(sizeBox, 1, 0);
			attributesGrid.add(typeBox, 1, 1);
		} else if (subcategory.equals("Buty")) {
			ComboBox<String> sizeBox = new ComboBox<>();
			sizeBox.getItems().setAll(FXCollections.observableArrayList(Resources.getShoesSizes()));
			sizeBox.setPromptText("rozmiar");
			sizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(Resources.getClothesTypes()));
			typeBox.setPromptText("rodzaj");
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(sizeBox, 1, 0);
			attributesGrid.add(typeBox, 1, 1);
		} else if (subcategory.equals("Dodatki")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
		} else if (subcategory.equals("Domowe")) {
			ComboBox<String> speciesBox = new ComboBox<>();
			speciesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getDomesticSpecies()));
			speciesBox.setPromptText("gatunek");
			speciesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(speciesBox, 0, 1);
		} else if (subcategory.equals("Gospodarcze")) {
			ComboBox<String> speciesBox = new ComboBox<>();
			speciesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getFarmSpecies()));
			speciesBox.setPromptText("gatunek");
			speciesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(speciesBox, 0, 1);
		} else if (subcategory.equals("Akcesoria")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
		} else if (subcategory.equals("Budowlane")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Motoryzacyjne")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Transportowe")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Inne")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Jêzykowe")) {
			ComboBox<String> languagesBox = new ComboBox<>();
			languagesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getLanguages()));
			languagesBox.setPromptText("jêzyk");
			languagesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			TextField year = new TextField();
			year.setPromptText("rok wydania");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			/*TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);*/

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(languagesBox, 1, 0);
			//attributesGrid.add(author, 1, 1);
			attributesGrid.add(year, 1, 0);

		} else if (subcategory.equals("Naukowe")) {

			TextField year = new TextField();
			year.setPromptText("rok wydania");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			/*TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);*/

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(year, 1, 0);
			//attributesGrid.add(author, 1, 1);

		} else if (subcategory.equals("Literatura")) {

			TextField year = new TextField();
			year.setPromptText("rok wydania");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			/*TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);*/

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			attributesGrid.add(year, 1, 0);
			//attributesGrid.add(author, 1, 1);
		} else if (subcategory.equals("Czasopisma")) {

			ComboBox<String> magazineTypesBox = new ComboBox<>();
			magazineTypesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getMagazineTypes()));
			magazineTypesBox.setPromptText("rodzaj");
			magazineTypesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			TextField year = new TextField();
			year.setPromptText("rok wydania");
			year.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			/*TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);*/

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(conditionBox, 0, 1);
			//attributesGrid.add(author, 1, 0);
			attributesGrid.add(magazineTypesBox, 1, 0);
			attributesGrid.add(year, 1, 1);
		} else if (subcategory.equals("Dzia³ki")) {

			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(Resources.getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(Resources.getAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(transactionType, 0, 1);
			attributesGrid.add(areaBox, 1, 0);
		} else if (subcategory.equals("Domy")) {
			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(Resources.getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(Resources.getHomeAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(transactionType, 0, 1);
			attributesGrid.add(areaBox, 1, 0);
		} else if (subcategory.equals("Mieszkania")) {
			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(Resources.getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(Resources.getFlatAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
			attributesGrid.add(transactionType, 0, 1);
			attributesGrid.add(areaBox, 1, 0);
		} else if (subcategory.equals("Umowa o pracê")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Umowa o dzie³o")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		} else if (subcategory.equals("Umowa zlecenie")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(price, 0, 0);
		}
	}

}
