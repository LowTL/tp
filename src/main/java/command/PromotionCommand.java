package command;

import exceptions.InvalidDateException;
import item.Item;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;

import java.time.DayOfWeek;

public class PromotionCommand extends Command {

    protected Promotion promotion;

    public PromotionCommand(
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
    public void execute() throws InvalidDateException {
        System.out.println(promotion);
        Promotionlist.addPromotion(promotion);
    }



}
