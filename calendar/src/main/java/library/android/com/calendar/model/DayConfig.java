package library.android.com.calendar.model;

import android.support.annotation.ColorRes;

public class DayConfig {

    //Flag to check whether it will be week indicator or the day itself
    private boolean isWeekIndicator = false;
    //The value of the day or indicator to display
    private String dayToDisplay;
    //The price of the selected day
    private String priceOfDay;
    //The day of the week
    private int dayOfTheWeek;
    //The momth it belongs to
    private int monthOwner;
    //The year it belongs to
    private String yearOwner;
    //Color of the price to display
    private int priceColor = -1;
    //Color of the circle of the time to display
    private int daySelectedColor = -1;
    //Color of the range of time to display
    private int rangeColor = -1;
    //Color of the day or week indicator to display
    private int weekIndicatorColor = -1;
    //Flag to display the circle of the starting day
    private boolean isStartSelectedDay = false;
    //Flag to display the range color between the start and the end day
    private boolean isMiddleRangeDay = false;
    //Flag to display the circle of the ending day
    private boolean isEndSelectedDay = false;
    //Flag to indicate this day will be empty and has no action
    private boolean isEmptySpace = false;


    public boolean isWeekIndicator() {
        return isWeekIndicator;
    }

    public void setWeekIndicator(boolean weekIndicator) {
        isWeekIndicator = weekIndicator;
    }

    public String getDayToDisplay() {
        return dayToDisplay != null ? dayToDisplay : "";
    }

    public void setDayToDisplay(String dayToDisplay) {
        this.dayToDisplay = dayToDisplay;
    }

    public String getPriceOfDay() {
        return priceOfDay != null ? priceOfDay : "";
    }

    public void setPriceOfDay(String priceOfDay) {
        this.priceOfDay = priceOfDay;
    }

    public int getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(int priceColor) {
        this.priceColor = priceColor;
    }

    public int getDaySelectedColor() {
        return daySelectedColor;
    }

    public void setDaySelectedColor(@ColorRes int daySelectedColor) {
        this.daySelectedColor = daySelectedColor;
    }

    public int getRangeColor() {
        return rangeColor;
    }

    public void setRangeColor(@ColorRes int rangeColor) {
        this.rangeColor = rangeColor;
    }

    public int getWeekIndicatorColor() {
        return weekIndicatorColor;
    }

    public void setWeekIndicatorColor(@ColorRes int weekIndicatorColor) {
        this.weekIndicatorColor = weekIndicatorColor;
    }

    public boolean isStartSelectedDay() {
        return isStartSelectedDay;
    }

    public void setStartSelectedDay(boolean startSelectedDay) {
        isStartSelectedDay = startSelectedDay;
    }

    public boolean isEndSelectedDay() {
        return isEndSelectedDay;
    }

    public void setEndSelectedDay(boolean endSelectedDay) {
        isEndSelectedDay = endSelectedDay;
    }

    public boolean isMiddleRangeDay() {
        return isMiddleRangeDay;
    }

    public void setMiddleRangeDay(boolean middleRangeDay) {
        isMiddleRangeDay = middleRangeDay;
    }

    public boolean isEmptySpace() {
        return isEmptySpace;
    }

    public void setEmptySpace(boolean emptySpace) {
        isEmptySpace = emptySpace;
    }

    public int getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(int dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getMonthOwner() {
        return monthOwner;
    }

    public void setMonthOwner(int monthOwner) {
        this.monthOwner = monthOwner;
    }

    public String getYearOwner() {
        return yearOwner;
    }

    public void setYearOwner(String yearOwner) {
        this.yearOwner = yearOwner;
    }
}
