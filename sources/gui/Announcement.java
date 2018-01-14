package gui;

import java.util.HashMap;
import java.util.Map;

import Message.Message;

public class Announcement implements Message{

	private static final long serialVersionUID = -3597330109539745391L;
	
	private AnnouncementInfo info;
	String category;
	String subcategory;
	Map<String, String> attributes;
	String description;
	
	Announcement(AnnouncementInfo info, String category, String subcategory, Map<String, String> attributes, String description) {
		this.info = info;
		this.category = category;
		this.subcategory = subcategory;
		this.attributes = attributes;
		this.description = description;
	}
	
	public Announcement() {
		info = new AnnouncementInfo();
		attributes = new HashMap<>();
	}
	
	AnnouncementInfo getAnnouncementInfo() {
		return info;
	}
	
	void setAnnouncementInfo(AnnouncementInfo info) {
		this.info = info;
	}
	
	String getCategory() {
		return category;
	}
	
	void setCategory(String category) {
		this.category = category;
	}
	
	String getSubcategory() {
		return subcategory;
	}
	
	void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	Map<String, String> getAttributes() {
		return attributes;
	}
	
	void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	String getDescription() {
		return description;
	}
	
	void setDescription(String description) {
		this.description = description;
	}
}
