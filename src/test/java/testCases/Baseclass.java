package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Baseclass {
	
	public Logger logger;   // create logger class variable
	public Properties p;  //create properties class variable
	
	public static WebDriver driver;   //driver in objects and this will be diff so

	
	@BeforeClass(groups= {"sanity","regression","master"})
	
	@Parameters({"os","browser"})
	
	public void setup( String os,String br) throws IOException
	{
		//load config.properties file 
		FileReader file = new FileReader(".//src//test//resources//config.properties"); // file reader object with file name to read data
		p = new Properties(); //creating object of properties class
		p.load(file);  // loading file now
		
		
		
		
		
		
		
		logger = LogManager.getLogger(this.getClass()); // we run many classes so can't give one specific class
		 
		 //this will fetch from log4j2.xml file and get into var logger automatically
		
	/*	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			driver=new RemoteWebDriver (new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			//case "firefox": driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		
		*/
		
		
		switch(br.toLowerCase())
		{
		case "chrome":
			driver = new ChromeDriver(); 
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Invalid browser");
			return; // return bcz if there is not valid browser no need to go further
		}
		  // like a = 10; no need of int a= 10; again
	driver.manage().deleteAllCookies(); // deletes all cookies
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(p.getProperty("appURL2")); // preading url from config.properties file
	driver.manage().window().maximize();
}
	@AfterClass (groups= {"sanity","regression","datadriven"} )  //after only once
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomestring()   // random string of characters mentioned
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
		
		}
		public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot ts = (TakesScreenshot) driver;
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
				
			return targetFilePath;

		}

}
