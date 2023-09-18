package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class ResetPwd extends BaseClass {

	public static void pagefactoyinit() {

		PageFactory.initElements(driver, ResetPwd.class);
	}

	@FindBy(xpath = "//div[text()='Request Subscription Trial']//following-sibling::button")
	public static WebElement mailnatorclose;

	@FindBy(xpath = "//input[@id='inbox_field']")
	public static WebElement mailnatormailid;

	@FindBy(xpath = "//td[contains(text(),'Password Reset Requested')]")
	public static WebElement resetmail;
	
	@FindBy(xpath = "//input[@id='inbox_field']//following::button[contains(text(),'GO')]")
	public static WebElement gobtn;
	
	@FindBy(xpath = "//html//body//a[contains(@href,'https://emircom-edc-testing.easyngo.com/reset')]")
	public static WebElement resetlink;
	
	@FindBy(xpath = "//img[@border='none']")
	public static WebElement scrolldown;
	
	@FindBy(id = "texthtml_msg_body")
	public static WebElement frame;
	
	@FindBy(xpath = "//button[text()='Reset Password']")
	public static WebElement resetpwdbtn;
	
	@FindBy(xpath = "//strong[text()='The two password fields didn’t match.']")
	public static WebElement pwddidnotmatch;
	
	@FindBy(xpath = "//strong[text()='This password is too common.']")
	public static WebElement toocommon;
	
	@FindBy(xpath = "//strong[text()='This password is entirely numeric.']")
	public static WebElement entirelynumeric;
	
	@FindBy(xpath = "//input[@name='new_password1']")
	public static WebElement newpassword;
	
	@FindBy(xpath = "//input[@name='new_password2']")
	public static WebElement cnfrmpassword;
	
	@FindBy(xpath = "//h4[text()='Password Reset Complete']")
	public static WebElement pwdresetcomplete;
	
	@FindBy(xpath = "//a[text()='Login']")
	public static WebElement resetloginbtn;
	
	
	
	/**
	 * Entering the new passwords for the reset password flow
	 * @param password1 Enter your new password
	 * @param password2 Confirm your new password entered
	 */
	public static void resetpwdflow(String password1, String password2) {
	
	sendKeys(newpassword, password1);

	sendKeys(cnfrmpassword, password2);

	clickElement(resetpwdbtn);
	
	}
	
	
	
	/**
	 * Clicking the link which is sent to the mailid to reset the password
	 * @param mailnatorURL Enter your the mailnator URL
	 * @param mailnatorid Enter your mail id which you have entered in forgot password mailid
	 */
	public static void clickingResetLinkInMailnator(String mailnatorURL, String mailnatorid) {
		
		driver.navigate().to(mailnatorURL);

		clickElement(mailnatorclose);

		sendKeys(mailnatormailid, mailnatorid);

		clickElement(gobtn);
		
		sleeptime3sec();
		
		clickElement(resetmail);

		switchtoiframe(frame);
		 
		clickElement(resetlink);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
