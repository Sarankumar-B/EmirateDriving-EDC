package com.pageobjectmodel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.reports.ExtentLogger;
import com.testcases.BaseClass;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

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

	@FindBy(xpath = "//img[@alt='LOGO']")
	public static WebElement logo;

	@FindBy(xpath = "//button[contains(.,'Filter')]")
	public static WebElement filterbtn;

	@FindBy(xpath = "//select[@name='vehicle_type']//option[not(text()='-- Select --')]//parent::select")
	public static WebElement vehicledropdown;

	@FindBy(xpath = "//select[@name='payment_method']//option[not(text()='-- Select --')]//parent::select")
	public static WebElement paymentdropdown;

	@FindBy(xpath = "//select[@name='voucher_status']//option[not(text()='-- Select --')]//parent::select")
	public static WebElement voucherdropdown;

	@FindBy(xpath = "//button[contains(text(),'Export csv')]")
	public static WebElement exportcsv;

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

	public static String timeStamp() {
		long currentTimeMillis = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH");
		return dateFormat.format(new Date(currentTimeMillis));
	}

	static boolean fileexists = false;

	public static void downloadFileVerification(String expectedPath) {
		sleeptime();
		File downloadedFile = new File(expectedPath);

		if (fileexists) {
			System.out.println(expectedPath + "File not found. Download may have failed.");
			fail();
		}
		while (!fileexists) {
			if (downloadedFile.exists()) {
				ExtentLogger.pass(expectedPath + " File downloaded successfully!");
				System.out.println();
				boolean file = downloadedFile.delete();
				System.out.println(file);
				fileexists = true;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static BufferedImage expectedImage;
	static BufferedImage actualImage;

	public static void fetchingexpectedandactualimg(String expectedImagePath) throws IOException {
		File expectedImageFile = new File(expectedImagePath);
		expectedImage = ImageIO.read(expectedImageFile);
		File screenshot = logo.getScreenshotAs(OutputType.FILE);
		actualImage = ImageIO.read(screenshot);
	}

	public static void comparingExpectedAndActualimg() {
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);
		if (diff.hasDiff()) {
			System.out.println("Images are NOT same");
			fail();
		} else {
			System.out.println("Images are same");
		}
	}

	public static void iteratingDropDdown() {
		VoucherList.filterbtn.click();
		Select vehicleType = new Select(vehicledropdown);
		List<WebElement> option1 = vehicleType.getOptions();
		for (int i = 1; i < option1.size(); i++) {
			vehicleType.selectByIndex(i);

			Select paymentMode = new Select(paymentdropdown);
			List<WebElement> option2 = paymentMode.getOptions();
			for (int j = 1; j < option2.size(); j++) {
				paymentMode.selectByIndex(j);

				Select voucherStatus = new Select(voucherdropdown);
				List<WebElement> option3 = voucherStatus.getOptions();
				for (int k = 1; k < option3.size(); k++) {
					voucherStatus.selectByIndex(k);
					String selectedVehicle = option1.get(i).getText();
					String selectedPayment = option2.get(j).getText();
					String selectedStatus = option3.get(k).getText();
					driver.findElement(By.xpath("//button[text()='Apply']")).click();
					sleeptime();
					try {
						String totalCount = VoucherList.totalvouchercount.getText();
						String[] split = totalCount.split(" ");
						ExtentLogger.pass("Selected options: " + selectedVehicle + ", " + selectedPayment + ", "
								+ selectedStatus + ", " + split[1] + split[2]);
					} catch (NoSuchElementException e) {
						String totalCount = driver.findElement(By.xpath("//td[text()='No Records Found']")).getText();
						ExtentLogger.pass("Selected options: " + selectedVehicle + ", " + selectedPayment + ", "
								+ selectedStatus + ", " + totalCount);
					}
					VoucherList.filterbtn.click();
				}
			}
		}
	}

	static boolean isFileFound;

	public static void exportingCSV() {
		VoucherList.exportcsv.click();
		String timeStamp = VoucherList.timeStamp();
		sleeptime();
		// Verify if a file with a partial name exists in the download directory
		String partialFileName = "voucher_list_" + timeStamp; // Replace with the desired partial name
		long startTime = System.currentTimeMillis();
		long twoMinutesInMillis = (long) 2 * 60 * 1000; // Two minutes in milliseconds
		while (!isFileFound) {
			isFileWithPartialNameDownloaded(partialFileName);
			if (isFileFound) {
				ExtentLogger.pass("File with partial name found.");
			}
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime >= twoMinutesInMillis) {
				ExtentLogger.pass("File with partial name not found.");
				fail();
			}
		}

	}

	private static void isFileWithPartialNameDownloaded(String partialFileName) {
		File downloadDirectory = new File(System.getProperty("user.dir") + getproperty("Download"));
		File[] downloadedFiles = downloadDirectory.listFiles();
		sleeptime();
		for (File file : downloadedFiles) {
			System.out.println(partialFileName);
			System.out.println(file.getName());
			if (file.getName().contains(partialFileName)) {
				boolean delete = file.delete();
				System.out.println(delete);
				isFileFound = true;
			}
		}
	}

}
