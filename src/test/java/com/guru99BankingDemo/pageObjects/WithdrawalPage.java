package com.guru99BankingDemo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.guru99BankingDemo.testCases.BaseClass;


public class WithdrawalPage extends BaseClass {
	AccountOptionsPage objAccountOptions;

	WebDriver ldriver;

	public WithdrawalPage(WebDriver driver) {
		ldriver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountno")
	WebElement accountNoElement;
	@FindBy(name = "ammount")
	WebElement amountElement;
	@FindBy(name = "desc")
	WebElement descriptionElement;
	@FindBy(name = "AccSubmit")
	WebElement submitBtnElement;
	@FindBy(name = "res")
	WebElement resetBtnElement;

	public void submitWithdrawal(String accountNo,String amount,String description) {
		objAccountOptions = new AccountOptionsPage(ldriver);
		objAccountOptions.withdrawalOption();

		if (accountNo.isEmpty()&&amount.isEmpty()&&description.isEmpty()) {
			submitBtnElement.click();
		} else {
			accountNoElement.sendKeys(accountNo);
			amountElement.sendKeys(amount);
			descriptionElement.sendKeys(description);
			submitBtnElement.click();
		}

	}

	public void resetWithdrawal(String accountNo,String amount,String description) {
		objAccountOptions = new AccountOptionsPage(ldriver);
		objAccountOptions.withdrawalOption();

		accountNoElement.sendKeys(accountNo);
		amountElement.sendKeys(amount);
		descriptionElement.sendKeys(description);
		resetBtnElement.click();

	}
	
	public boolean isAccountNoEmpty() {
		return accountNoElement.getText().isEmpty();
	}

}
