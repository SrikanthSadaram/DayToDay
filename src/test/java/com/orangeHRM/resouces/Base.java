package com.orangeHRM.resouces;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	
	public void getScreenShot(String Name) throws IOException
	{
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH-mm-ss");  
		   LocalDateTime now = LocalDateTime.now();  
		  String time = dtf.format(now);
		   System.out.println(time); 
		String Destination = System.getProperty("user.dir")+"\\Screenshots\\" + Name + time + ".png";
		FileUtils.copyFile(source, new File(Destination));
	}
	
	public void ExplictWait(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(Ele));
	}
	
	public void jsClick(WebElement EleName)
	{
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].click();", EleName); 
		
	}

}
