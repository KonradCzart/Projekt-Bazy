package Message;

/**
 * @author Konrad Czart
 * Class for sending messages with success
 */
public class SuccessMessage implements Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7772190242890883885L;
	private int codSuccess;
	private String description;
	private String description2;
	
	public SuccessMessage(int codSuccess, String description)
	{
		this.codSuccess = codSuccess;
		this.description = description;
		this.description2 = "";
	}
	
	public int getCodSuccess()
	{
		return codSuccess;
	}
	
	public String getDescription()
	{
		return description;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	
	
}
