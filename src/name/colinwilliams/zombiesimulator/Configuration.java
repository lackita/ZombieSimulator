package name.colinwilliams.zombiesimulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    Properties data;
    public Configuration(String file) {
	data = new Properties();
	load_file(file);
    }

    private void load_file(String file) {
	try {
	    data.load(new FileInputStream(file));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public int battlefield_height() {
	return Integer.parseInt(data.getProperty("BATTLEFIELD_HEIGHT"));
    }

    public int battlefield_width() {
	return Integer.parseInt(data.getProperty("BATTLEFIELD_WIDTH"));
    }
    
    public int body_diameter() {
	return Integer.parseInt(data.getProperty("BODY_DIAMETER"));
    }
}
