package Message;

import DataBase.AccountsStatus;

public class SettingWindowMessage implements Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624715997350761237L;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String salt;
	private AccountsStatus accountStatus;
	
	
	
	public AccountsStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountsStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

}
