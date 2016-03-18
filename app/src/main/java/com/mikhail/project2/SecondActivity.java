package com.mikhail.project2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView restaurantsCountHeader;
    ListView restaurantsList;
    Intent secondActivityIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        restaurantsCountHeader = (TextView) findViewById(R.id.restaurants_count);
        restaurantsList = (ListView) findViewById(R.id.restaurantsList);

        secondActivityIntent = getIntent();


        RestaurantsData helper = RestaurantsData.getInstance(SecondActivity.this);

        int id = getIntent().getIntExtra("id", -1);
        int prices[] = getIntent().getIntArrayExtra("price");
        float ratingSelection = getIntent().getFloatExtra("rating", 0.0f);
        String deliverySelection = getIntent().getStringExtra("delivery");


        RestaurantsData dbSetup = new RestaurantsData(SecondActivity.this);
        dbSetup.getReadableDatabase();


        Cursor cursor = RestaurantsData.getInstance(SecondActivity.this).getRestaurantsList(prices, ratingSelection, deliverySelection);


        String[] columns = new String[]{RestaurantsData.COL_NAME, RestaurantsData.COL_PRICE, RestaurantsData.COL_RATING, RestaurantsData.COL_DELIVERY};
        int[] viewNames = new int[]{R.id.text, R.id.text1, R.id.text2, R.id.text3,};
        CursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(SecondActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

        restaurantsList.setAdapter(simpleCursorAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;

//        getMenuInflater().inflate(R.menu.main2, menu);
//        MenuItem myMenu = menu.findItem(R.id.action_settings);
//
//        myMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//
//        return true;
    }

}
