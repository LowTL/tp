//@@author HengShuHong
package promotion;

import command.Command;
import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import itemlist.Itemlist;
import storage.PromotionStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Promotionlist {
    private static final ArrayList<Promotion> promotions = new ArrayList<>();

    protected static final Logger LOGGER = Logger.getLogger(Promotionlist.class.getName());

    public static int getIndex(Promotion promotion) {
        return promotions.indexOf(promotion);
    }

    public static void deletePromotion(int index) {
        promotions.remove(index);
    }

    /**
     * Boolean to determine if the item has an existing promotion
     *
     * @param itemName name of item
     * @return true if item is on promotion, false if item is not on promotion
     */
    public static boolean itemIsOnPromo(String itemName) {
        for (Promotion promotion : promotions) {
            if (promotion.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the year is a leap year
     *
     * @param year
     * @return true if it is leap year, false if it is not
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0;
    }

    /**
     * Adds a promotion to promotionlist.
     * Multiple checks are done to ensure that the promotion entered is valid.
     *
     * @param promotion
     * @throws InvalidDateException is thrown when the date or time is invalid
     * @throws CommandFormatException is thrown when discount is invalid or item is not found
     */
    public static void addPromotion(Promotion promotion) throws InvalidDateException, CommandFormatException {
        int startDay = promotion.getStartDay();
        int endDate = promotion.getEndDay();
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
        if (!isValidMonth(startDay, startMonth, startYear) || !isValidMonth(endDate, endMonth, endYear)) {
            throw new InvalidDateException("INVALID_DATE");
        }
        if (!isValidTime(startTime, endTime)) {
            throw new InvalidDateException("INVALID_TIME");
        }
        if (!isValidDuration(startDay, startMonth, startYear, endDate, endMonth, endYear)) {
            throw new InvalidDateException("INVALID_PERIOD");
        }
        promotions.add(promotion);
        PromotionStorage.overwritePromotionFile(Promotionlist.getAllPromotion());
    }

    /**
     * Checks if the input discount is valid
     *
     * @param discount
     * @return true if the discount ranges from 0 to 1, returns false otherwise
     */
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
        if (time < promotion.getStartTime() || time > promotion.getEndTime()) {
            return false;
        }
        if (year < promotion.getStartYear() || year > promotion.getEndYear()) {
            return false;
        }
        if (year > promotion.getStartYear() && year < promotion.getEndYear()) {
            return true;
        }
        if (year == promotion.getStartYear()) {
            if (month < promotion.getStartMonth().getValue()) {
                return false;
            }
            if (day < promotion.getStartDay()) {
                return false;
            }
            return true;
        }
        if (year == promotion.getEndYear()) {
            if (month > promotion.getEndMonth().getValue()) {
                return false;
            }
            if (day > promotion.getEndDay()) {
                return false;
            }
            return true;
        }
        LOGGER.log(Level.WARNING, "Unable to create promotion");
        return true;
    }


    /**
     * Checks if the time of promotion is valid
     *
     * @param startTime starting time of the promotion
     * @param endTime ending time of the promotion
     * @return true if time ranges from 0000 and 2359, and ending time is later than starting time
     */
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

    /**
     * Checks if time ranges from 0000 and 2359
     *
     * @param timeStr time with String datatype
     * @return true if hour ranges from 0 to 23, minutes ranges from 0 to 59
     */
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


    /**
     * Checks if the duration of the promotion period is valid
     *
     * @param startDay
     * @param startMonth
     * @param startYear
     * @param endDay
     * @param endMonth
     * @param endYear
     * @return true if promotion period is valid, return false if end is earlier than the start of promotion
     */
    public static boolean isValidDuration (int startDay, Month startMonth, int startYear, int endDay, Month endMonth,
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
        if (endDay > startDay) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the day exists in the month specified
     *
     * @param day
     * @param month
     * @param year
     * @return true if day exists, false if does not exists
     * @throws InvalidDateException is thrown when the day does not exists in the month
     */
    public static boolean isValidMonth(int day, Month month, int year) throws InvalidDateException {
        switch (month) {
        case FEB:
            if (isLeapYear(year) && (day < 30 && day > 0)) {
                return true;
            } else {
                return day <= 28 && day >= 1;
            }
        case JAN: //fall through
        case MAR: //fall through
        case MAY: //fall through
        case JUL: //fall through
        case DEC: //fall through
        case OCT: //fall through
        case AUG:
            return day <= 31 && day >= 1;
        case APR: //fall through
        case SEP: //fall through
        case NOV: //fall through
        case JUN:
            return day <= 30 && day >= 1;
        default:
            throw new InvalidDateException("INVALID_PERIOD");
        }
    }

    /**
     * Gets the promotion for the item
     *
     * @param itemName
     * @return promotion of the item, return null if there is no promotion
     */
    public static Promotion getPromotion(String itemName) {
        for (Promotion promotion: promotions) {
            if (promotion.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                return promotion;
            }
        }
        return null;
    }

    /**
     * Gets all promotion in the promotion lists
     *
     * @return all promotions
     */
    public static ArrayList<Promotion> getAllPromotion() {
        return promotions;
    }
}
