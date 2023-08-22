package com.automation.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class DriverInstance {

	protected WebDriver driver;
	
	@BeforeClass
	public void initDriverInstance() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		driver.manage().window().maximize();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
	@AfterClass
	public void closeDriverInstance() {
		driver.close();
	}
}
