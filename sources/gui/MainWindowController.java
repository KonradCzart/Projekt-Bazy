package gui;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import org.omg.CORBA.INITIALIZE;

import client.Client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
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
		
		client = Window.client;
		client.setMainWindow(this);
	}
	


	ArrayList<String> getCategories() {
		return new ArrayList<String>(Arrays.asList("Motoryzacja", "Elektronika", "Ubrania", "Zwierzęta", "Usługi",
				"Praca", "Książki", "Nieruchomośi"));
	}

	ArrayList<String> getSubcategories(String category) {
		if (category.equals(motorisationButton.getText())) {
			return new ArrayList<String>(
					Arrays.asList("Samochody osobowe", "Samochody ciężarowe", "Motocykle", "Części"));
		} else if (category.equals(electronicsButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Komputery", "Telefony", "Sprzęt AGD", "Telewizory"));
		} else if (category.equals(clothesButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Ubrania", "Buty", "Dodatki"));
		} else if (category.equals(animalsButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Domowe", "Gospodarcze", "Akcesoria"));
		} else if (category.equals(servicesButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Budowlane", "Motoryzacyjne", "Transportowe", "Inne"));
		} else if (category.equals(booksButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Językowe", "Naukowe", "Literatura", "Czasopisma"));
		} else if (category.equals(jobButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Umowa o pracę", "Umowa zlecenie", "Umowa o dzieło"));
		} else if (category.equals(propertyButton.getText())) {
			return new ArrayList<String>(Arrays.asList("Działki", "Domy", "Mieszkania"));
		} else
			return new ArrayList<String>(Arrays.asList("BŁAD"));
	}

	ArrayList<String> getCondition() {
		return new ArrayList<String>(Arrays.asList("nowy", "używany", "dowolny"));
	}

	ArrayList<String> getCarMakes() {
		return new ArrayList<String>(Arrays.asList("Audi", "BMW", "Chevrolet", "Citroën", "Dacia", "Daewoo", "Fiat",
				"Ford", "Honda", "Hyundai", "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault",
				"Skoda", "Suzuki", "Toyota", "Volkswagen", "Pozostałe"));
	}
	
	ArrayList<String> getFuelTypes() {
		return new ArrayList<String>(Arrays.asList("Benzyna", "Dissel"));
	}
	
	ArrayList<String> getCarTypes() {
		return new ArrayList<String>(Arrays.asList("Dostawcze", "Terenowe"));
	}
	
	ArrayList<String> getMotorcycleMakes() {
		return new ArrayList<String>(Arrays.asList("Aprilia", "Barton", "Benelli", "Beta", "Bmw", "Cagiva", "Ducati",
				"GAS GAS", "Harley-Davidson", "Honda", "Horex", "Husqvarna", "Jawa", "Junak", "Kawasaki", "Kingway", "MZ",
				"Romet", "Suzuki", "Victory", "Yamaha", "Zipp", "Pozostałe"));
	}
	
	ArrayList<String> getComputerManufacturers() {
		return new ArrayList<String>(Arrays.asList("Acer", "Apple", "Asus", "Dell", "Fujitsu", "HP", "Lenovo",
				"MSI", "Toshiba", "Pozostałe"));
	}
	
	ArrayList<String> getPhonesManufacturers() {
		return new ArrayList<String>(Arrays.asList("Apple", "HTC", "Huawei", "LG", "Micorosoft", "Motorola", "Nokia",
				"Samsung", "Sony", "Pozostałe"));
	}
	
	ArrayList<String> getTVManufacturers() {
		return new ArrayList<String>(Arrays.asList("Samsung", "Sony", "Philips", "LG", "Panasonic", "Pozostałe"));
	}
	
	ArrayList<String> getClothesSizes() {
		return new ArrayList<String>(Arrays.asList("XS", "S", "M", "L", "XL", "XXL", "XXXL"));
	}
	
	ArrayList<String> getClothesTypes() {
		return new ArrayList<String>(Arrays.asList("męskie", "damskie"));
	}
	
	ArrayList<String> getShoesSizes() {
		return new ArrayList<String>(Arrays.asList("35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"));
	}
	
	ArrayList<String> getDomesticSpecies() {
		return new ArrayList<String>(Arrays.asList("Psy", "Koty", "Ptaki", "Króliki", "Gryzonie", "Rybki", "Pozostałe"));
	}
	
	ArrayList<String> getFarmSpecies() {
		return new ArrayList<String>(Arrays.asList("Bydło", "Drób", "Kozy", "Owce", "Trzoda", "Pozostałe"));
	}
	
	ArrayList<String> getLanguages() {
		return new ArrayList<String>(Arrays.asList("Angielski", "Francuski", "Niemiecki", "Rosyjski", "Inne"));
	}
	
	ArrayList<String> getGenres() {
		return new ArrayList<String>(Arrays.asList("Angielski", "Francuski", "Niemiecki", "Rosyjski", "Inne"));
	}
	
	ArrayList<String> getMagazineTypes() {
		return new ArrayList<String>(Arrays.asList("Młodzieżowe", "Przyrodnicze", "Informatyczne", "Popularnonaukowe", "Kulinarne", "Motoryzacyjne", "Erotyczne", "Inne"));
	}
	
	ArrayList<String> getTransactionTypes() {
		return new ArrayList<String>(Arrays.asList("Sprzedaż", "Wynajem"));
	}
	
	ArrayList<String> getMilageRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 50 tys", "50 - 150 tys", "150 - 250 tys", "powyżej 250 tys"));
	}
	
	ArrayList<String> getAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 500 m²", "500 - 1000 m²", "1000 - 2000 m²", "powyżej 2000 m²"));
	}
	
	ArrayList<String> getHomeAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 50 m²", "50 - 100 m²", "100 - 200 m²", "powyżej 200 m²"));
	}
	
	ArrayList<String> getFlatAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 25 m²", "25 - 50 m²", "50 - 100 m²", "powyżej 100 m²"));
	}
	
	ArrayList<String> getScreenSizeRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 30", "30 - 40", "40 - 50", "powyżej 50"));
	}
	
	
//	ObservableList<AnnouncementInfo> getRecentAnnouncements() {
//		return FXCollections.observableArrayList(new ArrayList(new AnnouncementInfo(date, productName, title, price)))
//	}

	@FXML
	private void initialize() {
		
		minPrice = new TextField();
		minPrice.setPromptText("cena od");
		minPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		maxPrice = new TextField();
		maxPrice.setPromptText("cena do");
		maxPrice.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		conditionBox = new ComboBox<>();
		conditionBox.getItems().setAll(getCondition());
		conditionBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		conditionBox.setPromptText("stan");
		
		categoryBox.getItems().setAll(FXCollections.observableArrayList(getCategories()));
		conditionBox.getItems().setAll(FXCollections.observableArrayList(getCondition()));
		
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().startDateProperty());
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
		AnnouncementInfo a1 = new AnnouncementInfo();
		a1.setStartDate(new Date(118, 6, 12));
		a1.setProductName("Audi R8");
		a1.setTitle("Wszystkie na to lecą!!!");
		a1.setPrice(12);
		
		AnnouncementInfo a2 = new AnnouncementInfo();
		a2.setStartDate(new Date(112, 6, 12));
		a2.setProductName("Opel Astra");
		a2.setTitle("Opla sprzedam");
		a2.setPrice(1100);
		
		ArrayList<AnnouncementInfo> alist = new ArrayList<>(Arrays.asList(a1, a2, new AnnouncementInfo("2017-03-19", "Harnaś", "Skrzynka piwa Harnaś", 11.47)));
		announcementsList = FXCollections.observableArrayList(alist);
		setAnnouncements(announcementsList);
		addAnnauncement(new AnnouncementInfo("2017-09-16", "Perłą", "Najlepsze piwo", 2.49));
		
		if(isUserLoggedIn()) {
			
		}
		
	}
	
	private ObservableList<AnnouncementInfo> announcementsList;
	
	void setAnnouncements(ObservableList<AnnouncementInfo> list) {
		announcementsTable.setItems(list);
	}
	
	void addAnnauncement(AnnouncementInfo annauncement) {
		announcementsList.add(annauncement);
	}
	
	boolean isUserLoggedIn() {
		return Window.isLoggedIn;
	}

	@FXML
	private Button myAccountButton;
	
	@FXML
	private Button addAnnouncementButton;

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
	private TableView announcementsTable;
	
	@FXML
	private TableColumn<AnnouncementInfo, Date> dateColumn;
	
	@FXML
	private TableColumn<AnnouncementInfo, String> productNameColumn;
	
	@FXML
	private TableColumn<AnnouncementInfo, String> titleColumn;
	
	@FXML
	private TableColumn<AnnouncementInfo, Double> priceColumn;

	@FXML
	private void searchButtonActivated(ActionEvent event) {
		// SEAECH
	}

	@FXML
	private Label categoryLabel;

	
	private TextField minPrice;

	
	private TextField maxPrice;

	
	private ComboBox<String> conditionBox;

	@FXML
	private Button motorisationButton;

	private void updateSubcategoryPane(String category) {
		if (!category.equals(categoryLabel.getText())) {
			attributesGrid.getChildren().clear();
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
			
			ComboBox<String> milageBox = new ComboBox<>();
			milageBox.setPromptText("przebieg");
			milageBox.getItems().setAll(getMilageRanges());
			milageBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
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
			attributesGrid.add(milageBox, 1, 2);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			
			
		}
		else if(subcategory.equals("Samochody ciężarowe")) {
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
			
			ComboBox<String> milageBox = new ComboBox<>();
			milageBox.setPromptText("przebieg");
			milageBox.getItems().setAll(getMilageRanges());
			milageBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
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
			attributesGrid.add(milageBox, 1, 2);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			
		}
		else if(subcategory.equals("Motocykle")) {
			ComboBox<String> makesBox = new ComboBox<>();
			makesBox.getItems().setAll(FXCollections.observableArrayList(Resources.getMotorcycleMakes()));
			makesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			makesBox.setPromptText("marka");
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok prod. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok prod. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(makesBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);			
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
		}
		else if(subcategory.equals("Części")) {
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
		else if(subcategory.equals("Sprzęt AGD")) {
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
			
			ComboBox<String> screenSizeBox = new ComboBox<>();
			screenSizeBox.setPromptText("przekątna");
			screenSizeBox.getItems().setAll(getScreenSizeRanges());
			screenSizeBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(manufacturerBox, 1, 0);
			attributesGrid.add(screenSizeBox, 1, 1);
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
		else if(subcategory.equals("Budowlane")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Motoryzacyjne")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Transportowe")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Inne")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Językowe")) {
			ComboBox<String> languagesBox = new ComboBox<>();
			languagesBox.getItems().setAll(FXCollections.observableArrayList(getLanguages()));
			languagesBox.setPromptText("język");
			languagesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok wyd. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok wyd. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(languagesBox, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			attributesGrid.add(author, 3, 0);
			
		}
		else if(subcategory.equals("Naukowe")) {
		
			TextField minYear = new TextField();
			minYear.setPromptText("rok wyd. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok wyd. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(author, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
			
		}
		else if(subcategory.equals("Literatura")) {
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok wyd. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok wyd. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(author, 1, 0);
			attributesGrid.add(conditionBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
		}
		else if(subcategory.equals("Czasopisma")) {
			
			ComboBox<String> magazineTypesBox = new ComboBox<>();
			magazineTypesBox.getItems().setAll(FXCollections.observableArrayList(getMagazineTypes()));
			magazineTypesBox.setPromptText("rodzaj");
			magazineTypesBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField minYear = new TextField();
			minYear.setPromptText("rok wyd. od");
			minYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField maxYear = new TextField();
			maxYear.setPromptText("rok wyd. do");
			maxYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			TextField author = new TextField();
			author.setPromptText("autor");
			author.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(conditionBox, 0, 2);
			attributesGrid.add(author, 1, 0);
			attributesGrid.add(magazineTypesBox, 1, 1);
			attributesGrid.add(minYear, 2, 0);
			attributesGrid.add(maxYear, 2, 1);
		}
		else if(subcategory.equals("Działki")) {
			
			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(getAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(transactionType, 1, 0);
			attributesGrid.add(areaBox, 1, 1);
		}
		else if(subcategory.equals("Domy")) {
			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(getHomeAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(transactionType, 1, 0);
			attributesGrid.add(areaBox, 1, 1);
		}
		else if(subcategory.equals("Mieszkania")) {
			ComboBox<String> transactionType = new ComboBox<>();
			transactionType.getItems().setAll(FXCollections.observableArrayList(getTransactionTypes()));
			transactionType.setPromptText("rodzaj oferty");
			transactionType.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			
			ComboBox<String> areaBox = new ComboBox<>();
			areaBox.setPromptText("powierzchnia");
			areaBox.getItems().setAll(getFlatAreaRanges());
			areaBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

			
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
			attributesGrid.add(transactionType, 1, 0);
			attributesGrid.add(areaBox, 1, 1);
		}
		else if(subcategory.equals("Umowa o pracę")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Umowa o dzieło")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
		else if(subcategory.equals("Umowa zlecenie")) {
			attributesGrid.getChildren().clear();
			attributesGrid.add(minPrice, 0, 0);
			attributesGrid.add(maxPrice, 0, 1);
		}
	}

	@FXML
	private void addAnnouncementButtonActivated(ActionEvent event) {
			Stage stage = (Stage) myAccountButton.getScene().getWindow();
			FXMLLoader loader;
			if (isUserLoggedIn()) {
				loader = new FXMLLoader(getClass().getResource("AddAnnouncementWindow.fxml"));
			} else {
				loader = new FXMLLoader(getClass().getResource("SignInOrRegisterWindow.fxml"));
				
			}

			try {
				Scene scene = new Scene((Pane) loader.load());
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
			stage.show();
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
		// ZWIERZĘTA
		updateSubcategoryPane(animalsButton.getText());
	}

	@FXML
	private Button servicesButton;

	@FXML
	private void servicesButtonAcivated(ActionEvent event) {
		// USŁUGI
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
		// KSIAŻKI
		updateSubcategoryPane(booksButton.getText());
	}

	@FXML
	private Button propertyButton;

	@FXML
	private void propertyButtonActivated(ActionEvent event) {
		// NIERÓCHOMOŚĆI
		updateSubcategoryPane(propertyButton.getText());
	}

	@FXML
	private void handleButtonAction(ActionEvent event) {
		Stage stage = (Stage) myAccountButton.getScene().getWindow();
		FXMLLoader loader;
		if (isUserLoggedIn()) {
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

//		if (Window.isLoggedIn) { 
//			MyAnnouncementsWindowController controller = loader.<MyAnnouncementsWindowController>getController();
//			client.setAnnouncementWindow(controller);
//			controller.setClient(client);
//		}
//		else {
//			SignInOrRegisterController controller = loader.<SignInOrRegisterController>getController();
//			client.setRegisterWindow(controller);
//			controller.setClient(client);
//		}
		
		
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
