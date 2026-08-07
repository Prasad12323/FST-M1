package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity1 {
	private WebDriver driver;
	
	@BeforeClass
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/");
	}
	
	
	@Test(priority=0)
	public void homePageTest() {
		String title = driver.getTitle();
		
		Assert.assertEquals("Training Support", title);
		
		driver.findElement(By.linkText("About Us")).click();
	}
	
	@Test(dependsOnMethods = {"homePageTest"})
	public void aboutUsTest() {
		String pageTitle = driver.getTitle();
		
		System.out.println("Title of new page is: " + pageTitle);
		
		Assert.assertEquals("About Training Support", pageTitle);
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
}
