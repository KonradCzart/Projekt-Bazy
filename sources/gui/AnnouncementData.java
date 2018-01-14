package gui;

import Message.Message;

public class AnnouncementData implements Message{
	String date;
	String productName;
	String title;
	Double price;
	
	public AnnouncementData(String date, String productName, String title, Double price) {
		this.date = date;
		this.productName = productName;
		this.title = title;
		this.price = price;
	}
	
	String getDate() {
		return date;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	double getPrice() {
		return price;
	}
	
	void setPrice(double price) {
		this.price = price;
	}
}
