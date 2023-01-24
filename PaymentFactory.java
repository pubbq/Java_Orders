package app;

/**
 * Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */


public class PaymentFactory {


	/**
	 * Crate a Payment object according to the given parameters
	 * 
	 * @param amount
	 *            total amount that need to be paid
	 * @param args
	 *            list of parameters depends on the payment type. For example, Cash
	 *            payment only requires cash tendered, while CreditCard payment
	 *            requires CardType, and card's number
	 * @return 
	 * 		Payment	
	 * @throws IllegalArgumentException
	 * 			  - if the payment type is unavailable => not [CASH|CARD],
	 * 			    throw IllegalArgumentException exception with a specific message 
	 * 				"xx is an unavailable payment type." where xxx is the unavailable input argument
	 * 
	 * 			  - if the card type is unavailable => not [VISA|AMERICANEXPRESS|JCB|MASTERCARD] 
	 * 			    throw IllegalArgumentException exception with a specific message 
	 * 				"xxx is an unavailable card type." where xxx is the unavailable input argument
	 * 
	 * @throws IndexOutOfBoundsException
	 * 			  - if the number of arguments is insufficient to construct the payment,
	 * 			    throw IndexOutOfBoundsException exception
	 * 			    For example, Cash payment needs 2 arguments: amount and cash value, 
	 * 							 CreditCard payment needs 3 arguments: amount, card's type, and card's number
	 * 
	 */
	public static Payment createPayment(double amount, String[] args)
			throws IllegalArgumentException, IndexOutOfBoundsException {

		String type = args[0];
		
		if (type.equalsIgnoreCase("CASH")) 
		{
			// TODO 15: Create `Cash` payment class by parsing arguments according to the
			// Cash constructor method
			if(args.length >= 2)  
			{
				Double cash = Double.parseDouble(args[1]); //change from string to double because cash accept double
				Cash c = new Cash(amount, cash);
	                return c;
	        } else { throw new IndexOutOfBoundsException("1"); } //if the payment type is unavailable => not [CASH|CARD]
	                
	            
		} else if (type.equalsIgnoreCase("CARD")) {
			// TODO 16: Create `CreditCard` payment class by parsing arguments according to
			// the CreditCard constructor method
			
			//need to have 3 > from CreditCard payment needs 3 arguments: amount, card's type, and card's number
			if(args.length == 3 && (args[1].equalsIgnoreCase("VISA") || args[1].equalsIgnoreCase("AMERICANEXPRESS") || args[1].equalsIgnoreCase("JCB") || args[1].equalsIgnoreCase("MASTERCARD"))) 
			{
                CreditCard.CardType ct = null; //create new credit CredType > ct = to change string to CardType
                if(args[1].equalsIgnoreCase("VISA")) { ct = CreditCard.CardType.VISA; }
                else if(args[1].equalsIgnoreCase("AMERICANEXPRESS")) ct = CreditCard.CardType.AMERICANEXPRESS;
                else if(args[1].equalsIgnoreCase("JCB")) ct = CreditCard.CardType.JCB;
                else if(args[1].equalsIgnoreCase("MASTERCARD")) ct = CreditCard.CardType.MASTERCARD;
                CreditCard cc = new CreditCard(amount, ct, args[2]);
                return cc;
            }
            else 
            {
                if(args.length >= 2 && !(args[1].equalsIgnoreCase("VISA") || args[1].equalsIgnoreCase("AMERICANEXPRESS") || args[1].equalsIgnoreCase("JCB") || args[1].equalsIgnoreCase("MASTERCARD"))) 
                	{ throw new IllegalArgumentException(args[1] + " is an unavailable card type."); }             
                else { throw new IndexOutOfBoundsException("2"); } //in correct at index 2
            }
			
		} else {
			// DO NOT MODIFY
			throw new IllegalArgumentException(type + " is an unavailable payment type.");
		}

	}
}
