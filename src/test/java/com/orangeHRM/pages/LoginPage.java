package com.orangeHRM.pages;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangeHRM.resouces.Base;

public class LoginPage extends Base{
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(xpath="//input[@id='txtUsername']")
	public WebElement UserName;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	public WebElement Password;
	
	@FindBy(xpath="//input[@id='btnLogin']")
	public WebElement Login;
	
	@FindBy(xpath="//a[@id='welcome']")
	public WebElement welcome;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement logout;
	
	
	public void getLogin() throws IOException
	{
		getDataProperties();
		String UName = prop.getProperty("username");
		String Pswd = prop.getProperty("password");
		UserName.sendKeys(UName);
		Password.sendKeys(Pswd);
		Login.click();
	
	}
	
	public void getLogout() throws InterruptedException
	{
		jsClick(welcome);
		jsClick(logout);
	}

}
