package reminder;

import item.Item;
import itemlist.Itemlist;

public class LowStockReminder {

    private static final int DEFAULT_LOW_STOCK_THRESHOLD = 5;

    protected String itemName;


    public static void execute(){
        outOfStockItemsReminder();
        lowOnStockItemsReminder();
    }

    private static void outOfStockItemsReminder(){
        int count = 0;
        System.out.println("Out-of-stock Items:");
        for (Item item : Itemlist.getItems()) {
            if (item.getIsOOS()) {
                System.out.println(item.getItemName());
                count++;
            }
        }
        if (count == 0){
            System.out.println("No items out of stock");
        }
    }

    private static void lowOnStockItemsReminder(){
        int count = 0;
        System.out.println("Low-on-stock Items:");
        for (Item item : Itemlist.getItems()) {
            if (item.getQuantity() <= DEFAULT_LOW_STOCK_THRESHOLD && item.getQuantity() > 0) {  //low stock condition
                System.out.println(item.getItemName());
                count++;
            }
        }
        if (count == 0){
            System.out.println("No items low on stock");
        }
    }
}
