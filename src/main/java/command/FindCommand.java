package command;

import exceptions.EmptyListException;
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

    public String getItemInfo() {
        return itemInfo;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public void execute() throws EmptyListException {
        if (itemInfo.equals("NA")) {
            itemInfo = "item + qty + uom + cat + buy + sell";
        }
        ArrayList<String> searchList = filterList();
        TextUi.showList(searchList);
    }

    public ArrayList<String> filterList() throws EmptyListException {
        ArrayList<String> searchList = new ArrayList<>();
        for (Item item : Itemlist.getItems()) {
            if (itemInfo.toLowerCase().contains("item") && item.getItemName().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("qty") && Integer.toString(item.getQuantity()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("uom") && item.getUnitOfMeasurement().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (item.getCategory() != null) {
                if (itemInfo.toLowerCase().contains("cat") && item.getCategory().toLowerCase().contains(keyword)) {
                    searchList.add(String.valueOf(item));
                }
            }
            if (itemInfo.toLowerCase().contains("buy") && Float.toString(item.getBuyPrice()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
            if (itemInfo.toLowerCase().contains("sell") && Float.toString(item.getSellPrice()).equals(keyword)) {
                searchList.add(String.valueOf(item));
            }
        }
        if (searchList.isEmpty()) {
            throw new EmptyListException("Item");
        }
        return searchList;
    }

}
