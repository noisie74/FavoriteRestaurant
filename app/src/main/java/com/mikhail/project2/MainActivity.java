package com.mikhail.project2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView neighborhoodName;
    TextView dollarOne;
    TextView dollarTwo;
    TextView dollarThree;
    TextView dollarFour;
    TextView price;
    TextView rating;
    TextView delivery;
    TextView yes;
    TextView no;
    TextView filterYourSearch;
    RatingBar ratingBar;
    Button clearButton;
    Button searchButton;

    Intent toGoToSecondActivity;
    int dollarOneCounter;
    int dollarTwoCounter;
    int dollarThreeCounter;
    int dollarFourCounter;
    int yesCounter;
    int noCounter;

    private static final boolean PREF_KEY_COUNTER_DEFAULT = false;
    private static String PREF_KEY_COUNTER = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        toGoToSecondActivity = new Intent(MainActivity.this, SecondActivity.class);


        clickListenersMainActivity();
        setSharedPreferences();

    }

    private void clickListenersMainActivity() {

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

                toGoToSecondActivity.putExtra("price", prices);
                toGoToSecondActivity.putExtra("rating", rating);
                toGoToSecondActivity.putExtra("delivery", delivery);

                startActivity(toGoToSecondActivity);

            }
        });

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

    private boolean ifNoItemsSelected() {

        if (!dollarOne.isSelected() && !dollarTwo.isSelected() && !dollarThree.isSelected() && !dollarFour.isSelected() && !yes.isSelected() && !no.isSelected() && isRatingZero(true)) {
            Toast.makeText(MainActivity.this, "Please specify your search!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    private int[] getPriceSelections() {
        int prices[] = new int[4];
        if (dollarOne.isSelected()) prices[0] = 1;
        if (dollarTwo.isSelected()) prices[1] = 1;
        if (dollarThree.isSelected()) prices[2] = 1;
        if (dollarFour.isSelected()) prices[3] = 1;
        return prices;
    }

    private boolean isRatingZero(boolean isTrue) {
        if (ratingBar.getRating() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void insertData() {

        RestaurantsData restaurant = new RestaurantsData(this);
        restaurant.insert(1, "The Lunch Box", "1", "4.0", "YES", "1720 Franklin St, Oakland, CA 94612", "New American", R.drawable.lunchbox, false);
        restaurant.insert(2, "Bacheesos", "1", "3.5", "YES", "246 Grand Ave Oakland, CA 94610", "Mediterranean", R.drawable.bacheesos, false);
        restaurant.insert(3, "Flora Restaurant & Bar", "3", "4.0", "Delivery: NO", "1900 Telegraph Ave, Oakland, CA 94612", "New American", R.drawable.flora, false);
        restaurant.insert(4, "Kingston 11 Cuisine", "3", "4.0", "NO", "2270 Telegraph Ave, Oakland, CA 94612", "Caribbean", R.drawable.kingston, false);
        restaurant.insert(5, "Ozumo", "3", "3.5", "NO", "2251 Broadway, Oakland, CA 94612", "Sushi Bars", R.drawable.ozumo, false);
        restaurant.insert(6, "Ike’s Place", "2", "4.0", "YES", "2204 Broadway, Oakland, CA 94612", "Sandwiches", R.drawable.ikes, false);
        restaurant.insert(7, "Picán", "4", "4.5", "NO", "2295 Broadway, Oakland, CA 94612", "Southern", R.drawable.pican, false);
        restaurant.insert(8, "Fat Cat Cafe", "1", "4.0", "YES", "1720 Telegraph Ave, Oakland, CA 94612", "Sandwiches", R.drawable.fatcat, false);
        restaurant.insert(9, "Liba Falafel", "2", "4.5", "NO", "380 17th St, Oakland, CA 94612", "Falafel", R.drawable.liba, false);
        restaurant.insert(10, "Xolo", "1", "3.5", "YES", "1916 Telegraph Ave, Oakland, CA 94612", "Mexican", R.drawable.xolo1, false);
        restaurant.insert(11, "Hawker Fare", "3", "3.0", "NO", "301 Franklin St, Oakland, CA 94607", "Laotian", R.drawable.hawkefare, false);
        restaurant.insert(12, "Henry’s Gallery Cafe", "1", "4.0", "NO", "1700 Franklin St, Oakland, CA 94612", "New American", R.drawable.henry, false);
        restaurant.insert(13, "Mazzat Grill", "2", "5.0", "YES", "1924 Franklin St, Oakland, CA 94612", "Falafel", R.drawable.mazzat, false);
        restaurant.insert(14, "Torpedo Sushi", "2", "4.0", "YES", "25 Grand Ave, Oakland, CA 94612", "Sushi Bars", R.drawable.torpedo, false);
        restaurant.insert(15, "Space Burger", "2", "Rating: 2.5", "Delivery: NO", "2150 Telegraph Ave, Oakland, CA 94612", "Burgers", R.drawable.space, false);

    }

    private void setSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!getSharedPreferences()) {
            insertData();
            Log.e("MainActivity", "dataInserted!");
            editor.putBoolean(PREF_KEY_COUNTER, true);
            editor.apply();
        }

    }

    private boolean getSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        return sharedPreferences.getBoolean(PREF_KEY_COUNTER, PREF_KEY_COUNTER_DEFAULT);
    }

    private void dollarTextViewsClickListeners(){



    }


}
