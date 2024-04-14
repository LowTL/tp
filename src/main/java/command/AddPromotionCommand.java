//@@author HengShuHong
package command;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import item.Item;
import itemlist.Itemlist;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;
import ui.TextUi;

import java.util.logging.Level;

public class AddPromotionCommand extends Command {

    protected Promotion promotion;

    public AddPromotionCommand(
            String itemName,
            Float discount,
            int startDate, Month startMonth, int startYear,
            int endDate, Month endMonth, int endYear,
            int startTime,
            int endTime) {
        Item item = Itemlist.getItem(itemName);
        this.promotion = new Promotion(
                item.getItemName(), discount,
                startDate, startMonth, startYear,
                endDate, endMonth, endYear,
                startTime, endTime);
        LOGGER.info("AddPromotion successfully created.");
    }

    /**
     * Adds a new promotion for an item that is in the item list
     *
     * @throws InvalidDateException if there is an invalid date for promotion
     * @throws CommandFormatException if item is not found in the list or there is an invalid discount
     */
    @Override
    public void execute() throws InvalidDateException, CommandFormatException {
        try {
            Promotionlist.addPromotion(promotion);
            TextUi.replyToUser(
                    "The following promotion has been added",
                    promotion.getItemName() + " have a " + String.format("%.2f", promotion.getDiscount() * 100) +
                            "% discount", "Period: " + promotion.getStartDay() + " " + promotion.getStartMonth() +
                            " " + promotion.getStartYear() + " to " + promotion.getEndDay() + " " +
                            promotion.getEndMonth() + " " + promotion.getEndYear(),
                    "Time: " + String.format("%04d", promotion.getStartTime()) + " to " +
                            String.format("%04d", promotion.getEndTime())
            );
            LOGGER.info("Promotion successfully created.");
        } catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
            LOGGER.log(Level.WARNING, "Unable to create promotion", e);
        }
    }
}
