package com.drivermanager;

import java.awt.Robot;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.configproperties.GetProperties;
import com.testcases.BaseClass;

public class Driver {
	public static Select s;
	public static Robot r;
	public static Alert a;
	public static Actions act;
	public static Properties props;
	public static WebDriver driver;
	public static String browserName;
	public static String browserVersion;
    
     
	@Parameters("browsername")
	@BeforeMethod
	/**
	 * Launching the browsers based on the inputs given in the parameters of xml files
	 * 
	 */
	public void invokeApp(String browsername) throws IOException {
		GetProperties.gettingProperties();
		
		if (browsername.equalsIgnoreCase("Chrome")) {
			//Create instance of ChromeOptions Class
			ChromeOptions handlingSSL = new ChromeOptions();
			//Using the accept insecure cert method with true as parameter to accept the untrusted certificate
			handlingSSL.setAcceptInsecureCerts(true);
			//Creating instance of Chrome driver by passing reference of ChromeOptions object
			driver = new ChromeDriver(handlingSSL);
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			browserName = caps.getBrowserName();
			browserVersion = caps.getBrowserVersion();

		} else if (browsername.equalsIgnoreCase("Firefox")) {
			driver = new FirefoxDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			browserName = caps.getBrowserName();
			browserVersion = caps.getBrowserVersion();
			
		} else if (browsername.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			browserName = caps.getBrowserName();
			browserVersion = caps.getBrowserVersion();
		}
		
		BaseClass.pageFactoryInitiation();
		driver.manage().window().maximize();
		driver.get(props.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	//@AfterMethod
	/**
	 * Closing the browsers after every execution
	 * 
	 */
	public void closebrowser() {
		driver.quit();
	}

}
