package pizza_ordering_system;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
@ManagedBean(name="database")
@ViewScoped
@SessionScoped

public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	private Connection connection = null;
    private Statement st = null;
    private ResultSet rs = null;
    @Resource(name="jdbc/pizza_ordering_system")
    private DataSource dss;
    
    //GLOBÁLIS VÁLTOZÓK A KEDVEZMÉNYRE, ILYENEKRE... TODO
    private static final SimpleDateFormat sdfHHmm = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat sdfHH = new SimpleDateFormat("HH");
    private static final SimpleDateFormat sdfmm = new SimpleDateFormat("mm");
    
    private static final int OVENS_USABLE=5;
    private static final int PRICE_CUT_FROM=5000;
    private static final int PRICE_CUT=500;
    private static final int TRANSPORT_TIME=20;
    
    //Felhasznalo Adatok
    private String newUserID;
    private String newUserName;
    private String newUserAddress;
    private String newUserPhoneNumber;
    
	
    //Pizza Adatok
    private String newPizzaID;
    private int newPizzaPrice;
    private String newPizzaSize;
    private String newPizzaDescription;
    private int newPizzaCookingTime;
    
    //Rendeles Adatok... TODO
	private String newOrderID;
    private String newOrderFID;
    private List<String> OrderFIDList = new ArrayList<>();
    
    private String newOrderPID;
    private List<String> OrderPIDList = new ArrayList<>();
    
    private int newOrderNumber;
    private List<Integer> OrderNumberList = new ArrayList<>();
    
    private int newOrderPrice;
    private Timestamp newOrderDate;
    private Timestamp newOrderEndDate;
    
    private String newOrderSID;
    private int newOrderPlusEndTime;
    
    
    
    //Suto Adatok... TODO
    private String OvenID;
    private int OvenState;
	private int x;
    
	//Torles 
	private int deleteOrderByNumber;
	
	
	public void connection() throws SQLException{
    	try {
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/pizza_ordering_system?verifyServerCertificate=false&useSSL=true", "root","");
    		
    	  } catch (Exception e) {
    		
    		System.out.println(e.getMessage());
    	  }
    }
	
	
	public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public void addUser() {
        try {
            connection();
            
            PreparedStatement ps = connection.prepareStatement("insert into Felhasznalo (FID, NEV, CIM, TELEFON) values (?,?,?,?)");
         
           
            ps.setString(1, newUserID);
            ps.setString(2, newUserName);
            ps.setString(3, newUserAddress);
            ps.setString(4, newUserPhoneNumber);
     
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close();
        }

    }
	
	public List<Users> ListUsers(){
		
		List<Users> UsersList = new ArrayList<>();	
      try {
          connection();
        
          PreparedStatement ps = connection.prepareStatement("Select * From felhasznalo ");
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
        	  Users user = new Users();
        	  
        	  user.setFID(rs.getString("FID"));
        	  user.setNev(rs.getString("nev"));
        	  user.setCim(rs.getString("cim"));
        	  user.setTelefon(rs.getString("telefon"));
        	  UsersList.add(user); 
          }
          
          return UsersList;

      } catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
         close();
      }
	}
	
	
	public void addPizza() {
        try {
            connection();
            
            PreparedStatement ps = connection.prepareStatement("insert into pizza (PID, AR, MERET, LEIRAS, SUTIDO) values (?,?,?,?,?)");
         
           
            ps.setString(1, newPizzaID);
            ps.setInt(2, newPizzaPrice);
            ps.setString(3, newPizzaSize);
            ps.setString(4, newPizzaDescription);
            ps.setInt(5, newPizzaCookingTime);
     
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close();
        }

    }
	
public List<Pizza> ListPizzas(){
		
		List<Pizza> PizzasList = new ArrayList<>();	
      try {
          connection();
        
          PreparedStatement ps = connection.prepareStatement("Select * From pizza");
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
        	  Pizza pizza = new Pizza();
        	  
        	  pizza.setPID(rs.getString("PID"));
        	  pizza.setAr(rs.getInt("ar"));
        	  pizza.setMeret(rs.getString("meret"));
        	  pizza.setLeiras(rs.getString("leiras"));
        	  pizza.setSutido(rs.getInt("sutido"));
        	  
        	  
        	  PizzasList.add(pizza); 
          }
          
          return PizzasList;

      } catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
         close();
      }
	}
	

public List<Ovens> ListOvens(){
	
	List<Ovens> OvensList = new ArrayList<>();	
  try {
      connection();
    
      PreparedStatement ps = connection.prepareStatement("Select * From sutok ");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
    	  Ovens oven = new Ovens();
    	  
    	  oven.setSID(rs.getString("SID"));
    	  oven.setFoglalt(rs.getInt("foglalt"));;
    	  
    	  if(oven.getFoglalt()==0) oven.setFoglaltszoveg("Nem süt");
    	  if(oven.getFoglalt()==1) oven.setFoglaltszoveg("Süt");
    	
    	  OvensList.add(oven); 
      }
      
      return OvensList;

  } catch (Exception e) {
      e.printStackTrace();
      return null;
  } finally {
     close();
  }
}

public List<String> OrderFIDs(){
	
  try {
      connection();
    
      PreparedStatement ps = connection.prepareStatement("SELECT FID FROM felhasznalo");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
    	  String FIDs;
    	  
    	  FIDs= rs.getString("FID");
    	  
    	  
    	 OrderFIDList.add(FIDs);
      }
      
      return OrderFIDList;

  } catch (Exception e) {
      e.printStackTrace();
      return null;
  } finally {
     close();
  }
}

public List<String> OrderPIDs(){
	
	  try {
	      connection();
	    
	      PreparedStatement ps = connection.prepareStatement("SELECT PID FROM pizza");
	      ResultSet rs = ps.executeQuery();
	      while (rs.next()) {
	    	  String PIDs;
	    	  
	    	  PIDs= rs.getString("PID");
	    	  
	    	  
	    	 OrderPIDList.add(PIDs);
	      }
	      
	      return OrderPIDList;

	  } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	  } finally {
	     close();
	  }
	}

public List<Integer> OrderNumbers(){
	
	  try {
	      connection();
	    
	      PreparedStatement ps = connection.prepareStatement("SELECT RSZAM FROM rendeles GROUP BY RSZAM");
	      ResultSet rs = ps.executeQuery();
	      while (rs.next()) {
	    	  int rszam;
	    	  
	    	  rszam= rs.getInt("RSZAM");
	    	  
	    	  
	    	 OrderNumberList.add(rszam);
	    	
	      }
	      
	      return OrderNumberList;

	  } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	  } finally {
	     close();
	  }
	}

	public void OrderPriceFromPizza(){
	
	  try {
	      connection();
	    
	      PreparedStatement ps = connection.prepareStatement("SELECT AR FROM pizza WHERE PID=?");
	       
	      	  ps.setString(1, newOrderPID);
	    	  
	          ResultSet rs=ps.executeQuery();
	      	  
	          while(rs.next()) {
		  			newOrderPrice=rs.getInt(1);
		  		}
	      	  
	    	  
	      
	  } catch (Exception e) {
	      e.printStackTrace();
	  } finally {
	     close();
	  }
	}

	public void OrderDate() {
		 newOrderDate = new Timestamp(System.currentTimeMillis());
	}
	
	public void OrderEndDate() {
		try {
		      connection();
		    
		      PreparedStatement ps = connection.prepareStatement("SELECT SUTIDO FROM pizza WHERE PID=?");
		       
		      	  ps.setString(1, newOrderPID);
		    	  
		          ResultSet rs=ps.executeQuery();
		      	  int tempTime = 0;
		          
		          while(rs.next()) {
			  			tempTime=rs.getInt(1);
			  		}
		      	  
		          Timestamp temp = new Timestamp(System.currentTimeMillis());
		          
		          long t=temp.getTime();
		          long m=tempTime*60*1000;
        
		          newOrderEndDate = new Timestamp(t+m);    	  
		      
		          if(newOrderPlusEndTime!=0)
		          {
		        	  newOrderEndDate = new Timestamp(t + m +(newOrderPlusEndTime * 1000));
		          }
		          
		          
		  } catch (Exception e) {
		      e.printStackTrace();
		  } finally {
		     close();
		  }
	}

	public void OrderSID() {
		try {
		      connection();
		    
		      PreparedStatement ps = connection.prepareStatement("SELECT SID FROM sutok WHERE foglalt=0");
		       
		      	 
		      	 int tempInt = 0;
		          ResultSet rs=ps.executeQuery();
		      	  newOrderPlusEndTime= 0;
		          if(rs.first()) { 
		        	  
		        	  newOrderSID=rs.getString(1);
		        	  String tempString=newOrderSID.substring(2);
		        	  tempInt=Integer.parseInt(tempString);
		        	  if(tempInt <= OVENS_USABLE) {
		        	  PreparedStatement ps2=connection.prepareStatement("UPDATE SUTOK SET FOGLALT=1 WHERE SID=?");

		        	  ps2.setString(1, newOrderSID);
		        	  ps2.executeUpdate();
		        	  
		          }
		        	  else { 
		        		 // SELECT KESZIDO,SID FROM rendeles WHERE SID is not null
			        	  PreparedStatement ps3 = connection.prepareStatement("SELECT SID, COUNT(*), max(KESZIDO) FROM rendeles GROUP BY SID");
			        	  
			        	  ResultSet rs3=ps3.executeQuery();
				      	  Timestamp tempTime;
				      	  Timestamp tempCurrentTime= new Timestamp(System.currentTimeMillis());
				      	  long tempMilliSeconds=0;
				      	  int tempSeconds;
				      	  int seconds = 0;
				      	  
				          while(rs3.next()) {
				        	  
				        	    tempTime=rs3.getTimestamp(3);
					  			tempMilliSeconds=tempTime.getTime() - tempCurrentTime.getTime();
					  			tempSeconds=(int) tempMilliSeconds / 1000;
					  			
					  		//	tempMinutes = (tempSeconds/3600) + ((tempSeconds % 3600) /60) + ((tempSeconds % 3600) % 60);
					  			
					  			if((seconds>tempSeconds) || (seconds==0)) {  
					  			seconds=tempSeconds;
					  			newOrderSID=rs3.getString(1);
					  			newOrderPlusEndTime=seconds;
					  			}
					  		}
			        	  
			          }
		          }
		         
		          
		          
		          
	      
		  } catch (Exception e) {
		      e.printStackTrace();
		  } finally {
		     close();
		  }
	}
	
	public void OrderNewNumberCheck() {
		if (newOrderNumber==999) {
			Random random = new Random();
			int x = random.nextInt(99999) + 100;
			newOrderNumber= x;
		}
	}
	
	public void addOrder() {
        try {
        	deleteOldOrdersSID();
            connection();
            
            PreparedStatement ps = connection.prepareStatement("insert into rendeles (RID, FID, PID, SID, RSZAM, AR, FELVETELIDO, KESZIDO) values (?,?,?,?,?,?,?,?)");
         
           
            ps.setString(1, newOrderID);
            ps.setString(2, newOrderFID);
            ps.setString(3, newOrderPID);
            OrderSID();
            ps.setString(4, newOrderSID);
            OrderNewNumberCheck();
            ps.setInt(5, newOrderNumber);
            OrderPriceFromPizza();
            ps.setInt(6, newOrderPrice);
            OrderDate();
            ps.setTimestamp(7, newOrderDate);
            OrderEndDate();
            ps.setTimestamp(8, newOrderEndDate);
     
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           close();
        }

    }
	
   public List<OrderSummary> ListOrders(){
		
		List<OrderSummary> OrderList = new ArrayList<>();	
      try {
          connection();
        
          PreparedStatement ps = connection.prepareStatement("SELECT RSZAM, COUNT(*), sum(AR), max(KESZIDO) FROM rendeles GROUP BY RSZAM ORDER BY max(KESZIDO) DESC");
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
        	  OrderSummary SUM = new OrderSummary();
        	  
        	  SUM.setRszam(rs.getInt(1));        	  
        	  SUM.setArSUM(rs.getInt(3)); 
        	   if(SUM.getArSUM()>=PRICE_CUT_FROM) { 
        		  int temp=SUM.getArSUM();
        		  temp=temp - PRICE_CUT;
        		  SUM.setArSUM(temp);
        		  }
        	   
        	  SUM.setKeszido(rs.getTimestamp(4));  
        	  long t=SUM.getKeszido().getTime();
        	  long m=TRANSPORT_TIME*60*1000;

        	  Timestamp tempTimeWithTransport=new Timestamp (t+m);
        	  SUM.setKeszido(tempTimeWithTransport);
	  
        	  OrderList.add(SUM);
        	   
          }
          
          return OrderList;

      } catch (Exception e) {
          e.printStackTrace();
          return null;
      } finally {
         close();
      }
	}
   
   public void deleteOldOrdersSID()
   {
	   // SELECT SID FROM rendeles WHERE KESZIDO <= CURRENT_TIMESTAMP
	   //UPDATE rendeles SET SID=NULL WHERE KESZIDO <= CURRENT_TIMESTAMP
	   
	   try {
		      connection();
		      
		      PreparedStatement ps4 = connection.prepareStatement("SELECT SID,COUNT(*) FROM rendeles GROUP BY SID");
		      ResultSet rs3= ps4.executeQuery();
		      String tempSIDFIRST;
		      int tempCount;
		      List<String> newTempStringList = new ArrayList<>();
		      while(rs3.next()) {
		    	  tempSIDFIRST=rs3.getString(1);
		    	  tempCount=rs3.getInt(2);
		    	  if(tempCount==1) { newTempStringList.add(tempSIDFIRST); }
		      }
		      
		      
		      //SELECT SID,COUNT(*) FROM rendeles WHERE (KESZIDO <= CURRENT_TIMESTAMP) GROUP BY SID
		      PreparedStatement ps = connection.prepareStatement("SELECT SID FROM rendeles WHERE KESZIDO <= CURRENT_TIMESTAMP");
  
	          ResultSet rs=ps.executeQuery();
	      	  String tempSID;
	      	  
	          while(rs.next()) {
		  			tempSID=rs.getString(1);
		  			
		  			if (newTempStringList.contains(tempSID)) { 
		  			PreparedStatement ps2 = connection.prepareStatement("UPDATE sutok SET foglalt=0 WHERE SID=?");
		  			
		  			ps2.setString(1, tempSID);
		  			ps2.executeUpdate();
		  			}
		  		}
		      	  
		      PreparedStatement ps3 = connection.prepareStatement("UPDATE rendeles SET SID=NULL WHERE KESZIDO <= CURRENT_TIMESTAMP");
		      ps3.executeUpdate();    
		      	  
		    	  
		      
		  } catch (Exception e) {
		      e.printStackTrace();
		  } finally {
		     close();
		  }
	   
	   
   }
   
   
   public void deleteOrder() {
       try {
           connection();
           
           PreparedStatement ps = connection.prepareStatement("DELETE FROM rendeles WHERE RSZAM=?");
        
          
           ps.setInt(1, deleteOrderByNumber);
          
    
           ps.executeUpdate();
           
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
          close();
       }

   }
	
	public String getNewUserID() {
		return newUserID;
	}
	public void setNewUserID(String newUserID) {
		this.newUserID = newUserID;
	}
	public String getNewUserName() {
		return newUserName;
	}
	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}
	public String getNewUserPhoneNumber() {
		return newUserPhoneNumber;
	}
	public void setNewUserPhoneNumber(String newUserPhoneNumber) {
		this.newUserPhoneNumber = newUserPhoneNumber;
	}
	public String getNewUserAddress() {
		return newUserAddress;
	}
	public void setNewUserAddress(String newUserAddress) {
		this.newUserAddress = newUserAddress;
	}


	public String getNewPizzaID() {
		return newPizzaID;
	}


	public void setNewPizzaID(String newPizzaID) {
		this.newPizzaID = newPizzaID;
	}


	public int getNewPizzaPrice() {
		return newPizzaPrice;
	}


	public void setNewPizzaPrice(int newPizzaPrice) {
		this.newPizzaPrice = newPizzaPrice;
	}


	public String getNewPizzaSize() {
		return newPizzaSize;
	}


	public void setNewPizzaSize(String newPizzaSize) {
		this.newPizzaSize = newPizzaSize;
	}


	public String getNewPizzaDescription() {
		return newPizzaDescription;
	}


	public void setNewPizzaDescription(String newPizzaDescription) {
		this.newPizzaDescription = newPizzaDescription;
	}


	public int getNewPizzaCookingTime() {
		return newPizzaCookingTime;
	}


	public void setNewPizzaCookingTime(int newPizzaCookingTime) {
		this.newPizzaCookingTime = newPizzaCookingTime;
	}


	public String getOvenID() {
		return OvenID;
	}


	public void setOvenID(String ovenID) {
		OvenID = ovenID;
	}


	public int isOvenState() {
		return OvenState;
	}


	public void setOvenState(int ovenState) {
		OvenState = ovenState;
	}


	public String getNewOrderID() {
		return newOrderID;
	}


	public void setNewOrderID(String newOrderID) {
		this.newOrderID = newOrderID;
	}


	public String getNewOrderFID() {
		return newOrderFID;
	}


	public void setNewOrderFID(String newOrderFID) {
		this.newOrderFID = newOrderFID;
	}


	public String getNewOrderPID() {
		return newOrderPID;
	}


	public void setNewOrderPID(String newOrderPID) {
		this.newOrderPID = newOrderPID;
	}


	public int getNewOrderNumber() {
		return newOrderNumber;
	}


	public void setNewOrderNumber(int newOrderNumber) {
		this.newOrderNumber = newOrderNumber;
		
	}


	public int getNewOrderPrice() {
		return newOrderPrice;
	}


	public void setNewOrderPrice(int newOrderPrice) {
		this.newOrderPrice = newOrderPrice;
	}


	public Timestamp getNewOrderDate() {
		return newOrderDate;
	}


	public void setNewOrderDate(Timestamp newOrderDate) {
		this.newOrderDate = newOrderDate;
	}


	public Timestamp getNewOrderEndDate() {
		return newOrderEndDate;
	}


	public void setNewOrderEndDate(Timestamp newOrderEndDate) {
		this.newOrderEndDate = newOrderEndDate;
	}


	public String getNewOrderSID() {
		return newOrderSID;
	}


	public void setNewOrderSID(String newOrderSID) {
		this.newOrderSID = newOrderSID;
	}


	public int getNewOrderPlusEndTime() {
		return newOrderPlusEndTime;
	}


	public void setNewOrderPlusEndTime(int newOrderPlusEndTime) {
		this.newOrderPlusEndTime = newOrderPlusEndTime;
	}


	public int getDeleteOrderByNumber() {
		return deleteOrderByNumber;
	}


	public void setDeleteOrderByNumber(int deleteOrderByNumber) {
		this.deleteOrderByNumber = deleteOrderByNumber;
	}
	
	
}
