package com.pageobjectmodel;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testcases.BaseClass;

public class Profile extends BaseClass {

	public static void pagefactoyinit() {

		PageFactory.initElements(driver, Profile.class);
	}

	@FindBy(xpath = "//span[text()=' Edit']")
	public static WebElement editbtn;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	public static WebElement fntxtbox;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	public static WebElement lntxtbox;

	@FindBy(xpath = "//input[@placeholder='Email Id']")
	public static WebElement mailtxtbox;

	@FindBy(xpath = "//span[@onclick='profile_upload()']")
	public static WebElement profileuploadbtn;

	@FindBy(xpath = "//button[text()='Save']")
	public static WebElement savebtn;

	@FindBy(xpath = "//button[text()='OK']")
	public static WebElement okbtn;

	@FindBy(xpath = "(//div[@class='app-details'])[2]")
	public static WebElement verifyfn;

	@FindBy(xpath = "(//div[@class='app-details'])[3]")
	public static WebElement verifyln;

	@FindBy(xpath = "(//div[@class='app-details'])[4]")
	public static WebElement verifymail;

	@FindBy(xpath = "//span[@class='cropper-face cropper-move']")
	public static WebElement src;

	@FindBy(xpath = "//div[@style='width: 161.616px; height: 161.616px; transform: translateX(219.192px);']")
	public static WebElement dest;

	@FindBy(xpath = "//span[@class='cropper-point point-nw']")
	public static WebElement croptopleft;

	@FindBy(xpath = "//button[text()='Go']")
	public static WebElement go;

	@FindBy(xpath = "//div[text()='Profile Picture updated successfully']")
	public static WebElement updatedsuccessfully;

	@FindBy(xpath = "//div[@class='profile-pic']//img[contains(@src,'pic')]")
	public static WebElement profilepicverification;
	/**
	 * 
	 * Verifying the mandatory fields details are present in the profile page
	 * 
	 */
	public static void profiledetailsvalidation() {

		for (int i = 1; i <= 7; i++) {
			WebElement findElement = driver
					.findElement(By.xpath("(//div[contains(@class,'col-lg-3 col-md-6')])[" + i + "]"));
			elementpresence(findElement);
		}
	}


	/**
	 *
	 * Clearing the profile page details to verify the edit option
	 */
	public static void clearprofiledetails() {

		for (int i = 2; i <= 4; i++) {
			WebElement findElement = driver.findElement(By.xpath("(//input[@type=\"text\"])[" + i + "]"));
			findElement.clear();
		}
	}
	/**
	 * Entering the Mandatory profile details after editing 
	 * 
	 * @param fn Enter your first name here
	 * @param ln Enter your last name here
	 * @param mail Enter your mail Id here
	 */
	public static void enterProfileDetails(String fn, String ln, String mail) {
		sendKeys(fntxtbox, fn);
		sendKeys(lntxtbox, ln);
		sendKeys(mailtxtbox, mail);
		clickElement(savebtn);
		clickElement(okbtn);
	}
	/**
	 * Verifying the entered profile details are changed according to user input
	 * @param fn Enter your first name here
	 * @param ln Enter your last name here
	 * @param mail Enter your mail Id here
	 */
	public static void verifyingEnteredProfileDetails(String fn, String ln, String mail) {
		String fntext = verifyfn.getText();
		assertEquals(fntext, fn);
		String lntext = verifyln.getText();
		assertEquals(lntext, ln);
		String mailtext = verifymail.getText();
		assertEquals(mailtext, mail);

	}
	/**
	 * Navigating to the edit profile page 
	 */
	public static void navigateToEditProfile() {
		mousehover(VoucherCreation.hamburgermenu);
		clickElement(VoucherCreation.profilemenu);
		profiledetailsvalidation();
		clickElement(editbtn);
	}
	
	/**
	 * Verifying the uploaded profile picture in changed or not 
	 */
	public static void profileUploadverification() {
		clickElement(go);
		elementpresence(updatedsuccessfully);
		clickElement(okbtn);
		elementpresence(profilepicverification);

	}

}
