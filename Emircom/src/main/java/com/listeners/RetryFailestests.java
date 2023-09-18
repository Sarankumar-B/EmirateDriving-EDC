package com.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.testcases.BaseClass;

public class RetryFailestests implements IRetryAnalyzer {

	private int count = 0;
	private int retries = 1;

	@Override
	/**
	 * Rerunning the failed cases based the count inputs
	 * 
	 */
	public boolean retry(ITestResult result) {
		boolean value = false;
		if (BaseClass.getproperty("Rerunfailedcases").equalsIgnoreCase("Yes")) {
			value = count < retries;
			count++;
		}
		return value;
	}

}
