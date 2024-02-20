package xpheno;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.drivermanager.DriverManager;
import com.testcases.BaseClass;

public class XphenoSearch extends BaseClass {

	@Test
	public void xphenoSearch() {
		WebElement us = DriverManager.getDriver().findElement(By.xpath("//input[@name='username']"));
		sendKeys(us, "vinoth.shankar@impigertech.com");

		WebElement pw = DriverManager.getDriver().findElement(By.xpath("//input[@name='password']"));
		sendKeys(pw, "impiger#123");

		WebElement submit = DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']"));
		clickElement(submit);

		sleeptime();	

		WebElement csearch = DriverManager.getDriver().findElement(By.xpath("//span[text()='Candidate Search']"));
		clickbyjavascript(csearch);

		randomname("//input[@placeholder='Search Keyword']", "keyword");

		WebElement inputField = DriverManager.getDriver().findElement(By.xpath("//input[@placeholder='Search Keyword']"));
		inputField.sendKeys(Keys.BACK_SPACE);

		WebElement search = DriverManager.getDriver().findElement(By.xpath("//button[text()='Search']"));
		clickElement(search);

		RandomDropDown(
				"//div[@class='search-result-body MuiBox-root css-0']//div//span//input//following::div//div[@class='search-result-candidate-name MuiBox-root css-0']",
				"0");
	}

}
