package com.example.ms.foodshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * Created by MS on 18-02-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foodshopping_Database.db";
    private static final String TABLE_NAME = "shopping_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "ShopName";
    private static final String COL_3 = "Amount";
    private static final String COL_4 = "Year";
    private static final String COL_5 = "WeekNumber";
    private static final String COL_6 = "RegTime";

    public DatabaseHelper(Context context)
    {
        //create Database
        super(context, DATABASE_NAME, null, 1);
        //context.deleteDatabase(DATABASE_NAME); //SLETTER HELE DATABASEN
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT,"
                + COL_3 + " Integer," + COL_4 + " Integer," + COL_5 + " Integer," + COL_6 + " TEXT);";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void InsertNewShopping(String shopName, Double amount)
    {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Integer month = Calendar.MONTH;
        Integer date = Calendar.getInstance().get(Calendar.DATE);
        Integer weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, shopName);
        contentValues.put(COL_3, amount);
        contentValues.put(COL_4, year);
        contentValues.put(COL_5, weekNumber);
        String dateString = date + "-" + month + "-" + year;
        contentValues.put(COL_6, dateString);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void DeleteShopping(String date, String shopName, double amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL_2 + " = ? AND " + COL_3 + " = ?", new String[] {shopName, String.valueOf(amount)});
        db.close();
    }

    public ArrayList<Shopping> getHistoryShopping()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ShopName, Amount, RegTime FROM " + TABLE_NAME;
        Cursor result = db.rawQuery(query, null);

        ArrayList<Shopping> shoppingList = new ArrayList<>();
        int count = result.getCount();

        if (count > 0)
        {
            while (result.moveToNext())
            {
                Shopping shopping = new Shopping();
                shopping.setShopName(result.getString(0));
                shopping.setAmount(result.getDouble(1));
                shopping.setRegTime(result.getString(2));
                shoppingList.add(shopping);
            }
        }
        result.close();
        db.close();
        Collections.reverse(shoppingList);
        return shoppingList;
    }

    public ArrayList<Shopping> getWeekShopping()
    {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Integer weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        ArrayList<Shopping> shoppingList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < 12; i++) {
            String query = "SELECT Year, WeekNumber, SUM(Amount) FROM " + TABLE_NAME + " WHERE Year = " + year + " AND WeekNumber = " + weekNumber;
            Cursor result = db.rawQuery(query, null);

            int count = result.getCount();

            if (count > 0)
            {
                while (result.moveToNext())
                {
                    Shopping shopping = new Shopping();
                    shopping.setYear(result.getInt(0));
                    shopping.setWeekNumber(result.getInt(1));
                    shopping.setTotalWeek(result.getDouble(2));
                    if (shopping.getTotalWeek() != 0){
                        shoppingList.add(shopping);
                    }
                }
            }
            result.close();
            weekNumber--;
            if (weekNumber.equals(0)) break;
        }
        db.close();
        //Collections.reverse(shoppingList);
        return shoppingList;
    }

    /*public void UpdateMovie(String drink, int price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, drink);
        contentValues.put(COL_3, price);
        db.update(TABLE_NAME, contentValues, COL_2 + " = ?", new String[]{drink});
        db.close();
    }*/

    /*//Deleting all movies in Database with Movie.MoviesAdded = true
    public void DeleteAllFavorites()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, COL_12 + " = 1", null);
        db.close();
    }

    //Deleting all movies in Database with Movie.MoviesAdded = false
    public void DeleteSearchHistory()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, COL_12 + " = 0", null);
        db.close();
    }

    public Movie GetMovieWithId(int Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " = " + Id;
        Cursor result = db.rawQuery(query, null);
        int count = result.getCount();

        Movie movie = new Movie();

        if (count > 0)
        {
            while (result.moveToNext())
            {
                movie.Id = (result.getInt(0));
                movie.Title = (result.getString(1));
                movie.Year = (result.getString(2));
                movie.Runtime = (result.getString(3));
                movie.Genre = (result.getString(4));
                movie.Director = (result.getString(5));
                movie.Actors = (result.getString(6));
                movie.Plot = (result.getString(7));
                movie.ImdbRating = (result.getString(8));
                movie.PosterUrl = (result.getString(9));
                if (result.getBlob(10) != null)
                {
                    movie.Poster = (DbBitmapUtility.getImage(result.getBlob(10)));
                }

                int flag = (result.getInt(11));
                movie.MovieAdded = (flag==1)?true:false;
            }
        }

        result.close();
        return movie;
    }

    public ArrayList<Movie> getSearchHistory()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_12 + " = 0";
        Cursor result = db.rawQuery(query, null);

        ArrayList<Movie> movies = new ArrayList<>();
        int count = result.getCount();

        if (count > 0)
        {
            while (result.moveToNext())
            {
                Movie movie = new Movie();
                movie.Id = (result.getInt(0));
                movie.Title = (result.getString(1));
                movie.Year = (result.getString(2));
                movie.Runtime = (result.getString(3));
                movie.Genre = (result.getString(4));
                movie.Director = (result.getString(5));
                movie.Actors = (result.getString(6));
                movie.Plot = (result.getString(7));
                movie.ImdbRating = (result.getString(8));
                movie.PosterUrl = (result.getString(9));

                if (result.getBlob(10) != null)
                {
                    movie.Poster = (DbBitmapUtility.getImage(result.getBlob(10)));
                }

                int flag = (result.getInt(11));
                movie.MovieAdded = (flag==1)?true:false;

                movies.add(movie);
            }
        }
        result.close();
        db.close();
        Collections.reverse(movies);
        return movies;
    }*/
}
