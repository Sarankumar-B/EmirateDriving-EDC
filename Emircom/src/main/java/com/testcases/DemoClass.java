package com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.pageobjectmodel.LogIn;

public class DemoClass extends BaseClass {

	
	
		
		public static void main(String[] args) {
			
			LogIn.loginFlow(getproperty("ValidUsr"), getproperty("ValidPwd"));

			driver.findElement(By.xpath("//button[contains(.,'Filter')]")).click();
			
			String dropdown1Id = "//select[@name='vehicle_type']//option[not(text()='-- Select --')]";
			Select dropdown1 = new Select(driver.findElement(By.id(dropdown1Id)));
			for (WebElement option1 : dropdown1.getOptions()) {
				option1.click();
			}
		}

}
