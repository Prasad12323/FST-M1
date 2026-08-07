package activities;


import java.io.FileInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestActivity11 {
	private WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
	public void driverSetup() {
		driver = new FirefoxDriver();
		
		driver.get("https://training-support.net/webelements/simple-form");
	}
	
	
	 
	public static List<List<String>> readExcel(String filePath) {
		List<List<String>> data = new ArrayList<>();
 
		Workbook excelFile;
		try {
			excelFile = new XSSFWorkbook(new FileInputStream(filePath));
 
			Sheet sheet1 = excelFile.getSheetAt(0);
 
			for (Row rows : sheet1) {
				if (rows.getRowNum() == 0) {
					continue;
				}
				List<String> rowData = new ArrayList<>();
				for (Cell cells : rows) {
					switch (cells.getCellType()) {
					case STRING:
						rowData.add(cells.getStringCellValue().trim());
						break;
 
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cells)) {
							rowData.add(cells.getLocalDateTimeCellValue()
								.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						} else {
							rowData.add(String.valueOf(cells.getNumericCellValue()));
						}
						break;
					default:
						rowData.add("-");
					}
				}
 
				data.add(rowData);
 
				excelFile.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
 
		return data;
	} 
	
	@DataProvider(name="form-data")
	public static Object[][] formData() throws Exception {
		List<List<String>> excelData = readExcel("src/test/resources/input.xlsx");
	
		Object[][] data = new Object[excelData.size()][];
		for (int i = 0; i < excelData.size(); i++) {
			data[i] = excelData.get(i).toArray();
		}
 
		return data;
	}
	
	@Test(dataProvider="form-data")
	public void form(String[] rows) throws InterruptedException {
		driver.findElement(By.id("full-name")).sendKeys(rows[0]);
		driver.findElement(By.id("email")).sendKeys(rows[1]);
		driver.findElement(By.name("event-date")).sendKeys(rows[2]);
		driver.findElement(By.id("additional-details")).sendKeys(rows[3]);
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		Thread.sleep(1000);
		
		String message = driver.findElement(By.id("action-confirmation")).getText();
		Assert.assertEquals(message, "Your event has been scheduled!");
		
		driver.navigate().refresh();		
	}
	
	@AfterClass(alwaysRun=true)
	public void closeDriver() {
		driver.quit();
	}
}
