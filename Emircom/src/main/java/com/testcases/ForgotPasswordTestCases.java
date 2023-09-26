package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.Forgetpwd;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.ResetPwd;
import com.pageobjectmodel.VoucherCreation;

public class ForgotPasswordTestCases extends BaseClass {

	/**
	 * Navigating to the forget password page verfication 
	 */
	@Test
	public void verifyingForgetPwdPageNavigationTC06() {
		elementpresence(LogIn.forgetpwd);
		clickElement(LogIn.forgetpwd);
		verifystringURL(getproperty("ForgerPwdURL"));
		elementpresence(Forgetpwd.forgetpwdtext);
		clickElement(Forgetpwd.submitbtn);
		elementpresence(Forgetpwd.pleaseentermailerror);
		pageScreenshot();
		sendKeys(Forgetpwd.emailplaceholder, getproperty("InvalidUsr"));
		clickElement(Forgetpwd.submitbtn);
		elementpresence(Forgetpwd.invalidmailerror);
		pageScreenshot();
		Forgetpwd.emailplaceholder.clear();
		sendKeys(Forgetpwd.emailplaceholder,getproperty("InvalidMailid"));
		clickElement(Forgetpwd.submitbtn);
		elementpresence(Forgetpwd.noaccountserror);
	}

	/**
	 * Verify the error messages of the reset password page
	 */
	@Test
	public void verifyingErrorMsgOfResetPasswordPageTC07() {
		Forgetpwd.gettinglinkforresetpass(props.getProperty("CssMailid"));
		ResetPwd.clickingResetLinkInMailnator(getproperty("MailNatorURL"), getproperty("ValidUsr"));
		switchtochildwindow();
		ResetPwd.resetpwdflow(getproperty("PwNotmatch1"), getproperty("PwNotmatch2"));
		elementpresence(ResetPwd.pwddidnotmatch);
		ResetPwd.resetpwdflow(getproperty("Pwtoocommon1"), getproperty("Pwtoocommon1"));
		elementpresence(ResetPwd.entirelynumeric);
		ResetPwd.resetpwdflow(getproperty("Newpwd1"), getproperty("Newpwd2"));
		elementpresence(ResetPwd.pwdresetcomplete);
		clickElement(ResetPwd.resetloginbtn);
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("Newpwd1"));
	}

	@Test
	/**
	 * Verifying whether user able to reset the password by clicking on forget password
	 */
	public void verifyingResetPasswordflowTC08() {
		Forgetpwd.gettinglinkforresetpass(props.getProperty("CssMailid"));
		ResetPwd.clickingResetLinkInMailnator(getproperty("MailNatorURL"), getproperty("ValidUsr"));
		switchtochildwindow();
		ResetPwd.resetpwdflow(getproperty("OldPwd1"), getproperty("OldPwd2"));
		elementpresence(ResetPwd.pwdresetcomplete);
		clickElement(ResetPwd.resetloginbtn);
		LogIn.loginFlow(props.getProperty("ValidUsr"), props.getProperty("ValidPwd"));
		VoucherCreation.loggedInUserverify(props.getProperty("ValidUsr"));
	}
}
