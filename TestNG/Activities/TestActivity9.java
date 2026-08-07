package activities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity9 {
	private WebDriver driver;
	private Alert alert;
	
	@BeforeClass(alwaysRun=true)
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/alerts");
        Reporter.log("Opened Browser |");
        
        Reporter.log("Title of page: " + driver.getTitle() + "|");
	}
	
	@BeforeClass(alwaysRun=true)
	public void switchContext() {
        Reporter.log("Test Case Setup started |");
		driver.switchTo().defaultContent();
	}
	
	@Test()
	public void simpleAlertTestCase() {
        Reporter.log("simpleAlertTestCase started |");
        
		driver.findElement(By.id("simple")).click();
		Reporter.log("Simple Alert opened |");
		
		alert = driver.switchTo().alert();
		Reporter.log("Switched foucs to alert |");
		
		Reporter.log("Text in simple alert is: " + alert.getText() + "|");
		
		alert.accept();
		Reporter.log("Alert accepeted |");
		
		Assert.assertEquals("You just accepted a simple alert!", driver.findElement(By.id("result")).getText());	
		
		Reporter.log("Test Case completed |");
	}
	
	@Test()
	public void confirmAlertTestCase() {
        Reporter.log("confirmAlertTestCase started |");
		
		driver.findElement(By.id("confirmation")).click();
		Reporter.log("Confirm Alert opened |");
		
		alert = driver.switchTo().alert();
		Reporter.log("Switched foucs to alert |");
		
		Reporter.log("Text in confirm alert is: " + alert.getText() + "|");
		
		alert.dismiss();
		Reporter.log("Alert dismissed |");
		
		Assert.assertEquals("You just dismissed a confirmation alert!" , driver.findElement(By.id("result")).getText());	
		
		Reporter.log("Test Case completed |");
	}
	
	@Test()
	public void promptAlertTestCase() {
        Reporter.log("promptAlertTestCase started |");

		driver.findElement(By.id("prompt")).click();
		Reporter.log("Prompt Alert opened |");

		
		alert = driver.switchTo().alert();
		Reporter.log("Switched foucs to alert |");
		
		Reporter.log("Text in prompt alert is: " + alert.getText() + "|");
		
		alert.sendKeys("TestNG");
		Reporter.log("Typing TestNG in prompt alert |");
		
		alert.accept();
				
		Assert.assertEquals("You typed \"TestNG\" into the prompt!", driver.findElement(By.id("result")).getText());	
		
		Reporter.log("Test Case completed |");
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
