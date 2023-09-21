package com.testcases;

import java.io.IOException;
import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;
import com.pageobjectmodel.VoucherRedeem;

public class VoucherListTestCases extends BaseClass {

	@Test(priority = 1, enabled = true)
	private void allPageNavigationAndVerifyingEntriesTC22() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.pageNavigatingandverifying();
	}

	@Test(priority = 2,  enabled = true)
	private void voucherSearchInVoucherlistTC23() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.searchVoucherIdAndVerifyingStatus(VoucherList.voucherbycurrentdate);
	}

	@Test(priority = 3, enabled = true)
	private void downloadVoucherVerificationTC24() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri4"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightmechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherRedeem.searchVoucherId();
		VoucherList.clickingDownload();
		VoucherList.downloadFileVerification("C:\\Users\\ranjithkumar.sivakum\\Downloads\\voucher_"+VoucherRedeem.voucherId+".pdf");
	}

	@Test(priority = 4, enabled = true)
	private void logoDiffeVerificationTC25() throws IOException {
		VoucherList.fetchingexpectedandactualimg("./src/test/resources/Logos/img3.png");
		VoucherList.comparingExpectedAndActualimg();
	}
	
	@Test(priority = 5, enabled = true)
	private void verifyingFilterFlowTC26() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.iteratingDropDdown();		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
