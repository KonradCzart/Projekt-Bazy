package gui;

import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AnnouncementInfo {
	private SimpleObjectProperty<Date> startDate;
	private StringProperty productName;
	private StringProperty title;
	private DoubleProperty price;
	
	public AnnouncementInfo() {
		startDate = new SimpleObjectProperty<>();
		productName = new SimpleStringProperty();
		title = new SimpleStringProperty();
		price = new SimpleDoubleProperty();
	}
	
	public Object getStartDate() {
		return startDate.get();
	}
	
	public SimpleObjectProperty<Date> startDateProperty() {
		return startDate;
	}
	
	public void setStartDate(Date date) {
		startDate.set(date);
	}
	
	public String getProductName() {
		return productName.get();
	}
	
	public StringProperty productNameProperty() {
		return productName;
	}
	
	public void setProductName(String name) {
		productName.set(name);
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public double getPrice() {
		return price.get();
	}
	
	public DoubleProperty priceProperty() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price.set(price);
	}
	
	
	
}
