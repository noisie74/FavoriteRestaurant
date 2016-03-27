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

/**
 SearchResultsActivity shows search results
 for restaurantsItems according to filters selected
 in the MainActivity
 */

public class SearchResultsActivity extends AppCompatActivity {

    TextView restaurantsCountHeader;
    ListView restaurantsList;
    Intent searchResultsActivityIntent;
    Cursor cursor;
    CursorAdapter simpleCursorAdapter;
    MenuItem star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        restaurantsCountHeader = (TextView) findViewById(R.id.restaurants_count);
        restaurantsList = (ListView) findViewById(R.id.restaurantsList);

        searchResultsActivityIntent = getIntent();

        onNewIntent(getIntent());
        insertReceivedSearchResultsToTheListView();
        restaurantsListClickListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // Create toolbar with search and favorites widgets

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        star = menu.findItem(R.id.favorites); // Create button that allows user see restaurantsItems in favorites

        star.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                /**
                 gets data from Favorites Column in the database and sends it to FavoriteActivity
                 */

                Cursor cursorFavorites = RestaurantsDataSQLite.getInstance(SearchResultsActivity.this).getRestaurantIfInFavorites();

                int restaurantID = cursorFavorites.getColumnIndex(RestaurantsDataSQLite.COL_FAVORITES);

                Intent intent = new Intent(SearchResultsActivity.this, FavoriteListActivity.class);

                intent.putExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, restaurantID);
                startActivity(intent);

                return true;
            }

        });

        return true;

    }

    @Override
    protected void onNewIntent(Intent intent) { // Enabling search function to show result
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {  // Getting search Intent from Search Manager
        Log.d("SearchResultsActivity", "clicked");

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            String query = intent.getStringExtra(SearchManager.QUERY);

            cursor = RestaurantsDataSQLite.getInstance(SearchResultsActivity.this).getSearchResults(query);

            simpleCursorAdapter.swapCursor(cursor);
            simpleCursorAdapter.notifyDataSetChanged();

        }
    }

    private void insertReceivedSearchResultsToTheListView() {  // get search results from MainActivity, create CursorAdapter and insert data into ListView

        int prices[] = getIntent().getIntArrayExtra(Constants.PRICE);
        float ratingSelection = getIntent().getFloatExtra(Constants.RATING, 0.0f);
        String deliverySelection = getIntent().getStringExtra(Constants.DELIVERY);

        RestaurantsDataSQLite dbSetup =  RestaurantsDataSQLite.getInstance(SearchResultsActivity.this);
        dbSetup.getReadableDatabase();

        cursor = RestaurantsDataSQLite.getInstance(SearchResultsActivity.this).getRestaurantsList(prices, ratingSelection, deliverySelection);

        /**
         Create simpleCursorAdapter and set it to ListView
         */

        final String[] columns = new String[]{RestaurantsDataSQLite.COL_NAME};
        int[] viewNames = new int[]{R.id.text};
        simpleCursorAdapter = new SimpleCursorAdapter(SearchResultsActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

        restaurantsList.setAdapter(simpleCursorAdapter);

    }

    private void restaurantsListClickListener() {  // Create clickListeners for each item in the list

        restaurantsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 get data from database by ID and send it to DetailActivity
                 */

                cursor.moveToPosition(position);
                int getDataFromCursor = cursor.getColumnIndex(RestaurantsDataSQLite.COL_ID);

                String restaurantIDString = cursor.getString(getDataFromCursor);
                int restaurantID = (Integer.valueOf(restaurantIDString));

                Intent intent = new Intent(SearchResultsActivity.this, DetailActivity.class);

                intent.putExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, restaurantID);
                startActivity(intent);
                Log.d("restaurantIDString", "searchResultsActivityIntent");
            }
        });
    }

}
