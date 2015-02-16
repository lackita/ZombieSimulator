package name.colinwilliams.portfolio.zombiesimulator;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class BodyTest {
    Body human;
    Body zombie;
    
    @Before
    public void create_mob() {
	human = Body.human();
	zombie = Body.zombie();
    }
    
    @Test
    public void undead() {
	assertTrue(zombie.is_undead());
	assertFalse(human.is_undead());	
    }
    
    @Test
    public void zombie_bites_human() {
	zombie.bite(human);
	assertTrue(human.is_undead());
    }
    
    @Test
    public void human_bites_zombie() {
	human.bite(zombie);
	assertTrue(human.is_undead());
    }
    
    @Test
    public void human_bites_human() {
	Body other_human = Body.human();
	human.bite(other_human);
	assertFalse(human.is_undead());
	assertFalse(other_human.is_undead());
    }
}
