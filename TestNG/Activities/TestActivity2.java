package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity2 {
	private WebDriver driver;
	
	@BeforeClass
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/target-practice");
	}
	
	
	@Test(priority=0)
	public void testCase1() {
		String title = driver.getTitle();
		
		System.out.print(title);
		
		Assert.assertEquals("Selenium: Target Practice", title);
	}
	
	@Test()
	public void testCase2() {
		WebElement blackbtn  = driver.findElement(By.cssSelector("buttom.black"));
		Assert.assertTrue(blackbtn.isDisplayed());
	}
	
	@Test(enabled=false)
	public void testCase3() {
		WebElement amberBtn =  driver.findElement(By.xpath("//button[text()='Amber']"));
		
		Assert.assertTrue(amberBtn.isEnabled());
	}
	
	
	@Test()
	public void testCase4() {
		throw new SkipException("Test Skipped!");
	}

	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
}
