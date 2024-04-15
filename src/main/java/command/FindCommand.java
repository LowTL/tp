//@@author HengShuHong
package command;

import exceptions.CommandFormatException;
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

    /**
     * Searches for item(s) given a specified keyword
     *
     * @throws EmptyListException when the filtered list is empty (nothing found)
     */
    @Override
    public void execute() throws EmptyListException {
        if (itemInfo.equals("NA")) {
            itemInfo = "item/qty/uom/cat/buy/sell";
        }
        try {
            ArrayList<String> searchList = filterList();
            TextUi.showList(searchList);
            LOGGER.info("Itemlist successfully filtered.");
        } catch (EmptyListException e) {
            LOGGER.warning("EMPTY LIST");
        } catch (CommandFormatException e) {
            LOGGER.warning("INVALID FILTER");
        }
    }

    /**
     * Enhances the find feature by searching through a filtered list, e.g. search for "fruits" in /item /uom
     *
     * @return a list of items that matches the filter and the keyword
     * @throws EmptyListException when the filtered list is empty (nothing found)
     */
    public ArrayList<String> filterList() throws EmptyListException, CommandFormatException {
        ArrayList<String> searchList = new ArrayList<>();
        String [] getFilter = itemInfo.split("/");
        for (String s : getFilter) {
            String filter = s.toLowerCase();
            switch (filter) {
            case "item": //fall through
            case "qty": //fall through
            case "uom": //fall through
            case "cat": //fall through
            case "buy": //fall through
            case "sell":
                filterItemsBySingleFilter(searchList, filter, keyword);
                break;
            default:
                throw new CommandFormatException("INVALID_FILTER");
            }
        }
        if (searchList.isEmpty()) {
            LOGGER.warning("Item not found.");
            throw new EmptyListException("Item");
        }
        return searchList;
    }

    private void filterItemsBySingleFilter(ArrayList<String> searchList, String filter, String keyword) {
        for (Item item : Itemlist.getItems()) {
            if (searchList.contains(String.valueOf(item))) {
                continue;
            }
            if (filter.equalsIgnoreCase("item") && item.getItemName().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
                continue;
            }
            if (filter.equalsIgnoreCase("qty") && Integer.toString(item.getQuantity()).equals(keyword)) {
                searchList.add(String.valueOf(item));
                continue;
            }
            if (filter.equalsIgnoreCase("uom") && item.getUnitOfMeasurement().toLowerCase().contains(keyword)) {
                searchList.add(String.valueOf(item));
                continue;
            }
            if (item.getCategory() != null && filter.equalsIgnoreCase("cat") && item.getCategory().toLowerCase().
                    contains(keyword)) {
                searchList.add(String.valueOf(item));
                continue;
            }
            try {
                if (filter.equalsIgnoreCase("buy") && Math.abs(item.getBuyPrice() - Float.parseFloat(keyword))
                        <= 0.01f) {
                    searchList.add(String.valueOf(item));
                    continue;
                }
                if (filter.equalsIgnoreCase("sell") && Math.abs(item.getSellPrice() - Float.parseFloat(keyword))
                        <= 0.01f) {
                    searchList.add(String.valueOf(item));
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Keyword contains non numeric characters");
            }
        }
    }
}
