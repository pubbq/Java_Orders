package app;

/**
 * Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */

public class Cash extends Payment {
	
	// TODO 13: Complete class `Cash` that inherits Payment class.
	// 		 `Cash`:
	//			Subclass "private" instance variables:
	//			- cash 
	//			Extra public interfaces
	//        	- public double getChange() 
	//				calculate and return change if cash tendered is more than the paid amount.
	//				Since the smallest coin in Thailand is 0.25 Baht, for the decimal point, the change must be rounded-down to 
	//				0.25, or 0.50, or 0.75 Baht. 
	//				For example, if the amount is 199.45 and the cash is 200.00, the change must be 0.50 Baht (not 0.55 Baht).
	//				In addition, if cash tendered is less than the required amount, return 0.00.
	//		   Overrided behaviors
	//			- public boolean isValid() if the cash tendered is more than the paid amount, return true. Otherwise, return false
	//				For example: 
	//					paid amount is 3600 and cash tendered is 4000, so this method returns true
	//					paid amount is 4000 and cash tendered is 3600, so this method returns false
	// 			- public String log() return cash payment information -> amount, cash tendered, change
	//				For example: 
	//					"[VALID] CASH::3600.25::4001.00::400.75" 
	//			
	
	
	// ============ Instance Variables ============
	private double cash;	// Cash tendered is a sum of money given in payment. It may not be equal to the exact amount owed.		
	// ============================================
	
	
	// =============== Constructors ===============
	/**
	 * Constructor initializes the payment method's name as "CASH", paid amount, and cash tendered.
	 * @param paid amount 
	 * @param cash tendered
	 */
	public Cash(double amount, double cash) {
		super("CASH", amount);
		this.cash = cash;
	}
	// ============================================
	
	// YOUR CODE GOES HERE
	
	public double getChange() {
		 
		        double change = cash-amount; 
		        if(change <= 0) return 0.0; //if change equal minus or zero
		        int x = (int) change; //x equal integer of change
		        double y = change-x; //y equal decimal number

		        //if decimal number is between * and * > decimal number=*
		        //set value of change equal to change that not include decimal number plus decimal number after check condition
		        if(y>=0 && y<0.25)
		        {
		            y = 0; 
		            change = x + y;
		        }
		        else if(y>=0.25 && y<0.5)
		        {
		            y = 0.25;
		            change = x + y;
		        }
		        else if(y>=0.5 && y<0.75)
		        {
		            y = 0.5;
		            change = x + y;
		        }
		        else if(y>=0.75 && y<=0.99)
		        {
		            y = 0.75;
		            change = x + y;
		        }
		        else if(cash<amount)
		        	{ change = 0.0; }

		        return change;	//return change after edit value   
			
	}
	
	
	
	@Override
	public boolean isValid() { //check if cash greater than amount
		if(cash > amount)
			return true ;
		else return false ;
	}
	
	public String log() 
	{
	    String form = String.format("%s::%.2f::%.2f::%.2f", this.getMethod(), this.getAmount(), this.cash, this.getChange());;
	    if(isValid()) 
	       { return "[VALID] " + form; }
	    else { return "[VOID] " + form; }
	}
	
	
	
}