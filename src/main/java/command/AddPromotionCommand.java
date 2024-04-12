package command;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;
import ui.TextUi;

public class AddPromotionCommand extends Command {

    protected Promotion promotion;

    public AddPromotionCommand(
            String itemName,
            Float discount,
            int startDate, Month startMonth, int startYear,
            int endDate, Month endMonth, int endYear,
            int startTime,
            int endTime) {
        this.promotion = new Promotion(
                itemName, discount,
                startDate, startMonth, startYear,
                endDate, endMonth, endYear,
                startTime, endTime);
    }

    @Override
    public void execute() throws InvalidDateException, CommandFormatException {
        try {
            Promotionlist.addPromotion(promotion);
            TextUi.replyToUser(
                    "The following promotion has been added",
                    promotion.getItemName() + " have a " + String.format("%.2f", promotion.getDiscount() * 100) +
                            "% discount", "Period: " + promotion.getStartDate() + " " + promotion.getStartMonth() +
                            " " + promotion.getStartYear() + " to " + promotion.getEndDate() + " " +
                            promotion.getEndMonth() + " " + promotion.getEndYear(),
                    "Time: " + String.format("%04d", promotion.getStartTime()) + " to " +
                            String.format("%04d", promotion.getEndTime())
            );
        } catch (InvalidDateException | CommandFormatException e){
            System.out.print("");
        }
    }
}
