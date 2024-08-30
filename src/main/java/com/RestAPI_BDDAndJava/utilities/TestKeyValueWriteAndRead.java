package com.RestAPI_BDDAndJava.utilities;

//import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

public class TestKeyValueWriteAndRead {

	public static void writetheDataintoFile(String key, String value) {
		// Write key-value pairs to a file
		Properties properties = new Properties();
		properties.setProperty(key, value);

		try (FileOutputStream outputStream = new FileOutputStream("./Configuration/testKeyValue.properties")) {
			properties.store(outputStream, "Configuration Settings");
			System.out.println("Key-value pairs have been written to the file.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String readtheDataFromFile(String pairKey) {
		String pairValue = null;
		// Read key-value pairs from the file
		Properties readProperties = new Properties();

		try (FileInputStream inputStream = new FileInputStream("./Configuration/testKeyValue.properties")) {
			readProperties.load(inputStream);

			pairValue = readProperties.getProperty(pairKey).toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return pairValue;
	}
	
	public static void  removeFiledata(){
		
		 try (FileWriter writer = new FileWriter("./Configuration/testKeyValue.properties", false)) {
	            // Overwrite the file with an empty string
	            writer.write("");
	            System.out.println("All data has been removed from the file.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}


}



