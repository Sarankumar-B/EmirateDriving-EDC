package com.configproperties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.drivermanager.Driver;

public class GetProperties extends Driver {

	
	/**
	 * Fecthing the string texts form the property files
	 * 
	 */
	public static void gettingProperties() throws IOException {
		FileReader reader = new FileReader("./src/main/java/com/configproperties/config.properties");
		props = new Properties();
		props.load(reader);
	}
}
