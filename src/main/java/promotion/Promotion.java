package promotion;

import ui.TextUi;

public class Promotion {

    protected String itemName;

    protected Float discount;

    protected int startDate;

    protected Month startMonth;

    protected int startYear;

    protected int endDate;

    protected Month endMonth;

    protected int endYear;

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


    public float getDiscount() {
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

    @Override
    public String toString() {
        return getItemName() + " have a " + String.format("%.2f", (getDiscount()*100)) +
                "% discount" + "\n" + "Period: " + getStartDate() + " " + getStartMonth() + " "
                + getStartYear() + " to " + getEndDate()+ " " + getEndMonth() + " " +
                getEndYear() + "\n" + "Time: " + String.format("%04d", getStartTime()) + " to " +
                String.format("%04d", getEndTime());
    }

    public void print(String args) {
        TextUi.replyToUser(args);
    }
}
