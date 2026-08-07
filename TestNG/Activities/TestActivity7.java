package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestActivity7 {
	private WebDriver driver;
		
	@BeforeClass(alwaysRun=true)
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/login-form");
	}
	
	
	@Test(priority=0)
	public void homePageTest() {
		String title = driver.getTitle();
		
		Assert.assertEquals("Selenium: Login Form", title);
	}
	
	
	@DataProvider(name="Credentials")
	public static Object[][] Credential(){
		return new Object[][] {
			{"admin1" , "password2"},
			{"admin" , "password"}
		};
	}

	@Test(dependsOnMethods = {"homePageTest"} , dataProvider="Credentials")
	public void loginForm(String username, String password) throws InterruptedException {
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = 		driver.findElement(By.id("password"));
		
		usernameField.clear();
		passwordField.clear();
		
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		Thread.sleep(1000);
				
		Assert.assertEquals("Welcome Back, Admin!", driver.findElement(By.cssSelector("h2.mt-5")).getText());
	}

	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
