package name.colinwilliams.zombiesimulator;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import name.colinwilliams.dotcollision.Dot;

public class Battlefield {
    private Window window;
    private Random positions;
    private int size;
    HashMap<Dot, Body> bodies;

    public Battlefield(int size) {
	window = new Window(size);
	positions = new Random();
	this.size = size;
	bodies = new HashMap<Dot, Body>();
    }

    public void begin() {	
	place_bodies();
        window.open();
        move_bodies();
    }

    private void place_bodies() {
	for(int i = 0;i < size / 4;++i) {
	    add_body(new Body(i % 4 == 0, random_point()));
	}
    }

    private void add_body(Body b) {
	if (window.add_dot(b.get_dot()))
	    bodies.put(b.get_dot(), b);
    }

    private Point random_point() {
	return new Point(positions.nextInt(size - 10) + 5, positions.nextInt(size - 40) + 5);
    }

    private void move_bodies() {
	for (Body body : bodies.values()) {
	    body.move(nearby_bodies(body));
	    Collection<Dot> collisions = window.update_location(body.get_dot());
	    body.resolve_collisions(lookup_colliding_bodies(body, collisions));
	}
	window.refresh();
	move_again();
    }

    private HashSet<Body> lookup_colliding_bodies(Body body, Collection<Dot> collisions) {
	HashSet<Body> colliding_bodies = new HashSet<Body>();
	for (Dot collision : collisions) {
	    colliding_bodies.add(bodies.get(collision));
	}
	return colliding_bodies;
    }

    private HashSet<Body> nearby_bodies(Body b) {
	HashSet<Body> visible = new HashSet<Body>();
	for (Dot d : window.get_dots_within(new Dot(b.get_dot().get_center(), 30))) {
	    visible.add(bodies.get(d));
	}
	return visible;
    }

    private void move_again() {
	try{
	    Thread.sleep(10);
	} catch (Exception exc){}
	move_bodies();
    }
}
