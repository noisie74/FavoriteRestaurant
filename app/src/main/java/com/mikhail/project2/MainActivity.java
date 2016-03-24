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
        setSharedPreferences(); // Call SharedPreferences not to get duplicated data from database

    }

    private void clickListenersMainActivity() { // Call all clickListeners methods

        dollarTextViewsClickListeners();
        deliveryYesNoClickListeners();
        searchButtonClickListener();
        clearButtonClickListener();

    }

    private boolean ifNoItemsSelected() {  // Check if none of the filters(TextViews and ratingBar) are selected

        if (!dollarOne.isSelected() && !dollarTwo.isSelected() && !dollarThree.isSelected() && !dollarFour.isSelected() && !yes.isSelected() && !no.isSelected() && isRatingZero(true)) {
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

    private boolean isRatingZero(boolean isTrue) {  // Check if the ratingBar has no stars
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
            DatabaseMain.insertData(this); // Calling DatabaseMain class to  pull data from database
            Log.e("MainActivity", "dataInserted!");
            editor.putBoolean(PREF_KEY_COUNTER, true);
            editor.apply();
        }

    }

    private boolean getSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        return sharedPreferences.getBoolean(PREF_KEY_COUNTER, PREF_KEY_COUNTER_DEFAULT);
    }


    /**
     clickListeners methods for all the widgets
     of MainActivity

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

    private void deliveryYesNoClickListeners() {

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

                toGoToSecondActivity.putExtra("price", prices);
                toGoToSecondActivity.putExtra("rating", rating);
                toGoToSecondActivity.putExtra("delivery", delivery);

                startActivity(toGoToSecondActivity);

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

}
