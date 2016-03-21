package com.mikhail.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    public static final String COL_CONTACTS = "contacts";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_IMAGE = "image";
    public static final String COL_FAVORITES = "favorites";


    public static final String[] TABLE_COLUMNS = {COL_ID, COL_NAME, COL_PRICE, COL_RATING, COL_DELIVERY,
            COL_CONTACTS, COL_DESCRIPTION, COL_IMAGE, COL_FAVORITES};

    private static final String SQL_CREATE_RESTAURANTS_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_PRICE + " TEXT, "
                    + COL_RATING + " TEXT, " + COL_DELIVERY + " TEXT, " + COL_CONTACTS + " TEXT, "
                    + COL_DESCRIPTION + " TEXT, " + COL_IMAGE + " INTEGER, " + COL_FAVORITES + " BOOLEAN )";

    private static RestaurantsData instance;


    public RestaurantsData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static RestaurantsData getInstance(Context context) {
        if (instance == null) {
            instance = new RestaurantsData(context.getApplicationContext());
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

    public void insert(int id, String name, String price, String rating, String delivery, String contacts, String description, int image, boolean favorites) {
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("rating", rating);
        values.put("delivery", delivery);
        values.put("contacts", contacts);
        values.put("description", description);
        values.put("image", image);
        values.put("favorites", favorites);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public void updateFavorites(int id, boolean favorites) {
        // Get a reference to the database
        SQLiteDatabase db = getWritableDatabase();

        // create a new content value to store values
        ContentValues values = new ContentValues();
        values.put("favorites", favorites);

        db.update(TABLE_NAME, values, "_id = ?", new String[]{String.valueOf(id)});
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

    public Cursor getRestaurantsList(int prices[], float rating, String delivery) {

        Log.e("RestaurantData", " rating: " + rating + " delivery: " + delivery);

        SQLiteDatabase db = this.getReadableDatabase();

        String searchParameters = "";

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == 1) {
                searchParameters += " " + COL_PRICE + " = " + "\'" + String.valueOf(i + 1) + "\'";
                searchParameters += " OR ";

            }
        }

        if (!searchParameters.isEmpty())
            searchParameters = searchParameters.substring(0, searchParameters.length() - 4);
        Log.e("TEST", "searchParameters: " + searchParameters);


        if (rating != 0) {
            Log.e("getRestaurantsList", "rating: " + rating);
            String strRating = "\'" + String.valueOf(rating) + "\'";

            if (searchParameters != null && !searchParameters.isEmpty()) {
                searchParameters = searchParameters + " AND " + COL_RATING + " = " + strRating;
            } else {
                searchParameters = COL_RATING + " = " + strRating;
            }
        }

        if (delivery != null) {

            if (searchParameters != null && !searchParameters.isEmpty()) {
                searchParameters = searchParameters + " AND " + COL_DELIVERY + " = " + "\'" + delivery + "\'";
            } else {
                searchParameters = COL_DELIVERY + " = " + "\'" + delivery + "\'";
            }
        }


//        if(price == 1){
//            strPrice = "";
//        } else if(price == 2){
//            strPrice = "'$$";
//        }else if(price == 3){
//            strPrice = "$$$";
//        }else if(price == 4){
//            strPrice = "$$$$";
//        }

        return db.query(TABLE_NAME, TABLE_COLUMNS, searchParameters, null, null, null, null, null);
    }


    public Cursor getRestaurantByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);


        return cursor;


    }

    public String getRestaurantByContacts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_CONTACTS));
        } else {
            return "No Description Found";
        }

    }

    public String getRestaurantByDescription(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
        } else {
            return "No Description Found";
        }

    }

    public String getRestaurantByImage(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_IMAGE));
        } else {
            return "No Description Found";
        }

    }

    public String getRestaurantByFavorites(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_FAVORITES));
        } else {
            return "No Description Found";
        }

    }


    public Cursor getRestaurantByName(String COL_NAME) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, TABLE_COLUMNS,
                COL_ID + " LIKE ?",
                new String[]{String.valueOf(COL_NAME)},
                null,
                null,
                null,
                null);

        return cursor;
    }


    public Cursor getSearchResults(String query) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, // a. table
                TABLE_COLUMNS, // b. column names
                COL_NAME + " LIKE ?", // c. selections
                new String[]{"%" + query + "%" }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;

    }

}



