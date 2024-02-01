package com.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.drivermanager.Driver;
import com.reports.ExtentLogger;
import com.reports.ExtentReport;

public class BaseClass extends Driver {

	/**
	 * Fetching Browser Details dynamically
	 */
	public static void browserDetails() {
		ExtentReport.extent.setSystemInfo("Browser Name", browserName);
		ExtentReport.extent.setSystemInfo("Browser Version", browserVersion);
		sleeptime3sec();
	}

	/**
	 * Entering in the textbox Element
	 * 
	 * @param element
	 * @throws InterruptedException
	 */
	public static void sendKeys(WebElement element, String value) {
		element.sendKeys(value);
		try {
			String attribute = element.getAttribute("value");
			String text = element.getAttribute("placeholder");
			if (!attribute.isEmpty()) {
				ExtentLogger.pass(element.getAttribute("value") + " is entered successfully in " + text);
			}
		} catch (WebDriverException e) {
		}
	}

	/**
	 * Clicking the WebElement
	 *
	 * @param element
	 * @throws InterruptedException
	 */
	public static void clickElement(WebElement element) {
		waitForElementToBeClickable(Duration.ofSeconds(10), element);
		element.click();
		try {
			String text = element.getText();
			if (!text.isEmpty()) {
				ExtentLogger.info(text + " is clicked");
			}
		} catch (WebDriverException e) {
		}
	}

	public static void clickbyjavascript(WebElement element) {
		waitForElementToBeClickable(Duration.ofSeconds(10), element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		try {
			String text = element.getText();
			if (!text.isEmpty()) {
				ExtentLogger.info(text + " is clicked");
			}
		} catch (WebDriverException e) {
		}
	}

	/**
	 * verifying presence of the WebElement
	 * 
	 * @param element
	 * @throws InterruptedException
	 */
	public static void elementpresence(WebElement element) {
		element.isDisplayed();
		try {
			String text = element.getText();
			if (!text.isEmpty()) {
				ExtentLogger.info(text + " Element is Present in the application");
			}
		} catch (WebDriverException e) {
		}
	}

	/**
	 * verifying the given URL
	 */
	public void verifystringURL(String url) {
		sleeptime3sec();
		String currenturl = driver.getCurrentUrl();
		assertEquals(url, currenturl);
		ExtentLogger.pass("Current URL: " + currenturl + " is same as the given URL");
	}

	/**
	 * getting screenshot of the page and displays in extent report
	 */
	public static void pageScreenshot() {
		ExtentLogger.pass("Screenshot of the Page", true);
	}

	/**
	 * Mousehovering to the WebElement
	 */
	public static void mousehover(WebElement element) {
		act = new Actions(driver);
		act.moveToElement(element).perform();
		sleeptime3sec();
		try {
			String text = element.getText();
			if (!text.isEmpty()) {
				ExtentLogger.pass("Mousehovered to the element : " + element.getText());
			}
		} catch (WebDriverException e) {
		}
	}

	/**
	 * Scrolling down to the WebElement
	 */
	public static void scrolltoelement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * sleeptime till 5sec
	 */
	public static void sleeptime() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void keyactions(int key) {
		sleeptime3sec();
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		r.keyPress(key);
		r.keyRelease(key);
		sleeptime3sec();

	}

	/**
	 * Switching into frames with webelement
	 */
	public static void switchtoiframe(WebElement element) {

		driver.switchTo().frame(element);
		sleeptime3sec();

	}

	/**
	 * Fetching the details from config.property file
	 */
	public static String getproperty(String propkey) {
		return props.getProperty(propkey);
	}

	/**
	 * Switching to the child window
	 */
	public static void switchtochildwindow() {
		String originalWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				sleeptime();
				break;
			}
		}

	}

	/**
	 * Sleep time derived for 3secs
	 */
	public static void sleeptime3sec() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copying a text to the clip board
	 * 
	 * @param path : path you need to copy
	 */
	public static void copyText(String path) {

		StringSelection ss = new StringSelection(path);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

	}

	/**
	 * Dragging a element with x and y offsets
	 * 
	 * @param ele  : refers to the element
	 * @param xhor : to move the element horizontally
	 * @param yver : to move the element vertically
	 */
	public static void draganddropwithoffset(WebElement ele, int xhor, int yver) {

		Actions a = new Actions(driver);

		a.dragAndDropBy(ele, xhor, yver);

		a.build().perform();

	}

	/**
	 * To perform CTRL+V action
	 * 
	 * @throws AWTException
	 */
	public static void ctrlVAction() throws AWTException {

		sleeptime3sec();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);

		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);

		keyactions(KeyEvent.VK_ENTER);
		sleeptime3sec();

	}

	/**
	 * To perform CTRL+V action
	 * 
	 * @throws AWTException
	 */
	public static void ctrlJAction() throws AWTException {
		sleeptime3sec();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_J);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_J);
		sleeptime3sec();

	}

	/**
	 * Openning New Tab
	 * 
	 * @throws AWTException
	 */
	public static void newTab() throws AWTException {
		sleeptime3sec();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_T);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);
		keyactions(KeyEvent.VK_ENTER);
		sleeptime3sec();

	}

	/**
	 * Wait for the WebElement explicity until the Element to be clickable
	 * 
	 * @param seconds Mention seconds
	 */
	public static void waitForElementToBeClickable(Duration seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Wait for the WebElement explicity until the Element to be disappeared
	 * 
	 * @param seconds Mention Seconds
	 */
	public static void waitForElementToBeDisappear(Duration seconds, By element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**
	 * Wait for the WebElement explicity until the Element to be appeared
	 * 
	 * @param seconds mention seconds
	 */
	public static void waitForElementToBeAppear(Duration seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Dropdow select by index
	 * 
	 * @param value
	 * @param id
	 */
	public static void selectbyindex(WebElement element, int index) {
		s = new Select(element);
		s.selectByIndex(index);
	}

	

	public void mousehoverandclickbyxpath(String xpathVal) throws MoveTargetOutOfBoundsException {

		WebElement l = driver.findElement(By.xpath(xpathVal));
		// object of Actions class
		Actions a = new Actions(driver);
		// move to element
		a.moveToElement(l);
		a.perform();
		WebElement lq = driver.findElement(By.xpath(xpathVal));
		lq.click();
	}

	public By verifyTextByXpath(By xpathVal) {

		String defalutlantext22 = driver.findElement(xpathVal).getText();

		if (defalutlantext22.equals("Languages")) {

			System.out.println("The Defalut text shown on language selection is Languages");
		} else {
			System.out.println(
					"The defalut selected content language is not all and displayed language is" + defalutlantext22);
		}

		return xpathVal;

	}

	public void clickByXpath(By passbtn) {
		try {
			driver.findElement(passbtn).click();
			System.out.println("The element of xpath " + passbtn + " is clicked");
		} catch (NoSuchElementException e) {
			System.err.println("The element of xpath " + passbtn + " is not available in the DOM ");

		} catch (StaleElementReferenceException e) {
			System.err.println("The element of  xpath " + passbtn + " is modified ");
		} catch (ElementClickInterceptedException e) {
			System.err.println("The Element of xpath " + passbtn + "is not clickable");
		} catch (ElementNotInteractableException e) {
			System.err.println("The element of xpath " + passbtn + " it not interactable in the application");
		}

	}

	public void verifycountoflist(String xpathVal, int no) {
		String[] options = driver.findElement(By.xpath(xpathVal)).getText().split("\n");
		System.out.println(options);
		int count = options.length;
		if (count == no) {
			System.out.println("Count of the elements = True");
		} else {
			System.out.println("Count of the elements = Fail ");
			fail();

		}

	}

	public void listoflanguage(String xpathVal) {

		String options = driver.findElement(By.xpath(xpathVal)).getText();
		System.out.println(options);

	}

	public void clickByXpathwithoutexception(By xpathVal) {
		driver.findElement(xpathVal).click();

	}

	public void enterByXpath(By xpathValue, String data) {

		WebElement ele = driver.findElement(xpathValue);
		ele.clear();
		ele.sendKeys(data);
		System.out.println("The element of xpath " + xpathValue + " entered with data " + data);

	}

	public String errormsgByXpath(String xpathVal) {

		String errmsg = driver.findElement(By.xpath(xpathVal)).getText();
		System.out.println("Error validation displayed" + errmsg);
		return errmsg;

	}

	public static void gettext(WebElement element) {

		String text = element.getText();
		System.out.println(text);
	}

	public static void selectbyvalue(WebElement element, String value) {
		s = new Select(element);
		s.selectByValue(value);

	}

	public static void selectbytext(WebElement element, String txt) {
		s = new Select(element);
		s.selectByVisibleText(txt);

	}

	public static void alertaccept() {
		a = driver.switchTo().alert();
		a.accept();
	}

	public static void alertdismiss() {
		a = driver.switchTo().alert();
		a.dismiss();
	}

	public static void alertsendkeys(String txt) {
		a = driver.switchTo().alert();
		a.sendKeys(txt);
	}

	public static String excelPass(String sheetName, int row, int cell) throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\ranjithkumar.sivakum\\maven\\ExcelData\\TC1.xlsx");

		Workbook book = new HSSFWorkbook(fis);

		Sheet sheet = book.getSheet(sheetName);

		Row r = sheet.getRow(row);

		String pass = r.getCell(cell).getStringCellValue();

		book.close();

		return pass;

	}

	public static void screenshot(String filepath) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);
		FileUtils.copyFile(srcFile, destFile);

	}

}
