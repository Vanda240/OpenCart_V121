package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass 
{
	@Test(groups= {"regression","master"})
	public void test_account_Registration() throws IOException
	{
		logger.info("Starting TC_001_AccountRegistration");
		
		try
		{
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		logger.info("Home Page Displayed");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on My Account");
		
		hp.clickRegister();
		logger.info("Clicked on Register");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName("Henry");
		logger.info("Provided First Name");
		
		regpage.setLastName("Fox");
		logger.info("Provided Last Name");
		
		regpage.setEmail(randomestring()+"@gmail.com");
		logger.info("Provided Email");
		
		regpage.setTelephone("2345678901");
		logger.info("Provided Phone Number");
		
		regpage.setPassword("abcdgf");
		logger.info("Provided Password");
		
		regpage.setConfirmPassword("abcdgf");
		logger.info("Provided Confirmed Password");
		
		regpage.setPrivacyPolicy();
		logger.info("Set Private Policy ");
		
		regpage.clickContinue();
		logger.info("Clicked on Continue");
		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Account Registration Success");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Account Registration Failed");
			captureScreen(driver,"test_account_Registration"); //if test case failed it will call the capture screenshot. We have to write this code before assertion because it is hard assertion it will not execute the next line code. 
			Assert.assertTrue(false);
		}
		
		
	}
		catch(Exception e)
		{
			logger.fatal("Account Registration Failed");
			captureScreen(driver,"test_account_Registration");
			Assert.fail();
		}
		
		logger.info("Finished TC_001_AccountRegistration");
		
	}


}
