package command;

import item.Item;
import itemlist.Itemlist;

public class LowStockCommand extends Command{

    protected static final int DEFAULT_LOW_STOCK_THRESHOLD = 5;
    protected int lowStockAmount;

    public LowStockCommand(){
        this.lowStockAmount = DEFAULT_LOW_STOCK_THRESHOLD;
    }
    public LowStockCommand(int lowStockAmount){
        this.lowStockAmount = lowStockAmount;
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

    public static void lowOnStockItemsReminder(int lowStockAmount){
        int count = 0;
        System.out.println("Low-on-stock Items: (less than " + lowStockAmount + ")");
        for (Item item : Itemlist.getItems()) {
            if (item.getQuantity() <= lowStockAmount && item.getQuantity() > 0) {  //low stock condition
                System.out.println(item.getItemName());
                count++;
            }
        }
        if (count == 0){
            System.out.println("No items low on stock");
        }
    }
    @Override
    public void execute() {
        outOfStockItemsReminder();
        lowOnStockItemsReminder(lowStockAmount);
    }
}
