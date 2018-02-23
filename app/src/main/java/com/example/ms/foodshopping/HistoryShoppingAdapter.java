package com.example.ms.foodshopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MS on 23-02-2018.
 */

public class HistoryShoppingAdapter extends ArrayAdapter<Shopping> {

    public HistoryShoppingAdapter(Context context, List<Shopping> weekTotals)
    {
        super(context, R.layout.row_historyshopping_custom, weekTotals);
    }

    private static class ViewHolder
    {
        TextView textViewYear;
        TextView textViewShopName;
        TextView textViewAmount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HistoryShoppingAdapter.ViewHolder holder;

        if (convertView == null)
        {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            convertView = myInflater.inflate(R.layout.row_historyshopping_custom, parent, false);

            holder = new HistoryShoppingAdapter.ViewHolder();

            holder.textViewYear = convertView.findViewById(R.id.textViewHistoryShoppingCustomRowYear);
            holder.textViewShopName = convertView.findViewById(R.id.textViewHistoryShoppingCustomRowShopName);
            holder.textViewAmount = convertView.findViewById(R.id.textViewHistoryShoppingCustomRowAmount);

            convertView.setTag(holder);
        }
        else
        {
            holder = (HistoryShoppingAdapter.ViewHolder) convertView.getTag();
        }

        Shopping shopping = getItem(position);

        if (shopping != null)
        {
            holder.textViewYear.setText("År: " + shopping.getYear());
            holder.textViewShopName.setText(shopping.getShopName());
            holder.textViewAmount.setText("Beløb: " + shopping.getAmount() + ",-");
        }

        return convertView;
    }
}
