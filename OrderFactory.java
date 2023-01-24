package app;
/**
* Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.google.gson.*;

public class OrderFactory {
	
    
    public static Order createOneOfEachOrder(String customerName, String[] paymentInfo) {
    	
    	Customer c = new Customer(customerName);
        Order order = new Order(c);
        for (int barcode = 1; barcode <= ItemFactory.MAX_BARCODE; barcode++) {
            order.addItem(ItemFactory.createItem(barcode, 1));
        }
        
        if(paymentInfo != null) {
        	order.setPayment(paymentInfo);
    	}
    	
    	return order;
    }
    
    

    /**
     * Read the text file at `filepath` and create Order using the information in the file.
     *
     * The format of the file is:
     * Customer Name
     * barcode1 quantity1
     * barcode2 qunatity2
     * . . .
     * NONE or Payment Data (at the last line)
     *
     * If the quantity is negative, it means that the customer wants to reduce the items.
     *
     * For example:
     * Hesitant Customer
     * 1 20
     * 3 10
     * 2 5
     * 1 -10
     * 1 -5
     * 2 5
     * 1 -10
     * 1 -100
     * 2 -10
     * NONE
     *
     * In this example, only items with the barcode of 3 is left (quantity = 10) and there is no payment method.
     *
     * @param filepath
     * @return Order | null
     * @throws FileNotFoundException 
     */
    public static Order createOrderFromFile(String filepath)  {
        // TODO 26: Implement createOrderFromFile(String filepath)
    	try
    	{
            File file = new File(filepath); //crate new file
            Scanner inputfile = new Scanner(file); //input file
            String customerName = inputfile.nextLine(); //name customer equal first line from text input
            Customer customer;
            if(customerName.charAt(customerName.length()-1) == ')')
            {
                String[] sp = customerName.split("[(),]"); 
                customer = new OnlineCustomer(sp[0].strip() ,sp[1].strip() ,sp[2].strip());
            } else { customer = new Customer(customerName); } 
            Order order = new Order(customer);
            boolean check = false; //check that have items
            while (inputfile.hasNextInt()) //while file have next line
            {
                check = true;
                int c = inputfile.nextInt(); //code
                int q = inputfile.nextInt(); //quantity
                if (q >= 0) { order.addItem(c,q); }
                else { order.reduceItem(c, Math.abs(q)); }
            }
            if (check) { inputfile.nextLine(); }
            String payment = inputfile.nextLine(); 
            if(! payment.equals("NONE")) 
            {
                String[] sp = payment.split(" |:{2}");
                order.setPayment(sp);
            }
            inputfile.close(); //close scanner
            return order ; 
        } catch (Exception e) 
    	{
            System.out.println(e);
            return null ;
        }
}  		
    
    /**
     * Write `order` into a file at `filepath`. The format of the output is:
     *
     * Sale Person: Student Full Name (Student ID)
     * <order log()>
     *
     * For example:
     * Sale Person: Siripen Pongpaichet (6488000)
	 * Customer: 1: Thanapon Noraset
	 * - Sinovac\t3000.00\t2 (doses)\t6000.00
	 * - AstraZeneca\t300.00\t1 (doses)\t300.00
	 * Total: 6300.00
	 * [VALID] CASH::6300.00::7000.00::700.00
     *
     * @param order
     * @param filepath
     * @throws IOException
     */
    public static void writeOrderText(Order order, String filepath) throws IOException {
        // TODO 27: Implement writeOrderText(Order order, String filepath)
    	PrintWriter print = new PrintWriter(filepath);
        print.println("Sale Person: "+"Supithcha Jongphoemwatthanaphon (6488045)");
        print.println(order.log()); //write order in form of log order
        print.close();
    	
    }
    
    
    /**
     * Write `order` into a file at `filepath`. The format of the output is in JSON:
     * 
-----------------------------------------------------
{
  "orderID": 4,
  "customer": {
    "customerID": 1,
    "name": "Thanapon Noraset"
  },
  "items": [
    {
      "name": "Sinovac",
      "price": 3000.0,
      "quantity": 2
    },
    {
      "name": "AstraZeneca",
      "price": 300.0,
      "quantity": 1
    },
    
  ],
  "payment": {
    "type": "Cash",
    "properties": {
      "cash": 7000.0,
      "amount": 6300.0,
      "method": "CASH"
    }
  }
}
-----------------------------------------------------
     * 
     * @param order
     * @param filepath
     * @throws JsonIOException
     * @throws IOException
     */
    public static void writeOrderJson(Order order, String filepath) throws JsonIOException, IOException {
    	// TODO 28: Implement writeOrderJson(Order order, String filepath)
    	// Hint. Since the Payment is an abstract class which cannot be constructed,
    	// so you will need to apply your custom serializer and deserializer.
    	// If you use Gson library, you can register GsonBuilder with PaymentAdapter class  
    	// (already provided - see the file for more detail).
    	// However, feel free to explore other methods and create any additional classes or methods as needed.
    	GsonBuilder gb = new  GsonBuilder(); //crate new Gson
        gb.registerTypeAdapter(Payment.class, new PaymentAdapter());
        Gson gs = gb.setPrettyPrinting().create();
        String js = gs.toJson(order); //change to Json
        BufferedWriter write = new BufferedWriter(new FileWriter(filepath));
        if(order != null) 
        	{ write.write(js); }
        write.close(); //close BufferedWriter
    	
    }
		
}