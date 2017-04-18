package edu.pitt.cs.cs1635.anp147.sleepycommuters.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms.RecurringAlarm;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.R;

/**
 * Created by austinpilz on 4/17/17.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.MyViewHolder> {
    private List<RecurringAlarm> alarmList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre;
        ImageView activate;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.alarmTime);
            genre = (TextView) view.findViewById(R.id.alarmName);
            activate = (ImageView) view.findViewById(R.id.activate);

        }
    }


    public AlarmAdapter(List<RecurringAlarm> lines) {
        this.alarmList = lines;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecurringAlarm alarm = alarmList.get(position);
        holder.title.setText(alarm.get_time());
        holder.genre.setText(alarm.get_alert_name());
//        holder.activate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    // The toggle is enabled
//                } else {
//                    // The toggle is disabled
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }


}
