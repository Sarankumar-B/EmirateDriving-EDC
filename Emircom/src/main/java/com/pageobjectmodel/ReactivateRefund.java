package com.pageobjectmodel;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class ReactivateRefund extends BaseClass {

	public static String voucherId;
	public static String otp;

	public static void pagefactoyinit() {
		PageFactory.initElements(driver, ReactivateRefund.class);
	}

	@FindBy(xpath = "//button[contains(text(),'Reactivate')]")
	private static WebElement reactivatereqbutton;

	@FindBy(xpath = "//a[text()=' Vouchers']")
	public static WebElement finvoucherhover;

	@FindBy(xpath = "//a[text()='Vouchers List']")
	public static WebElement finvoucherlist;

	@FindBy(xpath = "//a[text()=' Reactivate']")
	public static WebElement reactivateapprove;

	@FindBy(xpath = "//a[text()=' Reactivate Reject']")
	public static WebElement reactivatereject;

	@FindBy(xpath = "//td[text()='Reactivated']")
	public static WebElement reactivated;

	@FindBy(xpath = "//td[text()='Reactivate Rejected']")
	public static WebElement reactivaterejected;

	@FindBy(xpath = "//a[text()=' Refund Request']")
	public static WebElement refundreqbutton;

	@FindBy(xpath = "//td[contains(text(),'Voucher Refund Request')]")
	public static WebElement waittillotp;

	@FindBy(xpath = "//body[contains(text(),'Your refund request otp for voucher')]")
	public static WebElement getotp;

	@FindBy(xpath = "//input[@placeholder='OTP']")
	public static WebElement otpplaceholder;

	@FindBy(xpath = "//select[@id='refund_reason']")
	public static WebElement refundreason;

	@FindBy(xpath = "//button[text()='Refund Request ']")
	public static WebElement refundrequest;

	@FindBy(xpath = "//p[text()='Refund Request Sent!']")
	public static WebElement refundrequestsent;

	@FindBy(xpath = "//p[text()='Refund Request Sent!']//parent::div//button[text()='Close']")
	public static WebElement close;

	@FindBy(xpath = "//td[text()='Refund Requested']")
	public static WebElement refundrequested;

	@FindBy(xpath = "//a[text()=' Refund Approve']")
	public static WebElement refundapproveinlist;

	@FindBy(xpath = "//a[text()=' Refund Reject']")
	public static WebElement refundrejectinlist;

	@FindBy(xpath = "//button[text()='Refund Reject']")
	public static WebElement refundreject;

	@FindBy(xpath = "//button[text()='Refund Approve ']")
	public static WebElement refundapprove;

	@FindBy(xpath = "//td[text()='Refund Approved']")
	public static WebElement refundapprovedinlist;

	@FindBy(xpath = "//td[text()='Refund Rejected']")
	public static WebElement refundrejectedinlist;

	@FindBy(xpath = "//td[text()='Refunded']")
	public static WebElement refunded;

	@FindBy(xpath = "//a[text()=' Pay']")
	public static WebElement pay;

	@FindBy(xpath = "//button[text()='Pay']")
	public static WebElement paybtn;

	public static void finvoucherclick() {
		mousehover(ReactivateRefund.finvoucherhover);
		clickElement(ReactivateRefund.finvoucherlist);
	}

	/**
	 * Raising the reactivate request
	 */
	public static void raisingReactivateRequest() {

		sendKeys(VoucherList.statussearch, "Redeemed");

		keyactions(KeyEvent.VK_ENTER);

		voucherId = VoucherList.fetchidtoreactivate.getText();

		clickbyjavascript(VoucherList.createdvoucherbtn);

		clickElement(VoucherList.reactivaterequest);

		sendKeys(VoucherList.remarks, "Reactivate the Request");

		clickElement(ReactivateRefund.reactivatereqbutton);
		
		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		clickElement(Profile.okbtn);

	}

	/**
	 * Searching voucher Id and verifying the status of the voucher
	 * @param status Mention webelemnt to verify status of the voucher
	 */
	public static void verifyVoucherStatus(WebElement status) {
		sendKeys(VoucherRedeem.voucheridsearch, voucherId);
		keyactions(KeyEvent.VK_ENTER);
		elementpresence(status);
	}

	/**
	 * Approving or declining the reactivate request
	 * @param option Mention the webelement to approve or decline the reactivate request
	 *  @param remarks Enter the remars for the reactivate request 
	 */
	public static void reactivateOrDecline(WebElement option, String remarks) {

		sendKeys(VoucherList.statussearch, "Reactivate");

		sendKeys(VoucherRedeem.voucheridsearch, voucherId);

		clickbyjavascript(VoucherList.createdvoucherbtn);

		clickElement(option);

		sendKeys(VoucherList.remarks, remarks);

		clickElement(ReactivateRefund.reactivatereqbutton);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		clickElement(Profile.okbtn);

	}

	/**
	 * Fetching the OTP from mailnator for refund request
	 */
	public static void fetchingOtp() {

		VoucherRedeem.searchVoucherId();

		clickbyjavascript(VoucherList.createdvoucherbtn);

		clickElement(ReactivateRefund.refundreqbutton);

		try {
			newTab();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		switchtochildwindow();

		driver.navigate().to(getproperty("MailNatorURL"));

		clickElement(ResetPwd.mailnatorclose);

		sendKeys(ResetPwd.mailnatormailid, VoucherCreation.gmailusrid);

		clickElement(ResetPwd.gobtn);

		clickElement(ReactivateRefund.waittillotp);

		switchtoiframe(ResetPwd.frame);

		String splitotp = ReactivateRefund.getotp.getText();

		otp = splitotp.substring(splitotp.length() - 6);
	}

	
	/**
	 * Entering the OTP fetched from mainator and raising refund for the voucher created
	 */
	public static void enteringOtpandRaisingRefund() {
		
		switchtochildwindow();

		sendKeys(ReactivateRefund.otpplaceholder, otp);

		clickElement(Forgetpwd.submitbtn);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		selectbyindex(ReactivateRefund.refundreason, 2);

		clickElement(ReactivateRefund.refundrequest);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		elementpresence(ReactivateRefund.refundrequestsent);

		clickElement(ReactivateRefund.close);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

	}

	
	/**
	 * Approving the refund request as a finance user 
	 */
	public static void approvingRefundAsfinance() {
		ReactivateRefund.finvoucherclick();

		VoucherRedeem.searchVoucherId();

		elementpresence(ReactivateRefund.refundrequested);

		clickbyjavascript(VoucherList.createdvoucherbtn);

		clickElement(ReactivateRefund.refundapproveinlist);

		clickElement(ReactivateRefund.refundapprove);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		clickElement(Profile.okbtn);

	}

	/**
	 * Paying the cash for refund approved by the finance user
	 */
	public static void approvingRefundAscashier() {
		ReactivateRefund.finvoucherclick();

		VoucherRedeem.searchVoucherId();

		elementpresence(ReactivateRefund.refundapprovedinlist);

		clickbyjavascript(VoucherList.createdvoucherbtn);

		clickElement(ReactivateRefund.pay);

		sendKeys(VoucherList.remarks, "Refunded");

		clickElement(ReactivateRefund.paybtn);

		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

		clickElement(Profile.okbtn);

	}
	
	/**
	 * Declining the Refund Raised as financier
	 */
	public static void decliningRefundAsFinancier() {
		
	ReactivateRefund.finvoucherclick();

	VoucherRedeem.searchVoucherId();

	elementpresence(ReactivateRefund.refundrequested);

	clickbyjavascript(VoucherList.createdvoucherbtn);

	clickElement(ReactivateRefund.refundrejectinlist);

	sendKeys(VoucherList.remarks, "Refund will be rejected");

	clickElement(ReactivateRefund.refundreject);

	waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);

	clickElement(Profile.okbtn);

}
}

