package com.example.ms.foodshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WeekTotalAdapter extends ArrayAdapter<Shopping> {

    public WeekTotalAdapter(Context context, List<Shopping> weekTotals)
    {
        super(context, R.layout.row_weektotal_custom, weekTotals);
    }

    private static class ViewHolder
    {
        TextView textViewYear;
        TextView textViewWeekNumber;
        TextView textViewSum;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            convertView = myInflater.inflate(R.layout.row_weektotal_custom, parent, false);

            holder = new ViewHolder();

            holder.textViewYear = convertView.findViewById(R.id.textViewWeekTotalCustomRowYear);
            holder.textViewWeekNumber = convertView.findViewById(R.id.textViewWeekTotalCustomRowWeekNumber);
            holder.textViewSum = convertView.findViewById(R.id.textViewWeekTotalCustomRowSum);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Shopping shopping = getItem(position);

        if (shopping != null)
        {
            holder.textViewYear.setText("År: " + shopping.getYear());
            holder.textViewWeekNumber.setText("Uge: " + shopping.getWeekNumber());
            Double weekTotal = shopping.getTotalWeek();
            weekTotal = Math.round(weekTotal * 100d) / 100d;
            holder.textViewSum.setText("Kr. " + weekTotal + ",-");
        }

        return convertView;
    }
}