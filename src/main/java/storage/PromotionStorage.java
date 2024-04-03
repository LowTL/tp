package storage;

import exceptions.CommandFormatException;
import exceptions.InvalidDateException;
import item.Item;
import promotion.Month;
import promotion.Promotion;
import promotion.Promotionlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PromotionStorage extends Storage{

    private static final String PRMOTIONSTORAGE = "./PromotionStorage.txt";

    public static String getFileDirectory() {
        return PRMOTIONSTORAGE;
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    public static void readFromFile(String fileName) {
        int count = 0;
        String itemName = "";
        float discount = 0;
        int startDate = 0, startYear = 0, endDate = 0, endYear = 0;
        Month startMonth = null,endMonth = null;
        int startTime = 0, endTime = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                switch (count) {
                case (0):
                    String[] itemNameParts = fileLine.split(" have a ");
                    itemName = itemNameParts[0];
                    discount = Float.parseFloat(itemNameParts[1].split("%")[0]) / 100;
                    count = count + 1;
                    break;
                case (1):
                    String[] periodParts = fileLine.split("Period: ");
                    String[] startEndPeriod = periodParts[1].split(" to ");
                    String[] getStart = startEndPeriod[0].split(" ");
                    String[] getEnd = startEndPeriod[1].split(" ");
                    startDate = Integer.parseInt(getStart[0]);
                    startMonth = Month.valueOf(getStart[1]);
                    startYear = Integer.parseInt(getStart[2]);
                    endDate = Integer.parseInt(getEnd[0]);
                    endMonth = Month.valueOf(getEnd[1]);
                    endYear = Integer.parseInt(getEnd[2]);
                    count = count + 1;
                    break;
                case(2):
                    String[] getTime = fileLine.split("Time: ");
                    String[] startEndTime = getTime[1].split(" to ");
                    startTime = Integer.parseInt(startEndTime[0]);
                    endTime = Integer.parseInt(startEndTime[1]);
                    Promotion toAdd = new Promotion(itemName, discount, startDate, startMonth, startYear,
                            endDate, endMonth, endYear, startTime, endTime);
                    Promotionlist.addPromotion(toAdd);
                    count = 0;
                    break;
                default:
                    System.out.println("Read Promotion File Error");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid numbers found.");
        } catch (InvalidDateException | CommandFormatException e) {
            System.out.println(e);;
        }
    }



    public static void addToPromotionFile(ArrayList<Promotion> promotions) {
        assert promotions != null : "Promotions cannot be null.";
        Promotion lastPromotion = promotions.get(promotions.size() - 1);
        String descriptionAdded = (lastPromotion.getItemName() + " have a " +
                (lastPromotion.getDiscount()*100) + "% discount" + "\n" + "Period: " + lastPromotion.getStartDate() +
                " " + lastPromotion.getStartMonth() + " " + lastPromotion.getStartYear() + " to " +
                lastPromotion.getEndDate()+ " " + lastPromotion.getEndMonth() + " " + lastPromotion.getEndYear() + "\n" +
                "Time: " + lastPromotion.getStartTime() + " to " + lastPromotion.getEndTime() + "\n");
        updateFile(descriptionAdded, true);
    }

    public static void overwritePromotionFile(ArrayList<Promotion> promotions) {
        assert promotions != null : "Promotions cannot be null.";
        int length = promotions.size();
        for (Promotion promotion: promotions) {
            String descriptionAdded = (promotion.getItemName() + " have a " +
                    (promotion.getDiscount()*100) + "% discount" + "\n" + "Period: " + promotion.getStartDate() +
                    " " + promotion.getStartMonth() + " " + promotion.getStartYear() + " to " +
                    promotion.getEndDate()+ " " + promotion.getEndMonth() + " " + promotion.getEndYear() + "\n" +
                    "Time: " + promotion.getStartTime() + " to " + promotion.getEndTime() + "\n");
            if ( promotions.indexOf(promotion) == 0) {
                updateFile(descriptionAdded, false);
            } else {
                updateFile(descriptionAdded, true);
            }
        }
    }
}
