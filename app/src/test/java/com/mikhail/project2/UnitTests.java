package com.mikhail.project2;

import android.database.Cursor;
import android.view.View;

import junit.framework.Assert;

import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Testing if Constant values are correct
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

}

