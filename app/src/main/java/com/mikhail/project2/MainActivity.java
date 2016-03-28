package com.mikhail.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * App starts with MainActivity
 * MainActivity has all search filters
 * and brings user to SearchResultActivity
 */

public class MainActivity extends AppCompatActivity {

    TextView neighborhoodName, dollarOne, dollarTwo, dollarThree, dollarFour,
            price, rating, delivery, yes, no, filterYourSearch;

    RatingBar ratingBar;
    Button clearButton, searchButton;
    Intent startSearchResultsActivity;
    MenuItem map;

    private int dollarOneCounter, dollarTwoCounter, dollarThreeCounter,
            dollarFourCounter, yesCounter, noCounter;


    private static final boolean PREF_KEY_COUNTER_DEFAULT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startSearchResultsActivity = new Intent(MainActivity.this, SearchResultsActivity.class);

        initViews();
        clickListenersMainActivity();
        setSharedPreferences(); // Call SharedPreferences not to get duplicated data from database

    }

    private void clickListenersMainActivity() { // Call all clickListeners methods

        dollarTextViewsClickListeners();
        deliveryYesNoClickListeners();
        searchButtonClickListener();
        clearButtonClickListener();

    }

    private boolean ifNoItemsSelected() {  // Check if none of the filters(TextViews and ratingBar) are selected

        if (!dollarOne.isSelected() && !dollarTwo.isSelected() && !dollarThree.isSelected()
                && !dollarFour.isSelected() && !yes.isSelected() && !no.isSelected() && isRatingZero()) {
            Toast.makeText(MainActivity.this, "Please specify your search!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    private int[] getPriceSelections() {  // Create array of ints object that holds selected prices
        int prices[] = new int[4];
        if (dollarOne.isSelected()) prices[0] = 1;
        if (dollarTwo.isSelected()) prices[1] = 1;
        if (dollarThree.isSelected()) prices[2] = 1;
        if (dollarFour.isSelected()) prices[3] = 1;
        return prices;
    }

    /**
     * @return true, if ratingBar has no rating
     */

    private boolean isRatingZero() {
        if (ratingBar.getRating() == 0) {
            return true;
        } else {
            return false;
        }
    }


    private void setSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!getSharedPreferences()) {
             populateDatabase();
//            DatabaseData.insertData(this); // Calling DatabaseData class to  pull data from database
            Log.e("MainActivity", "dataInserted!");
            editor.putBoolean(Constants.PREF_KEY_COUNTER_MAIN_ACTIVITY, true);
            editor.apply();
        }

    }

    private boolean getSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        return sharedPreferences.getBoolean(Constants.PREF_KEY_COUNTER_MAIN_ACTIVITY, PREF_KEY_COUNTER_DEFAULT);
    }


    /**
     * clickListeners methods for all the widgets
     * of MainActivity
     */

    private void dollarTextViewsClickListeners() {

        dollarOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dollarOneCounter % 2 == 0) {
                    dollarOne.setSelected(true);
                } else dollarOne.setSelected(false);
                dollarOneCounter++;
            }
        });

        dollarTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dollarTwoCounter % 2 == 0) {
                    dollarTwo.setSelected(true);
                    dollarTwo.setSelected(true);
                } else dollarTwo.setSelected(false);
                dollarTwoCounter++;
            }
        });

        dollarThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dollarThreeCounter % 2 == 0) {
                    dollarThree.setSelected(true);
                    dollarThree.setSelected(true);
                } else dollarThree.setSelected(false);
                dollarThreeCounter++;
            }
        });

        dollarFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dollarFourCounter % 2 == 0) {
                    dollarFour.setSelected(true);
                    dollarFour.setSelected(true);
                } else dollarFour.setSelected(false);
                dollarFourCounter++;
            }
        });

    }

    private void deliveryYesNoClickListeners() { // Check delivery filters

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesCounter % 2 == 0) {
                    yes.setSelected(true);
                    yes.setSelected(true);
                } else yes.setSelected(false);
                yesCounter++;
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noCounter % 2 == 0) {
                    no.setSelected(true);
                    no.setSelected(true);
                } else no.setSelected(false);
                noCounter++;
            }
        });

    }

    private void searchButtonClickListener() {

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int prices[] = getPriceSelections();
                float rating = ratingBar.getRating();
                String delivery = null;


                if (yes.isSelected() && !no.isSelected()) {
                    delivery = yes.getText().toString();
                } else if (!yes.isSelected() && no.isSelected()) {
                    delivery = no.getText().toString();
                } else if (yes.isSelected() && no.isSelected()) {
                    Toast.makeText(MainActivity.this, "Please select only one option of delivery!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ifNoItemsSelected()) return;

                startSearchResultsActivity.putExtra(Constants.PRICE, prices);
                startSearchResultsActivity.putExtra(Constants.RATING, rating);
                startSearchResultsActivity.putExtra(Constants.DELIVERY, delivery);

                startActivity(startSearchResultsActivity);

            }
        });

    }

    private void clearButtonClickListener() {

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dollarOne.setSelected(false);
                dollarTwo.setSelected(false);
                dollarThree.setSelected(false);
                dollarFour.setSelected(false);
                yes.setSelected(false);
                no.setSelected(false);
                ratingBar.setRating(0);
                dollarOneCounter = 0;
                dollarTwoCounter = 0;
                dollarThreeCounter = 0;
                dollarFourCounter = 0;
                yesCounter = 0;
                noCounter = 0;
            }
        });

    }

    /**
     * Initialize Views
     */
    private void initViews() {

        neighborhoodName = (TextView) findViewById(R.id.header);
        dollarOne = (TextView) findViewById(R.id.dollarOne);
        dollarTwo = (TextView) findViewById(R.id.dollarTwo);
        dollarThree = (TextView) findViewById(R.id.dollarThree);
        dollarFour = (TextView) findViewById(R.id.dollarFour);
        price = (TextView) findViewById(R.id.price);
        rating = (TextView) findViewById(R.id.rating);
        delivery = (TextView) findViewById(R.id.delivery);
        yes = (TextView) findViewById(R.id.yes);
        no = (TextView) findViewById(R.id.no);
        filterYourSearch = (TextView) findViewById(R.id.filter);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        clearButton = (Button) findViewById(R.id.buttonClear);
        searchButton = (Button) findViewById(R.id.button_search);
    }

    /**
     * Create maps icon at the toolbar
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // Create toolbar with search and favorites widgets

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main2, menu);

        map = menu.findItem(R.id.google_maps); // Create button that allows user see location of the neighborhood

        map.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                /**
                 gets data from Favorites Column in the database and sends it to Maps_Activity
                 */

                Cursor cursorFavorites = RestaurantsDataSQLite.getInstance(MainActivity.this).getRestaurantIfInFavorites();

                int restaurantID = cursorFavorites.getColumnIndex(RestaurantsDataSQLite.COL_FAVORITES);

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);

                intent.putExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, restaurantID);
                startActivity(intent);

                return true;
            }

        });

        return true;

    }

    /**
     this method populates data from database
     */

    private void populateDatabase() {
        ArrayList<Restaurants> restaurantsItems = PopulateRestaurants.getRestaurants(this);
        for (Restaurants restaurants : restaurantsItems) {
            RestaurantsDataSQLite.getInstance(this).insert(
                    restaurants.getId(),
                    restaurants.getName(),
                    restaurants.getPrice(),
                    restaurants.getRating(),
                    restaurants.getDelivery(),
                    restaurants.getContacts(),
                    restaurants.getDescription(),
                    restaurants.getImageResourceId(),
                    restaurants.isFavorite());
        }
    }

}
