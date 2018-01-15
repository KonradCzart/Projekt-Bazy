package gui;

import java.util.HashMap;
import java.util.Map;

import Message.Message;
import javafx.beans.property.StringProperty;

public class Announcement implements Message{

	private static final long serialVersionUID = -3597330109539745391L;
	
	String id;
	String category;
	String subcategory;
	Map<String, String> attributes;
	String description;
	private String productName;
	private String title;
	
	public Announcement( String productName, String title, String category, String subcategory, Map<String, String> attributes, String description) 
	{
		this.id = "";
		this.productName = productName;
		this.title = title;
		this.category = category;
		this.subcategory = subcategory;
		this.attributes = attributes;
		this.description = description;
	}
	
	public Announcement() {
		attributes = new HashMap<>();
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

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubcategory() {
		return subcategory;
	}
	
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
