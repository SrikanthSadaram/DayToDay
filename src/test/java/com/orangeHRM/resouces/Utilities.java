package com.orangeHRM.resouces;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {
	
	public Properties prop;
	
	public void getDataProperties() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src\\main\\java\\data.properties");
		
		prop.load(fis);
	}

}
