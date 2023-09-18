package com.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.reports.ExtentLogger;
import com.reports.ExtentReport;

public class ListernerClass implements ITestListener, ISuiteListener {

	/**
	 * Invoked before the SuiteRunner starts and implements the extent report
	 * initialization
	 */
	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invoked after the SuiteRunner has run all the tests in the suite Flush the
	 * report on each iteration
	 */
	@Override
	public void onFinish(ISuite suite) {
		try {
			ExtentReport.flushing();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invoked each time before a test will be invoked Create the method name as a
	 * node in the Extent Report
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());

	}

	/**
	 * Invoked each time a test succeeds and denotes the teststep as passed
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getMethodName() + " is passed", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invoked each time a test fails and print the exception on the report
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ExtentLogger.fail(result.getMethod().getMethodName() + " is failed", true);
			ExtentLogger.fail(result.getThrowable().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Invoked each time a test skips and print the status for the step in report
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped", true);
			ExtentLogger.skip(result.getThrowable().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
