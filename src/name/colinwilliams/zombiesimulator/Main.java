package name.colinwilliams.zombiesimulator;

public class Main {
    public static void main(String[] args) {
	//Configuration cfg = new Configuration(args[0]);
	Battlefield b = new Battlefield(1000);
	b.begin();
    }
}
