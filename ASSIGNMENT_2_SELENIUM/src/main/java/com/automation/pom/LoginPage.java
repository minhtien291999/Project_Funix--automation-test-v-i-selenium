package com.automation.pom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.PropertiesFileUtils;

public class LoginPage {
	private WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		wait = new WebDriverWait(driver, 30);
	}
	
	public void enterEmail(String email) throws InterruptedException{
		String elementIdentified = PropertiesFileUtils.getProperty("login_email");
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
		inputEmail.sendKeys(email);
		Thread.sleep(2000);
	}
	
	public void enterPassword(String password) throws InterruptedException{
		String elementIdentified = PropertiesFileUtils.getProperty("login_password");
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
		inputEmail.sendKeys(password);
		Thread.sleep(2000);
	}
	
	public void clickSignIn() throws InterruptedException{
		String elementIdentified = PropertiesFileUtils.getProperty("login_signin");
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementIdentified)));
		inputEmail.click();
		Thread.sleep(2000);
	}
	
}