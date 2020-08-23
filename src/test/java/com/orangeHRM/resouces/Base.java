package com.orangeHRM.resouces;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base extends Utilities {
	
	public WebDriver driver;
	public WebDriver initializeDriver() throws IOException
	{
		getDataProperties();
		
		String BName = prop.getProperty("browser");
		String Url = prop.getProperty("url");
		
		if(BName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "src\\main\\java\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (BName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "src\\main\\java\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else 
		{
			System.out.println("Browser selection is not valid");
		}
		
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

}
