package com.mikhail.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
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


        RestaurantsData dbSetup = new RestaurantsData(SecondActivity.this);
        dbSetup.getReadableDatabase();


        RestaurantsData restaurant = new RestaurantsData(this);
//
        restaurant.insert(1, "The Lunch Box ", "-$-", " ★4.0 ", " Delivery: YES");
        restaurant.insert(2, "Bacheesos ", "-$-", " ★3.5 ", " Delivery: YES");
        restaurant.insert(3, "Flora Restaurant & Bar ", " $$$ ", " 4.0 ", " Delivery: NO");
        restaurant.insert(4, "Kingston 11 Cuisine ", " $$$ ", " 4.0 ", " Delivery: NO");
        restaurant.insert(5, "Flora Restaurant & Bar", " $$ ", " 3.0 ", " Delivery: NO");
        restaurant.insert(6, "Ike’s Place ", " $$ ", " 4.0 ", " Delivery: YES");
        restaurant.insert(7, "Picán ", " $$$$ ", " 4.5 ", " Delivery: NO");
        restaurant.insert(8, "Fat Cat Cafe ", " $ ", " 4.0 ", " Delivery: YES");
        restaurant.insert(9, "Liba Falafel ", " $$ ", " 4.5 ", " Delivery: NO");
        restaurant.insert(10, "Xolo ", " $ ", " 3.5 ", " Delivery: YES");
        restaurant.insert(11, "Hawker Fare ", " $$$ ", " 3.0 ", " Delivery: NO");
        restaurant.insert(12, "Henry’s Gallery Cafe ", " $ ", " 4.0 ", " Delivery: NO");
        restaurant.insert(13, "Mazzat Grill ", " $$ ", " 5.0 ", " Delivery: YES");
        restaurant.insert(14, "Torpedo Sushi ", " $$ ", " 4.0 ", " Delivery: YES");
        restaurant.insert(15, "Space Burger ", " $$ ", " 2.5 ", " Delivery: NO");


        Cursor cursor = RestaurantsData.getInstance(SecondActivity.this).getRestaurantsList();


        String[] columns = new String[]{RestaurantsData.COL_NAME, RestaurantsData.COL_PRICE, RestaurantsData.COL_RATING, RestaurantsData.COL_DELIVERY};
        int[] viewNames = new int[]{R.id.text, R.id.text1, R.id.text2, R.id.text3,};
        CursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(SecondActivity.this, R.layout.custom, cursor, columns, viewNames, 0);

        restaurantsList.setAdapter(simpleCursorAdapter);
    }


}
