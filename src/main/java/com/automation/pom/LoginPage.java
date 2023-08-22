package com.automation.pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.utils.CaptureScreenShot;
import com.automation.utils.PropertiesFileUtils;

public class LoginPage {
private WebDriver driver;
private WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void enterEmail(String email) throws InterruptedException {
		try {
			String emailLocated = PropertiesFileUtils.getProperty("emailTextbox_xpath");
			WebElement emailTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailLocated)));
			emailTextbox.sendKeys(email);
				Thread.sleep(2000);
		} catch(Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
			CaptureScreenShot.takeScreenShot(driver, "enterEmailFailed");
			Assert.fail();
		}
	
	}

	public void enterPassword(String password) throws InterruptedException {
		try {
			String passLocated = PropertiesFileUtils.getProperty("passTextbox_name");
			WebElement passTextbox = driver.findElement(By.name(passLocated));
			passTextbox.sendKeys(password);
				Thread.sleep(2000);
		}catch(Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
			CaptureScreenShot.takeScreenShot(driver, "enterPasswordFailed");
			Assert.fail();
		}
		
	}
	
	public void clickLogin() throws InterruptedException {
		try {
			String btnLoginLocated = PropertiesFileUtils.getProperty("btnLogin_xpath");
			WebElement btnLogin = driver.findElement(By.xpath(btnLoginLocated));
			btnLogin.click();
				Thread.sleep(2000);
		}catch(Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
			CaptureScreenShot.takeScreenShot(driver, "clickLoginFailed");
			Assert.fail();
		}		
	}
}
