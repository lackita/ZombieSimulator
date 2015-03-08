package name.colinwilliams.zombiesimulator;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;

import name.colinwilliams.dotcollision.Dot;

public class Zombie extends Personality {

    public Zombie(Dot dot) {
	super(true, dot);
	dot.set_color(new Color(55, 122, 31));
    }

    public boolean is_undead() {
	return true;
    }

    public void move(HashSet<Body> nearby) {
        Body closest = find_closest_opponent(nearby);
        if (closest != null)
	    dot.move(calculate_x_towards(closest), calculate_y_towards(closest));
	else
            move_randomly();
    }

    public void resolve_collisions(Collection<Body> bodies) {
	for (Body b : bodies)
	    if (!b.is_undead())
		b.bite();
	if (!bodies.isEmpty()) dot.restore_previous_center();
    }
}
