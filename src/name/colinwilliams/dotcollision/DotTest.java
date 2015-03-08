package name.colinwilliams.dotcollision;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;

import org.junit.Test;

public class DotTest {

    @Test
    public void equals() {
	assertEquals(new Dot(new Point(0, 0), 5), new Dot(new Point(0, 0), 5));
	assertNotEquals(new Dot(new Point(0, 1), 5), new Dot(new Point(0, 0), 5));
    }

    @Test
    public void overlaps() {
	int radius = 5;
	assertTrue(new Dot(new Point(0, 0), radius).overlaps(new Dot(new Point(0, 1), radius)));
	assertFalse(new Dot(new Point(0, 0), radius).overlaps(new Dot(new Point(0, 10), radius)));

	// Testing collision detection with dots that straddle
	// the diagonal border.  To do this, calculating the
	// coordinates of a dot that would precisely abut one
	// at (0, 0).
	double distance_to_abutting_dot = radius * 2;
	// x = y <=> 2x^2 = distance_to_abutting_dot^2
	double abutting_x = distance_to_abutting_dot / Math.sqrt(2);

	int overlapping_x = (int) Math.floor(abutting_x);
	Dot overlapping = new Dot(new Point(overlapping_x, overlapping_x), radius);
	assertTrue(new Dot(new Point(0, 0), radius).overlaps(overlapping));
	assertTrue(overlapping.overlaps(new Dot(new Point(0, 0), radius)));
	
	int nonoverlapping_x = (int) Math.ceil(abutting_x);
	Dot nonoverlapping = new Dot(new Point(nonoverlapping_x, nonoverlapping_x), radius);
	assertFalse(new Dot(new Point(0, 0), radius).overlaps(nonoverlapping));
	assertFalse(nonoverlapping.overlaps(new Dot(new Point(0, 0), radius)));
    }
    
    @Test
    public void overlaps_varying_radius() {
	assertTrue(new Dot(new Point(0, 0), 5).overlaps(new Dot(new Point(11, 0), 20)));
    }
    
    @Test
    public void square_corners_covering_radius_5() {
	Dot d = new Dot(new Point(10, 10), 5);
	HashSet<Point> corners = new HashSet<Point>();
	corners.add(new Point(0, 0));
	corners.add(new Point(0, 10));
	corners.add(new Point(10, 0));
	corners.add(new Point(10, 10));
	assertEquals(corners, d.square_corners_covering());
    }
    
    @Test
    public void negative_coordinates() {
	Dot d = new Dot(new Point(0, 0), 5);
	HashSet<Point> corners = new HashSet<Point>();
	corners.add(new Point(-10, -10));
	corners.add(new Point(0, -10));
	corners.add(new Point(-10, 0));
	corners.add(new Point(0, 0));
	assertEquals(corners, d.square_corners_covering());
    }
    
    @Test
    public void larger_dot() {
	Dot d = new Dot(new Point(0, 0), 15);
	HashSet<Point> corners = new HashSet<Point>();
	corners.add(new Point(-20, -20));
	corners.add(new Point(-20, -10));
	corners.add(new Point(-20, 0));
	corners.add(new Point(-20, 10));
	corners.add(new Point(-10, -20));
	corners.add(new Point(-10, -10));
	corners.add(new Point(-10, 0));
	corners.add(new Point(-10, 10));
	corners.add(new Point(0, -20));
	corners.add(new Point(0, -10));
	corners.add(new Point(0, 0));
	corners.add(new Point(0, 10));
	corners.add(new Point(10, -20));
	corners.add(new Point(10, -10));
	corners.add(new Point(10, 0));
	corners.add(new Point(10, 10));
	assertEquals(corners, d.square_corners_covering());	
    }
}
