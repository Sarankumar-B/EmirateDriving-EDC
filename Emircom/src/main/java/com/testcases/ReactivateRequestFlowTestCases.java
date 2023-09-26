package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.ReactivateRefund;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherRedeem;

public class ReactivateRequestFlowTestCases extends BaseClass {

	/**
	 * Verifying the reactivate approval flow
	 */
	@Test
	public void verifyingReactivateApprovalFlowTC18() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		ReactivateRefund.raisingReactivateRequest();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("FinanceUsr"), getproperty("ValidPwd"));
		ReactivateRefund.finvoucherclick();
		ReactivateRefund.reactivateOrDecline(ReactivateRefund.reactivateapprove, "Approved");
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		ReactivateRefund.verifyVoucherStatus(ReactivateRefund.reactivated);
	}

	/**
	 * Verifying the reactivate rejection flow
	 */
	@Test
	public void verifyingReactivateRejectFlowTC19() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri2"), getproperty("Age19"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightmechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("SecurityUsr"), getproperty("SecurityPwd"));
		VoucherRedeem.clickingRedeemOption(VoucherRedeem.voucherId);
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		ReactivateRefund.raisingReactivateRequest();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("FinanceUsr"), getproperty("ValidPwd"));
		ReactivateRefund.finvoucherclick();
		ReactivateRefund.reactivateOrDecline(ReactivateRefund.reactivatereject, "Rejected");
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		ReactivateRefund.verifyVoucherStatus(ReactivateRefund.reactivaterejected);
	}

}
