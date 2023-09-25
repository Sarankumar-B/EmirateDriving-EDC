package com.testcases;

import static org.testng.Assert.fail;

import java.io.File;
import org.testng.annotations.Test;

import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherList;
import com.reports.ExtentLogger;

public class DemoClass extends BaseClass {

	static boolean isFileFound;

	@Test
	public static void fetch() {

		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		// Trigger the download action (e.g., click a download link)
		VoucherList.exportcsv.click();
		String timeStamp = VoucherList.timeStamp();
		sleeptime();
		// Verify if a file with a partial name exists in the download directory
		String partialFileName = "voucher_list_" + timeStamp; // Replace with the desired partial name
		long startTime = System.currentTimeMillis();
		long twoMinutesInMillis = 2 * 60 * 1000; // Two minutes in milliseconds
		while (!isFileFound) {
			isFileWithPartialNameDownloaded(partialFileName);
			if (isFileFound) {
				ExtentLogger.pass("File with partial name found.");
			}
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime >= twoMinutesInMillis) {
				ExtentLogger.pass("File with partial name not found.");
				fail();
			}
		}
	}
	
	private static void isFileWithPartialNameDownloaded(String partialFileName) {
		File downloadDirectory = new File(System.getProperty("user.dir")+getproperty("Download")); // Set your download directory
		System.out.println(getproperty("Download"));
		File[] downloadedFiles = downloadDirectory.listFiles();
		sleeptime();
		for (File file : downloadedFiles) {
			System.out.println(partialFileName);
			System.out.println(file.getName());
			if (file.getName().contains(partialFileName)) {
				boolean delete = file.delete();
				System.out.println(delete);
				isFileFound = true;
			}
		}
	}
}
