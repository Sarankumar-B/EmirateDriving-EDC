package com.pageobjectmodel;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reports.ExtentLogger;
import com.testcases.BaseClass;

//Url of this page: https://emircom-edc-testing.easyngo.com/voucher_registration/

public class VoucherCreation extends BaseClass {

	public static String gmailusrid;

	public static void pagefactoryinit() {
		PageFactory.initElements(driver, VoucherCreation.class);
	}

	@FindBy(xpath = "//a[@class='dropdown-item'][text()=' Profile']")
	public static WebElement profilemenu;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	public static WebElement logoutbtn;

	@FindBy(xpath = "//img[@class='profile']")
	public static WebElement hamburgermenu;

	@FindBy(xpath = "//label[text()='Username']//following::div")
	public static WebElement usernametext;

	@FindBy(id = "emirates_id")
	public static WebElement emiratesid;

	@FindBy(id = "trifle_number")
	public static WebElement tryfilenumber;

	@FindBy(xpath = "//button[contains(text(),' Create Voucher')]")
	public static WebElement createvoucherbtn;

	@FindBy(id = "date_of_birth")
	public static WebElement dob;

	@FindBy(id = "mobile_number")
	public static WebElement mobileno;

	@FindBy(id = "email")
	public static WebElement email;

	@FindBy(id = "address_line_1")
	public static WebElement add1;

	@FindBy(id = "address_line_2")
	public static WebElement add2;

	@FindBy(id = "post_number")
	public static WebElement pincode;

	@FindBy(xpath = "//div[text()='Motor Cycle']//preceding-sibling::div")
	public static WebElement motorcycle;

	@FindBy(xpath = "//div[text()='Light Vehicle']//preceding-sibling::div")
	public static WebElement lightVehicle;

	@FindBy(xpath = "//div[text()='Heavy Bus']//preceding-sibling::div")
	public static WebElement heavybus;

	@FindBy(xpath = "//div[text()='Light Bus']//preceding-sibling::div")
	public static WebElement lightbus;

	@FindBy(xpath = "//div[text()='Light Mech Equipment']//preceding-sibling::div")
	public static WebElement lightmechequipment;

	@FindBy(xpath = "//div[text()='Heavy Vehicle']//preceding-sibling::div")
	public static WebElement heavyvehicle;

	@FindBy(xpath = "//div[text()='Heavy Mech Equipment']//preceding-sibling::div")
	public static WebElement heavymechequipment;

	@FindBy(xpath = "//a[contains(text(),'Browse')]")
	public static WebElement attachment;

	@FindBy(xpath = "//button[contains(text(),'Payment')]")
	public static WebElement paymentbtn;

	@FindBy(xpath = "//div[text()='Cash']")
	public static WebElement paymenttypecash;

	@FindBy(xpath = "//div[text()='Credit / Debit Card']")
	public static WebElement paymenttypecard;

	@FindBy(xpath = "//input[@id='cardholder_name']")
	public static WebElement cardholdername;

	@FindBy(xpath = "//input[@id='card_number']")
	public static WebElement cardnumber;

	@FindBy(xpath = "//input[@id='month']")
	public static WebElement month;

	@FindBy(xpath = "//input[@id='year']")
	public static WebElement year;

	@FindBy(xpath = "//input[@id='cvv']")
	public static WebElement cvv;

	@FindBy(xpath = "//a[contains(text(),'Proceed to Payment')]")
	public static WebElement proceedbtn;

	@FindBy(xpath = "//div[@class='total-amount-number']")
	public static WebElement totalfee;

	@FindBy(xpath = "//span[text()='Total']//following-sibling::span[text()]")
	public static WebElement totalfeeinvoucher;

	@FindBy(xpath = "//button[contains(text(),'View my E-Voucher')]")
	public static WebElement viewvoucherbtn;

	@FindBy(xpath = "//input[@placeholder='Email ID']")
	public static WebElement emailid;

	@FindBy(xpath = "//select[@id='branch']")
	public static WebElement branch;

	@FindBy(xpath = "//label[text()='Use Mobile Number']")
	public static WebElement whatsappcheckbox;

	@FindBy(xpath = "//a[@onclick='open_file_upload()']")
	public static WebElement browsebtn;

	@FindBy(xpath = "//div[@class='ref-num-text']")
	public static WebElement voucherid;

	@FindBy(xpath = "//i[@onclick='redirect_modal(this)']")
	public static WebElement closevoucherview;

	public static By loadericon = By.xpath("//div[text()='Please wait...']");

	@FindBy(xpath = "//th[@title='Select Month']")
	public static WebElement monthtitle;

	@FindBy(xpath = "//span[@title='Previous Month']")
	public static WebElement prevmonth;

	@FindBy(xpath = "//span[@title='Next Month']")
	public static WebElement nxtmonth;

	@FindBy(xpath = "//i[@class='fa fa-calendar']")
	public static WebElement calendar;

	@FindBy(xpath = "//input[@placeholder='Applicant Name']")
	public static WebElement nameplaceholder;

	@FindBy(xpath = "//label[.='Please enter Emirates Id']")
	public static WebElement enteremiratesid;

	@FindBy(xpath = "//label[.='Emirates Id should be 15 numbers']")
	public static WebElement idshouldbe15no;

	@FindBy(xpath = "//label[.='Applicant Name Error']")
	public static WebElement nameerror;

	@FindBy(xpath = "//label[.='Please select Date of Birth']")
	public static WebElement selectdob;

	@FindBy(xpath = "//label[.='Age should be greater than 16']")
	public static WebElement ageshouldgreaterthan15;

	@FindBy(xpath = "//label[.='Age should be less than 110']")
	public static WebElement ageshouldlessthan110;

	@FindBy(xpath = "//label[.='Please enter Mobile Number']")
	public static WebElement entermobilenumerrmsg;

	@FindBy(xpath = "//label[.='Invalid Mobile Number']")
	public static WebElement invalidnumerrmsg;

	@FindBy(xpath = "//label[.='Please enter WhatsApp Number']")
	public static WebElement enterwpmobilenumerrmsg;

	@FindBy(xpath = "//label[.='Please enter Email Id']")
	public static WebElement enteremailerrmsg;

	@FindBy(xpath = "//label[.='Invalid Email Id']")
	public static WebElement invalidemail;

	@FindBy(xpath = "//label[.='Please select Branch']")
	public static WebElement selectbranch;

	@FindBy(xpath = "//label[.='Please select Vehicle Type']")
	public static WebElement selecttype;

	@FindBy(xpath = "//label[.='Please enter Tryfile Number']")
	public static WebElement entertryfilemno;

	/**
	 * Generating a dynamic 15 digit numbers with current time for unique emirates
	 * Id
	 *
	 */
	public static String emiratesIdGenrator() {
		long currentTimeMillis = System.currentTimeMillis();
		long randomPart = (long) (Math.random() * 1000000000000L);
		String uniqueNumber = String.format("%d%012d", currentTimeMillis, randomPart);
		return uniqueNumber.substring(0, 15);
	}

	/**
	 * Generating a dynamic 9 digit numbers starting with 54 for unique mobile
	 * numbers
	 */
	public static String mobileNumber() {
		String prefix = "54";
		long randomPart = (long) (Math.random() * 100000000L); // Generates a random 8-digit number
		String uniqueNumber = String.format("%s%08d", prefix, randomPart);
		return uniqueNumber.substring(0, 9); // Ensure it's exactly 9 digits
	}

	/**
	 * Generating a dynamic gmail id with the use of current time
	 */
	public static String generateGmailIdWithCurrentTime() {
		long currentTimeMillis = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedTimestamp = dateFormat.format(new Date(currentTimeMillis));
		return "emircom" + formattedTimestamp + "@mailinator.com";
	}

	/**
	 * Adding the card details for payments
	 */
	public static void addingCardDetails() {
		sendKeys(cardholdername, getproperty("CardHolderName"));
		sendKeys(cardnumber, getproperty("CardNumber"));
		sendKeys(month, getproperty("Month"));
		sendKeys(year, getproperty("Year"));
		sendKeys(cvv, getproperty("CVV"));

	}

	/**
	 * Verify the logged in user by asserting the given username and username
	 * present in profile page
	 * 
	 * @param validUser Enter the valid user
	 */
	public static void loggedInUserverify(String validUser) {
		mousehover(hamburgermenu);
		clickElement(profilemenu);
		String user = usernametext.getText();
		assertEquals(validUser, user);
		ExtentLogger.pass("The actual User Name: " + user + " is same as entered in username field");

	}

	/**
	 * Clicking logout
	 */
	public static void clickLogOut() {
		mousehover(hamburgermenu);
		clickElement(logoutbtn);
	}

	/**
	 * Creating voucher with option as both CSS user and Guest user
	 * 
	 * @param createvoucherby Click element to create voucher by css or guest
	 * @param trifile         Enter your trifile Id
	 * @param age             Enter Your age
	 * @param add1            Enter you address line 1
	 * @param add2            Enter you address line 2
	 * @param pincode         Enter you pincode
	 * @param vehicletype     Click the vehicletype
	 */
	public static void creatingVoucher(WebElement createvoucherby, String trifile, String age, String add1, String add2,
			String pincode, WebElement vehicletype) {
		clickbyjavascript(createvoucherby);
		sendKeys(emiratesid, emiratesIdGenrator());
		sendKeys(tryfilenumber, trifile);
		keyactions(KeyEvent.VK_TAB);
		waitForElementToBeDisappear(Duration.ofSeconds(10), loadericon);
		clickbyjavascript(Profile.okbtn);
		sendKeys(nameplaceholder, "ranjith");
		waitForElementToBeDisappear(Duration.ofSeconds(10), loadericon);
		sendKeys(mobileno, mobileNumber());
		try {
			selectDate(age);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickbyjavascript(whatsappcheckbox);
		String gmailIdWithCurrentTime = generateGmailIdWithCurrentTime();
		sendKeys(email, gmailIdWithCurrentTime);
		gmailusrid = gmailIdWithCurrentTime.substring(0, 21);
		if (createvoucherby == LogIn.voucherimg) {
			selectbyindex(branch, 2);
		}
		sendKeys(VoucherCreation.add1, add1);
		sendKeys(VoucherCreation.add2, add2);
		sendKeys(VoucherCreation.pincode, pincode);
		clickbyjavascript(vehicletype);
		if (createvoucherby == LogIn.voucherimg) {
			clickElement(VoucherCreation.browsebtn);
			copyText(getproperty("FilePath"));
			try {
				ctrlVAction();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		waitForElementToBeDisappear(Duration.ofSeconds(10), loadericon);
		clickbyjavascript(VoucherCreation.paymentbtn);
	}

	/**
	 * Verify the amount in the payment page and voucher are same
	 * 
	 * @param paymentType Select the payment type
	 */
	public static void verifyingvoucheramount(WebElement paymentType) {

		clickElement(paymentType);

		if (paymentType == paymenttypecard) {
			VoucherCreation.addingCardDetails();
		}

		String totalamountinpaymentpage = VoucherCreation.totalfee.getText();

		clickElement(VoucherCreation.proceedbtn);

		clickElement(VoucherCreation.viewvoucherbtn);

		waitForElementToBeAppear(Duration.ofSeconds(5), VoucherCreation.totalfeeinvoucher);

		String totalamountinvoucher = VoucherCreation.totalfeeinvoucher.getText();

		assertEquals(totalamountinpaymentpage, totalamountinvoucher);
	}

	/**
	 * Selecting the date in calender based on the user input
	 * 
	 * @param targetDate Enter the date you need to select in a format(dd-MM-yyyy)
	 */
	public static void selectDate(String targetDate) throws Exception {

		clickElement(calendar);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date formattedTargetDate;
		try {
			targetDateFormat.setLenient(false);
			formattedTargetDate = targetDateFormat.parse(targetDate);
			calendar.setTime(formattedTargetDate);
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int targetYear = calendar.get(Calendar.YEAR);
			String actualDate = monthtitle.getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
			int actualMonth = calendar.get(Calendar.MONTH);
			int actualYear = calendar.get(Calendar.YEAR);
			while (targetMonth < actualMonth || targetYear < actualYear) {
				clickElement(prevmonth);
				actualDate = monthtitle.getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}
			while (targetMonth > actualMonth || targetYear > actualYear) {
				clickElement(nxtmonth);
				actualDate = monthtitle.getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);
			}
			driver.findElement(
					By.xpath("//table[@class='table-condensed']//td[@class='day' or @class='day weekend'][text()="
							+ targetDay + "]"))
					.click();
		} catch (ParseException e) {
			throw new Exception("Invalid date is provided, please check input date");
		}
	}

	public static void errorMsgValidations() throws Exception {
		clickElement(LogIn.voucherimg);
		clickElement(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.enteremiratesid);
		clickbyjavascript(VoucherCreation.paymentbtn);
		sendKeys(VoucherCreation.emiratesid, "122345");
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.idshouldbe15no);
		VoucherCreation.emiratesid.clear();
		sendKeys(VoucherCreation.emiratesid, VoucherCreation.emiratesIdGenrator());
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.nameerror);
		sendKeys(VoucherCreation.nameplaceholder, getproperty("Newfn"));
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.selectdob);
		VoucherCreation.selectDate("12-09-2023");
		VoucherCreation.dob.clear();
		elementpresence(VoucherCreation.ageshouldgreaterthan15);
		sendKeys(VoucherCreation.dob, getproperty("Age120"));
		VoucherCreation.dob.clear();
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.ageshouldlessthan110);
		sendKeys(VoucherCreation.dob, getproperty("Age19"));
		clickbyjavascript(VoucherCreation.paymentbtn);
		sleeptime3sec();
		elementpresence(VoucherCreation.entermobilenumerrmsg);
		sendKeys(VoucherCreation.mobileno, "1234556");
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.invalidnumerrmsg);
		VoucherCreation.mobileno.clear();
		sendKeys(VoucherCreation.mobileno, VoucherCreation.mobileNumber());
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.enterwpmobilenumerrmsg);
		clickbyjavascript(VoucherCreation.whatsappcheckbox);
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.enteremailerrmsg);
		sendKeys(VoucherCreation.emailid, "sample");
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.invalidemail);
		VoucherCreation.emailid.clear();
		sendKeys(VoucherCreation.emailid, VoucherCreation.generateGmailIdWithCurrentTime());
		elementpresence(VoucherCreation.selectbranch);
		selectbyindex(VoucherCreation.branch, 4);
		clickbyjavascript(VoucherCreation.paymentbtn);
		elementpresence(VoucherCreation.entertryfilemno);
		sendKeys(VoucherCreation.tryfilenumber, "1234003");
		keyactions(KeyEvent.VK_TAB);
		waitForElementToBeDisappear(Duration.ofSeconds(10), VoucherCreation.loadericon);
		clickElement(Profile.okbtn);
		elementpresence(VoucherCreation.selecttype);
	}
}
