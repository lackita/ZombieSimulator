package name.colinwilliams.zombiesimulator;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import name.colinwilliams.dotcollision.Dot;
import name.colinwilliams.dotcollision.DotCollider;

public class Window {
    private DotCollider dots;
    private int size;
    private JFrame frame;
    public Window(int size) {
	dots = new DotCollider();
	this.size = size;
    }

    public void open() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                build_frame();
            }
        });
    }

    public boolean add_dot(Dot d) {
	if (d.get_center().x > 0 && d.get_center().y > 0 && dots.dots_within(d).isEmpty()) {
	    dots.add(d);
	    return true;
	}
	return false;
    }
    
    private void build_frame() {
	frame = new JFrame("Zombie Simulator");
	frame.setSize(size, size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new Panel());
    }

    public void refresh() {
	if (frame != null)
	    frame.repaint();
    }

    private class Panel extends JPanel {
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    synchronized(dots) {
		for (Dot d : dots) {
		    Point c = d.get_center();
		    int r = d.get_radius();
		    g.setColor(d.get_color());
		    g.fillOval(c.x - r, c.y - r, 2 * r, 2 * r);
		}
	    }
	}
    }

    public Collection<Dot> update_location(Dot d) {
	synchronized(dots) {
	    dots.update_location(d);
	    return dots.get_collisions(d);
	}
    }

    public HashSet<Dot> get_dots_within(Dot dot) {
	return dots.dots_within(dot);
    }
}
