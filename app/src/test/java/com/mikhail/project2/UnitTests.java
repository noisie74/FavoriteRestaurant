package com.mikhail.project2;


import junit.framework.Assert;

import org.junit.Test;



/**
 * _________Testing if Constant values are correct__________
 */
public class UnitTests {


    @Test
    public void checkIfPriceIsCorrect() {


        String actualResult = Constants.PRICE;
        String expectedResult = "price";


        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkIfRatingIsCorrect() {


        String actualResult = Constants.RATING;
        String expectedResult = "rating";


        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void checkIfDeliveryIsCorrect() {

        String actualResult = Constants.DELIVERY;
        String expectedResult = "delivery";


        Assert.assertEquals(expectedResult, actualResult);
    }

/**
_________Testing if correct restaurants data matches database
*/

    @Test
    public void checkRestaurantsName() {

        Restaurants restaurantsTest = new Restaurants();

        restaurantsTest.setName("Ozumo");

        String actualResult = "Ozumo";
        String expectedResult = restaurantsTest.getName();

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void checkRestaurantsRating() {

        Restaurants restaurantsTest = new Restaurants();

        restaurantsTest.setRating("3.5");

        String actualResult = "3.5";
        String expectedResult = restaurantsTest.getRating();

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void checkIsFavorites() {

        Restaurants restaurantsTest = new Restaurants();
        restaurantsTest.setFavorite(false);

        boolean actualResult = false;
        boolean expectedResult = restaurantsTest.isFavorite();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetImageResourceID() {

        Restaurants restaurantsTest = new Restaurants();
        restaurantsTest.setImageResourceId(R.drawable.ozumo);

        int actualResult = R.drawable.ozumo;
        int expectedResult = restaurantsTest.getImageResourceId();

        Assert.assertEquals(expectedResult, actualResult);
    }
}

