package pizza_ordering_system;

import java.sql.Timestamp;

public class Orders {

	
	public Orders() {
		
	}
	public Orders(String RID, String FID, String PID, String SID, int rszam, Timestamp felvetelido, Timestamp keszido,
			int ar) {
		super();
		this.RID = RID;
		this.FID = FID;
		this.PID = PID;
		this.SID = SID;
		this.rszam = rszam;
		this.felvetelido = felvetelido;
		this.keszido = keszido;
		this.ar = ar;
	}
	
	private String RID;
	private String FID;
	private String PID;
	private String SID;
	private int rszam;
	private Timestamp felvetelido;
	private Timestamp keszido;
	private int ar;
	
	public String getRID() {
		return RID;
	}
	public void setRID(String RID) {
		this.RID = RID;
	}
	public String getFID() {
		return FID;
	}
	public void setFID(String FID) {
		this.FID = FID;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String PID) {
		this.PID = PID;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String SID) {
		this.SID = SID;
	}
	public int getRszam() {
		return rszam;
	}
	public void setRszam(int rszam) {
		this.rszam = rszam;
	}
	public Timestamp getFelvetelido() {
		return felvetelido;
	}
	public void setFelvetelido(Timestamp felvetelido) {
		this.felvetelido = felvetelido;
	}
	public Timestamp getKeszido() {
		return keszido;
	}
	public void setKeszido(Timestamp keszido) {
		this.keszido = keszido;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
	
	
	
	

	
	
}
