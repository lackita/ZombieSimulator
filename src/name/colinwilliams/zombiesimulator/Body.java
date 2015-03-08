package name.colinwilliams.zombiesimulator;

import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;

import name.colinwilliams.dotcollision.Dot;

public class Body {
    Personality personality;

    public Body(boolean undead, Point center) {
	this(undead, new Dot(center, 5));
    }
    
    public Body(boolean undead, Dot dot) {
	if (undead)
	    personality = new Zombie(dot);
	else
	    personality = new Human(dot);
    }

    public boolean is_undead() {
	return personality.undead;
    }

    public Dot get_dot() {
	return personality.dot;
    }

    public void resolve_collisions(Collection<Body> collisions) {
	personality.resolve_collisions(collisions);
    }

    public void move(HashSet<Body> nearby) {
	personality.move(nearby);
    }

    public void bite() {
	personality = new Zombie(personality.dot);
    }
}
