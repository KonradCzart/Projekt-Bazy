package Message;

import java.util.ArrayList;

import gui.AnnouncementData;

public class AnnDataMessage implements Message {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 8030880148911215129L;
	
	private ArrayList<AnnouncementData> arrayAD;
	
	public AnnDataMessage()
	{
		arrayAD = null;
	}

	public ArrayList<AnnouncementData> getArrayAD() {
		return arrayAD;
	}

	public void setArrayAD(ArrayList<AnnouncementData> arrayAD) {
		this.arrayAD = arrayAD;
	}
	
	
}
