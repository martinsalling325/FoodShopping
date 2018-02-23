package com.example.ms.foodshopping;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by MS on 18-02-2018.
 */

public class Tab1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShowWeekTotals();
    }

    public void ShowWeekTotals(){
        ListView listViewTab1 = getView().findViewById(R.id.listViewTab1TotalWeek);

        ArrayList<Shopping> weekTotals = MainActivity.dbHelper.getWeekShopping();
        if (!weekTotals.isEmpty())
        {
            listViewTab1.setAdapter(null);
            ListAdapter adapter = new WeekTotalAdapter(getActivity(), weekTotals);

            if (listViewTab1 != null) {

                listViewTab1.setAdapter(adapter);
            }

            /*listViewTab1.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Movie selectedMovie = (Movie) parent.getItemAtPosition(position);

                    Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
                    intent.putExtra("movieId", selectedMovie.Id);
                    startActivity(intent);
                }
            });*/
        }

        else
        {
            listViewTab1.setAdapter(null);
        }
    }
}
