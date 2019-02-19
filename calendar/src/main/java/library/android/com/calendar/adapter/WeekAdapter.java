package library.android.com.calendar.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import library.android.com.calendar.R;
import library.android.com.calendar.model.DayConfig;

import java.util.ArrayList;
import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {

    private List<DayConfig> weekList = new ArrayList<>();
    private DaySelectedCallback mListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.day_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DayConfig currentDay = weekList.get(position);

        if (currentDay.isWeekIndicator()) {
            displayProperWeekIndicator(viewHolder, currentDay);
        } else {
            displayProperDayIndicator(viewHolder, currentDay);
        }
    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }

    private void displayProperWeekIndicator(ViewHolder viewHolder, DayConfig dayConfig) {
        viewHolder.tvPrice.setVisibility(View.GONE);
        viewHolder.cvDaySelector.setVisibility(View.INVISIBLE);
        viewHolder.tvDay.setText(dayConfig.getDayToDisplay());
        viewHolder.tvDay.setTextColor(ContextCompat.getColor(viewHolder.tvDay.getContext(), dayConfig.getWeekIndicatorColor()));
    }

    private void displayProperDayIndicator(ViewHolder viewHolder, final DayConfig dayConfig) {
        viewHolder.tvPrice.setText(dayConfig.getPriceOfDay());
        if (dayConfig.getPriceColor() != -1) {
            viewHolder.tvPrice.setTextColor(ContextCompat.getColor(viewHolder.tvPrice.getContext(), dayConfig.getPriceColor()));
        }
        viewHolder.tvDay.setText(dayConfig.getDayToDisplay());
        if (dayConfig.getWeekIndicatorColor() != -1) {
            viewHolder.tvDay.setTextColor(ContextCompat.getColor(viewHolder.tvDay.getContext(), dayConfig.getWeekIndicatorColor()));
        }

        if (dayConfig.isStartSelectedDay()) {
            viewHolder.ivStartRange.setBackgroundColor(ContextCompat.getColor(viewHolder.ivStartRange.getContext(), dayConfig.getRangeColor()));
            viewHolder.cvDaySelector.setBorderColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.cvDaySelector.setCircleBackgroundColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.cvDaySelector.setBackgroundColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.tvDay.setTextColor(ContextCompat.getColor(viewHolder.tvDay.getContext(), R.color.white));
        }

        if (dayConfig.isMiddleRangeDay()) {
            viewHolder.ivMiddleRange.setBackgroundColor(ContextCompat.getColor(viewHolder.ivMiddleRange.getContext(), dayConfig.getRangeColor()));
        }

        if (dayConfig.isEndSelectedDay()) {
            viewHolder.ivEndRange.setBackgroundColor(ContextCompat.getColor(viewHolder.ivEndRange.getContext(), dayConfig.getRangeColor()));
            viewHolder.cvDaySelector.setBorderColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.cvDaySelector.setCircleBackgroundColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.cvDaySelector.setBackgroundColor(ContextCompat.getColor(viewHolder.cvDaySelector.getContext(), dayConfig.getDaySelectedColor()));
            viewHolder.tvDay.setTextColor(ContextCompat.getColor(viewHolder.tvDay.getContext(), R.color.white));
        }

        if (!dayConfig.isEmptySpace()) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onDaySelected(dayConfig);
                }
            });
        }
    }

    public void setWeek(List<DayConfig> weekList) {
        this.weekList = weekList;
        notifyDataSetChanged();
    }

    public void setOnDaySelectedListener(DaySelectedCallback listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView cvDaySelector;
        private ImageView ivStartRange;
        private ImageView ivMiddleRange;
        private ImageView ivEndRange;
        private TextView tvDay;
        private TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvDaySelector = itemView.findViewById(R.id.day_selected);
            ivStartRange = itemView.findViewById(R.id.day_start_range);
            ivMiddleRange = itemView.findViewById(R.id.day_middle_range);
            ivEndRange = itemView.findViewById(R.id.day_end_range);
            tvDay = itemView.findViewById(R.id.day_display);
            tvPrice = itemView.findViewById(R.id.day_price);
        }
    }

    public interface DaySelectedCallback {
        void onDaySelected(DayConfig daySelected);
    }

}
