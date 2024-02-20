package com.constants;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.drivermanager.DriverManager;
import com.pageobjectmodel.Forgetpwd;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.Profile;
import com.pageobjectmodel.ReactivateRefund;
import com.pageobjectmodel.ResetPwd;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherDecline;
import com.pageobjectmodel.VoucherList;
import com.pageobjectmodel.VoucherRedeem;
import com.testcases.BaseClass;

public class FrameworkConstants extends BaseClass {

	private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
	private static String extentreportfilepath = "";

	/**
	 * Get the folder path and name for the extent reports and implementing override
	 * reports concepts defined by the user
	 * @throws IOException 
	 */
	public static String getExtentReportFilePath() throws IOException {
		if (extentreportfilepath.isEmpty()) {
			extentreportfilepath = createReportPath();
		}
		return extentreportfilepath;
	}

	/**
	 * Specify the extent report filename as current date and time
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String createReportPath() throws IOException {
		FileReader reader = new FileReader("./src/main/java/com/configproperties/config.properties");
		props = new Properties();
		props.load(reader);
		if (props.getProperty("overridereports").equalsIgnoreCase("no")) {
			return EXTENTREPORTFOLDERPATH + getCurrentDateTime() + "/index.html";
		} else {
			return EXTENTREPORTFOLDERPATH + "/index.html";
		}
	}

	/**
	 * Get the current date and time on the dd_MM_yyyy_HH_mm_ss format and add as
	 * the filename
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		DateFormat filenamedate = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date currentdate = new Date();
		return filenamedate.format(currentdate);
	}
	
	/**
	 * 
	 * Page factory initiation for the driver
	 * 
	 */
	public static void pageFactoryInitiation() {
		VoucherCreation.pagefactoryinit();
		Forgetpwd.pagefactoyinit();
		ResetPwd.pagefactoyinit();
		Profile.pagefactoyinit();
		VoucherList.pagefactoryinit();
		VoucherRedeem.pagefactoryinit();
		VoucherDecline.pagefactoryinit();
		ReactivateRefund.pagefactoyinit();
	}
	
}