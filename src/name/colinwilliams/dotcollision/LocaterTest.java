package name.colinwilliams.dotcollision;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class LocaterTest {
    private DotCollider locater;
    @Before
    public void make_locater() {
	locater = new DotCollider();
    }
    
    @Test
    public void placement() {
	Dot existing = new Dot(new Point(0, 0), 5);
	locater.add(existing);
	assertTrue(locater.dots_within(new Dot(new Point(1, 1), 5)).contains(existing));
    }
    
    @Test
    public void corner_case() {
	Dot d = new Dot(new Point(92, 32), 5);
	locater.add(d);
	assertTrue(locater.dots_within(new Dot(new Point(86, 35), 5)).contains(d));
    }
    
    @Test
    public void relocated() {
	Dot d = new Dot(new Point(5, 5), 5);
	locater.add(d);
	d.move(100, 100);
	locater.update_location(d);
	locater.add(new Dot(new Point(105, 105), 5));
	assertFalse(locater.dots_within(d).isEmpty());
    }
}
