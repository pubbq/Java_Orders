package app;
/**
 * Name: Supithcha Jongphoemwatthanaphon
 * Student ID: 6488045
 * Section: 2
 */


public class ItemFactory {

    public static final int MAX_BARCODE = 4;  // Update this number if you have more than 4 barcode

    public static Item createItem(int barcode, int quantity) {
        if (barcode == 0) {
            // DO NO MODIFY
            return new Item("Unknown", 42.0, quantity);
        }
        else if (barcode == 1) {
            // TODO 08: Create item type 1
            // For example, return new Vaccine("mRNA Vaccine", 1800.0, quantity);
//            return new Item( getName(), getPrice(), quantity );
        	return new Item("mRNA Vaccine", 1800.0, quantity);
        } else if (barcode == 2) {
            // TODO 09: Create item type 2
            // For example, return new Vaccine("AstraZeneca", 300.0, quantity);
        	return new Item("AstraZeneca", 300.0, quantity);
        } else if (barcode == 3) {
            // TODO 10: Create item type 3
            // For example, return new MedicalPill("Tylenal 500mg", 5.0, quantity);
        	return new Item("Tylenal 500mg", 5.0, quantity);
        } else if (barcode == 4) {
            // TODO 11: Create item type 4
            // For example, return new MedicalPill("Sara Paracetamol 500mg", 1.2, quantity);
        	return new Item("Sara Paracetamol 500mg", 1.2, quantity);
        } else {
            return null;
        }
    }
}
