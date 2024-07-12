package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.Myaccountpage;

public class TC002_LoginTest extends Baseclass {
	
	@Test(groups={"sanity","master"})
	public void verify_login()
	{
		logger.info("**** Starting TC_002_LoginTest  ****");
		logger.debug("capturing application debug logs....");
		try
		{
		//Home page
		Homepage hp=new Homepage(driver);
		hp.clickMyAccount();
		logger.info("clicked on myaccount link on the home page..");
		hp.clickLogin(); //Login link under MyAccount
		logger.info("clicked on login link under myaccount..");
		
		//Login page
		Loginpage lp=new Loginpage(driver);
		logger.info("Entering valid email and password..");
		lp.setEmail(p.getProperty("email"));   // from properties file
		lp.setPassword(p.getProperty("password"));//from properties file
		lp.clickLogin(); //Login button
		logger.info("clicked on login button..");
		
		//My Account Page
		Myaccountpage macc=new Myaccountpage(driver);
				
		boolean targetPage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"Login failed");
		//Assert.assertEquals(targetPage, true);             // this is validation assert 
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**** Finished TC_002_LoginTest  ****");
	}
}

	


