package library.android.com.calendar.model;

import java.util.List;

public class MonthConfig {

    private String header;
    private int headerColor = -1;
    private List<DayConfig> daysOfTheMonth;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getHeaderColor() {
        return headerColor;
    }

    public void setHeaderColor(int headerColor) {
        this.headerColor = headerColor;
    }

    public List<DayConfig> getDaysOfTheMonth() {
        return daysOfTheMonth;
    }

    public void setDaysOfTheMonth(List<DayConfig> daysOfTheMonth) {
        this.daysOfTheMonth = daysOfTheMonth;
    }
}
