package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class Forgetpwd extends BaseClass{
	
	
public static void pagefactoyinit() {
		
		PageFactory.initElements(driver, Forgetpwd.class);
	}

	@FindBy(xpath = "//h4[text()='Forgot Password']")
	public static WebElement forgetpwdtext;
	
	@FindBy(xpath = "//button[text()='Submit']")
	public static WebElement submitbtn;
	
	@FindBy(xpath = "//label[text()='Please enter Email']")
	public static WebElement pleaseentermailerror;
	
	@FindBy(xpath = "//label[text()='Invalid Email']")
	public static WebElement invalidmailerror;
	
	@FindBy(xpath = "//input[@placeholder='Enter your email here']")
	public static WebElement emailplaceholder;
	
	@FindBy(xpath = "//div[text()='No account associated with user']")
	public static WebElement noaccountserror;
	
	
	
	/**
	 * Entering the maid id in forgot password feild 
	 * @param forgotmailid Enter the mail id you want to reset the password
	 */
	public static void gettinglinkforresetpass(String forgotmailid) {
	clickElement(LogIn.forgetpwd);
	sendKeys(Forgetpwd.emailplaceholder, forgotmailid);
	clickElement(Forgetpwd.submitbtn);
	}
	
	
	
	
	
}
