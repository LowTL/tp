package promotion;

import command.EditCommand;
import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import item.Item;
import itemlist.Itemlist;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static promotion.Month.JAN;

public class Promotion {

    protected String itemName;

    protected Float discount;

    protected int startDate;

    protected Month startMonth;

    protected int startYear;

    protected int endDate;

    protected Month endMonth;

    protected int endYear;

    protected DayOfWeek weekly;

    protected int startTime;

    protected int endTime;

    public Promotion(
            String itemName,
            Float discount,
            int startDate, Month startMonth, int startYear,
            int endDate, Month endMonth, int endYear,
            int startTime,
            int endTime) {
        this.itemName = itemName;
        this.discount = discount;
        this.startDate = startDate;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDate = endDate;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.startTime = startTime;
        this.endTime = endTime;

    }


    public Float getDiscount() {
        return discount;
    }

    public int getStartDate() {
        return startDate;
    }

    public Month getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndDate() {
        return endDate;
    }

    public Month getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getItemName() {
        return itemName;
    }

    public String toString() {
        return getItemName() + getDiscount();
    }
}
