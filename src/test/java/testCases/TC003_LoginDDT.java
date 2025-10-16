package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="datadriven") // dataproviders class is present in
																				// another package so importing that
																				// class
	public void verify_login_DDT(String email, String pwd, String exp) throws InterruptedException {
		logger.info("Started TC003_LoginDDT Test");
		
		try {
		// HomePage
		HomePage hm = new HomePage(driver);
		hm.clickMyAccount();
		hm.clickLogin();

		// LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();

		// MyAccountPage
		MyAccountPage myacc = new MyAccountPage(driver);
		boolean targetPage = myacc.isMyAccountPageExist();

		// Data is valid - login is success - test is passed- Logout
		//                 - login is failed - test is failed

		// Data is invalid - login is success -test is failed - logout
		//                 - login is failed - test is passed

		if (exp.equalsIgnoreCase("valid")) {

			if (targetPage == true) {

				myacc.clickLogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}

		}
		if (exp.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {

				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}

		}
		}catch(Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("Completed TC003_LoginDDT Test");

	}

}
