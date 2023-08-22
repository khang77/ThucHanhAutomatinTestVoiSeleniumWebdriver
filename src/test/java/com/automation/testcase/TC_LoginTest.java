package com.automation.testcase;

import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenShot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance{

	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException {
		String URL = PropertiesFileUtils.getProperty("base_URL");
		System.out.println(URL);
	
		driver.get(URL);
		System.out.println("OPEN WEB");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			
		String signInLocated = PropertiesFileUtils.getProperty("iconSignIn_xpath");
		WebElement iconSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signInLocated)));	
		
		// DAM BAO NGUOI DUNG CHUA DANG NHAP TAI KHOAN
		assertEquals(true, iconSignIn.isDisplayed(),"icon SignIn is not displayed");
		
		iconSignIn.click();
		System.out.println("CLICK ICON SIGNUP/LOGIN");
		LoginPage loginPage = new LoginPage(driver);
		PageFactory.initElements(driver, loginPage);
			
		loginPage.enterEmail(email);
		System.out.println("ENTER EMAIL");
		loginPage.enterPassword(password);
		System.out.println("ENTER PASS");
		loginPage.clickLogin();
		System.out.println("CLICK LOGIN");
		
		try {
		String signOutLocated = PropertiesFileUtils.getProperty("iconSignOut_xpath");
		WebElement iconSignOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(signOutLocated)));
		
		// DAM BAO DANG NHAP THANH CONG VOI TAI KHOAN TREN
		assertEquals(true, iconSignOut.isDisplayed(),"icon SignOut is not displayed");
		
		if (iconSignOut.isDisplayed()) {
			iconSignOut.click();
	    } 
		
		System.out.println("SIGN OUT");
		Thread.sleep(2000);
		
		} catch (Exception e) {
		    System.out.println("Exception");
		    e.printStackTrace();
		    Assert.fail();
		}
	}
	
	@DataProvider(name="Excel")
	public Object [][] testDataGenerator() throws Exception
	{
//		test data OK
		FileInputStream file = new FileInputStream(PropertiesFileUtils.getProperty("DataTest"));
		
//		test data ERROR
//		FileInputStream file = new FileInputStream(PropertiesFileUtils.getProperty("DataTestError"));
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet loginSheet = workbook.getSheet("Login");
		int numberOfRowData = loginSheet.getPhysicalNumberOfRows();
		
		Object[][] data = new Object[numberOfRowData][2];
		for(int i = 0; i < numberOfRowData; i++ ) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = username.getStringCellValue();
			data[i][1] = password.getStringCellValue();
		}
		workbook.close();
		return data;
	}
	
	@AfterMethod
	public void takeScreenShot(ITestResult result) throws InterruptedException {
		Thread.sleep(1000);
		
		if(ITestResult.FAILURE == result.getStatus()) {
			try {
				String email = (String)result.getParameters()[0];
				
				  int index = email.indexOf('@');
	                String name = email.substring(0, index);

				CaptureScreenShot.takeScreenShot(driver, name);
				System.out.println("Đã chụp màn hình: " + name);
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot" + e.getMessage());
			}
		}
	}
}
