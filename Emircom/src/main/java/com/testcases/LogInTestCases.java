package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;

public class LogInTestCases extends BaseClass {

	
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
		LogIn.verifyingcoreelementspresence();
	}

	/**
	 * Verifying the error messages for login page eg: "Invalid mail Id"
	 */
	@Test
	public void verifyingLoginPageErrorMsgTC03() {
		LogIn.logInPageErrosMsgsValidations(getproperty("ValidUsr"));
	}

	/**
	 * Verifying the login flow with valid credentials
	 */
	@Test
	public void verifyingLoginwithvalidcredentialsTC04() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.loggedInUserverify(props.getProperty("ValidUsr"));

	}

	/**
	 * Verifying the error messages for invalid credetials
	 */
	@Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
	public void verifyingInvalidCredentialerrormsgTC05(String user, String pass) {
		LogIn.loginFlow(user, pass);
		elementpresence(LogIn.invalidcredentialserrormsg);
	}
}
