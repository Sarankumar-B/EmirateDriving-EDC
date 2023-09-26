package com.testcases;

import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherRedeem;

public class RedeemingAndDecliningTestCases extends BaseClass {

	/**
	 * Verifying the redeeming flow 
	 */
	@Test
	public void redeemingVoucherTC16() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri2"), getproperty("Age19"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightmechequipment);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("SecurityUsr"), getproperty("SecurityPwd"));
		VoucherRedeem.clickingRedeemOption(VoucherRedeem.voucherId);
	}

	/**
	 * Verifying the declining flow
	 */
	@Test
	public void decliningVoucherTC17() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherCreation.creatingVoucher(VoucherCreation.createvoucherbtn, getproperty("Tri4"), getproperty("Age31"),
				getproperty("Add1"), getproperty("Add2"), getproperty("Pincode"), VoucherCreation.lightVehicle);
		VoucherCreation.verifyingvoucheramount(VoucherCreation.paymenttypecash);
		VoucherRedeem.gettingvoucherid();
		VoucherCreation.clickLogOut();
		LogIn.loginFlow(getproperty("SecurityUsr"), getproperty("SecurityPwd"));
		VoucherRedeem.clickingDeclineOption();
	}
}
