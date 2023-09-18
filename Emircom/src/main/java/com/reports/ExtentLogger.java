package com.reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.testcases.BaseClass;

public  class ExtentLogger extends BaseClass {

	/**
	 * Get the message for the passed steps
	 * 
	 * @param message
	 */
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}

	/**
	 * Get the message for the failed steps
	 * 
	 * @param message
	 */
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}

	/**
	 * Get the message info for the steps
	 * 
	 * @param message
	 */

	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}

	/**
	 * Get the message for the skipped steps
	 * 
	 * @param message
	 */
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}

	/**
	 * Get the screenshot for the passed steps
	 * 
	 * @param message
	 * @param isScreenshotNeeded
	 * @throws InterruptedException 
	 * 
	 */
	public static void pass(String message, boolean isScreenshotNeeded)  {
		if (props.getProperty("passedstepsscreenshot").equalsIgnoreCase("yes") && isScreenshotNeeded) {
			ExtentManager.getExtentTest().pass(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
		} else {
			pass(message);
		}
	}

	/**
	 * Get the screenshot for the failed steps
	 * 
	 * @param message
	 * @param isScreenshotNeeded
	 * @throws InterruptedException 
	 * 
	 */
	public static void fail(String message, boolean isScreenshotNeeded)  {
		if (props.getProperty("failedstepsscreenshot").equalsIgnoreCase("yes") && isScreenshotNeeded) {
			ExtentManager.getExtentTest().fail(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
		} else {
			fail(message);
		}
	}

	/**
	 * Get the screenshot for the skipped steps
	 * 
	 * @param message
	 * @param isScreenshotNeeded
	 * @throws Exception
	 */
	public static void skip(String message, boolean isScreenshotNeeded) {
		if (props.getProperty("skippedstepsscreenshot").equalsIgnoreCase("yes") && isScreenshotNeeded) {
			ExtentManager.getExtentTest().skip(message,
					MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
		} else {
			skip(message);
		}
	}

	/**
	 * Method to get screenshot in BASE64 format
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static String getBase64Image() {
		sleeptime3sec();
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
}
