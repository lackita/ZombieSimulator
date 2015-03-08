package name.colinwilliams.zombiesimulator;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;

import name.colinwilliams.dotcollision.Dot;

public class Human extends Personality {

    public Human(Dot dot) {
	super(false, dot);
	dot.set_color(Color.BLACK);
    }

    public boolean is_undead() {
	return false;
    }

    public void move(HashSet<Body> nearby) {
        Body closest = find_closest_opponent(nearby);
        if (closest != null)
	    dot.move(-2 * calculate_x_towards(closest), -2 * calculate_y_towards(closest));
        else
            move_randomly();
    }

    public void resolve_collisions(Collection<Body> bodies) {
	if (!bodies.isEmpty()) dot.restore_previous_center();
    }
}
