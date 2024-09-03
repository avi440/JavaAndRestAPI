package com.RestAPI_BDDAndJava.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class ReadConfigAPI {
	public static Properties prop;
	
	public ReadConfigAPI(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./Configuration/configAPI.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String baseURL() {
		String url = prop.getProperty("baseURLAPI");
		return url;
	}
	
	public static String demoGetPostRequest() {
		String tire = prop.getProperty("demoGetpostRequest");
		return tire;
	}

	public static String postcallingRequest() {
		String postcalling = prop.getProperty("postcalling");
		return postcalling;
	}
	
	public static String schemaVerifiying() {
		String browser = prop.getProperty("schematesting");
		return browser;
	}

}
