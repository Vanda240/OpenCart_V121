package testBase;

import java.io.File;
import java.io.IOException;

//We create Base Class to put reusable methods which will be used in test cases.

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
public WebDriver driver;
public Logger logger;
public ResourceBundle rb;

	@BeforeClass(groups= {"regression","master","sanity"}) 
	@Parameters({"browser"})
	public void setup(String br)
	{
		//Loading config.properties
		rb=ResourceBundle.getBundle("config");
		
		//Logging
		
		logger=LogManager.getLogger(this.getClass()); //'this' means current case class(TC_001_AccountRegistration) '.getClass()' means current class name.
		
		
		//driver instance
		if(br.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup(); //launch browser
		driver=new ChromeDriver();
		logger.info("Launched Chrome Browser");
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup(); //launch browser
			driver=new EdgeDriver();
			logger.info("Launched Edge Browser");
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup(); //launch browser
			driver=new FirefoxDriver();
			logger.info("Launched Firefox Browser");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass(groups= {"regression","master","sanity"}) 
	public void tearDown()
	{
	driver.close();
	}
	
	
	public String randomestring() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5); //generate random string
		return (generatedString);
	}
	
	public int randomeNumber() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(5); //generate random numbers
		return (Integer.parseInt(generatedString2));
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
	}

	


}
