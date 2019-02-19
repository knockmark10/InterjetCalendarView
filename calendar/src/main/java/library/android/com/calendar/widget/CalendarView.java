package library.android.com.calendar.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import library.android.com.calendar.adapter.MonthAdapter;
import library.android.com.calendar.manager.SnapHelper;
import library.android.com.calendar.model.DayConfig;
import library.android.com.calendar.model.MonthConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarView extends RecyclerView implements MonthAdapter.MonthOnDaySelectedCallback {

    private MonthAdapter monthAdapter;
    private int weekIndicatorColor = -1;
    private int dayColor = -1;
    private int headerColor = -1;
    private int daySelectedColor = -1;
    private int rangeColor = -1;
    private CalendarViewCallback mListener;
    private List<MonthConfig> months = new ArrayList<>();

    public CalendarView(@NonNull Context context) {
        super(context);
    }

    public CalendarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CalendarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CalendarView setWeekIndicatorColor(@ColorRes int colorRes) {
        this.weekIndicatorColor = colorRes;
        return this;
    }

    public CalendarView setDayColor(@ColorRes int colorRes) {
        this.dayColor = colorRes;
        return this;
    }

    public CalendarView setHeaderColor(@ColorRes int colorRes) {
        this.headerColor = colorRes;
        return this;
    }

    public CalendarView setRangeColor(@ColorRes int colorRes) {
        this.rangeColor = colorRes;
        return this;
    }

    public CalendarView setDaySelectedColor(@ColorRes int colorRes) {
        this.daySelectedColor = colorRes;
        return this;
    }

    public void create(Calendar currentCalendar) {
        checkNotNull();
        monthAdapter = new MonthAdapter();
        SnapHelper snapHelper = new SnapHelper();
        monthAdapter.setMonths(buildCalendar(currentCalendar));
        monthAdapter.setOnDaySelectedListener(this);
        this.setAdapter(monthAdapter);
        snapHelper.attachToRecyclerView(this);
        this.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        this.hasFixedSize();
    }

    private List<MonthConfig> buildCalendar(Calendar currentCalendar) {
        this.months.clear();
        int initialMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        for (int i = 0; i < 4; i++) {
            MonthConfig currentMonth = new MonthConfig();
            currentMonth.setHeader(buildHeader(currentCalendar));
            currentMonth.setHeaderColor(headerColor);
            List<DayConfig> weekIndicator = buildWeekIndicator(weekIndicatorColor);
            weekIndicator.addAll(buildDaysOfTheMonth(currentCalendar, dayColor));
            currentMonth.setDaysOfTheMonth(weekIndicator);
            if (initialMonth == 11) {
                initialMonth = 0;
                currentYear++;
            } else {
                initialMonth++;
            }
            currentCalendar.set(Calendar.MONTH, initialMonth);
            currentCalendar.set(Calendar.YEAR, currentYear);
            months.add(currentMonth);
        }
        return months;
    }

    /**
     * Should be on every month
     */
    private List<DayConfig> buildWeekIndicator(int weekIndicatorColor) {
        List<DayConfig> daysOfTheWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DayConfig currentDay = new DayConfig();
            currentDay.setWeekIndicator(true);
            currentDay.setWeekIndicatorColor(weekIndicatorColor);
            switch (i) {
                case 0:
                    currentDay.setDayToDisplay("LU");
                    break;
                case 1:
                    currentDay.setDayToDisplay("MA");
                    break;
                case 2:
                    currentDay.setDayToDisplay("MIE");
                    break;
                case 3:
                    currentDay.setDayToDisplay("JU");
                    break;
                case 4:
                    currentDay.setDayToDisplay("VI");
                    break;
                case 5:
                    currentDay.setDayToDisplay("SA");
                    break;
                case 6:
                    currentDay.setDayToDisplay("DO");
                    break;
                default:
                    break;
            }
            daysOfTheWeek.add(currentDay);
        }
        return daysOfTheWeek;
    }

    private List<DayConfig> buildDaysOfTheMonth(Calendar currentCalendar, int dayColor) {
        List<DayConfig> daysOfTheMonth = new ArrayList<>();
        currentCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int maxDays = currentCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int emptySpaces = currentCalendar.get(Calendar.DAY_OF_WEEK) - 2;

        //Fill empty spaces of starting month
        for (int i = 0; i < emptySpaces; i++) {
            DayConfig emptyDay = new DayConfig();
            emptyDay.setEmptySpace(true);
            daysOfTheMonth.add(emptyDay);
        }

        //Fill month with current days of the month
        for (int i = 1; i <= maxDays; i++) {
            Calendar builtCalendar = Calendar.getInstance();
            builtCalendar.set(Calendar.MONTH, currentCalendar.get(Calendar.MONTH));
            builtCalendar.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));
            builtCalendar.set(Calendar.DAY_OF_MONTH, i);

            DayConfig currentDay = new DayConfig();
            //Setting colors for the day
            currentDay.setWeekIndicatorColor(dayColor);
            currentDay.setDaySelectedColor(daySelectedColor);
            //Settings values to display
            currentDay.setDayToDisplay(String.valueOf(i));
            currentDay.setYearOwner(String.valueOf(currentCalendar.get(Calendar.YEAR)));
            currentDay.setMonthOwner(currentCalendar.get(Calendar.MONTH) + 1);
            currentDay.setDayOfTheWeek(builtCalendar.get(Calendar.DAY_OF_WEEK) - 1);
            daysOfTheMonth.add(currentDay);
        }

        //Fill the month with final empty spaces
        int remainingSpaces = (daysOfTheMonth.size() % 7);
        for (int i = 0; i < remainingSpaces; i++) {
            DayConfig emptyDay = new DayConfig();
            emptyDay.setEmptySpace(true);
            daysOfTheMonth.add(emptyDay);
        }
        return daysOfTheMonth;
    }

    private String buildHeader(Calendar currentCalendar) {
        int month = currentCalendar.get(Calendar.MONTH) + 1;
        int year = currentCalendar.get(Calendar.YEAR);
        switch (month) {
            case 1:
                return "ENE, " + year;
            case 2:
                return "FEB, " + year;
            case 3:
                return "MAR, " + year;
            case 4:
                return "ABR, " + year;
            case 5:
                return "MAY, " + year;
            case 6:
                return "JUN, " + year;
            case 7:
                return "JUL, " + year;
            case 8:
                return "AGO, " + year;
            case 9:
                return "SEP, " + year;
            case 10:
                return "OCT, " + year;
            case 11:
                return "NOV, " + year;
            case 12:
                return "DIC, " + year;
            default:
                return "";
        }
    }

    private void checkNotNull() {
        if (dayColor == -1) {
            throw new IllegalArgumentException("You must set the color of the day displayed.");
        }
        if (weekIndicatorColor == -1) {
            throw new IllegalArgumentException("You must set the color of the week indicator.");
        }
        if (headerColor == -1) {
            throw new IllegalArgumentException("You must set the color of the header.");
        }
        if (daySelectedColor == -1) {
            throw new IllegalArgumentException("You must set the color of the selected day. It represents the circle.");
        }
        if (rangeColor == -1) {
            throw new IllegalArgumentException("You must set the color of the selected range.");
        }
    }

    public void setOnDaySelectedListener(CalendarViewCallback listener) {
        mListener = listener;
    }

    public void setStartRange(DayConfig dayConfig) {
        for (MonthConfig moth : months) {
            for (DayConfig day : moth.getDaysOfTheMonth()) {
                if (dayConfig == day) {

                }
            }
        }

    }

    public void setEndRange(DayConfig dayConfig) {

    }

    @Override
    public void onDaySelected(DayConfig dayConfig) {
        mListener.onDaySelected(dayConfig);
    }

    public interface CalendarViewCallback {
        void onDaySelected(DayConfig daySelected);
    }

}


