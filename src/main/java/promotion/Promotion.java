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
            DayOfWeek weekly,
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
        this.weekly = weekly;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public void Main() throws InvalidDateException {
        if (!isValidMonth(startDate, startMonth, startYear) || !isValidMonth(endDate, endMonth, endYear)) {
            throw new InvalidDateException();
        }
        if (!isValidTime(startTime, endTime)) {
            throw new InvalidDateException();
        }
        if (isOnPromo(itemName)) { //this code should be under sell component
            //Item item = Itemlist.getItem("itemName");
            //float originalPrice = item.getSellPrice();
            //new EditCommand(itemName, "NA",-1, "NA", "NA", -1, discount*originalPrice);
        }
    }

    public boolean isOnPromo(String itemName) {
        if (!isPromoExist()) {
            return false;
        }
        if (!Promotionlist.itemIsOnPromo(itemName)) {
            return false;
        }
        return true;
    }

    public boolean isPromoExist() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("M");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");
        String formattedYear= currentTime.format(yearFormatter);
        String formattedMonth = currentTime.format(monthFormatter);
        String formattedDate = currentTime.format(dateFormatter);
        int year = Integer.parseInt(formattedYear);
        int month = Integer.parseInt(formattedMonth);
        int date = Integer.parseInt(formattedDate);
        if (year < startYear || year > endYear) {
            return false;
        }
        if (month < startMonth.getValue() || month > endMonth.getValue()) {
            return false;
        }
        if (date < startDate || date > endDate) {
            return false;
        }
        return true;
    }

    public boolean isValidTime (int startTime, int endTime) throws InvalidDateException {
        boolean startIsValid = isVerifiedTime(startTime);
        boolean endIsValid = isVerifiedTime(endTime);
        if (!startIsValid || !endIsValid) {
            return false;
        }
        return endTime <= startTime;
    }

    public boolean isVerifiedTime (int time) {
        String[] splitTime = String.valueOf(time).split("(?<=.)");
        if (splitTime.length != 4) {
            return false;
        }
        int hour = time / 100;
        int min = time % 100;
        if (hour > 23 || hour < 0) {
            return false;
        }
        return min <= 59 && min >= 0;
    }

    public boolean isValidMonth (int date, Month month, int year) throws InvalidDateException {
        switch (month) {
        case JAN:
            if (date > 31 || date < 1) {
                return false;
            }
        case FEB:
            if (isLeapYear(year) && (date > 29 || date < 1)) {
                return false;
            } else return date <= 28 && date >= 1;
        case MAR:
        case MAY:
        case JUL:
        case DEC:
        case OCT:
        case AUG:
            return date <= 31 && date >= 1;
        case APR:
        case SEP:
        case NOV:
        case JUN:
            return date <= 30 && date >= 1;
        default:
            throw new InvalidDateException();
        }
    }

    public Float getPromoPrice() {
        return promoPrice;
    }

    public String getItemName() {
        return itemName;
    }
}
