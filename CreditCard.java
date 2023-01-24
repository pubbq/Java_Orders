package app;

import java.util.Arrays;

/**
 * Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */

public class CreditCard extends Payment {
	
	// TODO 14: Complete class `CreditCard` that inherits Payment class.
	// 		 `CreditCard`:
	//			Subclass "private" instance variables:
	//			 - CardType type
	//			 - String number
	//
	//			Extra public interfaces
	//        	 - public String getFormattedCardNumber()	
	//					The card's number must be in the actual format with space between group of number based on card type
	// 
	//		    Overrided behaviors
	//			 - public boolean isValid() if card number is valid return true. Otherwise, return false
	//				For example: 
	//					if the card is VISA and has number "4234567890123456", this card is valid.
	//					If the card is JCB and has number "4234567890123456", this card is invalid. 
	//			 - public String log() return creditcard payment information
	//				For example: 
	//					"[VALID] CARD::3600.00::VISA::3434 567890 12345"
	//				
	
	// ============ Instance Variables ============
	public static enum CardType{ VISA, AMERICANEXPRESS, JCB, MASTERCARD }; 
					// different types of credit card 
		
	private CardType type;		// card's type
	private String number;		// card's number
	// ============================================

	
	// =============== Constructors ===============
	
	public CreditCard(double amount, CardType type, String number) {
		super("CARD", amount);
		this.type = type;
		this.number = number;		// i.e., 343456789012345
	}
	// ============================================
	
	
	/**
	 * Verify the validity of the card information. Different card type has different format of card number as follow
	 * VISA -> the number must be 16 digits, and start with number 4
	 * AMERICANEXPRESS -> the number must be 15 digits, and start with either 34 or 37
	 * JCB -> the number must be 16 digits, and start with 3528 to 3589
	 * MASTERCARD -> the number must be 16 digits, and start with 51 or 52
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", this card is valid.
	 * If the card is JCB and has number "4234567890123456", this card is invalid.
	 * 
	 * @return True if the card is valid, otherwise return false
	 */
	@Override
	public boolean isValid() {
		// TODO 14.1: implement isValid() method to validate card number	
		//check condition following the direction
		if(type == CardType.VISA)
		{
//			System.out.println(number.substring(0,1));
			if(number.length() == 16 && number.startsWith("4") ) 
				{ return true ; }
		}
		else if(type == CardType.AMERICANEXPRESS)
		{
			if(number.length() == 15 && ( number.startsWith("34") || number.startsWith("37") ) )
				return true ;
		}
		else if(type == CardType.JCB)
		{
			if(number.length() == 16 ) 
			{
				int num = Integer.parseInt(number.substring(0,4));
				for(int i=3528; i<=3589; i++)
					{ if(num == i) return true; }
			}
		}
		else if(type == CardType.MASTERCARD)
		{
			if(number.length() == 16 && ( number.startsWith("51") || number.startsWith("52") ) ) 
				return true ;
		}
		else return false ;
		return false ; //if not follow the direction condition return false
	}
	
	
	/**
	 * If the card is valid, formats the card's number according to the card's type.
	 * AMERICANEXPRESS (15 digits): #### ###### ##### (4-6-5)
	 * VISA, JCB, MASTERCARD (16 digits): #### #### #### #### (4-4-4-4)
	 * 
	 * For example, if the card is VISA and has number "4234567890123456", 
	 * the string value "4234 5678 9012 3456" will be returned.
	 * 
	 * if the card is AMERICANEXPRESS and has number "343456789012345", 
	 * the string value "3434 567890 12345" will be returned.
	 * 
	 * If the card information is invalid, returns "invalid card number".
	 * 
	 * @return a string of formatted card's number | "invalid card number"
	 */
	public String getFormattedCardNumber() {
		// TODO 14.2: implement getFormattedCardNumber() to return card number in a beautify format		
		if(isValid())
		{
			if(number.length() == 15)
				return number.substring(0,4) +" "+ number.substring(4,10) +" "+ number.substring(10,15) ; 
			if(number.length() == 16)
				return number.substring(0,4) +" "+ number.substring(4,8) +" "+ number.substring(8,12) +" "+ number.substring(12,16); 	
		}
		return "invalid card number" ;
		//substring 0,4 refer to first 4 digit
		//substring 4,10 refer to digit 5 to 8
		//substring 10,15 refer to digit 9 to 15
		
	}
	
	/**
	 * @Override log
	 * return credit card payment information
	 * 
	 * For example, 
	 * 	"[VALID] CARD::3600.00::VISA::3434 567890 12345"
     * @return string to provide information of this payment
	 */
	public String log() {
		// TODO 14.3: implement log() method of credit card
		String v; //string v to check valid of credit card log string
		if (isValid() == true) v = "VALID" ;
		else v = "VOID";	
		return "["+v +"]"+ " CARD::"+df.format(amount)+ "::" +type+ "::"+ getFormattedCardNumber();
	}


}
