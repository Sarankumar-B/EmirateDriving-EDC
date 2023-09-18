package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.testcases.BaseClass;

public class LogIn extends BaseClass {

	public static void pagefactoyinit() {
		PageFactory.initElements(driver, LogIn.class);
	}

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	public static WebElement loginbtn;

	@FindBy(xpath = "//input[@placeholder='Enter your username here']")
	public static WebElement usernameplaceholder;

	@FindBy(xpath = "//input[@placeholder='Enter your password here']")
	public static WebElement passwordplaceholder;

	@FindBy(xpath = "//div[@class='login-voucher']")
	public static WebElement voucherimg;

	@FindBy(xpath = "//div[@class='row justify-content-end align-items-center']")
	public static WebElement languageimg;

	@FindBy(xpath = "//label[text()='Please enter username']")
	public static WebElement usernameerrormsg;

	@FindBy(xpath = "//label[text()='Please enter Password']")
	public static WebElement pwderrormsg;

	@FindBy(xpath = "//label[@class='server_error_label']")
	public static WebElement invalidcredentialserrormsg;

	@FindBy(xpath = "//a[text()='Forget Password?']")
	public static WebElement forgetpwd;

	/**
	 * Enter emailId, password and clicking on logIn button
	 * 
	 * @param username Enter user name here
	 * @param pass     Enter password here
	 */
	public static void loginFlow(String username, String pass) {
		sendKeys(usernameplaceholder, username);
		sendKeys(passwordplaceholder, pass);
		clickElement(loginbtn);

	}

	/**
	 * Verifying the elements are present in the landing page or not
	 * 
	 */
	public static void verifyingcoreelementspresence() {
		elementpresence(usernameplaceholder);
		elementpresence(passwordplaceholder);
		elementpresence(voucherimg);
		elementpresence(languageimg);
		elementpresence(loginbtn);
	}


	/**
	 * Validating the error messages of the login flow
	 * @param validUser Enter a valid user name here
	 */
	public static void logInPageErrosMsgsValidations(String validUser) {
		clickElement(loginbtn);
		elementpresence(usernameerrormsg);
		sendKeys(usernameplaceholder, validUser);
		clickElement(loginbtn);
		elementpresence(pwderrormsg);
	}

}
