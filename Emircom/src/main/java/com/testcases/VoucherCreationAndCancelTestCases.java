package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;

public class VoucherCreationAndCancelTestCases extends BaseClass {

	/**
	 * Verifying the voucher creation flow as Customer service support
	 */
	@Test(priority = 1, enabled = true)
	public void voucherCreationAsCSSTC13() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri5"), "10-09-1992",
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.heavymechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
	}

	/**
	 * Verifying the voucher creation flow as Guest user
	 */
	@Test(priority = 2, enabled = true)
	public void voucherCreationAsGuestTC14() {
		VoucherCreation.creatingVoucher(LogIn.voucherimg, getproperty("Tri3"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.motorcycle);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecard);

	}

	/**
	 * Verifying the voucher cancellation flow
	 */
	@Test(priority = 3, enabled = true)
	public static void cancellingVoucherTC15() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.cancellingCreatedVoucher("created", "Not satisfied with the service");
	}
}
