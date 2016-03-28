package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 DetailActivity shows restaurant details
 for the restaurant selected in
 the SearchResultsActivity
 */

public class DetailActivity extends AppCompatActivity {


    TextView restaurantName, restaurantDescription, restaurantContacts,
             price, rating, delivery;
    ImageView restaurantImage;
    Button addToFavorites;
    Intent fromSearchResultsActivityIntent;
    RestaurantsDataSQLite helper;
    public int restaurantID, favorites, indexPrice;
    public String priceOfRestaurant, dollarSymbols;

    private static final int DEFAULT_RESTAURANT_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        helper = RestaurantsDataSQLite.getInstance(DetailActivity.this);

        addToFavoritesClickListener();

        fromSearchResultsActivityIntent = getIntent();
        restaurantID = fromSearchResultsActivityIntent.getIntExtra(Constants.PREF_KEY_COUNTER_SEARCH_RESULTS_ACTIVITY, DEFAULT_RESTAURANT_ID);
        Log.d("DetailActivity", "fromSearchResultsActivityIntent");

        getDataFromSearchResultsActivityAndInsertItToView();

    }

    private String getDollarSymbols() { // adds dollar symbols to price textViews
        if (Integer.parseInt(priceOfRestaurant) == 1) {
            dollarSymbols = "$";
        } else if (Integer.parseInt(priceOfRestaurant) == 2) {
            dollarSymbols = "$$";
        } else if (Integer.parseInt(priceOfRestaurant) == 3) {
            dollarSymbols = "$$$";
        } else if (Integer.parseInt(priceOfRestaurant) == 4) {
            dollarSymbols = "$$$$";
        }
        return dollarSymbols;
    }


    private void updateFavoriteDataBase() { // adds or removes restaurantsItems from Favorite Column in database

        if (favorites == 0) {
            helper.updateFavorites(restaurantID, false);
        } else if (favorites == 1) {
            helper.updateFavorites(restaurantID, true);
        }
    }

    private void updateColorOfFavoriteButton() { // updates color of favoriteButton

        if (favorites == 0) {
            addToFavorites.setBackgroundResource(R.drawable.round_button);
            addToFavorites.setText(R.string.addToFavorites);
        } else if (favorites == 1) {
            addToFavorites.setBackgroundResource(R.drawable.round_button2);
            addToFavorites.setText(R.string.inFavorites);
        }
    }

    private void addToFavoritesClickListener() { // clickListener of favoriteButton

        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favorites == 1) {
                    favorites = 0;
                } else if (favorites == 0) {
                    favorites = 1;
                }
                updateFavoriteDataBase();
                updateColorOfFavoriteButton();
            }
        });

    }

    /**
     this method gets data from SearchResultsActivity
     and inserts it to View widgets
     */

    private void getDataFromSearchResultsActivityAndInsertItToView() {

        if (restaurantID != DEFAULT_RESTAURANT_ID) {

            Cursor cursor = helper.getRestaurantByID(restaurantID);
            cursor.moveToFirst();

            int index = cursor.getColumnIndex(RestaurantsDataSQLite.COL_NAME);
            int indexDescription = cursor.getColumnIndex(RestaurantsDataSQLite.COL_DESCRIPTION);
            int indexContacts = cursor.getColumnIndex(RestaurantsDataSQLite.COL_CONTACTS);
            int indexFavorites = cursor.getColumnIndex(RestaurantsDataSQLite.COL_FAVORITES);
            int indexRating = cursor.getColumnIndex(RestaurantsDataSQLite.COL_RATING);
            int indexDelivery = cursor.getColumnIndex(RestaurantsDataSQLite.COL_DELIVERY);
            int indexImage = cursor.getInt(cursor.getColumnIndex(RestaurantsDataSQLite.COL_IMAGE));

            String nameOfRestaurant = cursor.getString(index);
            String description = cursor.getString(indexDescription);
            String contacts = cursor.getString(indexContacts);
            String ratingOfRestaurant = cursor.getString(indexRating);
            String deliveryOfRestaurant = cursor.getString(indexDelivery);


            String ratingStars = "Rating: ";
            String deliveryText = "Delivery: ";

            indexPrice = cursor.getColumnIndex(RestaurantsDataSQLite.COL_PRICE);
            priceOfRestaurant = cursor.getString(indexPrice);
            favorites = cursor.getInt(indexFavorites);

            updateColorOfFavoriteButton();

            setTitle(nameOfRestaurant);
            restaurantContacts.setText(contacts);
            restaurantDescription.setText(description);
            restaurantImage.setImageResource(indexImage);
            price.setText(getDollarSymbols());
            rating.setText(ratingStars.concat(ratingOfRestaurant));
            delivery.setText(deliveryText.concat(deliveryOfRestaurant));
        }

    }

    /**
     * initialize Views
     */
    private void initViews(){

        restaurantName = (TextView) findViewById(R.id.restaurant_header);
        restaurantDescription = (TextView) findViewById(R.id.restaurant_description);
        restaurantContacts = (TextView) findViewById(R.id.restaurant_contacts);
        price = (TextView) findViewById(R.id.price);
        rating = (TextView) findViewById(R.id.rating);
        delivery = (TextView) findViewById(R.id.delivery);
        restaurantImage = (ImageView) findViewById(R.id.restaurant_image);
        addToFavorites = (Button) findViewById(R.id.add_to_favorites);
    }

}
