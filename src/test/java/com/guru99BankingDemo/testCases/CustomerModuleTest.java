package com.guru99BankingDemo.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99BankingDemo.pageObjects.DeleteCustomerPage;
import com.guru99BankingDemo.pageObjects.EditCustomerPage;
import com.guru99BankingDemo.pageObjects.NewCustomerPage;
import com.guru99BankingDemo.utilities.Config;
import com.guru99BankingDemo.utilities.XLUtils;

public class CustomerModuleTest extends BaseClass {

	String name = randomestring();
	String gender = "male";
	String day = "22";
	String month = "11";
	String year = "1998";
	String address = randomestring();
	String city = randomestring();
	String state = randomestring();
	String pin = "199124";
	String mobile = randomeNum();
	String email = randomestring() + "@gmail.com";
	String password = "12345";

	String AddCustomerSuccessMessage = "Customer Registered Successfully!!!";

	NewCustomerPage objNewCustomer;
	EditCustomerPage objEditCustomer;
	DeleteCustomerPage objDeleteCustomer;
	Config config;

	/* Add New Customer Part */

	@Test(priority = 1, description = "Manager can add new Customer using valid data")
	public void addCustomerUsingValidData() throws InterruptedException, IOException {

		objNewCustomer = new NewCustomerPage(driver);
		config = new Config();

		loginToTheApplication();
		objNewCustomer.submitNewCustomerData(name, gender, month, day, year, address, city, state, pin, mobile, email,
				password);
		Thread.sleep(2000);
		if (driver.getPageSource().contains(AddCustomerSuccessMessage)) {
			logger.info("Test Passed");
			config.setCustomerId(driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText());
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreen(driver, "addCustomerUsingValidData");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 2, dataProvider = "NewCustomerDataProvider", description = "Manager can Reset Customer Form")
	public void resetCustomerForm(String name, String gender, String month, String day, String year, String address,
			String city, String state, String pin, String mobile, String email, String password)
			throws InterruptedException, IOException {
		objNewCustomer = new NewCustomerPage(driver);
		loginToTheApplication();
		objNewCustomer.resetNewCustomerData(name, gender, month, day, year, address, city, state, pin, mobile, email,
				password);
		Thread.sleep(2000);
		if (objNewCustomer.isCustomerNameEmpty()) {
			logger.info("Test passed");
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "resetCustomerForm");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 3, dataProvider = "NewCustomerDataProvider", description = "Manager can't add new Customer using valid data")
	public void addCustomerUsingInvalidData(String name, String gender, String month, String day, String year,
			String address, String city, String state, String pin, String mobile, String email, String password)
			throws InterruptedException, IOException {

		objNewCustomer = new NewCustomerPage(driver);
		loginToTheApplication();
		objNewCustomer.submitNewCustomerData(name, gender, month, day, year, address, city, state, pin, mobile, email,
				password);
		Thread.sleep(2000);
		if (isAlertPresent() == true) {
			logger.info("Test Passed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);

		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "addCustomerUsingInvalidData");
			Assert.assertTrue(false);
		}

	}

	/* Edit Customer Part */

	@Test(priority = 4, description = "Manager can edit Customer using valid Customer Id")
	public void editCustomerUsingValidId() throws InterruptedException, IOException {
		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.editCustomer(customerId);
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Edit Customer")) {
			logger.info("Test Passed");
			Assert.assertTrue(true);

		} else {
			logger.warn("Test Failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreen(driver, "editCustomerUsingValidId");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 5, description = "Manager can reset Edit Customer Id form")
	public void resetEditCustomerIdForm() throws InterruptedException, IOException {
		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.resetCustomer(customerId);
		Thread.sleep(2000);
		if (objEditCustomer.isCustomerIdFieldEmpty()) {
			logger.info("Test Passed");
			Assert.assertTrue(true);

		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "resetEditCustomerForm");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 6, description = "Manager can edit Customer using valid Customer Data")
	public void editCustomerUsingValidData() throws InterruptedException, IOException {
		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.editCustomerData(address, city, state, pin, mobile, email);
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Customer details updated Successfully!!!")) {
			logger.info("Test Passed");
			Assert.assertTrue(true);

		} else {
			logger.warn("Test Failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			captureScreen(driver, "editCustomerUsingValidData");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 7, description = "Manager can reset Edit Customer data form")
	public void resetCustomerDataForm() throws InterruptedException, IOException {
		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.resetCustomerData(address, city, state, pin, mobile, email);
		Thread.sleep(2000);
		if (driver.getPageSource().contains("Customer details updated Successfully!!!")) {
			logger.warn("Test failed");
			captureScreen(driver, "resetCustomerDataForm");
			Assert.assertTrue(false);

		} else {
			logger.info("Test passed");
			Assert.assertTrue(true);
		}

	}

	@Test(priority = 8, dataProvider = "EditDeleteCustomerDataProvider", description = "Manager can't edit Customer info using Invalid Customer Id")
	public void editCustomerUsingInvalidId(String customerId) throws InterruptedException, IOException {

		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.editCustomer(customerId);
		Thread.sleep(2000);

		if (isAlertPresent()) {
			logger.info("Test passed");
			driver.switchTo().alert().accept();
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "editCustomerUsingInvalidId");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 9, description = "Manager can't edit Customer Name,Gender,Date Of Birth")
	public void nameGenderBirthDateEditable() throws InterruptedException, IOException {
		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		if (objEditCustomer.isNameGenderBithDateEditable() == false) {
			logger.info("Test passed");
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "nameGenderBirthDateEditable");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 10, dataProvider = "EditCustomerDataProvider", description = "Manager can't edit Customer info using Invalid Customer data")
	public void editCustomerUsingInvalidData(String address, String city, String state, String pin, String mobile,
			String email) throws InterruptedException, IOException {

		objEditCustomer = new EditCustomerPage(driver);
		loginToTheApplication();
		objEditCustomer.editCustomerData(address, city, state, pin, mobile, email);
		Thread.sleep(2000);

		if (isAlertPresent()) {
			logger.info("Test passed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "editCustomerUsingInvalidData");
			Assert.assertTrue(false);
		}

	}

	/* Delete Customer Part */

	@Test(priority = 11, description = "Manager can delete Customer info using valid Customer Id")
	public void deleteCustomerUsingValidId() throws InterruptedException, IOException {

		objDeleteCustomer = new DeleteCustomerPage(driver);
		loginToTheApplication();
		objDeleteCustomer.deleteCustomer(customerId);
		driver.switchTo().alert().accept();
		if (driver.switchTo().alert().getText().contains("Customer deleted Successfully")) {
			logger.info("Test passed");
			driver.switchTo().alert().accept();
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "deleteCustomerUsingValidId");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 12, dataProvider = "EditDeleteCustomerDataProvider", description = "Manager can't delete Customer info using Invalid Customer Id")
	public void deleteCustomerUsingInvalidId(String customerId) throws InterruptedException, IOException {

		objDeleteCustomer = new DeleteCustomerPage(driver);
		loginToTheApplication();
		objDeleteCustomer.deleteCustomer(customerId);
		Thread.sleep(2000);

		if (isAlertPresent() == false) {
			logger.info("Test passed");
			Assert.assertTrue(true);
		} else if (driver.switchTo().alert().getText().contains("Please fill all fields")) {
			logger.info("Test passed");
			driver.switchTo().alert().accept();
			Assert.assertTrue(true);
		} else if (driver.switchTo().alert().getText().contains("Do you really want to delete this Customer?")) {
			driver.switchTo().alert().accept();
			if (driver.switchTo().alert().getText().contains("Customer does not exist!!")) {
				logger.info("Test passed");
				driver.switchTo().alert().accept();
				Assert.assertTrue(true);
			} else if (driver.switchTo().alert().getText().contains("Customer deleted Successfully")) {
				logger.warn("Test Failed");
				captureScreen(driver, "deleteCustomerUsingInvalidId");
				Assert.assertTrue(false);
			}

		}

	}

	@Test(priority = 13, description = "Manager can reset Delete Customer Form")
	public void resetDeleteCustomerForm() throws InterruptedException, IOException {

		objDeleteCustomer = new DeleteCustomerPage(driver);
		loginToTheApplication();
		objDeleteCustomer.resetCustomer(customerId);
		if (objDeleteCustomer.isCustomerIdFieldEmpty()) {
			logger.info("Test passed");
			Assert.assertTrue(true);
		} else {
			logger.warn("Test Failed");
			captureScreen(driver, "resetDeleteCustomerForm");
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "NewCustomerDataProvider")
	Object[][] getNewCustomerData() throws IOException {
		String path = System.getProperty("user.dir")
				+ "/src/test/java/com/guru99BankingDemo/testData/CustomerModuleData.xlsx";

		int rowNum = XLUtils.getRowCount(path, "NewCustomerData");
		int colCount = XLUtils.getCellCount(path, "NewCustomerData", 1);
		Object[][] customerData = new Object[rowNum][colCount];

		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colCount; j++) {
				customerData[i - 1][j] = XLUtils.getCellData(path, "NewCustomerData", i, j);
			}

		}
		return customerData;
	}

	@DataProvider(name = "EditDeleteCustomerDataProvider")
	Object[][] getDeleteCustomerData() throws IOException {
		String path = System.getProperty("user.dir")
				+ "/src/test/java/com/guru99BankingDemo/testData/CustomerModuleData.xlsx";

		int rowNum = XLUtils.getRowCount(path, "EditDeleteCustomerIdData");
		int colCount = XLUtils.getCellCount(path, "EditDeleteCustomerIdData", 1);
		Object[][] customerData= new Object[rowNum][colCount];

		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colCount; j++) {
				customerData[i - 1][j] = XLUtils.getCellData(path, "EditDeleteCustomerIdData", i, j);
			}

		}
		return customerData;
	}

	@DataProvider(name = "EditCustomerDataProvider")
	Object[][] getEditCustomerIdData() throws IOException {
		String path = System.getProperty("user.dir")
				+ "/src/test/java/com/guru99BankingDemo/testData/CustomerModuleData.xlsx";

		int rowNum = XLUtils.getRowCount(path, "EditCustomerData");
		int colCount = XLUtils.getCellCount(path, "EditCustomerData", 1);
		Object[][] customerData = new Object[rowNum][colCount];

		for (int i = 1; i <= rowNum; i++) {
			for (int j = 0; j < colCount; j++) {
				customerData[i - 1][j] = XLUtils.getCellData(path, "EditCustomerData", i, j);
			}

		}
		return customerData;
	}

}
