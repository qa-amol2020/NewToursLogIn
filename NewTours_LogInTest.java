package com.DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewTours_LogInTest extends BaseTest {
			
	@Test
	public void logIn() throws IOException
	{
		FileInputStream file = new FileInputStream
				("./src/com/ApplicationTestDataFiles/NewTours_LogInTestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		
		int rowCount=sheet.getLastRowNum();
		
		for(int i=1;i<=rowCount;i++)
		
		{
		
			Row row=sheet.getRow(i);
		
		// <input type="text" name="userName" size="10">
		WebElement userName=driver.findElement(By.name("userName"));
		userName.clear();
		userName.sendKeys(row.getCell(0).getStringCellValue());
		
		
		// <input type="password" name="password" size="10">
		WebElement password=driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys(row.getCell(1).getStringCellValue());
		
		WebElement signIn=driver.findElement(By.name("login"));
		signIn.click();
				
		String expected_HomePageTitle="Find";
		System.out.println(" The expected title is : "+expected_HomePageTitle);
		
		String actual_WebPageTitle=driver.getTitle();
		System.out.println(" The actual Title is : "+actual_WebPageTitle);
		
		
		if(actual_WebPageTitle.contains(expected_HomePageTitle))
		{
			System.out.println(" LogIN Successfull - PASS");
			row.createCell(2).setCellValue("LogIN Successfull - PASS");
			
		}
		else
		{
			System.out.println(" LogIn Failed - FAIL");
			row.createCell(2).setCellValue("LogIn Failed - FAIL");
		}
		
FileOutputStream file1 = new FileOutputStream
	("./src/com/ApplicationTestResultFiles/NewTours_LogInTestResults.xlsx");
		workBook.write(file1);
		
		driver.navigate().back();
		//driver.navigate().refresh();
	}	
			
		
	}
	
	
	
	
	
	

}
