package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {


    TextView textView;
    ListView listView;
    Intent fourthActivityIntent;
    int restaurantID;
    final static int DEFAULT_VALUE = -1;
    RestaurantsData helper;
    SimpleCursorAdapter cursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        textView = (TextView) findViewById(R.id.restaurants_favorite);
        listView = (ListView) findViewById(R.id.restaurantsList);

        fourthActivityIntent = getIntent();
        restaurantID = fourthActivityIntent.getIntExtra("data", DEFAULT_VALUE);

        helper = RestaurantsData.getInstance(FourthActivity.this);




        if (restaurantID != DEFAULT_VALUE){

            Cursor cursor = helper.getRestaurantIfInFavorites();

            cursor.moveToFirst();

            final String[] columns = new String[]{RestaurantsData.COL_NAME};
            int[] viewNames = new int[]{R.id.text};
            cursorAdapter = new SimpleCursorAdapter(FourthActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

            listView.setAdapter(cursorAdapter);


        }

    }
}
