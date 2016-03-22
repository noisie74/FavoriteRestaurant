package com.mikhail.project2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView restaurantsCountHeader;
    ListView restaurantsList;
    Intent secondActivityIntent;
    Cursor cursor;
    CursorAdapter simpleCursorAdapter;
    MenuItem star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        restaurantsCountHeader = (TextView) findViewById(R.id.restaurants_count);
        restaurantsList = (ListView) findViewById(R.id.restaurantsList);

        secondActivityIntent = getIntent();

        onNewIntent(getIntent());

        int prices[] = getIntent().getIntArrayExtra("price");
        float ratingSelection = getIntent().getFloatExtra("rating", 0.0f);
        String deliverySelection = getIntent().getStringExtra("delivery");

        RestaurantsData dbSetup = new RestaurantsData(SecondActivity.this);
        dbSetup.getReadableDatabase();


        cursor = RestaurantsData.getInstance(SecondActivity.this).getRestaurantsList(prices, ratingSelection, deliverySelection);


        final String[] columns = new String[]{RestaurantsData.COL_NAME};
        int[] viewNames = new int[]{R.id.text};
        simpleCursorAdapter = new SimpleCursorAdapter(SecondActivity.this, R.layout.custom, cursor, columns, viewNames, 0);


        restaurantsList.setAdapter(simpleCursorAdapter);


        restaurantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);
                int getDataFromCursor = cursor.getColumnIndex(RestaurantsData.COL_ID);

                String restaurantIDString = cursor.getString(getDataFromCursor);
                int restaurantID = (Integer.valueOf(restaurantIDString));

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                intent.putExtra("data", restaurantID);
                startActivity(intent);
                Log.d("restaurantIDString", "secondActivityIntent");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        star = menu.findItem(R.id.favorites);

        star.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Cursor cursorFavorites = RestaurantsData.getInstance(SecondActivity.this).getRestaurantIfInFavorites();

                int restaurantID = cursorFavorites.getColumnIndex(RestaurantsData.COL_FAVORITES);

                Intent intent = new Intent(SecondActivity.this, FavoriteListActivity.class);

                intent.putExtra("data", restaurantID);
                startActivity(intent);

                return true;

            }

        });

        return true;

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.d("secondActivity", "clicked");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            cursor = RestaurantsData.getInstance(SecondActivity.this).getSearchResults(query);

            simpleCursorAdapter.swapCursor(cursor);
            simpleCursorAdapter.notifyDataSetChanged();

        }
    }

}
