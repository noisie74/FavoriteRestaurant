package com.mikhail.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mikhail on 3/15/16.
 */
public class RestaurantsData extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Restaurants.db";
    public static final String TABLE_NAME = "restaurants";
//    public static final String SQL_CREATE_RESTAURANTS_TABLE =
//            "CREATE TABLE restaurants ( _id INTEGER PRIMARY KEY, name TEXT, price TEXT, rating TEXT, delivery TEXT )";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE = "price";
    public static final String COL_RATING = "rating";
    public static final String COL_DELIVERY = "delivery";
    public static final String[] TABLE_COLUMNS = {COL_ID, COL_NAME, COL_PRICE, COL_RATING, COL_DELIVERY};

    private static final String SQL_CREATE_RESTAURANTS_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_PRICE + " TEXT, " + COL_RATING + " TEXT, " + COL_DELIVERY + " TEXT )";

    private static RestaurantsData instance;


    public RestaurantsData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static RestaurantsData getInstance(Context context) {
        if (instance == null) {
            instance = new RestaurantsData(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_RESTAURANTS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }

    public void insert(int id, String name, String price, String rating, String delivery) {
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("rating", rating);
        values.put("delivery", delivery);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void delete(int id) {
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // Define the selection, or the where
        String selection = "id = ?";

        // Define the selection values. The ?'s in the selection
        // The number of values in the following array should equal the number of ? in the where clause
        String[] selectionArgs = new String[]{String.valueOf(id)};

        // Delete everything that satisfies the selection
        db.delete("restaurants", selection, selectionArgs);
    }

    public Cursor getRestaurantsList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,null, null, null, null, null, null);

        return cursor;
    }

}
