package gui;

import java.util.ArrayList;
import java.util.Arrays;

public class Resources {
	static ArrayList<String> getCategories() {
		return new ArrayList<String>(Arrays.asList("Motoryzacja", "Elektronika", "Ubrania", "Zwierzęta", "Usługi",
				"Praca", "Książki", "Nieruchomości"));
	}

	static ArrayList<String> getSubcategories(String category) {
		if (category.equals("Motoryzacja")) {
			return new ArrayList<String>(
					Arrays.asList("Samochody osobowe", "Samochody ciężarowe", "Motocykle", "Części"));
		} else if (category.equals("Elektronika")) {
			return new ArrayList<String>(Arrays.asList("Komputery", "Telefony", "Sprzęt AGD", "Telewizory"));
		} else if (category.equals("Ubrania")) {
			return new ArrayList<String>(Arrays.asList("Ubrania", "Buty", "Dodatki"));
		} else if (category.equals("Zwierzęta")) {
			return new ArrayList<String>(Arrays.asList("Domowe", "Gospodarcze", "Akcesoria"));
		} else if (category.equals("Usługi")) {
			return new ArrayList<String>(Arrays.asList("Budowlane", "Motoryzacyjne", "Transportowe", "Inne"));
		} else if (category.equals("Książki")) {
			return new ArrayList<String>(Arrays.asList("Językowe", "Naukowe", "Literatura", "Czasopisma"));
		} else if (category.equals("Praca")) {
			return new ArrayList<String>(Arrays.asList("Umowa o pracę", "Umowa zlecenie", "Umowa o dzieło"));
		} else if (category.equals("Nieruchomości")) {
			return new ArrayList<String>(Arrays.asList("Działki", "Domy", "Mieszkania"));
		} else
			return new ArrayList<String>(Arrays.asList("BŁAD"));
	}

	static ArrayList<String> getCondition() {
		return new ArrayList<String>(Arrays.asList("nowy", "używany", "dowolny"));
	}

	static ArrayList<String> getCarMakes() {
		return new ArrayList<String>(Arrays.asList("Audi", "BMW", "Chevrolet", "Citroën", "Dacia", "Daewoo", "Fiat",
				"Ford", "Honda", "Hyundai", "Mazda", "Mercedes", "Mitsubishi", "Nissan", "Opel", "Peugeot", "Renault",
				"Skoda", "Suzuki", "Toyota", "Volkswagen", "Pozostałe"));
	}
	
	static ArrayList<String> getFuelTypes() {
		return new ArrayList<String>(Arrays.asList("Benzyna", "Gaz"));
	}
	
	static ArrayList<String> getCarTypes() {
		return new ArrayList<String>(Arrays.asList("Dostawcze", "Terenowe"));
	}
	
	static ArrayList<String> getMotorcycleMakes() {
		return new ArrayList<String>(Arrays.asList("Aprilia", "Barton", "Benelli", "Beta", "Bmw", "Cagiva", "Ducati",
				"GAS GAS", "Harley-Davidson", "Honda", "Horex", "Husqvarna", "Jawa", "Junak", "Kawasaki", "Kingway", "MZ",
				"Romet", "Suzuki", "Victory", "Yamaha", "Zipp", "Pozostałe"));
	}
	
	static ArrayList<String> getComputerManufacturers() {
		return new ArrayList<String>(Arrays.asList("Acer", "Apple", "Asus", "Dell", "Fujitsu", "HP", "Lenovo",
				"MSI", "Toshiba", "Pozostałe"));
	}
	
	static ArrayList<String> getPhonesManufacturers() {
		return new ArrayList<String>(Arrays.asList("Apple", "HTC", "Huawei", "LG", "Micorosoft", "Motorola", "Nokia",
				"Samsung", "Sony", "Pozostałe"));
	}
	
	static ArrayList<String> getTVManufacturers() {
		return new ArrayList<String>(Arrays.asList("Samsung", "Sony", "Philips", "LG", "Panasonic", "Pozostałe"));
	}
	
	static ArrayList<String> getClothesSizes() {
		return new ArrayList<String>(Arrays.asList("XS", "S", "M", "L", "XL", "XXL", "XXXL"));
	}
	
	static ArrayList<String> getClothesTypes() {
		return new ArrayList<String>(Arrays.asList("męskie", "damskie"));
	}
	
	static ArrayList<String> getShoesSizes() {
		return new ArrayList<String>(Arrays.asList("35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50"));
	}
	
	static ArrayList<String> getDomesticSpecies() {
		return new ArrayList<String>(Arrays.asList("Psy", "Koty", "Ptaki", "Króliki", "Gryzonie", "Rybki", "Pozostałe"));
	}
	
	static ArrayList<String> getFarmSpecies() {
		return new ArrayList<String>(Arrays.asList("Bydło", "Drób", "Kozy", "Owce", "Trzoda", "Pozostałe"));
	}
	
	static ArrayList<String> getLanguages() {
		return new ArrayList<String>(Arrays.asList("Angielski", "Francuski", "Niemiecki", "Rosyjski", "Inne"));
	}
	
	static ArrayList<String> getGenres() {
		return new ArrayList<String>(Arrays.asList("Angielski", "Francuski", "Niemiecki", "Rosyjski", "Inne"));
	}
	
	static ArrayList<String> getMagazineTypes() {
		return new ArrayList<String>(Arrays.asList("Młodzieżowe", "Przyrodnicze", "Informatyczne", "Popularnonaukowe", "Kulinarne", "Motoryzacyjne", "Erotyczne", "Inne"));
	}
	
	static ArrayList<String> getTransactionTypes() {
		return new ArrayList<String>(Arrays.asList("Sprzedaż", "Wynajem"));
	}
	
	static ArrayList<String> getMilageRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 50 tys", "50 - 150 tys", "150 - 250 tys", "powyżej 250 tys"));
	}
	
	static ArrayList<String> getAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 500 m²", "500 - 1000 m²", "1000 - 2000 m²", "powyżej 2000 m²"));
	}
	
	static ArrayList<String> getHomeAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 50 m²", "50 - 100 m²", "100 - 200 m²", "powyżej 200 m²"));
	}
	
	static ArrayList<String> getFlatAreaRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 25 m²", "25 - 50 m²", "50 - 100 m²", "powyżej 100 m²"));
	}
	
	static ArrayList<String> getScreenSizeRanges() {
		return new ArrayList<String>(Arrays.asList("poniżej 30", "30 - 40", "40 - 50", "powyżej 50"));
	}
}
