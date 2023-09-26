package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;

public class VoucherCreationAndCancelTestCases extends BaseClass {

	/**
	 * Verifying the voucher creation flow as Customer service support
	 */
	@Test
	public void voucherCreationAsCSSTC13() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri5"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.heavymechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
	}

	/**
	 * Verifying the voucher creation flow as Guest user
	 */
	@Test
	public void voucherCreationAsGuestTC14() {
		VoucherCreation.creatingVoucher(LogIn.voucherimg, getproperty("Tri3"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.motorcycle);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecard);

	}

	/**
	 * Verifying the voucher cancellation flow
	 */
	@Test
	public static void cancellingVoucherTC15() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.cancellingCreatedVoucher("created", "Not satisfied with the service");
	}
}
