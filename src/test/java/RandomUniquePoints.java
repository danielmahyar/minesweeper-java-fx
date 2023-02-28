import com.minesweeper.utils.RandomUtil;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomUniquePoints {

    @Test
    public void testRandomPoints() {
        Point[] points = RandomUtil.getRandomCoordinatesBetweenInterval(20, 10, 100, 10, 100);
        List<Point> point_list = new ArrayList<>();

        Collections.addAll(point_list, points);

        for(Point point : points) {
            assertEquals(1, point_list.stream().filter(p -> p == point).count());
            assertTrue(isBetweenInterval((int) point.getX(), 10, 100));
            assertTrue(isBetweenInterval((int) point.getY(), 10, 100));
        }

        // Check it has the same amount has required
        assertEquals(20, points.length);
    }

    @Test
    public void testManyRandomPoints() {
        Point[] points = RandomUtil.getRandomCoordinatesBetweenInterval(100, 10, 100, 10, 100);
        List<Point> point_list = new ArrayList<>();

        Collections.addAll(point_list, points);

        for(Point point : points) {
            assertEquals(1, point_list.stream().filter(p -> p == point).count());
            assertTrue(isBetweenInterval((int) point.getX(), 10, 100));
            assertTrue(isBetweenInterval((int) point.getY(), 10, 100));
        }

        // Check it has the same amount has required
        assertEquals(100, points.length);
    }

    @Test
    public void testDifferentMinMax() {
        int min_x = 10;
        int min_y = 10;
        int max_x = 50;
        int max_y = 98;
        int amount = 100;

        Point[] points = RandomUtil.getRandomCoordinatesBetweenInterval(amount, min_x, max_x, min_y, max_y);
        List<Point> point_list = new ArrayList<>();

        Collections.addAll(point_list, points);

        for (Point point : points) {
            assertEquals(1, point_list.stream().filter(p -> p == point).count());
            assertTrue(isBetweenInterval((int) point.getX(), min_x, max_x));
            assertTrue(isBetweenInterval((int) point.getY(), min_y, max_y));
        }

        // Check it has the same amount has required
        assertEquals(amount, points.length);
    }

    private boolean isBetweenInterval(int num, int min, int max){
        System.out.println("num: " + num + " min: " + min + " max: " + max);
        return num >= min && num <= max;
    }
}
