package app;
public class Bonus extends Payment {
	//Payment is superclass and Bonus is subclass extends Payment
	
	static Payment payment ; //initialize payment data type Payment
	private String phonenumber ; //initialize data type String
	
	public Bonus(double amount, String number) {
		super("PromptPay", amount); //use keyword super because it refer to superclass payment
		this.amount = amount ;
		this.phonenumber = number ;
	}

	@Override 
	public boolean isValid() {
		int l = phonenumber.length() ;
//		System.out.println(l) ;
		if(l == 10) //if phone number have length equal to 10 means condition true(valid) return true
			{ return true ; }
		return false ; //otherwise return false means phone number invalid
	}	
	
	public String log() {
		String status ; 
		if (isValid() == true) //if phone number have length 10
		{
			status = "VALID" ; //status of this payment VALID
			return "[" +status +"]" + " PromptPay::" +df.format(amount) +"::"+phonenumber ;
		}
		return "[VOID]" + " PromptPay::" +df.format(amount) +"::"+"invalid card number" ;
	}
	//return string follow format of test case
}


