package com.orangeHRM.testCases;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.orangeHRM.pages.LeavePage;
import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.resouces.Base;

public class AddLeaveEntitlement extends Base{
	
	LoginPage login;
	LeavePage LePage;
	
	@BeforeClass
	public void homePageNavigation() throws IOException
	{
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.getLogin();
		LePage = new LeavePage(driver);
	}
	
	@Test
	public void addingLeaveEntitlement() throws InterruptedException, IOException
	{
		
		LePage.Leave.click();
		LePage.Entitlements.click();
		LePage.AddLeaveEntitlement.click();
		LePage.getAddLeaveEntitilement();
		
	}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException
	{
		login.getLogout();
		driver.close();
	}

}
