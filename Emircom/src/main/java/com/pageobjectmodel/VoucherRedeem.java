package com.pageobjectmodel;

import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class VoucherRedeem extends BaseClass {

	public static String voucherId;

	public static void pagefactoryinit() {
		PageFactory.initElements(driver, VoucherRedeem.class);
	}

	@FindBy(xpath = "//button[contains(text(),'Redeem Using Voucher ID')]")
	public static WebElement redeemusingvoucher;

	@FindBy(xpath = "//input[@placeholder='Enter Voucher ID']")
	public static WebElement entervoucherid;

	@FindBy(xpath = "//button[text()='Go']")
	public static WebElement gobtn;

	@FindBy(xpath = "//button[contains(text(),'REDEEM')]")
	public static WebElement redeembtn;

	@FindBy(xpath = "//button[contains(text(),'DECLINE')]")
	public static WebElement declinebtn;

	@FindBy(xpath = "//p[text()='Verified & Redeemed!']")
	public static WebElement redeemedsuccessfully;

	@FindBy(xpath = "//button[text()='Close']")
	public static WebElement redeemedtabclose;

	@FindBy(xpath = "//input[@id='fltr2']")
	public static WebElement voucheridsearch;

	@FindBy(xpath = "//td[text()='Redeemed']")
	public static WebElement redeemedverification;

	/**
	 * Fetching the voucher Id from the created voucher
	 */
	public static void gettingvoucherid() {

		String split = VoucherCreation.voucherid.getText();
		
		String[] parts = split.split(" ");

		voucherId = parts[1];

		clickElement(VoucherCreation.closevoucherview);
        
	}

	/**
	 * Clicking on Redeeming option
	 * @param voucherid Enter the voucherid need to reedemed
	 */
	public static void clickingRedeemOption(String voucherid) {

		clickElement(VoucherRedeem.redeemusingvoucher);

		sendKeys(VoucherRedeem.entervoucherid, voucherId);

		clickElement(VoucherRedeem.gobtn);

		clickElement(VoucherRedeem.redeembtn);

		elementpresence(VoucherRedeem.redeemedsuccessfully);

		clickElement(VoucherRedeem.redeemedtabclose);

		sendKeys(VoucherRedeem.voucheridsearch, voucherid);

		keyactions(KeyEvent.VK_ENTER);

		elementpresence(VoucherRedeem.redeemedverification);

	}

	/**
	 * Clicking on decline option for the created voucher
	 */
	public static void clickingDeclineOption() {

		clickElement(VoucherRedeem.redeemusingvoucher);

		sendKeys(VoucherRedeem.entervoucherid, voucherId);

		clickElement(VoucherRedeem.gobtn);
		
		clickElement(VoucherDecline.declinebtn);

		elementpresence(VoucherDecline.areyousure);

		clickElement(VoucherDecline.declinepopup);

		clickElement(Profile.okbtn);

		sendKeys(VoucherRedeem.voucheridsearch, voucherId);

		keyactions(KeyEvent.VK_ENTER);

		elementpresence(VoucherDecline.declinedverification);

	}
	
	
	/**
	 * Searching the voucher id
	 */
	public static void searchVoucherId() {
		sendKeys(VoucherRedeem.voucheridsearch, VoucherRedeem.voucherId);
		keyactions(KeyEvent.VK_ENTER);
	}

}
