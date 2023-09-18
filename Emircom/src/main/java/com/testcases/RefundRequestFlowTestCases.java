package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.ReactivateRefund;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherRedeem;

public class RefundRequestFlowTestCases extends BaseClass {

	/**
	 * Verifying the refund approval flow
	 */
	@Test(priority = 1)
	public void refundApproveTC20() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri2"), getproperty("Age19"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.heavyvehicle);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		ReactivateRefund.fetchingOtp();
		ReactivateRefund.enteringOtpandRaisingRefund();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("FinanceUsr"), getproperty("ValidPwd"));
		ReactivateRefund.approvingRefundAsfinance();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("CashierUsr"), getproperty("ValidPwd"));
		ReactivateRefund.approvingRefundAscashier();
		VoucherRedeem.searchVoucherId();
		elementpresence(ReactivateRefund.refunded);
	}

	/**
	 * Verifying the refund rejection flow
	 */
	@Test(priority = 2)
	public void refundRejectTC21() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri2"), getproperty("Age19"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.heavyvehicle);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		ReactivateRefund.fetchingOtp();
		ReactivateRefund.enteringOtpandRaisingRefund();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("FinanceUsr"), getproperty("ValidPwd"));
		ReactivateRefund.decliningRefundAsFinancier();
		VoucherRedeem.searchVoucherId();
		elementpresence(ReactivateRefund.refundrejectedinlist);
	}
}