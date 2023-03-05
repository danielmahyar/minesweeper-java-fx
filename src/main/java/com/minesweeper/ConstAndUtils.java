package com.minesweeper;

import javafx.scene.image.Image;

import java.net.URL;

public class ConstAndUtils {

    final static public Image FIELD_CLICKABLE_IMAGE = getURLAsImage("images/100x100_clickable.png");
    final static public Image FIELD_FLAGGED_IMAGE = getURLAsImage("images/100x100_flagged.png");

    final static public Image FIELD_NUMBER_ONE = getURLAsImage("images/100x100_field_one.png");
    final static public Image FIELD_NUMBER_TWO = getURLAsImage("images/100x100_field_two.png");
    final static public Image FIELD_NUMBER_THREE = getURLAsImage("images/100x100_field_three.png");
    final static public Image FIELD_NUMBER_FOUR = getURLAsImage("images/100x100_field_four.png");
    final static public Image FIELD_NUMBER_FIVE = getURLAsImage("images/100x100_field_five.png");
    final static public Image FIELD_NUMBER_SIX = getURLAsImage("images/100x100_field_six.png");
    final static public Image FIELD_NUMBER_SEVEN = getURLAsImage("images/100x100_field_seven.png");
    final static public Image FIELD_NUMBER_EIGHT = getURLAsImage("images/100x100_field_eight.png");



    private static Image getURLAsImage(String url){
        return new Image(getResourceAsURL(url).toExternalForm());
    }

    public static URL getResourceAsURL(String url){
        return ConstAndUtils.class.getResource(url);
    }
}
