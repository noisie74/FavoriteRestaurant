package com.mikhail.project2;

import android.content.Context;

/**
 * DatabaseData class contains all
 * restaurantsItems data
 */
public class DatabaseData {

    private DatabaseData(){

    }

    public static void insertData(Context context) {  // Insert data into the "RestaurantsDataSQLite" Database

        RestaurantsDataSQLite restaurant = RestaurantsDataSQLite.getInstance(context);

        restaurant.insert(1, context.getString(R.string.the_lunch_box_rest), context.getString(R.string.the_lunch_box_price), context.getString(R.string.the_lunch_box_rating), context.getString(R.string.the_lunch_box_delivery), context.getString(R.string.the_lunch_box_contacts), context.getString(R.string.the_lunch_box_description), R.drawable.lunchbox, false);
        restaurant.insert(2, context.getString(R.string.bacheesos_rest), context.getString(R.string.bacheesos_price), context.getString(R.string.bacheesos_rating), context.getString(R.string.bacheesos_delivery), context.getString(R.string.bacheesos_contacts), context.getString(R.string.bacheesos_description), R.drawable.bacheesos, false);
        restaurant.insert(3, context.getString(R.string.flora_rest), context.getString(R.string.flora_price), context.getString(R.string.flora_rating), context.getString(R.string.flora_delivery), context.getString(R.string.flora_contacts), context.getString(R.string.flora_description), R.drawable.flora, false);
        restaurant.insert(4, context.getString(R.string.kingston_rest), context.getString(R.string.kingston_price), context.getString(R.string.kingston_rating), context.getString(R.string.kingston_delivery), context.getString(R.string.kingston_contacts), context.getString(R.string.kingston_description), R.drawable.kingston, false);
        restaurant.insert(5, context.getString(R.string.ozumo_rest), context.getString(R.string.ozumo_price), context.getString(R.string.ozumo_rating), context.getString(R.string.ozumo_delivery), context.getString(R.string.ozumo_contacts), context.getString(R.string.ozumo_description), R.drawable.ozumo, false);
        restaurant.insert(6, context.getString(R.string.ikes_rest), context.getString(R.string.ikes_price), context.getString(R.string.ikes_rating), context.getString(R.string.ikes_delivery), context.getString(R.string.ikes_contacts), context.getString(R.string.ikes_description), R.drawable.ikes, false);
        restaurant.insert(7, context.getString(R.string.pican_rest), context.getString(R.string.pican_price), context.getString(R.string.pican_rating), context.getString(R.string.pican_delivery), context.getString(R.string.pican_contacts), context.getString(R.string.pican_description), R.drawable.pican, false);
        restaurant.insert(8, context.getString(R.string.fat_cat_rest), context.getString(R.string.fat_cat_price), context.getString(R.string.fat_cat_rating), context.getString(R.string.fat_cat_delivery), context.getString(R.string.fat_cat_contacts), context.getString(R.string.fat_cat_description), R.drawable.fatcat, false);
        restaurant.insert(9, context.getString(R.string.liba_rest), context.getString(R.string.liba_price), context.getString(R.string.liba_rating), context.getString(R.string.liba_delivery), context.getString(R.string.liba_contacts), context.getString(R.string.liba_description), R.drawable.liba, false);
        restaurant.insert(10, context.getString(R.string.xolo_rest), context.getString(R.string.xolo_price), context.getString(R.string.xolo_rating), context.getString(R.string.xolo_delivery), context.getString(R.string.xolo_conacts), context.getString(R.string.xolo_description), R.drawable.xolo1, false);
        restaurant.insert(11, context.getString(R.string.hawker_fare_rest), context.getString(R.string.hawker_fare_price), context.getString(R.string.hawker_fare_rating), context.getString(R.string.hawker_fare_delivery), context.getString(R.string.hawker_fare_contacts), context.getString(R.string.hawker_fare_description), R.drawable.hawkefare, false);
        restaurant.insert(12, context.getString(R.string.henry_rest), context.getString(R.string.henry_price), context.getString(R.string.henry_rating), context.getString(R.string.henry_delivery), context.getString(R.string.henry_contacts), context.getString(R.string.henry_description), R.drawable.henry, false);
        restaurant.insert(13, context.getString(R.string.mazzat_rest), context.getString(R.string.mazzat_price), context.getString(R.string.mazzat_rating), context.getString(R.string.mazzat_delivery), context.getString(R.string.mazzat_contacts), context.getString(R.string.mazzat_description), R.drawable.mazzat, false);
        restaurant.insert(14, context.getString(R.string.torpedo_rest), context.getString(R.string.torpedo_price), context.getString(R.string.torpedo_rating), context.getString(R.string.torpedo_delivery), context.getString(R.string.torpedo_contacts), context.getString(R.string.torpedo_description), R.drawable.torpedo, false);
        restaurant.insert(15, context.getString(R.string.space_burger_rest), context.getString(R.string.space_burger_price), context.getString(R.string.space_burger_rating), context.getString(R.string.space_burger_delivery), context.getString(R.string.space_burger_contacts), context.getString(R.string.space_burger_description), R.drawable.space, false);

    }
}
