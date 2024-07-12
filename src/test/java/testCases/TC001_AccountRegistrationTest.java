package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistartionpage;

import pageObjects.Homepage;

public class TC001_AccountRegistrationTest extends Baseclass
{
	/*(public WebDriver driver;      // declaring variable as class var  int a; public so it can used anywhere
	@BeforeClass   // because only once
	public void setup()   // public so it can be accessed throughout project
	{
		driver = new ChromeDriver();   // like a = 10; no need of int a= 10; again
	driver.manage().deleteAllCookies(); // deletes all cookies
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://tutorialsninja.com/demo/");
	driver.manage().window().maximize();
}

	@AfterClass    //after only once
	public void teardown()
	{
		driver.quit();
	}*/  // common methods so base class
	
	//actual test method
	@Test(groups ={"regression","master"})
	public void verify_account_registration()
	{
		logger.info("*****************Starting TC001_AccountRegistrationTest.java *********** ");
		//we need to access clickacc n clickregister methods from poc homepage
		try// because if we get failed in any line
		{
		Homepage hp = new Homepage(driver);   //creating a object of the homepage class and it has constructor to be invoked
		hp.clickMyAccount();
		logger.info("Clicked on myacc link");
		hp.clickRegister();
		logger.info("Clicked on register link");

		//create object of accreg page and call all methods of that page 
		
		AccountRegistartionpage arp = new AccountRegistartionpage(driver);
		
		logger.info("providing customer details");
		
		
		
		//arp.setFirstName("John");   
		arp.setFirstName(randomestring().toUpperCase());   // to create random name n creates only small case so to change to upper
		arp.setLastName("Williams");
		arp.setEmail(randomestring()+"@gmail.com");    //randomly generate email instead of static data - calling method of same classso  called directly
		//arp.setPassword("ValliDore@123");
		//arp.setPassword(randomepassword());
		//arp.setConfirmPassword("ValliDore@123");
		//arp.setConfirmPassword(randomepassword());  // this is will be issue since every time that method is called it gives diff pw and it doesn't match with original pw  
		//so 
		String pwd = randomepassword(); //store it in a var n then pass that to both
		
		arp.setPassword(pwd);
		arp.setConfirmPassword(pwd);
		
		
		//arp.setTelephone("8762873530"); // we can give random numbers also through random method
		arp.setTelephone(randomenumber());
		arp.setPrivacyPolicy();
		arp.clickContinue();
		//action is done n now validation
		
		logger.debug("validation");
		
		String confirmmsg = arp.getConfirmationMsg();  // we should get the msg returned and validate
		if(confirmmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
			logger.info("Test passed");
		}
		else
		{
			logger.error("Testfailed");
			
			Assert.assertFalse(true);
		}
		
	
		
		//it gives error since once the email is entered again user is alreay registered so create userdefined method to pass random data
	
	
	/*public String randomestring()   // random string of characters mentioned
	{
		String generatedstring =  RandomStringUtils.randomAlphabetic(6);   //RandomStringUtils is a predefined class in commons library not directly java so dependency(commons lan3)
	return generatedstring;       //return the string generted
	
	}                                                  
	public String randomenumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);   // RandomStringUtils gives String only always that with 10 numbers
		return generatednumber;
	}
		public String randomepassword()   // since combination of num and alpha
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(5);
			String generatednumber = RandomStringUtils.randomNumeric(10);
			//String generatedpw = generatedstring+ generatednumber;
				String generatedpw = generatedstring+"@"+ generatednumber; //built in for only num n char not for symbol or spl char
			return generatedpw;
		
		}*/  // base class
	}
	catch(Exception e)
	{
		
		Assert.fail();
	}
		logger.info("*****************ending TC001_AccountRegistrationTest.java *********** ");	
}
	
}


