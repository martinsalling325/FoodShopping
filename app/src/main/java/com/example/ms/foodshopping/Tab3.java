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

public class Tab3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ShowHistoryShopping();
    }

    private void ShowHistoryShopping(){
        ListView listViewTab3 = getView().findViewById(R.id.listViewTab3HistoryShopping);

        ArrayList<Shopping> historyShoppingList = MainActivity.dbHelper.getHistoryShopping();
        if (!historyShoppingList.isEmpty())
        {
            listViewTab3.setAdapter(null);
            ListAdapter adapter = new HistoryShoppingAdapter(getActivity(), historyShoppingList);

            if (listViewTab3 != null) {

                listViewTab3.setAdapter(adapter);
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
            listViewTab3.setAdapter(null);
        }
    }
}
