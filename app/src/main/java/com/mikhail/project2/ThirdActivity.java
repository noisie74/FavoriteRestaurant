package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    Intent fromSecondActivity;
    TextView restaurantName;
    TextView restaurantDescription;
    TextView restaurantContacts;
    TextView restaurantIsInFavorites;
    ImageView restaurantImage;
    Button addToFavorites;
    int favorites;
    RestaurantsData helper;
    int restaurantID;


    private static final int DEFAULT_RESTAURANT_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        restaurantName = (TextView) findViewById(R.id.restaurant_header);
        restaurantDescription = (TextView) findViewById(R.id.restaurant_description);
        restaurantContacts = (TextView) findViewById(R.id.restaurant_contacts);
        restaurantIsInFavorites = (TextView) findViewById(R.id.restaurant_favorites);
        restaurantImage = (ImageView) findViewById(R.id.restaurant_image);
        addToFavorites = (Button) findViewById(R.id.add_to_favorites);

        helper = RestaurantsData.getInstance(ThirdActivity.this);

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


        fromSecondActivity = getIntent();
        restaurantID = fromSecondActivity.getIntExtra("data", DEFAULT_RESTAURANT_ID);
        if (restaurantID != DEFAULT_RESTAURANT_ID) {


            Cursor cursor = helper.getRestaurantByID(restaurantID);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(RestaurantsData.COL_NAME);
            int indexDescription = cursor.getColumnIndex(RestaurantsData.COL_DESCRIPTION);
            int indexContacts = cursor.getColumnIndex(RestaurantsData.COL_CONTACTS);
            int indexFavorites = cursor.getColumnIndex(RestaurantsData.COL_FAVORITES);

            int indexImage = cursor.getInt(cursor.getColumnIndex(RestaurantsData.COL_IMAGE));

            String contacts = cursor.getString(indexContacts);
            String description = cursor.getString(indexDescription);
            String nameOfRestaurant = cursor.getString(index);
            favorites = cursor.getInt(indexFavorites);


            updateColorOfFavoriteButton();

            setTitle(nameOfRestaurant);
            restaurantContacts.setText(contacts);
            restaurantDescription.setText(description);
            restaurantImage.setImageResource(indexImage);

        } else {
            Toast.makeText(ThirdActivity.this, "No description found", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateFavoriteDataBase() {

        if (favorites == 0) {
            helper.updateFavorites(restaurantID, false);
        } else if (favorites == 1) {
            helper.updateFavorites(restaurantID, true);
        }
    }

    public void updateColorOfFavoriteButton() {

        if (favorites == 0) {
            addToFavorites.setBackgroundResource(R.drawable.round_button);
            addToFavorites.setText(R.string.addToFavorites);
        } else if (favorites == 1) {
            addToFavorites.setBackgroundResource(R.drawable.round_button2);
            addToFavorites.setText(R.string.inFavorites);
        }
    }

}
