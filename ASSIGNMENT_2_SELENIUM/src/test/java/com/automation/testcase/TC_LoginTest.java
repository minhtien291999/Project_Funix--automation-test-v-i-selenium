package com.automation.testcase;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {

	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws InterruptedException {
		String Url = PropertiesFileUtils.getProperty("url");
		driver.get(Url);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		// click signIn
		 String icon_signup = PropertiesFileUtils.getProperty("icon_signup");
		WebElement iconSignIn = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(icon_signup)));
		iconSignIn.click();
		Thread.sleep(2000);

		LoginPage loginPage = new LoginPage(driver);
		PageFactory.initElements(driver, loginPage);

		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();

		// Kiểm tra signOut
		String SignOut = PropertiesFileUtils.getProperty("signout");
		WebElement signOutElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SignOut)));

		signOutElement.click();
		Thread.sleep(1000);

	}

	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {
		// đọc dữ liệu đầu vào từ file excel
		FileInputStream file = new FileInputStream("D:\\chromeDriver\\assignment2_data_test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet loginSheet = workbook.getSheet("login");
		int numberOfRowData = loginSheet.getPhysicalNumberOfRows();
		// có 2 cột , username, password.
		Object[][] data = new Object[numberOfRowData][2];

		for (int i = 0; i < numberOfRowData; i++) {
			XSSFRow row = loginSheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = username.getStringCellValue();// row i col 0
			data[i][1] = password.getStringCellValue();// row i col 0
		}
		return data;

	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		// ITestResult để lấy trạng thái và tên và tham số của từng Test Case
		// phương thức này sẽ được gọi mỗi khi @Test thực thi xong,
		// ta có thể kiểm tra kết quả testcase tại đây
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				// 1. Lấy tham số (parameters) đầu vào của TC vừa chạy
				// email:0, password:1
				String email = (String) result.getParameters()[0];
				// 2. Lấy ra phần tên trong email để làm tên của screenshot
				// Tìm vị trí(int index) của ký tự ‘@’ và lấy ra chuỗi con
				// đứng trước @ qua thư viện của String là: indexOf() và subString()
				int index = email.indexOf("@");
				String accountName = email.substring(index);
			} catch (Exception e) {
				System.out.println("Lỗi xảy ra screenshot " + e.getMessage());
			}

		}

	}
}
