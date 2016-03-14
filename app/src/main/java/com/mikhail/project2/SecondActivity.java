package com.mikhail.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
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
    }


}
