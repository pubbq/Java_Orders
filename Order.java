package app;
/**
* Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */


import java.util.ArrayList;
import java.util.List;

import test.ItemTest;


public class Order implements Loggable{

	// ============ Instance Variables ============
	public static int runningID = 0;

	private int orderID;
	private Customer customer;
	private ArrayList<Item> items;
	private Payment payment;
	// ============================================

    // =============== Constructors ===============

	/**
	 * Constructor to initialize orderID according to the running ID
	 * The first order's ID is 1. The second order's ID is 2, and so on.
	 * @param c : Customer
	 */
	public Order(Customer c) {
		// TODO 17: Implement a constructor method of Order
		this.orderID = runningID++ ; //ruuning id
		this.customer = c ; //customer equal c customer from parameter
		items = new ArrayList<>() ; //new array list for item
		
	}

	// ============================================

	//  ============= DO NOT MODIFY  =============
	
	public int getOrderID() {
		return orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int numItems() {
		return this.items.size();
	}
	
	public Payment getPayment() {
		return this.payment;
	}
	
	public boolean checkPaymentStatus() {
		if(payment != null) {
			return this.payment.isValid();
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object object) {
		Order o = (Order) object;
		return this.log().equalsIgnoreCase(o.log());
	}
	
	// ============================================

	/**
	 * Return total price of all items in the `items` array list.
	 * If the customer is an OnlineCustomer, include his shipping fee into the total too.
	 * Note that if there is no order, nothing to ship, the total price will always be zero.
	 * @return
	 */
	public double getTotalPrice() {
		// TODO 18: Implement getTotalPrice() method
		double ttp = 0.0 ;
		if(items.isEmpty()) return 0.0; //if no element from array list item
			
		{
			for(Item it : items) //loop array list item
				{ ttp += it.getTotal() ; } 
		}
		if ( customer instanceof OnlineCustomer ) //superclass call method from subclass using "instanceof"
			ttp += ((OnlineCustomer) customer).getShippingFee(); //if online customer include shipping fee
		return ttp;
	}
	
	
	/**
	 * Return an item in the `items` array list if the `name` exists
	 * otherwise return null
	 *
	 * @param name
	 * @return Item | null
	 */
	public Item findItem(String name) {
		// TODO 19: Implemenent findItem(String name) method
		
		for(int i=0 ;i<items.size(); i++) //loop through array list item
		{
			if(items.get(i).getName() == name) //if name match with name from parameter
			{ return items.get(i) ; } //return item which name match(exists)
		}
		return null ; //if not match
		
	}


	/**
	 * Add `newItem` to the `items` array list.
	 * If the name exists, add only the quantity to the existing item, ignoring the price.
	 * Otherwise add `newItem` as an object.
	 *
	 * For example:
	 * items:
	 *  0: Sinovac 1 doses
	 *
	 * Add Sinovac 1 doses
	 * 		-> items:
	 * 			0: Sinovac 2 doses
	 * Add AstraZeneca 1 doses
	 * 		-> items:
	 * 			0: Sinovac 2 doses
	 * 			1: AstraZeneca 1 doses
	 *
	 * @param newItem
	 */
	public void addItem(Item newItem) {
		// TODO 20: Implement addItem(Item newItem) method		

		if(this.items == null) items = new ArrayList<>() ;	//crate new item
		for(int i=0; i<items.size(); i++) //loop through array list item
		{
			if(items.get(i).getName() == newItem.getName())
			{
				int q =  newItem.getQuantity(); // q = quantity of newItem from parameter
				items.get(i).setQuantity(items.get(i).getQuantity()+q); //if match set quantity from newItem equal item's quantity
				return ;
			}
			if(items.get(i).getQuantity() <= 0) items.remove(i) ; //if quantity equal minus or zero remove this item
		}	
		items.add(newItem) ; //if not match to any condition just add newItem to array list item

	}

	/**
	 * Add an item using `barcode`. See `addItem(Item newItem)`	for the documentation
	 * The `barcode` of items is defined in `ItemFactory`
	 *
	 * @param barcode
	 * @param quantity
	 */
	public void addItem(int barcode, int quantity) {
		// TODO 21: Implement addItem(int barcode, int quantity) method
		// Hint:
		// - Use ItemFactory to create an item
		// - Use the above method addItem(Item newItem) to add the item
		addItem(ItemFactory.createItem(barcode, quantity));
		// call createItem method from ItemFactory set barcode, quantity equal to value from parameter
		
		
	}

	/**
	 * Reduce `reducingItem` to the `items` array list.
	 * If the name exists, reduce the quantity to the existing item.
	 * Otherwise do nothing.
	 *
	 * After the reduction, if the quantity is less than or equal to 0,
	 * remove the item from the list.
	 *
	 *
	 * For example:
	 * items:
	 *  0: Sinovac 2 doses
	 *
	 * Reduce Sinovac 1 doses
	 * 		-> items:
	 * 			0: Sinovac 1 doses
	 * Reduce Sinovac 1 doses
	 * 		-> items: (empty)
	 *
	 * @param reducingItem
	 */
	public void reduceItem(Item reducingItem) {
		// TODO 22: Implement reduceItem(Item reducingItem) method
		
		if(items == null) return; //if no item
		for(int i=0; i<items.size(); i++) //loop through each item
		{
			if( reducingItem.getName() == items.get(i).getName()) //name exists
			{
				items.get(i).setQuantity( items.get(i).getQuantity() - reducingItem.getQuantity()) ; //reduce quantity equal to reducing item quantity
				if(items.get(i).getQuantity() <= 0) 
					{ items.remove(i); } //remove item that have zero quantity after reduce
			}
		}	
	}

	/**
	 * Reduce item using `barcode`. See `reduceItem(Item reducingItem)` for the documentation.
	 * The `barcode` of items is defined in `ItemFactory`
	 *
	 * @param barcode
	 * @param deductQuantity
	 */
	public void reduceItem(int barcode, int deductQuantity) {
		// TODO 23: Implement reduceItem(int barcode, int deductQuantity) method
		reduceItem(ItemFactory.createItem(barcode, deductQuantity));
		// call createItem method from ItemFactory 
	}

	
	/**
	 * Set a payment method for this order
	 * @param args
	 */
	
	public void setPayment(String[] args) {
		// TODO 24: Implement setPayment(String type, Object[] params) method 
		// Hint:
		// - Use PaymentFactory to create an payment object
		// - Use getTotalPrice() to get an total order amount
		this.payment = PaymentFactory.createPayment(this.getTotalPrice(), args) ;
		// call createPayment method from PaymentFactory
	}
	
	
	/**
	 * Return a string representation of an order
	 * 
	 * Example:
	 * Customer: 1: Siripen Pongpaichet
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [VALID] CASH::6300::7000::700
	 * 
	 * If there is not payment yet, return [PENDING] in the last line
	 * 
	 * Example:
	 * Customer: 1: Siripen Pongpaichet
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [PENDING]
	 * 
	 * 
	 * @return String
	 */
	public String log() {
		// TODO 25: Implement log() method for Order class.
		String str = "Customer: "+this.customer.log() +"\n" ; //Customer: number: First name+last name
		if(this.items != null) //item information
		{
			for(int i=0; i<items.size(); i++)
				{ str += "- "+ items.get(i).log() +"\n" ; }
		}
		str += "Total: " +df.format(this.getTotalPrice()) + "\n" ; //total price
		if( getPayment() == null) { str += "[PENDING]" ; } //not success payment means pending
		else { str += payment.log(); } //payment validation + information of payment
		
		return str ;
	}
	
	
	
	// ============================================
}
