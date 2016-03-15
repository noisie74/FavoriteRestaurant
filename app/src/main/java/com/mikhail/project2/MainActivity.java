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
    int counter;
    int clickCount;

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

                while (ifTooManyItemsSelected()) {
                    searchButton.setEnabled(false);
//                    searchButton.setClickable(false);
                    return;
                }
                    run();
//                    searchButton.setEnabled(true);
                    searchButton.getText().toString();
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
                clickCount++;
            }
        });

        dollarOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    dollarOne.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarTwo.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || yes.isSelected() || no.isSelected()) {
                    dollarOne.setSelected(true);
                } else dollarOne.setSelected(false);
                counter++;
            }
        });

        dollarTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    dollarTwo.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || yes.isSelected() || no.isSelected()) {
                    dollarTwo.setSelected(true);
                } else dollarTwo.setSelected(false);
                counter++;
            }
        });

        dollarThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    dollarThree.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarFour.isSelected() || yes.isSelected() || no.isSelected()) {
                    dollarThree.setSelected(true);
                } else dollarThree.setSelected(false);
                counter++;
            }
        });

        dollarFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    dollarFour.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || yes.isSelected() || no.isSelected()) {
                    dollarFour.setSelected(true);
                } else dollarFour.setSelected(false);
                counter++;
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    yes.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || no.isSelected()) {
                    yes.setSelected(true);
                } else yes.setSelected(false);
                counter++;
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter % 2 == 0) {
                    no.setSelected(true);
                } else if (clickCount % 2 == 0 || clickCount % 2 == 1 || dollarOne.isSelected() || dollarTwo.isSelected() || dollarThree.isSelected() || dollarFour.isSelected() || yes.isSelected()) {
                    no.setSelected(true);
                } else no.setSelected(false);
                counter++;
            }
        });
    }

    private boolean ifTooManyItemsSelected() {

        if (dollarOne.isSelected() && dollarTwo.isSelected() && dollarThree.isSelected() && dollarFour.isSelected() && yes.isSelected() && no.isSelected()) {
//            searchButton.setEnabled(false);
            Toast.makeText(MainActivity.this, "Please select your filter!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private boolean isRatingZero() {
        if (ratingBar.getRating() == 0) {
            return false;
        }
        return true;
    }

    public void run() {
        //re-enable the button
        searchButton.setEnabled(true);

    }

}
