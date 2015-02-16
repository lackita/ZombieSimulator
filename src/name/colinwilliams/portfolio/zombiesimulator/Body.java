package name.colinwilliams.portfolio.zombiesimulator;

public class Body {

    private boolean undead;

    public Body(boolean undead) {
	this.undead = undead;
    }

    public static Body human() {
	return new Body(false);
    }

    public static Body zombie() {
	return new Body(true);
    }

    public boolean is_undead() {
	return undead;
    }

    public void bite(Body victim) {
	if (victim.is_undead()) undead = true;
	if (is_undead()) victim.undead = true;
    }

}
