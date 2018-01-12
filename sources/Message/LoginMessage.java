package Message;

public class LoginMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3130922458983851096L;
	
	private Boolean first;
	private String salt;
	private String hashPassword;
	private String userName;
	
	public LoginMessage(Boolean first)
	{
		this.first = first;
		salt = "";
		hashPassword = "";
		userName = "";
	}
	
	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
