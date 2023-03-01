package com.minesweeper;

import javafx.scene.image.Image;

import java.net.URL;

public class ConstAndUtils {

    final static public Image FIELD_CLICKABLE_IMAGE = getURLAsImage("images/100x100_clickable.png");
    final static public Image FIELD_FLAGGED_IMAGE = getURLAsImage("images/100x100_flagged.png");


    private static Image getURLAsImage(String url){
        return new Image(getResourceAsURL(url).toExternalForm());
    }

    public static URL getResourceAsURL(String url){
        return ConstAndUtils.class.getResource(url);
    }
}
