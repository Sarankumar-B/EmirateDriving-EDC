package com.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.testcases.BaseClass;

public class LogIn extends BaseClass {

	public LogIn(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	public  WebElement loginbtn;

	@FindBy(xpath = "//input[@placeholder='Enter your username here']")
	public  WebElement usernameplaceholder;

	@FindBy(xpath = "//input[@placeholder='Enter your password here']")
	public  WebElement passwordplaceholder;

	@FindBy(xpath = "//div[@class='login-voucher']")
	public  WebElement voucherimg;

	@FindBy(xpath = "//div[@class='row justify-content-end align-items-center']")
	public  WebElement languageimg;

	@FindBy(xpath = "//label[text()='Please enter username']")
	public  WebElement usernameerrormsg;

	@FindBy(xpath = "//label[text()='Please enter Password']")
	public  WebElement pwderrormsg;

	@FindBy(xpath = "//label[@class='server_error_label']")
	public  WebElement invalidcredentialserrormsg;

	@FindBy(xpath = "//a[text()='Forget Password?']")
	public  WebElement forgetpwd;
	
	

	/**
	 * Enter emailId, password and clicking on logIn button
	 * 
	 * @param username Enter user name here
	 * @param pass     Enter password here
	 */
	public  void loginFlow(String username, String pass) {
		sendKeys(usernameplaceholder, username);
		sendKeys(passwordplaceholder, pass);
		clickElement(loginbtn);

	}

	/**
	 * Verifying the elements are present in the landing page or not
	 * 
	 */
	public  void verifyingcoreelementspresence() {
		elementpresence(usernameplaceholder);
		elementpresence(passwordplaceholder);
		elementpresence(voucherimg);
		elementpresence(languageimg);
		elementpresence(loginbtn);
	}

	/**
	 * Validating the error messages of the login flow
	 * 
	 * @param validUser Enter a valid user name here
	 */
	public  void logInPageErrosMsgsValidations(String validUser) {
		clickElement(loginbtn);
		elementpresence(usernameerrormsg);
		sendKeys(usernameplaceholder, validUser);
		clickElement(loginbtn);
		elementpresence(pwderrormsg);
	}

}
