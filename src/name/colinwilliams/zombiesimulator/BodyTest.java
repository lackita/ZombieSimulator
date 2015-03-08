package name.colinwilliams.zombiesimulator;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;

import org.junit.Test;
import org.junit.Before;

public class BodyTest {
    Body human;
    Body zombie;
    
    @Before
    public void create_mob() {
	human = new Body(false, new Point(0, 0));
	zombie = new Body(true, new Point(0, 0));
    }
    
    @Test
    public void undead() {
	assertTrue(zombie.is_undead());
	assertFalse(human.is_undead());	
    }
    
    @Test
    public void zombie_bites_human() {
	human.bite();
	assertTrue(human.is_undead());
	assertEquals(human.get_dot().get_color(), new Color(55, 122, 31));
    }
    
    @Test
    public void human_resolves_no_collisions() {
	human.get_dot().move(1, 1);
	human.resolve_collisions(new HashSet<Body>());
	assertNotEquals(human.get_dot().get_center(), new Point(0, 0));
    }
    
    @Test
    public void zombie_resolves_no_collisions() {
	zombie.get_dot().move(1, 1);
	zombie.resolve_collisions(new HashSet<Body>());
	assertNotEquals(zombie.get_dot().get_center(), new Point(0, 0));
    }
}
