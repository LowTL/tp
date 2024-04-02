package command;

import item.Item;
import promotion.Month;
import promotion.Promotion;

import java.time.DayOfWeek;

public class PromotionCommand extends Command {

    protected Promotion promotion;

    public void promotionCommand(
            String itemName,
            Float promoPrice,
            int startDate, Month startMonth, int startYear,
            int endDate, Month endMonth, int endYear,
            DayOfWeek weekly,
            int startTime,
            int endTime) {
        this.promotion = new Promotion(
                itemName, promoPrice,
                startDate, startMonth, startYear,
                endDate, endMonth, endYear,
                weekly, startTime, endTime);
    }


    public Promotion getPromotion() {
        return promotion;
    }


    @Override
    public void execute() {
    }



}
