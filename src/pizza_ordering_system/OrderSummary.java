package pizza_ordering_system;

import java.sql.Timestamp;

public class OrderSummary {
	
  public OrderSummary() {
		
	}
  
public OrderSummary(int rszam, int arSUM, Timestamp keszido) {
		super();
		this.rszam = rszam;
		this.arSUM = arSUM;
		this.keszido = keszido;
	}
  
  private int rszam;
  private int arSUM;
  private Timestamp keszido;
  
public int getRszam() {
	return rszam;
}
public void setRszam(int rszam) {
	this.rszam = rszam;
}
public int getArSUM() {
	return arSUM;
}
public void setArSUM(int arSUM) {
	this.arSUM = arSUM;
}
public Timestamp getKeszido() {
	return keszido;
}
public void setKeszido(Timestamp keszido) {
	this.keszido = keszido;
}
}
