package pizza_ordering_system;

public class Ovens {

	public Ovens() {
		
	}

	private String SID;
	private int foglalt;
	private String foglaltszoveg;
	
	public Ovens(String SID, int foglalt) {
		super();
		this.SID = SID;
		this.foglalt = foglalt;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String SID) {
		this.SID = SID;
	}

	public int getFoglalt() {
		return foglalt;
	}

	public void setFoglalt(int foglalt) {
		this.foglalt = foglalt;
	}

	public String getFoglaltszoveg() {
		return foglaltszoveg;
	}

	public void setFoglaltszoveg(String foglaltszoveg) {
		this.foglaltszoveg = foglaltszoveg;
	}
	
	
	
}
