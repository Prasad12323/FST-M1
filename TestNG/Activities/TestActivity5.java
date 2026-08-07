package activities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity5 {
	private WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/target-practice");
	}
	
	
	@Test(groups= {"ButtonTests","HeaderTests"})
	public void pageTitleTest() {
		String title = driver.getTitle();
		
		Assert.assertEquals("Selenium: Target Practice", title);
	}
	
	@Test(dependsOnMethods="pageTitleTest", groups="HeaderTests")
	public void headerTest1() {
		WebElement heading3 = driver.findElement(By.xpath("//h3[contains(text(),'#3')]"));
		
		Assert.assertEquals("Heading #3",heading3.getText());
	}
	
	@Test(dependsOnMethods="pageTitleTest", groups="HeaderTests")
	public void headerTest2() {
		Color heading3 = Color.fromString(driver.findElement(By.xpath("//h5[contains(@class,'purple')]")).getCssValue("color"));
		
		Assert.assertEquals("#9333ea",heading3.asHex());
	}
	
	@Test(dependsOnMethods="pageTitleTest", groups="ButtonTests")
	public void buttonTest1() {
		WebElement emeraldBtn = driver.findElement(By.xpath("//button[contains(@class,'emerald')]"));
		
		Assert.assertEquals("Emerald",emeraldBtn.getText());
	}
	
	@Test(dependsOnMethods="pageTitleTest", groups="ButtonTests")	
	public void buttonTest2() {
		Color fivthRowBtn = Color.fromString(driver.findElement(By.xpath("//button[contains(text(),'Purple')]")).getCssValue("color"));
		
		Assert.assertEquals("#581c87",fivthRowBtn.asHex());
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
