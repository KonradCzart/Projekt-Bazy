package Message;

public class DeleteMessage implements Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8468632581460757106L; 
	
	private String id;
	
	public DeleteMessage(String id)
	{
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
