package com.minesweeper.utils;

import java.util.*;
import java.awt.Point;

public class RandomUtil {

    public static Point[] getRandomCoordinatesBetweenInterval(int amount, int min_x, int max_x, int min_y, int max_y) {
        Set<Point> pointSet = new HashSet<>();

        // TODO Make sure square around origin has no bombs

        while (pointSet.size() < amount) {
            int x = getRandomIntBetween(min_x, max_x);
            int y = getRandomIntBetween(min_y, max_y);
            pointSet.add(new Point(x, y));
        }

        return pointSet.toArray(new Point[0]);
    }

    public static int getRandomIntBetween(int min, int max){
        return new Random().nextInt(max - min) + min;
    }
}
