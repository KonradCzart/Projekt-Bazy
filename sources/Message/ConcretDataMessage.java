package Message;

public class ConcretDataMessage implements Message{

		/**
	 * 
	 */
	private static final long serialVersionUID = -8581480828791189973L;
		private String ID;
		
		public ConcretDataMessage(String ID)
		{
			this.ID = ID;
		}

		public String getID() {
			return ID;
		}

		public void setID(String iD) {
			ID = iD;
		}
		
}
