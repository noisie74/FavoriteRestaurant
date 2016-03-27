package com.mikhail.project2;

import android.content.Context;

import java.util.ArrayList;

/**
 * This class populates the data for the restaurants
 * using an ArrayList and the Restaurants class
 */
public class PopulateRestaurants {

    public static ArrayList<Restaurants> restaurantsItems = new ArrayList<>();

    public static ArrayList<Restaurants> getRestaurants(Context context) {


        Restaurants theLunchBox = new Restaurants();

        theLunchBox.setId(1);
        theLunchBox.setName(context.getString(R.string.the_lunch_box_rest));
        theLunchBox.setPrice(context.getString(R.string.the_lunch_box_price));
        theLunchBox.setRating(context.getString(R.string.the_lunch_box_rating));
        theLunchBox.setDelivery(context.getString(R.string.the_lunch_box_delivery));
        theLunchBox.setContacts(context.getString(R.string.the_lunch_box_contacts));
        theLunchBox.setDescription(context.getString(R.string.the_lunch_box_description));
        theLunchBox.setImageResourceId(R.drawable.lunchbox);
        theLunchBox.setFavorite(false);
        restaurantsItems.add(theLunchBox);

        Restaurants bacheesos = new Restaurants();

        bacheesos.setId(2);
        bacheesos.setName(context.getString(R.string.bacheesos_rest));
        bacheesos.setPrice(context.getString(R.string.bacheesos_price));
        bacheesos.setRating(context.getString(R.string.bacheesos_rating));
        bacheesos.setDelivery(context.getString(R.string.bacheesos_delivery));
        bacheesos.setContacts(context.getString(R.string.bacheesos_contacts));
        bacheesos.setDescription(context.getString(R.string.bacheesos_description));
        bacheesos.setImageResourceId(R.drawable.bacheesos);
        bacheesos.setFavorite(false);
        restaurantsItems.add(bacheesos);


        Restaurants flora = new Restaurants();

        flora.setId(3);
        flora.setName(context.getString(R.string.flora_rest));
        flora.setPrice(context.getString(R.string.flora_price));
        flora.setRating(context.getString(R.string.flora_rating));
        flora.setDelivery(context.getString(R.string.flora_delivery));
        flora.setContacts(context.getString(R.string.flora_contacts));
        flora.setDescription(context.getString(R.string.flora_description));
        flora.setImageResourceId(R.drawable.flora);
        flora.setFavorite(false);
        restaurantsItems.add(flora);

        Restaurants kingston = new Restaurants();

        kingston.setId(4);
        kingston.setName(context.getString(R.string.kingston_rest));
        kingston.setPrice(context.getString(R.string.kingston_price));
        kingston.setRating(context.getString(R.string.kingston_rating));
        kingston.setDelivery(context.getString(R.string.kingston_delivery));
        kingston.setContacts(context.getString(R.string.kingston_contacts));
        kingston.setDescription(context.getString(R.string.kingston_description));
        kingston.setImageResourceId(R.drawable.kingston);
        kingston.setFavorite(false);
        restaurantsItems.add(kingston);

        Restaurants ozumo = new Restaurants();

        ozumo.setId(5);
        ozumo.setName(context.getString(R.string.ozumo_rest));
        ozumo.setPrice(context.getString(R.string.ozumo_price));
        ozumo.setRating(context.getString(R.string.ozumo_rating));
        ozumo.setDelivery(context.getString(R.string.ozumo_delivery));
        ozumo.setContacts(context.getString(R.string.ozumo_contacts));
        ozumo.setDescription(context.getString(R.string.ozumo_description));
        ozumo.setImageResourceId(R.drawable.ozumo);
        ozumo.setFavorite(false);
        restaurantsItems.add(ozumo);

        Restaurants ikes = new Restaurants();

        ikes.setId(6);
        ikes.setName(context.getString(R.string.ikes_rest));
        ikes.setPrice(context.getString(R.string.ikes_price));
        ikes.setRating(context.getString(R.string.ikes_rating));
        ikes.setDelivery(context.getString(R.string.ikes_delivery));
        ikes.setContacts(context.getString(R.string.ikes_contacts));
        ikes.setDescription(context.getString(R.string.ikes_description));
        ikes.setImageResourceId(R.drawable.ikes);
        ikes.setFavorite(false);
        restaurantsItems.add(ikes);

        Restaurants pican = new Restaurants();

        pican.setId(7);
        pican.setName(context.getString(R.string.pican_rest));
        pican.setPrice(context.getString(R.string.pican_price));
        pican.setRating(context.getString(R.string.pican_rating));
        pican.setDelivery(context.getString(R.string.pican_delivery));
        pican.setContacts(context.getString(R.string.pican_contacts));
        pican.setDescription(context.getString(R.string.pican_description));
        pican.setImageResourceId(R.drawable.pican);
        pican.setFavorite(false);
        restaurantsItems.add(pican);

        Restaurants fatCatCafe = new Restaurants();

        fatCatCafe.setId(8);
        fatCatCafe.setName(context.getString(R.string.fat_cat_rest));
        fatCatCafe.setPrice(context.getString(R.string.fat_cat_price));
        fatCatCafe.setRating(context.getString(R.string.fat_cat_rating));
        fatCatCafe.setDelivery(context.getString(R.string.fat_cat_delivery));
        fatCatCafe.setContacts(context.getString(R.string.fat_cat_contacts));
        fatCatCafe.setDescription(context.getString(R.string.fat_cat_description));
        fatCatCafe.setImageResourceId(R.drawable.fatcat);
        fatCatCafe.setFavorite(false);
        restaurantsItems.add(fatCatCafe);

        Restaurants libaFalafel = new Restaurants();

        libaFalafel.setId(9);
        libaFalafel.setName(context.getString(R.string.liba_rest));
        libaFalafel.setPrice(context.getString(R.string.liba_price));
        libaFalafel.setRating(context.getString(R.string.liba_rating));
        libaFalafel.setDelivery(context.getString(R.string.liba_delivery));
        libaFalafel.setContacts(context.getString(R.string.liba_contacts));
        libaFalafel.setDescription(context.getString(R.string.liba_description));
        libaFalafel.setImageResourceId(R.drawable.liba);
        libaFalafel.setFavorite(false);
        restaurantsItems.add(libaFalafel);

        Restaurants xolo = new Restaurants();

        xolo.setId(10);
        xolo.setName(context.getString(R.string.xolo_rest));
        xolo.setPrice(context.getString(R.string.xolo_price));
        xolo.setRating(context.getString(R.string.xolo_rating));
        xolo.setDelivery(context.getString(R.string.xolo_delivery));
        xolo.setContacts(context.getString(R.string.xolo_conacts));
        xolo.setDescription(context.getString(R.string.xolo_description));
        xolo.setImageResourceId(R.drawable.xolo1);
        xolo.setFavorite(false);
        restaurantsItems.add(xolo);

        Restaurants hawkerFare = new Restaurants();

        hawkerFare.setId(11);
        hawkerFare.setName(context.getString(R.string.hawker_fare_rest));
        hawkerFare.setPrice(context.getString(R.string.hawker_fare_price));
        hawkerFare.setRating(context.getString(R.string.hawker_fare_rating));
        hawkerFare.setDelivery(context.getString(R.string.hawker_fare_delivery));
        hawkerFare.setContacts(context.getString(R.string.hawker_fare_contacts));
        hawkerFare.setDescription(context.getString(R.string.hawker_fare_description));
        hawkerFare.setImageResourceId(R.drawable.hawkefare);
        hawkerFare.setFavorite(false);
        restaurantsItems.add(hawkerFare);

        Restaurants henryRest = new Restaurants();

        henryRest.setId(12);
        henryRest.setName(context.getString(R.string.henry_rest));
        henryRest.setPrice(context.getString(R.string.henry_price));
        henryRest.setRating(context.getString(R.string.henry_rating));
        henryRest.setDelivery(context.getString(R.string.henry_delivery));
        henryRest.setContacts(context.getString(R.string.henry_contacts));
        henryRest.setDescription(context.getString(R.string.henry_description));
        henryRest.setImageResourceId(R.drawable.henry);
        henryRest.setFavorite(false);
        restaurantsItems.add(henryRest);

        Restaurants mazzatRest = new Restaurants();

        mazzatRest.setId(13);
        mazzatRest.setName(context.getString(R.string.mazzat_rest));
        mazzatRest.setPrice(context.getString(R.string.mazzat_price));
        mazzatRest.setRating(context.getString(R.string.mazzat_rating));
        mazzatRest.setDelivery(context.getString(R.string.mazzat_delivery));
        mazzatRest.setContacts(context.getString(R.string.mazzat_contacts));
        mazzatRest.setDescription(context.getString(R.string.mazzat_description));
        mazzatRest.setImageResourceId(R.drawable.mazzat);
        mazzatRest.setFavorite(false);
        restaurantsItems.add(mazzatRest);

        Restaurants torpedoSushi = new Restaurants();

        torpedoSushi.setId(14);
        torpedoSushi.setName(context.getString(R.string.torpedo_rest));
        torpedoSushi.setPrice(context.getString(R.string.torpedo_price));
        torpedoSushi.setRating(context.getString(R.string.torpedo_rating));
        torpedoSushi.setDelivery(context.getString(R.string.torpedo_delivery));
        torpedoSushi.setContacts(context.getString(R.string.torpedo_contacts));
        torpedoSushi.setDescription(context.getString(R.string.torpedo_description));
        torpedoSushi.setImageResourceId(R.drawable.torpedo);
        torpedoSushi.setFavorite(false);
        restaurantsItems.add(torpedoSushi);

        Restaurants spaceBurger = new Restaurants();

        spaceBurger.setId(15);
        spaceBurger.setName(context.getString(R.string.space_burger_rest));
        spaceBurger.setPrice(context.getString(R.string.space_burger_price));
        spaceBurger.setRating(context.getString(R.string.space_burger_rating));
        spaceBurger.setDelivery(context.getString(R.string.space_burger_delivery));
        spaceBurger.setContacts(context.getString(R.string.space_burger_contacts));
        spaceBurger.setDescription(context.getString(R.string.space_burger_description));
        spaceBurger.setImageResourceId(R.drawable.space);
        spaceBurger.setFavorite(false);
        restaurantsItems.add(spaceBurger);

        return restaurantsItems;

    }
}
