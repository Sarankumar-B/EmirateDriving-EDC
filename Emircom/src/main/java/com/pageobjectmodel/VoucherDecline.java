package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class VoucherDecline extends BaseClass{
	
	public static void pagefactoryinit() {
		PageFactory.initElements(driver, VoucherDecline.class);
	}
	
	@FindBy(xpath = "//button[contains(text(),'DECLINE')]")
	public static WebElement declinebtn;
	 
	@FindBy(xpath = "//p[text()='Are you sure you want to decline this voucher?']")
	public static WebElement areyousure;
	
	@FindBy(xpath = "//a[text()='YES, DECLINE']")
	public static WebElement declinepopup;
	
	@FindBy(xpath = "//td[text()='Declined']")
	public static WebElement declinedverification;
}
