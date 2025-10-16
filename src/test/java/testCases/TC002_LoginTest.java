package testCases;

import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Sanity","Master"})
	public void Logintest() {

		logger.info("**Starting TC002 Login Test**");
		try {
			// HomePage
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			hm.clickLogin();

			// LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			lp.setPassword(prop.getProperty("password"));
			lp.clickLogin();

			// MyAccountPage
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExist();
			Assert.assertEquals(targetPage, true);

		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("**Finished TC002 Login Test**");
	}
}
