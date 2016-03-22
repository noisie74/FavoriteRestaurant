package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FavoriteListActivity extends AppCompatActivity {


    TextView textView;
    ListView listView;
    Intent fourthActivityIntent;
    int restaurantID;
    final static int DEFAULT_VALUE = -1;
    RestaurantsData helper;
    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorites);

        textView = (TextView) findViewById(R.id.restaurants_favorite);
        listView = (ListView) findViewById(R.id.restaurantsList);

        fourthActivityIntent = getIntent();
        restaurantID = fourthActivityIntent.getIntExtra("data", DEFAULT_VALUE);

        helper = RestaurantsData.getInstance(FavoriteListActivity.this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                cursor.moveToPosition(position);
                int getDataFromCursor = cursor.getColumnIndex(RestaurantsData.COL_ID);
                String restaurantIDString = cursor.getString(getDataFromCursor);
                int restaurantID = (Integer.valueOf(restaurantIDString));

                Intent intent = new Intent(FavoriteListActivity.this, ThirdActivity.class);

                intent.putExtra("data", restaurantID);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onResume() {
         super.onResume();

        if (restaurantID != DEFAULT_VALUE) {

            cursor = helper.getRestaurantIfInFavorites();

            cursor.moveToFirst();

            final String[] columns = new String[]{RestaurantsData.COL_NAME};
            int[] viewNames = new int[]{R.id.text};
            cursorAdapter = new SimpleCursorAdapter(FavoriteListActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

            listView.setAdapter(cursorAdapter);
        }
    }

}
