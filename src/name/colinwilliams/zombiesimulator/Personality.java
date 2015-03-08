package name.colinwilliams.zombiesimulator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import name.colinwilliams.dotcollision.Dot;

public abstract class Personality {

    protected Dot dot;
    private Random move_generator;

    public Personality(boolean undead, Dot dot) {
	this.dot = dot;
	move_generator = new Random();
    }

    public abstract boolean is_undead();
    public abstract void move(HashSet<Body> nearby);
    public abstract void resolve_collisions(Collection<Body> collisions);

    protected Body find_closest_opponent(HashSet<Body> nearby) {
        double best_distance = Double.MAX_VALUE;
        Body closest = null;
        for (Body candidate : nearby) {
            if (candidate.is_undead() != is_undead() && dot.get_center().distance(candidate.get_dot().get_center()) < best_distance) {
        	closest = candidate;
        	best_distance = dot.get_center().distance(candidate.get_dot().get_center()); 
            }
        }
        return closest;
    }

    protected void move_randomly() {
        dot.move(random_direction(), random_direction());
    }

    private int random_direction() {
        return move_generator.nextInt(3) - 1;
    }

    protected int calculate_x_towards(Body closest) {
	return calculate_axis_distance(dot.get_center().x, closest.get_dot().get_center().x);
    }

    protected int calculate_y_towards(Body closest) {
	return calculate_axis_distance(dot.get_center().y, closest.get_dot().get_center().y);
    }
    
    private int calculate_axis_distance(int origin, int direction) {
	int distance;
	if (origin < direction)
            distance = 1;
        else if (origin > direction)
            distance = -1;
        else
            distance = 0;
	return distance;
    }
}
