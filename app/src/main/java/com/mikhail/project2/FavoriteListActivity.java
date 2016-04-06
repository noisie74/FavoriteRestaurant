package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


/**
 * FavoriteListActivity shows the list of restaurantsItems
 * which are in "Favorites"
 */

public class FavoriteListActivity extends AppCompatActivity {


    TextView textView;
    ListView listView;
    Intent fromDetailActivityIntent;
    int restaurantID;
    final static int DEFAULT_VALUE = -1;
    RestaurantsDataSQLite helper;
    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorites);

        textView = (TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.restaurantsList);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fromDetailActivityIntent = getIntent();
        restaurantID = fromDetailActivityIntent.getIntExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, DEFAULT_VALUE);

        helper = RestaurantsDataSQLite.getInstance(FavoriteListActivity.this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // set items in the list clickable
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 get data from database using primary key - ID
                 and send it to DetailActivity
                 */

                cursor.moveToPosition(position);
                int getDataFromCursor = cursor.getColumnIndex(RestaurantsDataSQLite.COL_ID);
                String restaurantIDString = cursor.getString(getDataFromCursor);
                int restaurantID = (Integer.valueOf(restaurantIDString));

                Intent intent = new Intent(FavoriteListActivity.this, DetailActivity.class);

                intent.putExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, restaurantID);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() { // if user removes restaurant from favorites onResume shows correct restaurant List
        super.onResume();

        if (restaurantID != DEFAULT_VALUE) {
            cursor = helper.getRestaurantIfInFavorites();
            cursor.moveToFirst();

            /**
             create simpleCursorAdapter and set it to ListView
             */
            final String[] columns = new String[]{RestaurantsDataSQLite.COL_NAME};
            int[] viewNames = new int[]{R.id.text};
            cursorAdapter = new SimpleCursorAdapter(FavoriteListActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

            listView.setAdapter(cursorAdapter);
        }
    }

}
