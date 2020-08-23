package com.orangeHRM.pages;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.orangeHRM.resouces.Base;

public class LeavePage extends Base{
	
	public LeavePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="menu_leave_viewLeaveModule")
	public WebElement Leave;
	
	@FindBy(xpath="//input[@class='ac_input']")
	public WebElement value;
	
	@FindBy(id="menu_leave_Entitlements")
	public WebElement Entitlements;
	
	@FindBy(id="menu_leave_addLeaveEntitlement")
	public WebElement AddLeaveEntitlement;
	
	@FindBy(xpath="//input[@class='ac_input inputFormatHint']")
	public WebElement Employee;
	
	@FindBy(xpath="//select[@id='entitlements_leave_type']")
	public WebElement LeaveType;
	
	@FindBy(xpath="//input[@id='entitlements_entitlement']")
	public WebElement EntitlmentText;
	
	@FindBy(id="btnSave")
	public WebElement Save;
	
	@FindBy(id="dialogUpdateEntitlementConfirmBtn")
	public WebElement Confirm;
	
	@FindBy(id="menu_leave_assignLeave")
	public WebElement AssigLeave;
	
	@FindBy(xpath="//select[@id='assignleave_txtLeaveType']")
	public WebElement AssignLeaveType;
	
	@FindBy(id="assignBtn")
	public WebElement Assign;
	
	@FindBy(xpath="//input[@id='assignleave_txtFromDate']")
	public WebElement fromDate;
	
	@FindBy(xpath="//select[@class='ui-datepicker-month']")
	public WebElement monthDropDown;
	
	@FindBy(id="menu_leave_viewLeaveList")
	public WebElement leaveListMenu;
	
	@FindBy(xpath="//input[@id='btnSave']")
	public WebElement save;
	
	@FindBy(id="confirmOkButton")
	public WebElement ok;
	
	@FindBy(id="confirmCancelButton")
	public WebElement cancel;
	
	
	public void getAddLeaveEntitilement() throws InterruptedException, IOException
	{
		getDataProperties();
		String Name = prop.getProperty("EName");
		
		Employee.sendKeys(Name);
		value.sendKeys(Keys.TAB);
		
		Select sel = new Select(LeaveType);
		sel.selectByVisibleText("FMLA US");
		
		EntitlmentText.sendKeys("1.00");
		Save.click();
		Thread.sleep(2000);
		Confirm.click();
	}
	
	
	public void getAssignLeave() throws InterruptedException, IOException
	{
		getDataProperties();
		String Name = prop.getProperty("EName");
		String month = prop.getProperty("fMonth");
		String date = prop.getProperty("Date");
		
		
				
		Employee.sendKeys(Name);
		value.sendKeys(Keys.TAB);
		
		Select sel = new Select(AssignLeaveType);
		List<WebElement> webEle =	sel.getOptions();
		int size = webEle.size();
		
		int randnMumber = ThreadLocalRandom.current().nextInt(1, size);
		sel.selectByIndex(randnMumber);
		
		Thread.sleep(1000);
		String LeBal = driver.findElement(By.xpath("//div[@id='assignleave_leaveBalance']")).getText();
		String LBal = LeBal.replace("view details", "");
		double d = Double.parseDouble(LBal);
		
		fromDate.click();
		Select selMonth = new Select(monthDropDown);
		selMonth.selectByVisibleText(month);
		
		WebElement sDate = driver.findElement(By.xpath("//a[@class='ui-state-default'][text()='"+date+"']"));
		sDate.click();
		
		Thread.sleep(1000);
		Assign.click();
		if(d<=0)
		{
			Thread.sleep(1000);
			//ok.click();  //If Employee still want to apply
			cancel.click();  // If Employee do not want to apply
		}
		
	}
	
	//Canceling Leave request if Leave balance is 0
	public void getCancelingLeave() throws InterruptedException
	{
		int rowCount = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr")).size();
		for(int i=1;i<=rowCount;i++)
		{
			Thread.sleep(1000);
		String leaveBal = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr["+i+"]/td[4]")).getText();
			double leaveBalVal = Double.parseDouble(leaveBal);
			
			if(leaveBalVal<=0)
			{
				WebElement actionDrop = driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr["+i+"]/td[8]//select[@class='select_action quotaSelect']"));
				Select act = new Select(actionDrop);
				act.selectByVisibleText("Cancel");
				save.click();
			}
		}
	}


}
