package com.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.constants.FrameworkConstants;

public class ExtentReport {

	public static ExtentReports extent;
	
	/**
	 * Initiating the reports 
	 * @throws Exception 
	 */
	public static void initReport() throws Exception {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("EDC-Automation Test Report");
			spark.config().setCss(".badge-primary {color: #000;background-color: #ffffff;}");
			spark.loadXMLConfig(new File("extentConfig.xml"));
			extent.setSystemInfo("OS Details", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			extent.attachReporter(spark);
		
		}
	}

	/**
	 * Creating test case based on the name of @test annotations methods
	 */
	public static void createTest(String methodname) {
		ExtentManager.setExtentTest(extent.createTest(methodname));
	}
	
    
	

	/**
	 * Flushing the reports the and opening the reports
	 */
	public static void flushing() throws IOException {
		if (Objects.nonNull(extent)) {
			extent.flush();
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
		}

		

	}

}
