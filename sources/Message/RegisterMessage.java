package Message;

public class RegisterMessage implements Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3857378208379735907L;
	
	
	private String firstName;
	private String lastName;
	private String userName;
	private String passwordHash;
	private String salt;
	private int number;
	
	public RegisterMessage()
	{
		firstName = "";
		lastName = "";
		userName = "";
		passwordHash = "";
		salt = "";
		number = 0;
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
