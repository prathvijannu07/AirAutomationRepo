package com.testMaven;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class TestMaven {
	WebDriver driver;
	ExtentReports  reports = new ExtentReports();
	ExtentSparkReporter spark= new ExtentSparkReporter("target\\ExtentReport.html");

	@BeforeTest
	public void invock() {
		
		 System.setProperty("webdriver.gecko.driver", "D:\\GitRepo\\TestMavenProject\\TestProject\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://tickets.paytm.com/");
//		ExtentReports reports = new ExtentReports();
		
		reports.attachReporter(spark);
		reports.flush();
		
		
		
	}
	@Test
	public void checkElement() throws Exception {
		WebElement Way1=driver.findElement(By.id("ow"));
		if(Way1.isDisplayed())	
		reports.attachReporter(spark);
		reports.createTest("AirAutomation_Test_Report").log(Status.PASS, "OnWay is displayed");
		takeSnapShot(driver,"test-output\\test.png");
		reports.flush();
	}
	
	@AfterTest
	public void	tearDown() {
	driver.close();	
	}
	
	public  void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileHandler.copy(SrcFile, DestFile);
		}

}
