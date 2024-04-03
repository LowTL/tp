package promotion;

import command.EditCommand;
import exceptions.InvalidDateException;
import item.Item;
import itemlist.Itemlist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Promotionlist {
    private static final ArrayList<Promotion> promotions = new ArrayList<>();

    public Promotionlist() {
    }

    public static void deletePromotion(int index) {
        promotions.remove(index);
    }

    public static boolean itemIsOnPromo(String itemName) {
        for (Promotion promotion : Promotionlist.getPromo()) {
            if (promotion.getItemName().toLowerCase().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOnPromo(String itemName) {
        if (!Promotionlist.itemIsOnPromo(itemName)) {
            return false;
        }
        assert getPromotion(itemName) != null;
        if (!isPromoExist(getPromotion(itemName))) {
            return false;
        }
        return true;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public static void addPromotion(Promotion promotion) throws InvalidDateException {
        int startDate = promotion.getStartDate();
        int endDate = promotion.getEndDate();
        Month startMonth = promotion.getStartMonth();
        Month endMonth = promotion.getEndMonth();
        int startYear = promotion.getStartYear();
        int endYear = promotion.getEndYear();
        int startTime = promotion.getStartTime();
        int endTime = promotion.getEndTime();
        String itemName = promotion.getItemName();
        float discount = promotion.getDiscount();

        if (!Itemlist.itemIsExist(itemName)) { //promotion not created as item does not exist
            throw new InvalidDateException();
        }

        //promotion not created as either invalid date for the month or year
        if (!isValidMonth(startDate, startMonth, startYear) || !isValidMonth(endDate, endMonth, endYear)) {
            throw new InvalidDateException();
        }
        //
        if (!isValidTime(startTime, endTime)) {
            throw new InvalidDateException();
        }
        promotions.add(promotion); //promotion is valid and created
        //add in writeToPromotionList

    }

    public void deletePromo(int index) {
        promotions.remove(index);
    }


    public static boolean isPromoExist(Promotion promotion) {
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
        if (year < promotion.getStartDate() || year > promotion.getEndDate()) {
            return false;
        }
        if (month < promotion.getStartMonth().getValue() || month > promotion.getEndMonth().getValue()) {
            return false;
        }
        if (date < promotion.getStartDate() || date > promotion.getEndDate()) {
            return false;
        }
        return true;
    }

    public static boolean isValidTime(int startTime, int endTime) throws InvalidDateException {
        boolean startIsValid = isVerifiedTime(startTime);
        boolean endIsValid = isVerifiedTime(endTime);
        if (!startIsValid || !endIsValid) { //format for date and time is valid
            return false;
        }
        return true;
    }

    public static boolean isVerifiedTime(int time) {
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

    public static boolean isValidMonth(int date, Month month, int year) throws InvalidDateException {
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

    public static Promotion getPromotion(String itemName) {
        for (Promotion promotion: promotions) {
            if (promotion.getItemName().equals(itemName)) {
                return promotion;
            }
        }
        return null;
    }

    public static ArrayList<Promotion> getPromo() {
        return promotions;
    }
}
