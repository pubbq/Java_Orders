package app;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
* Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */


public class OnlineCustomer extends Customer {
	
	// TODO 03: Complete class `OnlineCustomer` that inherits Customer class.
	// 		 `OnlineCustomer`:
	//			Subclass "private" instance variables:
	//			- email 
	//			- zipcode 
	//			- SHIPPING <String, double> table storing shipping fee according to the zipcode
	//			Extra public interfaces
	//        	- public OnlineCustomer(String name, String email, String zipcode)
	//			- public String getEmail()
	// 			- public String getZipCode()
	//			- public double getShippingFee() returns 
	//		   Overrided behaviors
	//			- public String log() will also return email and zipcode in parenthesis
	//				For example: "2: Siripen Pongpaichet (siripen.pon@mahidol.edu, 73170)"
	//			- Public boolean equals()

	
	// ============ Instance Variables ============
	// This map table store the key value pair of zipcode and shipping fee
	private static final Map<String, Double> SHIPPING; 
	static {
		SHIPPING = new HashMap<>();
		SHIPPING.put("73170",  50.0);
		SHIPPING.put("10700",  20.0);
		SHIPPING.put("50230", 210.0);
		SHIPPING.put("83120", 250.0);
		SHIPPING.put("20120", 150.0);
	}
		
	private String email = "";
	private String zipcode = "";
	
	
	// ============================================
	
	
	
	// ============== DO NOT MODIFY ===============
	
	public OnlineCustomer(String name, String email, String zipcode) {
		super(name);
		this.email = email;
		this.zipcode = zipcode;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getZipcode() {
		return zipcode;
	}

	// ============================================
	
	
	// =========== YOUR CODE GOES HERE ===========
	 public String log()
	 { return   super.log() + " (" + email+", "+zipcode +")"; } // to string follow the format online customer
	 
	 
	 public boolean equals (String email, String zipcode)
	 {
		 if(this.email == email && this.zipcode == zipcode) // if email and zipcode match >parameter
			 { return true ; }
		 else return false ;
	 }
	
	 
	
	/**
	 * Lookup for the shipping fee from the SHIPPING table based on the customer's zipcode,
	 * if the zipcode does't exist, returns 99.00 as a default shipping fee.
	 * @return shipping fee 
	 */
	public double getShippingFee() {
		// TODO 04: Implement getShippingFee() method for this class
		try
		{
			double shipping = SHIPPING.get(zipcode) ; //check(try) zipcode exist
		} catch( Exception e )
			{ return 99.00 ; } //if not(catch) default shipping fee = 99.00
		return SHIPPING.get(zipcode) ;		
	}
		
	
}
