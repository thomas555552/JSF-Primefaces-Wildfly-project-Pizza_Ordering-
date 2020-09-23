package pizza_ordering_system;

public class Pizza {
   
	public Pizza() {
		
	}

	public Pizza(String PID, int ar, String meret, String leiras, int sutido) {
		super();
		this.PID = PID;
		this.ar = ar;
		this.meret = meret;
		this.leiras = leiras;
		this.sutido = sutido;
	}

	private String PID;
    private int ar;
    private String meret;
    private String leiras;
    private int sutido;
    
   

	public String getPID() {
		return PID;
	}

	public void setPID(String PID) {
		this.PID = PID;
	}

	public int getAr() {
		return ar;
	}

	public void setAr(int ar) {
		this.ar = ar;
	}

	public String getLeiras() {
		return leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	public int getSutido() {
		return sutido;
	}

	public void setSutido(int sutido) {
		this.sutido = sutido;
	}

	public String getMeret() {
		return meret;
	}

	public void setMeret(String meret) {
		this.meret = meret;
	}
    
    
    
    
}
