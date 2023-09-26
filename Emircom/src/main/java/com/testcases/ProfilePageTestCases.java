package com.testcases;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.Profile;
import com.pageobjectmodel.VoucherCreation;

public class ProfilePageTestCases extends BaseClass {

	/**
	 * Verifying the mandatory details of the user are present in the profile details
	 */
	@Test
	public void verifyingProfileDetailsTC09() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		mousehover(VoucherCreation.hamburgermenu);
		clickElement(VoucherCreation.profilemenu);
		Profile.profiledetailsvalidation();
		clickElement(Profile.editbtn);
		Profile.clearprofiledetails();
		Profile.enterProfileDetails(getproperty("Newfn"), getproperty("Newln"), getproperty("Newmailid"));
		Profile.verifyingEnteredProfileDetails(getproperty("Newfn"), getproperty("Newln"), getproperty("Newmailid"));

	}

	/**
	 * Verifying whether user able to edit the profile details
	 */
	@Test
	public void verifyinguserabletoeditprofiledetailsTC10() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		Profile.navigateToEditProfile();
		Profile.clearprofiledetails();
		Profile.enterProfileDetails(getproperty("Oldfn"), getproperty("Oldln"), getproperty("CssMailid"));
		Profile.verifyingEnteredProfileDetails(getproperty("Oldfn"), getproperty("Oldln"), getproperty("CssMailid"));
	}

	/**
	 * Verifying whether user able change the profile picture
	 */
	@Test
	public void verifyingProfilePictureUploadTC11() throws AWTException {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		Profile.navigateToEditProfile();
		clickElement(Profile.profileuploadbtn);
		copyText(getproperty("profilimgpath"));
		ctrlVAction();
		draganddropwithoffset(Profile.src, 0, -90);
		Profile.profileUploadverification();
	}

	/**
	 * Verifying whether user able to logout
	 */
	@Test
	public void verifyingLogoutFlowTC12() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.clickLogOut();
		verifystringURL(getproperty("LoginURL"));
	}

}
