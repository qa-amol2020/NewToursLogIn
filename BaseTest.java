package com.DataDriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	public WebDriver driver = null;
	public String url="http://newtours.demoaut.com";
		
	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./driverFiles/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		
		driver.manage().window().maximize();
	//	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
//This is Base Class of Newtours Login Test Class.
