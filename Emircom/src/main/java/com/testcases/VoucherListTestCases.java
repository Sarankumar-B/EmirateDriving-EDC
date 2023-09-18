package com.testcases;

import static org.testng.Assert.fail;
import java.io.File;
import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;
import com.pageobjectmodel.VoucherRedeem;

public class VoucherListTestCases extends BaseClass {

	@Test(priority = 1, enabled = true)
	public static void allPageNavigationAndVerifyingEntriesTC22() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.pageNavigatingandverifying();
	}
	
	@Test(priority = 2)
	public static void voucherSearchInVoucherlistTC23() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.searchVoucherIdAndVerifyingStatus(VoucherList.voucherbycurrentdate);
	}
	
	@Test(priority = 3, enabled = true)
	public static void downloadVoucherVerificationTC24() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri4"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightmechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherRedeem.searchVoucherId();
		clickbyjavascript(VoucherList.createdvoucherbtn);
		clickElement(VoucherList.downloadinlist);
		clickElement(VoucherList.downloadvoucher);
		 // Define the expected file path on your local machine
        String expectedFilePath = "C:\\Users\\ranjithkumar.sivakum\\Downloads\\voucher_"+VoucherRedeem.voucherId+".pdf";
        // Check if the file exists
        File downloadedFile = new File(expectedFilePath);
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

