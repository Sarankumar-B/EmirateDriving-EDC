package com.testcases;

import java.io.IOException;
import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;
import com.pageobjectmodel.VoucherRedeem;

public class VoucherListTestCases extends BaseClass {

	@Test
	private void allPageNavigationAndVerifyingEntriesTC22() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.pageNavigatingandverifying();
	}

	@Test
	private void voucherSearchInVoucherlistTC23() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.searchVoucherIdAndVerifyingStatus(VoucherList.voucherbycurrentdate);
	}

	@Test
	private void downloadVoucherVerificationTC24() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri4"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightmechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherRedeem.searchVoucherId();
		VoucherList.clickingDownload();
		VoucherList.downloadFileVerification(System.getProperty("user.dir") + getproperty("Download") + "//voucher_"
				+ VoucherRedeem.voucherId + ".pdf");
	}

	@Test
	private void logoDiffeVerificationTC25() throws IOException {
		VoucherList.fetchingexpectedandactualimg("./src/test/resources/Logos/img3.png");
		VoucherList.comparingExpectedAndActualimg();
	}

	@Test
	private void verifyingFilterFlowTC26() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.iteratingDropDdown();
	}

	@Test
	private void verifyingErrorMsgOfVoucherCreationTC27() throws Exception {
		VoucherCreation.errorMsgValidations();
	}

	@Test
	private void verifyingExportingCsvTC28() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.exportingCSV();
	}

}
