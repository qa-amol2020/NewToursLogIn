package com.DataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class NewUserRegistration extends BaseTest{
		
	@Test(priority=1)
	public void register()
	{
		WebElement register=driver.findElement(By.linkText("REGISTER"));
		register.click();
	}
	
	@Test(priority=2)
	public void newUserRegistration() throws IOException
	{
		FileInputStream file = new FileInputStream
				("./src/com/ApplicationTestDataFiles/NewUserRegistrationTestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheet("Sheet1");
		
		int rowCount=sheet.getLastRowNum();
		
		for(int i=1;i<=rowCount;i++)
		{
			Row row=sheet.getRow(i);
			
		WebElement firstName=driver.findElement(By.name("firstName"));
		firstName.clear();
		firstName.sendKeys(row.getCell(0).getStringCellValue());
		

		WebElement lastName=driver.findElement(By.name("lastName"));
		lastName.clear();
		lastName.sendKeys(row.getCell(1).getStringCellValue());

		// Type Casting
		double d=row.getCell(2).getNumericCellValue();
		long x=(long)d;
		String phoneNumber=Long.toString(x);
		
		WebElement phone=driver.findElement(By.name("phone"));
		phone.clear();
		phone.sendKeys(phoneNumber);

		WebElement emailId=driver.findElement(By.id("userName"));
		emailId.clear();
		emailId.sendKeys(row.getCell(3).getStringCellValue());

		WebElement address1=driver.findElement(By.name("address1"));
		address1.clear();
		address1.sendKeys(row.getCell(4).getStringCellValue());

		WebElement city=driver.findElement(By.name("city"));
		city.clear();
		city.sendKeys(row.getCell(5).getStringCellValue());

		WebElement state=driver.findElement(By.name("state"));
		state.clear();
		state.sendKeys(row.getCell(6).getStringCellValue());

		double p=row.getCell(7).getNumericCellValue();
		long y=(long)p;
		String postalCodeNumber=Long.toString(y);
		
		WebElement postalCode=driver.findElement(By.name("postalCode"));
		postalCode.clear();
		postalCode.sendKeys(postalCodeNumber);

		WebElement country=driver.findElement(By.name("country"));
		country.sendKeys(row.getCell(8).getStringCellValue());

		WebElement UserName=driver.findElement(By.id("email"));
		UserName.clear();
		UserName.sendKeys(row.getCell(9).getStringCellValue());

		WebElement password=driver.findElement(By.name("password"));
		password.clear();
		password.sendKeys(row.getCell(10).getStringCellValue());

		WebElement confirmPassword=driver.findElement(By.name("confirmPassword"));
		confirmPassword.clear();
		confirmPassword.sendKeys(row.getCell(11).getStringCellValue());

		WebElement submit=driver.findElement(By.name("register"));
		submit.click();

		// <b> Note: Your user name is Srinivas.</b>
		
		String expected_UserName=row.getCell(9).getStringCellValue();
		System.out.println(" The expected userName is : "+expected_UserName);
		
		String actual_RegistedUserName=driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b")).getText();
		System.out.println(" The actual UserName text of the Webpage is : "+actual_RegistedUserName);
		
		if(actual_RegistedUserName.contains(expected_UserName))
		{
			System.out.println(" User Registed Successfully - PASS");
			row.createCell(12).setCellValue("User Registed Successfully - PASS");
		}
		else
		{
			System.out.println(" User Registration Failed - FAIL ");
			row.createCell(12).setCellValue("User Registration Failed - FAIL ");
		}
		
		FileOutputStream file1 = new FileOutputStream("./src/com/ApplicationTestResultFiles/NewUserRegistration_TestResults.xlsx");
		workBook.write(file1);
		
		driver.navigate().back();
		
		
		}			
		
	}
	
	

}
