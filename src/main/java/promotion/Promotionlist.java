package promotion;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import storage.PromotionStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Promotionlist {
    private static final ArrayList<Promotion> promotions = new ArrayList<>();

    public Promotionlist() {
    }

    public static int getIndex(Promotion promotion) {
        return promotions.indexOf(promotion);
    }

    public static void deletePromotion(int index) {
        promotions.remove(index);
    }

    public static boolean itemIsOnPromo(String itemName) {
        for (Promotion promotion : promotions) {
            if (promotion.getItemName().toLowerCase().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    public static void addPromotion(Promotion promotion) throws InvalidDateException, CommandFormatException {
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

        if (!Itemlist.itemIsExist(itemName)) {
            throw new CommandFormatException("ITEM_NOT_FOUND");
        }
        if (Promotionlist.itemIsOnPromo(itemName)) {
            throw new InvalidDateException("ITEM_IS_PROMO");
        }
        if (!isValidDiscount(discount)) {
            throw new CommandFormatException("INVALID_DISCOUNT");
        }
        if (!isValidMonth(startDate, startMonth, startYear) || !isValidMonth(endDate, endMonth, endYear)) {
            throw new InvalidDateException("INVALID_PERIOD");
        }
        if (!isValidTime(startTime, endTime)) {
            throw new InvalidDateException("INVALID_TIME");
        }
        if (!isValidDuration(startDate, startMonth, startYear, endDate, endMonth, endYear)) {
            throw new InvalidDateException("INVALID_PERIOD");
        }
        promotions.add(promotion);
        PromotionStorage.overwritePromotionFile(Promotionlist.getAllPromotion());
    }

    public static boolean isValidDiscount (float discount) {
        return !(discount < 0) && !(discount > 1);
    }

    public static boolean isPromoExistNow(String itemName) {
        if (!itemIsOnPromo(itemName)) {
            return false;
        }
        Promotion promotion = getPromotion(itemName);
        LocalDateTime currentDateTime = LocalDateTime.now();

        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonthValue();
        int day = currentDateTime.getDayOfMonth();
        int hour = currentDateTime.getHour();
        int minute = currentDateTime.getMinute();
        String formattedTime = String.valueOf(hour) + String.valueOf(minute);
        int time = Integer.parseInt(formattedTime);
        if (year < promotion.getStartYear() || year > promotion.getEndYear()) {
            return false;
        }
        if (month < promotion.getStartMonth().getValue() || month > promotion.getEndMonth().getValue()) {
            return false;
        }
        if (day < promotion.getStartDate() || day > promotion.getEndDate()) {
            return false;
        }
        if (time < promotion.getStartTime() || time > promotion.getEndTime()) {
            return false;
        }
        return true;
    }

    public static boolean isValidTime(int startTime, int endTime) {
        String startTimeStr = String.format("%04d", startTime);
        String endTimeStr = String.format("%04d", endTime);
        boolean startIsValid = isVerifiedTime(startTimeStr);
        boolean endIsValid = isVerifiedTime(endTimeStr);
        if (!startIsValid || !endIsValid) {
            return false;
        }
        if (startTime > endTime) {
            return false;
        }
        return true;
    }

    public static boolean isVerifiedTime(String timeStr) {
        String[] splitTime = timeStr.split("(?<=.)");
        if (splitTime.length != 4) {
            return false;
        }
        int time = Integer.parseInt(timeStr);
        int hour = time / 100;
        int min = time % 100;
        if (hour > 23 || hour < 0) {
            return false;
        }
        return min <= 59 && min >= 0;
    }

    public static boolean isValidDuration (int startDate, Month startMonth, int startYear, int endDate, Month endMonth,
                                           int endYear) {
        int startMonthInt = startMonth.getValue();
        int endMonthInt = endMonth.getValue();
        if (endYear > startYear) {
            return true;
        } else if (endYear < startYear) {
            return false;
        }
        if (endMonthInt > startMonthInt) {
            return true;
        } else if (endMonthInt < startMonthInt) {
            return false;
        }
        if (endDate > startDate) {
            return true;
        }
        return false;
    }

    public static boolean isValidMonth(int date, Month month, int year) throws InvalidDateException {
        switch (month) {
        case FEB:
            if (isLeapYear(year) && (date < 30 && date > 0)) {
                return true;
            } else {
                return date <= 28 && date >= 1;
            }
        case JAN: //fall through
        case MAR: //fall through
        case MAY: //fall through
        case JUL: //fall through
        case DEC: //fall through
        case OCT: //fall through
        case AUG:
            return date <= 31 && date >= 1;
        case APR: //fall through
        case SEP: //fall through
        case NOV: //fall through
        case JUN:
            return date <= 30 && date >= 1;
        default:
            System.out.println("Date does not exist.");
            throw new InvalidDateException("INVALID_PERIOD");
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

    public static ArrayList<Promotion> getAllPromotion() {
        return promotions;
    }
}
