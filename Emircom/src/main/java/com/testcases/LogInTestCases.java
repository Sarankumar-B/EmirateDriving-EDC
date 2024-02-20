package com.testcases;

import org.testng.annotations.Test;

import com.drivermanager.DriverManager;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;

public class LogInTestCases extends BaseClass {

	LogIn li = new LogIn(DriverManager.getDriver());

	@Test
	/**
	 * Verifying whether the entered url is landing in home page
	 */
	public void pageLandingOnHomepageAndUrlVerificationTC01() {
		browserDetails();
		verifystringURL(getproperty("LoginURL"));
	}

	@Test
	/**
	 * Verifying the core elements are present in the login Page
	 */
	public void verifyingLogInPageElementsTC02() {

		li.verifyingcoreelementspresence();
	}

	/**
	 * Verifying the error messages for login page eg: "Invalid mail Id"
	 */
	@Test
	public void verifyingLoginPageErrorMsgTC03() {
		li.logInPageErrosMsgsValidations(getproperty("ValidUsr"));
	}

	/**
	 * Verifying the login flow with valid credentials
	 */
	@Test
	public void verifyingLoginwithvalidcredentialsTC04() {
		li.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.loggedInUserverify(props.getProperty("ValidUsr"));

	}

	/**
	 * Verifying the error messages for invalid credetials
	 */
	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void verifyingInvalidCredentialerrormsgTC05(String user, String pass) {
		li.loginFlow(user, pass);
		elementpresence(li.invalidcredentialserrormsg);
	}

}
