package com.mikhail.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;

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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        MenuItem myMenu = menu.findItem(R.id.action_settings);

        myMenu.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        return true;
    }


    private void clickListenersMainActivity() {

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ifTooManyItemsSelected() || ifNoItemsSelected()) {
                    return;
                }
                searchButton.getText().toString();
                startActivity(toGoToSecondActivity);

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchButton.setEnabled(true);

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
//               int dollarOneCounter;
            @Override
            public void onClick(View v) {
                if (dollarOneCounter % 2 == 0) {
                    dollarOne.setSelected(true);
                }  else dollarOne.setSelected(false);
                dollarOneCounter++;
            }
        });

        dollarTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dollarTwoCounter % 2 == 0) {
                    dollarTwo.setSelected(true);
//                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || yes.isSelected() || no.isSelected()) {
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
//                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarFour.isSelected() || yes.isSelected() || no.isSelected()) {
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
//                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || yes.isSelected() || no.isSelected()) {
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
//                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || no.isSelected()) {
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
//                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || yes.isSelected()) {
                    no.setSelected(true);
                } else no.setSelected(false);
                noCounter++;
            }
        });
    }

    private boolean ifTooManyItemsSelected() {

        if (dollarOne.isSelected() && dollarTwo.isSelected() && dollarThree.isSelected() && dollarFour.isSelected() && yes.isSelected() && no.isSelected() || yes.isSelected() && no.isSelected()) {
//            searchButton.setEnabled(false);
            Toast.makeText(MainActivity.this, "Please specify your search!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    private boolean ifNoItemsSelected(){

        if (!dollarOne.isSelected() && !dollarTwo.isSelected() && !dollarThree.isSelected() && !dollarFour.isSelected() && !yes.isSelected() && !no.isSelected() && isRatingZero(true)) {
            Toast.makeText(MainActivity.this, "Please specify your search!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    private boolean isRatingZero(boolean isTrue) {
        if (ratingBar.getRating() == 0) {
            return true;
        } else {
            return false;
        }
    }


}
