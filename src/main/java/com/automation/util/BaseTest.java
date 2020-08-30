package com.automation.util;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
	
	public static String baseurl="https://reqres.in/";
	@BeforeSuite(alwaysRun=true)
	public void config()
	{
	baseurl=Helper.propertyReader(Helper.commonFilePath, "baseurl");
		
	String subfolderpath= System.getProperty("user.dir")+"/htmlReport/" + com.automation.util.Helper.TimeStamp();
	com.automation.util.Helper.CreateFolder(subfolderpath);
	ExtentReport.initialize(subfolderpath+"/"+"api_automation.html");
	}
	
	@AfterMethod(alwaysRun=true)
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.SUCCESS) {
			ExtentReport.extentlog.log(LogStatus.PASS, "Test case " + result.getName()+" is passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			ExtentReport.extentlog.log(LogStatus.FAIL, "Test case" + result.getName()+ "is failed ");
			ExtentReport.extentlog.log(LogStatus.FAIL, "Test case is failed " + result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is skipped ");
			ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is skipped " + result.getName());
		}
		
		ExtentReport.extentreport.endTest(ExtentReport.extentlog);
	}
	
	@AfterSuite(alwaysRun=true)
	public void endReport()
	{
		ExtentReport.extentreport.flush();
		ExtentReport.extentreport.close();
		
	}

}
