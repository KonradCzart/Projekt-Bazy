package Message;

public class ChangeSettings implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5892718931018918289L;

	private String oldHashPassword;
	private String newHashPassword;
	private String newSalt;
	
	public ChangeSettings()
	{
		oldHashPassword = "";
		newHashPassword = "";
		newSalt = "";
	}
	
	public String getOldHashPassword() {
		return oldHashPassword;
	}
	public void setOldHashPassword(String oldHashPassword) {
		this.oldHashPassword = oldHashPassword;
	}
	public String getNewHashPassword() {
		return newHashPassword;
	}
	public void setNewHashPassword(String newHashPassword) {
		this.newHashPassword = newHashPassword;
	}
	public String getNewSalt() {
		return newSalt;
	}
	public void setNewSalt(String newSalt) {
		this.newSalt = newSalt;
	}
}
