package command;

import item.Item;
import itemlist.Itemlist;
import ui.TextUi;

import java.util.ArrayList;

public class FindCommand extends Command {

    protected String keyword;

    protected String itemInfo;

    public FindCommand(String itemInfo, String keyword) {
        this.keyword = keyword.toLowerCase();
        this.itemInfo = itemInfo;
    }

    @Override
    public void execute() {
        if (itemInfo.equals("NA")) {
            itemInfo = "item + qty + uom + cat + buy + sell";
        }
        ArrayList<String> searchList = filterList();
        TextUi.showInventoryList(searchList);
    }

    public ArrayList<String> filterList() {
        ArrayList<String> searchList = new ArrayList<>();
        for (Item item : Itemlist.getItems()) {
            if (itemInfo.toLowerCase().contains("item") && item.getItemName().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("qty") && Integer.toString(item.getQuantity()).contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("uom") && item.getUom().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (item.getCategory() != null) {
                if (itemInfo.toLowerCase().contains("cat") && item.getCategory().toLowerCase().contains(keyword)) {
                    searchList.add(String.valueOf(item));
                }
            }
            if (itemInfo.toLowerCase().contains("buy") && Float.toString(item.getBuyPrice()).contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("sell") && Float.toString(item.getSellPrice()).contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
        }
        return searchList;
    }
}
