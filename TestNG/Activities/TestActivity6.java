package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestActivity6 {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeClass(alwaysRun=true)
	public void driverSetup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		driver.get("https://training-support.net/webelements/login-form");
	}
	
	
	@Test(priority=0)
	public void homePageTest() {
		String title = driver.getTitle();
		
		Assert.assertEquals("Selenium: Login Form", title);
	}
	
	@Test(dependsOnMethods = {"homePageTest"})
	@Parameters({"username" , "password"})
	public void loginForm(String username, String password) throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.titleContains("Success"));
		
		Assert.assertEquals("Welcome Back, Admin!", driver.findElement(By.cssSelector("h2.mt-5")).getText());
	}

	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
