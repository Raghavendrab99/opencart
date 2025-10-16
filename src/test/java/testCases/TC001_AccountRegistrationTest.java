package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
    @Test(groups={"Regression","Master"})
	public void verify_account_registration() {
    	logger.info("****Starting TC001_AccountRegistrationTest****");
    	
    	try {
    	
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("****clicked on My account link****");
		hp.clickRegister();
		logger.info("****clicked on My Register link****");
		
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("****Providing customer details****");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		
	String password = randomAlphanumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		
		logger.info("****Validating expected message****");
		String confirmmessage = regpage.getConfirmationMessage();
		Assert.assertEquals(confirmmessage, "Your Account Has Been Created!");
		
    	} catch(Exception e) {
    		logger.error("Test Failed...");
    	}
    	
    	logger.info("****Finished TC001_AccountRegistrationTest****");
	}
    
  
}
