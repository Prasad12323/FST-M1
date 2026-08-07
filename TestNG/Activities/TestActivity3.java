package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity3 {
	private WebDriver driver;
	
	@BeforeClass
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/login-form");
	}
	
	
	@Test()
	public void loginTest() {
		WebElement username = driver.findElement(By.id("username"));	
		WebElement password = driver.findElement(By.id("password"));
		
		username.sendKeys("admin");
		password.sendKeys("password");
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		String message = driver.findElement(By.cssSelector("h2.mt-5")).getText();
		
		Assert.assertEquals("Welcome Back, Admin!",message);	
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}
}
