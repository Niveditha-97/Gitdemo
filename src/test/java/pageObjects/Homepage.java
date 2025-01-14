package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

	
		WebDriver driver;
		
		public Homepage(WebDriver driver)
		{
			this.driver= driver;
			PageFactory.initElements(driver, this);
			}
		
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement lnkMyaccount;

	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;

	@FindBy(linkText = "Login")   // Login link added in step5
	WebElement linkLogin;


	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}

	public void clickRegister()
	{
		lnkRegister.click();
	}


	public void clickLogin()    // added in step5
	{
		linkLogin.click();
	}



	}


