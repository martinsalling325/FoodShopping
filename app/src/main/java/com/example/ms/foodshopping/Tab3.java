package com.example.ms.foodshopping;

import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
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

            listViewTab3.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id)
                {

                    // Launches the Search DialogFragment
                    final Dialog dialog = new Dialog(getActivity());

                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.fragment_delete);

                    // Performs the delete
                    Button dialogBtnDelete = (Button) dialog.findViewById(R.id.btn_deleteConfirm);
                    dialogBtnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Shopping selectedObj = (Shopping) parent.getItemAtPosition(position);
                            MainActivity.dbHelper.DeleteShopping(selectedObj.getYear(), selectedObj.getShopName(), selectedObj.getAmount());
                            ShowHistoryShopping();

                            Snackbar snackbar = Snackbar
                                    .make(getView(), "Message is deleted", Snackbar.LENGTH_LONG)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Snackbar snackbar1 = Snackbar.make(getView(), "Message is restored!", Snackbar.LENGTH_SHORT);
                                            snackbar1.show();
                                        }
                                    });

                            snackbar.show();

                            dialog.dismiss();
                        }
                    });

                    // Closes the Dialog
                    Button dialogBtnCancel = (Button) dialog.findViewById(R.id.btn_deleteCancel);
                    dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
/*
                    Shopping selectedObj = (Shopping) parent.getItemAtPosition(position);
                    MainActivity.dbHelper.DeleteShopping(selectedObj.getRegTime(), selectedObj.getShopName(), selectedObj.getAmount());
                    ShowHistoryShopping();
*/
                    /*Intent intent = new Intent(FavoritesActivity.this, DetailActivity.class);
                    intent.putExtra("movieId", selectedMovie.Id);
                    startActivity(intent);*/
                }
            });
        }
        else
        {
            listViewTab3.setAdapter(null);
        }
    }
}
