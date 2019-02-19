package library.android.com.calendar.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import library.android.com.calendar.R;
import library.android.com.calendar.model.DayConfig;
import library.android.com.calendar.model.MonthConfig;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> implements WeekAdapter.DaySelectedCallback {

    private List<MonthConfig> monthList = new ArrayList<>();
    private WeekAdapter weekAdapter;
    private MonthOnDaySelectedCallback mListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.month_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MonthConfig currentMonth = monthList.get(i);
        viewHolder.tvHeader.setText(currentMonth.getHeader());
        viewHolder.tvHeader.setTextColor(ContextCompat.getColor(viewHolder.tvHeader.getContext(), currentMonth.getHeaderColor()));
        setupDaysAdapter(viewHolder, currentMonth.getDaysOfTheMonth());
    }

    public void setMonths(List<MonthConfig> months) {
        monthList = months;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return monthList.size();
    }

    private void setupDaysAdapter(ViewHolder viewHolder, List<DayConfig> days) {
        weekAdapter = new WeekAdapter();
        weekAdapter.setWeek(days);
        weekAdapter.setOnDaySelectedListener(this);
        viewHolder.rvDays.setAdapter(weekAdapter);
        viewHolder.rvDays.setLayoutManager(new GridLayoutManager(viewHolder.rvDays.getContext(), 7));
        viewHolder.rvDays.setHasFixedSize(true);
    }

    @Override
    public void onDaySelected(DayConfig daySelected) {
        mListener.onDaySelected(daySelected);
    }

    public void setOnDaySelectedListener(MonthOnDaySelectedCallback listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHeader;
        private RecyclerView rvDays;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.month_title);
            rvDays = itemView.findViewById(R.id.month_day_view);
        }
    }

    public interface MonthOnDaySelectedCallback {
        void onDaySelected(DayConfig dayConfig);
    }

}
