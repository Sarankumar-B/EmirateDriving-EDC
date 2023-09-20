package com.testcases;

import static org.testng.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.pageobjectmodel.LogIn;
import com.pageobjectmodel.VoucherCreation;
import com.pageobjectmodel.VoucherList;
import com.pageobjectmodel.VoucherRedeem;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class VoucherListTestCases extends BaseClass {

	@Test(priority = 1, enabled = false)
	public static void allPageNavigationAndVerifyingEntriesTC22() {
		LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));
		VoucherList.pageNavigatingandverifying();
	}

	@Test(priority = 2,  enabled = false)
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
		VoucherList.clickingDownload();
		VoucherList.downloadFileVerification("C:\\Users\\ranjithkumar.sivakum\\Downloads\\voucher_"+VoucherRedeem.voucherId+".pdf");
	}

	@Test
	private void logoDiffeVerificationTC25() throws IOException {
		String expectedImagePath = "./src/test/resources/Logos/img3.png";
		File expectedImageFile = new File(expectedImagePath);
		BufferedImage expectedImage = ImageIO.read(expectedImageFile);
		WebElement elementToCapture = driver.findElement(By.xpath("//img[@alt='LOGO']"));
		File screenshot = elementToCapture.getScreenshotAs(OutputType.FILE);
		BufferedImage read = ImageIO.read(screenshot);
		File output = new File("./src/test/resources/Screenshot/logopresent.png");
		ImageIO.write(read, "PNG", output);
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, read);
		if (diff.hasDiff()) {
			System.out.println("Images are NOT same");
			fail();
		} else {
			System.out.println("Images are same");
		}
	}
}
