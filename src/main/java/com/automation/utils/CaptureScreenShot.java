package com.automation.utils;


import java.io.File;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CaptureScreenShot {
	static String fileName = null;
	
	String name = null;
	public static void takeScreenShot(WebDriver driver, String name ) {
		
		try {
			File theDir = new File("./screenshots/");
			if(!theDir.exists()) {
				theDir.mkdirs();
			}
		
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			fileName = name+".png";
			File source = screenshot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("screenshots/" + fileName);
			FileUtils.copyFile(source, destinationFile);
			
			String attachName = destinationFile.toString();
			attachScreenshotToReport(attachName);
		} catch (Exception e) {
			System.out.println("Đã xảy ra lỗi khi chụp màn hình");
			e.printStackTrace();
		}
	}
	
	public static void attachScreenshotToReport(String filePath) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			File f = new File(filePath);
			Reporter.log(
					"<br><a title = \"ScreenShots\" href=\" " + f.getAbsolutePath()+"\">"+
			"<img alt='"+f.getName()+"'src='"+f+"'height='243'ưidth='418'</a><br>");	
		}catch(Exception e) {
			System.out.println("Đã xảy ra lỗi khi đính kèm screenshot vào báo cáo");
			e.printStackTrace();
		}
	}
}