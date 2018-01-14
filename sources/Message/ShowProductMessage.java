package Message;

import java.util.Map;

public class ShowProductMessage implements Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5189489614863927542L;
	private String subcategory;
	private Map<String, String> attributes;
	
	public ShowProductMessage()
	{
		subcategory = "";
		attributes = null;
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

	
}
