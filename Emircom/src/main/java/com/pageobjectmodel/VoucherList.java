package com.pageobjectmodel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.testcases.BaseClass;

public class VoucherList extends BaseClass {

	public static String voucherbycurrentdate;

	public static void pagefactoryinit() {
		PageFactory.initElements(driver, VoucherList.class);
	}

	@FindBy(xpath = "//input[contains(@data-name,'status')]")
	public static WebElement statussearch;

	@FindBy(xpath = "//td[text()='1']//following::button")
	public static WebElement createdvoucherbtn;

	@FindBy(xpath = "(//td[text()='1']//following::a[text()=' Cancel'])[1]")
	public static WebElement cancelbtn;

	@FindBy(xpath = "(//input[@placeholder='Remarks'])[1]")
	public static WebElement remarks;

	@FindBy(xpath = "//button[text()='Cancel']")
	public static WebElement cancelationpopbtn;

	@FindBy(xpath = "//div[text()='Cancelled Successfully']")
	public static WebElement cancelationsuccess;

	@FindBy(xpath = "(//td[@class='numbers-font'])[3]")
	public static WebElement emiratesid;

	@FindBy(xpath = "//input[@data-name='student__user__username']")
	public static WebElement emiratesidsearch;

	@FindBy(xpath = "//td[text()='Cancelled']")
	public static WebElement cancelled;

	@FindBy(xpath = "(//a[@class='vchr-id'])[1]")
	public static WebElement fetchidtoreactivate;

	@FindBy(xpath = "//a[text()=' Reactivate Request']")
	public static WebElement reactivaterequest;

	@FindBy(xpath = "//p[@class='center-sm m-0 fnt12']")
	public static WebElement totalvouchercount;

	@FindBy(xpath = "//a[@class='page-link ']")
	public static WebElement pagecount;

	@FindBy(xpath = "//i[@class='fas fa-angle-right']")
	public static WebElement rightnavigtion;

	@FindBy(xpath = "(//a[text()='220407335632']//ancestor::tr//following-sibling::td)[1]")
	public static WebElement firstvoucher;

	@FindBy(xpath = "(//td//a[text()='230908173719']//following::td[@style='text-align:left;'])[5]")
	public static WebElement voucherstatusinlist;

	@FindBy(xpath = "//span[@class='bg_red text-white fnt14 p-1 ml-2']")
	public static WebElement voucherstatusinapplicantdetail;

	@FindBy(xpath = "//a[contains(text(),'Download')]")
	public static WebElement downloadinlist;

	@FindBy(xpath = "//a[text()=' Download ']")
	public static WebElement downloadvoucher;

	/**
	 * Selecting the date in calender based on the user input
	 * 
	 * @param targetDate Enter the date you need to select in a format(dd-MM-yyyy)
	 */
	public static void cancellingCreatedVoucher(String status, String remarks) {
		sendKeys(VoucherList.statussearch, status);
		keyactions(KeyEvent.VK_ENTER);
		String created = VoucherList.emiratesid.getText();
		clickbyjavascript(VoucherList.createdvoucherbtn);
		clickElement(VoucherList.cancelbtn);
		sendKeys(VoucherList.remarks, remarks);
		clickElement(VoucherList.cancelationpopbtn);
		elementpresence(VoucherList.cancelationsuccess);
		clickElement(Profile.okbtn);
		sendKeys(VoucherList.emiratesidsearch, created);
		keyactions(KeyEvent.VK_ENTER);
		elementpresence(VoucherList.cancelled);
	}

	/**
	 * Fetching the total vouchers and pages count then navigating to the last page
	 */
	public static void pageNavigatingandverifying() {
		String vouchercount = VoucherList.totalvouchercount.getText();
		String[] part = vouchercount.split(" ");
		String pagecount = VoucherList.pagecount.getText();
		int parseInt = Integer.parseInt(pagecount);
		System.out.println(parseInt);
		for (int i = 2; i <= parseInt; i++) {
			if (VoucherList.currentdate() + 1 == i) {
				sleeptime3sec();
				voucherbycurrentdate = driver.findElement(By.xpath("//a[@class='vchr-id']")).getText();
			}
			driver.findElement(By.xpath("//a[@onclick='addURL(this, " + i + " )']")).click();
		}
		String totalcount = VoucherList.firstvoucher.getText();
		assertEquals(part[1], totalcount);
	}

	public static void searchVoucherIdAndVerifyingStatus(String voucherId) {
		WebElement button;
		boolean buttonFound = false;
		while (!buttonFound) {
			try {
				button = driver.findElement(By.xpath("//a[text()='" + voucherId + "']"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				button = null;
			}
			if (button != null) {
				String statusinlist = driver
						.findElement(By.xpath(
								"(//td//a[text()='" + voucherId + "']//following::td[@style='text-align:left;'])[5]"))
						.getText();
				System.out.println(statusinlist);
				System.out.println(voucherId);
				clickbyjavascript(button);
				String statusInDetail = voucherstatusinapplicantdetail.getText();
				Assert.assertTrue(statusinlist.equalsIgnoreCase(statusInDetail));
				buttonFound = true;
			} else {
				VoucherList.rightnavigtion.click();
			}

		}
	}

	public static int currentdate() {
		Date timestamp = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(timestamp);
		String[] split = currentDate.split("-");
		return Integer.parseInt(split[2]);
	}

	public static void clickingDownload() {
		clickbyjavascript(VoucherList.createdvoucherbtn);
		clickElement(VoucherList.downloadinlist);
		clickElement(VoucherList.downloadvoucher);
	}
	
	public static void downloadFileVerification(String expectedPath) {
	File downloadedFile = new File(expectedPath);
    sleeptime3sec();
    if (downloadedFile.exists()) {
        System.out.println("File downloaded successfully!");
        boolean file = downloadedFile.delete();
        System.out.println(file);
    } else {
        System.out.println("File not found. Download may have failed.");
        fail();
    }
	}
}
