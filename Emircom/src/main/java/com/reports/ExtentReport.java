package com.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports extent;
	public static ExtentTest logger;

	
	/**
	 * Initiating the reports 
	 * @throws IOException 
	 */
	public static void initReport() throws IOException {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("EDC-Automation Test Report");
			spark.config().setReportName("Emirates Driving Company");
			spark.config().setCss(".badge-primary{background-color:#08111c;}");
			
			spark.loadXMLConfig(new File("src/test/resources/config.xml"));
			extent.setSystemInfo("OS Details", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			
			extent.attachReporter(spark);
		
		}
	}

	/**
	 * Creating test case based on the name of @test annotations methods
	 */
	public static void createTest(String methodname) {

		logger = extent.createTest(methodname);
		ExtentManager.setExtentTest(logger);
	}
	
    
	

	/**
	 * Flushing the reports the and opening the reports
	 */
	public static void flushing() throws IOException {
		if (Objects.nonNull(extent)) {
			extent.flush();
			Desktop.getDesktop().browse(new File("index.html").toURI());
		}

		

	}

}
