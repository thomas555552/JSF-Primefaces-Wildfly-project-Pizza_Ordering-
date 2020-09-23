package pizza_ordering_system;

public class Users {


	public Users() {
		
	}


	private String FID;
	private String nev;
	private String cim;
	private String telefon;
	

	public Users(String FID, String nev, String cim, String telefon) {
		super();
		this.FID = FID;
		this.nev = nev;
		this.cim = cim;
		this.telefon = telefon;
	}


	public String getFID() {
		return FID;
	}


	public void setFID(String FID) {
		this.FID = FID;
	}


	public String getNev() {
		return nev;
	}


	public void setNev(String nev) {
		this.nev = nev;
	}


	public String getCim() {
		return cim;
	}


	public void setCim(String cim) {
		this.cim = cim;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	
	
}
