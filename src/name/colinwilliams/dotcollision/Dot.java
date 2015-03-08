package name.colinwilliams.dotcollision;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;

public class Dot {
    private Point center;
    private int radius;
    private Color color;
    private Dot previous;

    public Dot(Point center, int radius, Color color) {
	this(center, radius);
	this.color = color;
    }

    public Dot(Point center, int radius) {
	this.center = center;
	this.radius = radius;
	color = Color.BLACK;
	previous = this;
    }

    public boolean equals(Object other) {
	Point other_center = ((Dot) other).get_center();
	return center.x == other_center.x && center.y == other_center.y;
    }
    
    public boolean overlaps(Dot other_dot) {
	return center.distance(other_dot.get_center()) < radius + other_dot.get_radius();
    }

    public Point get_center() {
	return center;
    }
    
    public void move(int delta_x, int delta_y) {
	previous = new Dot((Point) center.clone(), radius, color);
	center.x += delta_x;
	center.y += delta_y;
    }

    public int get_radius() {
	return radius;
    }
    
    public HashSet<Point> square_corners_covering() {
	HashSet<Point> corners = new HashSet<Point>();
	Point top_left_corner = calculate_containing_square_corner(new Point(center.x - radius, center.y - radius));
	for (int x = top_left_corner.x;x <= center.x + radius;x += 10) {
	    for (int y = top_left_corner.y;y <= center.y + radius;y += 10) {
		corners.add(square_corner_containing(new Point(x, y)));
	    }
	}
	
	return corners;
    }

    private Point square_corner_containing(Point p) {
	Point square_corner = new Point(p.x, p.y);
	if (p.x < 0 && p.x % 10 != 0) square_corner.x -= 10;
	if (p.y < 0 && p.y % 10 != 0) square_corner.y -= 10;
	square_corner.x += Math.abs(square_corner.x % 10);
	square_corner.y += Math.abs(square_corner.y % 10);
	
	return square_corner;
    }

    private Point calculate_containing_square_corner(Point point) {
	return new Point(point.x - Math.abs(point.x % 10), point.y - Math.abs(point.y % 10));
    }

    public Color get_color() {
	return color;
    }

    public Dot previous_dot() {
	return previous;
    }

    public void set_color(Color color) {
	this.color = color;
    }

    public void restore_previous_center() {
	center.x = previous.center.x;
	center.y = previous.center.y;
    }
}
