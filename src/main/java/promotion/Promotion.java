//@@author HengShuHong
package promotion;

public class Promotion {

    protected String itemName;

    protected Float discount;

    protected int startDay;

    protected Month startMonth;

    protected int startYear;

    protected int endDay;

    protected Month endMonth;

    protected int endYear;

    protected int startTime;

    protected int endTime;

    public Promotion(
            String itemName,
            Float discount,
            int startDay, Month startMonth, int startYear,
            int endDay, Month endMonth, int endYear,
            int startTime,
            int endTime) {
        this.itemName = itemName;
        this.discount = discount;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
        this.startTime = startTime;
        this.endTime = endTime;

    }


    public float getDiscount() {
        return discount;
    }

    public int getStartDay() {
        return startDay;
    }

    public Month getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndDay() {
        return endDay;
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
        return getItemName() + " have a " + String.format("%.2f", (getDiscount() * 100)) +
                "% discount" + "\n" + "Period: " + getStartDay() + " " + getStartMonth() + " "
                + getStartYear() + " to " + getEndDay() + " " + getEndMonth() + " " +
                getEndYear() + "\n" + "Time: " + String.format("%04d", getStartTime()) + " to " +
                String.format("%04d", getEndTime());
    }
}
