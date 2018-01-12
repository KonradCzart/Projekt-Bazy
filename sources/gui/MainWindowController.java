package gui;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import org.omg.CORBA.INITIALIZE;

import client.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainWindowController {

	private Client client;
	public MainWindowController() {

		client = new Client();
		try {
			client.connectServer();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.setMainWindow(this);
		client.startServerListener();
				
	}

	ArrayList<String> getCategories() {
		return new ArrayList<String>(Arrays.asList("Motoryzacja", "Elektronika", "Ubrania", "Zwierzêta", "Us³ugi",
				"Praca", "Ksi¹¿ki", "Nieruchomoœi"));
	}

	ArrayList<String> getSubcategories(String category) {
		if (category.equals(motorisationButton.getText())) {
			return new ArrayList<String>(
					Arrays.asList("Samochody osobowe", "Samochody ciê¿arowe", "Motocykle", "Czêœci"));
		} else if (category.equals(electronicsButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Komputery", "Telefony", "Sprzêt AGD", "Telewizory"));
		} else if (category.equals(clothesButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Ubrania", "Buty", "Dodatki"));
		} else if (category.equals(animalsButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Domowe", "Gospodarcze", "Akcesoria"));
		} else if (category.equals(servicesButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Budowlane", "Motoryzacyjne", "Transportowe", "Inne"));
		} else if (category.equals(booksButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Jêzykowe", "Naukowe", "Literatura", "Czasopisma"));
		} else if (category.equals(jobButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Umowa o pracê", "Umowa zlecenie", "Umowa o dzie³o"));
		} else if (category.equals(propertyButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Dzia³ki", "Domy", "Mieszkania", "Wynajem"));
		} else
			return new ArrayList<String>(Arrays.asList("B£AD"));
	}

	ArrayList<String> getCondition() {
		return new ArrayList<String>(Arrays.asList("nowy", "u¿ywany", "dowolny"));
	}

	ArrayList<String> getCarMakes() {
		return new ArrayList<String>(Arrays.asList("Audi", "BMW", "Chevrolet", "Citroën", "Dacia", "Daewoo", "Fiat",
				"Ford", "Honda", "Hyundai", "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault",
				"Skoda", "Suzuki", "Toyota", "Volkswagen", "Pozosta³e"));
	}
	
	ArrayList<String> getFuelTypes() {
		return new ArrayList<String>(Arrays.asList("Benzyna", "Gaz"));
	}
	
	ArrayList<String> getCarTypes() {
		return new ArrayList<String>(Arrays.asList("Dostawcze", "Terenowe"));
	}
	
	ArrayList<String> getMotorcycleMakes() {
		return new ArrayList<String>(Arrays.asList("Aprilia", "Barton", "Benelli", "Beta", "Bmw", "Cagiva", "Ducati",
				"GAS GAS", "Harley-Davidson", "Honda", "Horex", "Husqvarna", "Jawa", "Junak", "Kawasaki", "Kingway", "MZ",
				"Romet", "Suzuki", "Victory", "Yamaha", "Zipp", "Pozosta³e"));
	}
	
	ArrayList<String> getComputerManufacturers() {
		return new ArrayList<String>(Arrays.asList("Acer", "Apple", "Asus", "Dell", "Fujitsu", "HP", "Lenovo",
				"MSI", "Toshiba", "Pozosta³e"));
	}
	
	ArrayList<String> getPhonesManufacturers() {
		return new ArrayList<String>(Arrays.asList("Apple", "HTC", "Huawei", "LG", "Micorosoft", "Motorola", "Nokia",
				"Samsung", "Sony", "Pozosta³e"));
	}
	
	ArrayList<String> getTVManufacturers() {
		return new ArrayList<String>(Arrays.asList("Samsung", "Sony", "Philips", "LG", "Panasonic", "Pozosta³e"));
	}
	
	ArrayList<String> getClothesSizes() {
		return new ArrayList<String>(Arrays.asList("XS", "S", "M", "L", "XL", "XXL", "XXXL"));
	}
	
	ArrayList<String> getClothesTypes() {
		return new ArrayList<String>(Arrays.asList("mêskie", "damskie"));
	}
	
	ArrayList<String> getShoesSizes() {
		return new ArrayList<String>(Arrays.asList("35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"));
	}
	
	ArrayList<String> getDomesticSpecies() {
		return new ArrayList<String>(Arrays.asList("Psy", "Koty", "Ptaki", "Króliki", "Gryzonie", "Rybki", "Pozosta³e"));
	}
	
	ArrayList<String> getFarmSpecies() {
		return new ArrayList<String>(Arrays.asList("Byd³o", "Drób", "Kozy", "Owce", "Trzoda", "Pozosta³e"));
	}

	@FXML
	private void initialize() {
		categoryBox.getItems().setAll(FXCollections.observableArrayList(getCategories()));
		conditionBox.getItems().setAll(FXCollections.observableArrayList(getCondition()));
	}

	@FXML
	private Button myAccountButton;

	@FXML
	private Button registerButton;

	@FXML
	private TextField searchField;

	@FXML
	private ComboBox<String> categoryBox;

	@FXML
	private GridPane subcategoryGrid;

	@FXML
	private GridPane attributesGrid;

	@FXML
	private Button searchButton;

	@FXML
	private void searchButtonActivated(ActionEvent event) {
		// SEAECH
	}

	@FXML
	private Label categoryLabel;

	@FXML
	private TextField minPrice;

	@FXML
	private TextField maxPrice;

	@FXML
	private ComboBox<String> conditionBox;

	@FXML
	private Button motorisationButton;

	private void updateSubcategoryPane(String category) {
		if (!category.equals(categoryLabel.getText())) {
			ArrayList<String> subcategories = getSubcategories(category);
			Button button;
			subcategoryGrid.getChildren().clear();
			// categoryLabel = new Label(motorisationButton.getText());
			// categoryLabel.setTextAlignment(TextAlignment.CENTER);
			// categoryLabel.setAlignment(Pos.CENTER);
			// categoryLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			categoryLabel.setText(category);
			subcategoryGrid.add(categoryLabel, 0, 0);
			for (int i = 0; i < subcategories.size(); i++) {
				button = new Button(subcategories.get(i));
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				subcategoryGrid.add(button, 0, i + 1);
				String subName = subcategories.get(i);
				button.setOnAction(new EventHandler<ActionEvent>() {
					String mySubcategory = subName;

					@Override
					public void handle(ActionEvent event) {
						updateAttributes(mySubcategory);
					}

				});
			}
		}
	}

	private void updateAttributes(String subcategory) {
		if (subcategory.equals("Samochody osobowe")) {
			ComboBox<String> makesBox = new ComboBox<>();
			makesBox.setPromptText("marka");
			makesBox.getItems().setAll(getCarMakes());
			makesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok prod. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok prod. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minMilage = new TextField();
			minMilage.setPromptText("przebieg od");
			minMilage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxMilage = new TextField();
			maxMilage.setPromptText("przebieg do");
			maxMilage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> fuelBox = new ComboBox<>();
			fuelBox.setPromptText("paliwo");
			fuelBox.getItems().setAll(FXCollections.observableArrayList(getFuelTypes()));
			fuelBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(makesBox, 1, 0);
			attributesGrid.add(fuelBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			attributesGrid.add(minMilage, 3, 0);
			attributesGrid.add(maxMilage, 3, 1);
			
		}
		else if(subcategory.equals("Samochody ciê¿arowe")) {
			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(getCarTypes()));
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			typeBox.setPromptText("typ");
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok prod. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok prod. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minMilage = new TextField();
			minMilage.setPromptText("przebieg od");
			minMilage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxMilage = new TextField();
			maxMilage.setPromptText("przebieg do");
			maxMilage.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> fuelBox = new ComboBox<>();
			fuelBox.setPromptText("paliwo");
			fuelBox.getItems().setAll(FXCollections.observableArrayList(getFuelTypes()));
			fuelBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(typeBox, 1, 0);
			attributesGrid.add(fuelBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			attributesGrid.add(minMilage, 3, 0);
			attributesGrid.add(maxMilage, 3, 1);
			
		}
		else if(subcategory.equals("Motocykle")) {
			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(getCarTypes()));
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			typeBox.setPromptText("typ");
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok prod. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok prod. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(typeBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);			
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
		}
		else if(subcategory.equals("Czêœci")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
		}
		else if(subcategory.equals("Komputery")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(getComputerManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
		}
		else if(subcategory.equals("Telefony")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(getPhonesManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
		}
		else if(subcategory.equals("Sprzêt AGD")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
		}
		else if(subcategory.equals("Telewizory")) {
			ComboBox<String> manufacturerBox = new ComboBox<>();
			manufacturerBox.getItems().setAll(FXCollections.observableArrayList(getTVManufacturers()));
			manufacturerBox.setPromptText("producent");
			manufacturerBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minSize = new TextField();
			minSize.setPromptText("przek¹tna od");
			minSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxSize = new TextField();
			maxSize.setPromptText("przek¹tna do");
			maxSize.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(manufacturerBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
			attributesGrid.add(minSize, 2, 0);
			attributesGrid.add(maxSize, 2, 1);
		}
		else if(subcategory.equals("Ubrania")) {
			ComboBox<String> sizeBox = new ComboBox<>();
			sizeBox.getItems().setAll(FXCollections.observableArrayList(getClothesSizes()));
			sizeBox.setPromptText("rozmiar");
			sizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(getClothesTypes()));
			typeBox.setPromptText("rodzaj");
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(sizeBox, 1, 0);
			attributesGrid.add(typeBox, 1, 1);
		}
		else if(subcategory.equals("Buty")) {
			ComboBox<String> sizeBox = new ComboBox<>();
			sizeBox.getItems().setAll(FXCollections.observableArrayList(getShoesSizes()));
			sizeBox.setPromptText("rozmiar");
			sizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> typeBox = new ComboBox<>();
			typeBox.getItems().setAll(FXCollections.observableArrayList(getClothesTypes()));
			typeBox.setPromptText("rodzaj");
			typeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(sizeBox, 1, 0);
			attributesGrid.add(typeBox, 1, 1);
		}
		else if(subcategory.equals("Dodatki")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
		}
		else if(subcategory.equals("Domowe")) {
			ComboBox<String> speciesBox = new ComboBox<>();
			speciesBox.getItems().setAll(FXCollections.observableArrayList(getDomesticSpecies()));
			speciesBox.setPromptText("gatunek");
			speciesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(speciesBox, 0, 2);
		}
		else if(subcategory.equals("Gospodarcze")) {
			ComboBox<String> speciesBox = new ComboBox<>();
			speciesBox.getItems().setAll(FXCollections.observableArrayList(getFarmSpecies()));
			speciesBox.setPromptText("gatunek");
			speciesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(speciesBox, 0, 2);
		}
		else if(subcategory.equals("Akcesoria")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
		}
	}

	@FXML
	private void motorisationButtonActivated(ActionEvent event) {
		// MOTORYZACJA
		updateSubcategoryPane(motorisationButton.getText());
	}

	@FXML
	private Button electronicsButton;

	@FXML
	private void electronicsButtonActivated(ActionEvent event) {
		// ELEKTRONIKA
		updateSubcategoryPane(electronicsButton.getText());
	}

	@FXML
	private Button clothesButton;

	@FXML
	private void clothesButtonActivated(ActionEvent event) {
		// UBRANIA
		updateSubcategoryPane(clothesButton.getText());
	}

	@FXML
	private Button animalsButton;

	@FXML
	private void animalsButtonActivated(ActionEvent event) {
		// ZWIERZÊTA
		updateSubcategoryPane(animalsButton.getText());
	}

	@FXML
	private Button servicesButton;

	@FXML
	private void servicesButtonAcivated(ActionEvent event) {
		// US£UGI
		updateSubcategoryPane(servicesButton.getText());
	}

	@FXML
	private Button jobButton;

	@FXML
	private void jobButtonActivated(ActionEvent event) {
		// PRACA
		updateSubcategoryPane(jobButton.getText());
	}

	@FXML
	private Button booksButton;

	@FXML
	private void booksButtonActivated(ActionEvent event) {
		// KSIA¯KI
		updateSubcategoryPane(booksButton.getText());
	}

	@FXML
	private Button propertyButton;

	@FXML
	private void propertyButtonActivated(ActionEvent event) {
		// NIERÓCHOMOŒÆI
		updateSubcategoryPane(propertyButton.getText());
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		Stage stage = (Stage) myAccountButton.getScene().getWindow();
		FXMLLoader loader;
		if (Window.isLoggedIn) {
			loader = new FXMLLoader(getClass().getResource("MyAnnouncementsWindow.fxml"));
		} else {
			loader = new FXMLLoader(getClass().getResource("SignInOrRegisterWindow.fxml"));
			
		}

		try {
			Scene scene = new Scene((Pane) loader.load());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (Window.isLoggedIn) { 
			MyAnnouncementsWindowController controller = loader.<MyAnnouncementsWindowController>getController();
			client.setAnnouncementWindow(controller);
			controller.setClient(client);
		}
		else {
			SignInOrRegisterController controller = loader.<SignInOrRegisterController>getController();
			client.setRegisterWindow(controller);
			controller.setClient(client);
		}
		
		
		stage.show();

	}

	@FXML
	private void handleRegisterAction(ActionEvent event) {

	}
	
	public void setClient(Client client)
	{
		this.client = client;
	}
}
