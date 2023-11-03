package Login_HomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/*import io.github.bonigarcia.wdm.WebDriverManager;*/

public class Login_Test {
	
	static WebDriver driver;
	Login_Page LP;
	TakeScreenshot shot = new TakeScreenshot(driver);
	static ExtentReports reports;
	static ExtentTest test;
	Properties prop, prop2;
	String Env_URL = null;
	String QA= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	String Stage= "https://web-staging.diathrive.com/";
	String UAT= "http://www.facebook.com";
	
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("BeforeSuite");
		System.out.println(System.getProperty("user.dir"));
		reports = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReports\\Login_Test.html",false);
		test = reports.startTest("Login_Test started");
	}
	@BeforeClass
	public static void startTest()
	{
		System.out.println("Login_Test started");
	}
	
	@BeforeTest
	@Parameters({"browser" })
	
	public  void beforeTest(String browser)throws InterruptedException, IOException
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
			/* WebDriverManager.chromedriver().setup(); */
			System.out.println("Chrome browser is opened");
			
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
			/* WebDriverManager.edgedriver().setup(); */
			System.out.println("Edge browser is opened");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		File file1 = new File("Config\\environment.Properties");
		FileInputStream fileInput1 = null;
		try
		{
			fileInput1 = new FileInputStream(file1);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		prop = new Properties();
		prop.load(fileInput1);
		
		Env_URL = prop.getProperty("CurrentExecution");
		System.out.println("current exeution environment is " +Env_URL);
		
		if(Env_URL.contains("orangehrmlive"))
			{
				System.out.println("Accessing orangehrmlive environment"+Env_URL);
				driver.get(Env_URL);
				Thread.sleep(2000);
			}
		else if(Env_URL.contains("UAT"))
			{
				System.out.println("accessing UAT environment");
				driver.get(Env_URL);
				Thread.sleep(2000);
			}
		
		else if (Env_URL.contains("web-staging"))
			{
				System.out.println("accessing stage environment");
				driver.get(Env_URL);
				Thread.sleep(2000);
			}
		LP = new Login_Page(driver);
		Thread.sleep(2000);
		
		File file2 = new File("Config\\login.Properties");
		FileInputStream fileInput2 = null;
		
		try
		{
			fileInput2 = new FileInputStream(file2);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		prop = new Properties();
		prop.load(fileInput2);
	}
	
	@Test (priority =1)
	public void Login() throws Exception
	{
		try
		{
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");
			
			LP.username(prop.getProperty("userName"));
			Thread.sleep(2000);
			System.out.println("Entered  suername");
			test.log(LogStatus.PASS, "Entered username");
			
			LP.password(prop.getProperty("passWord"));
			Thread.sleep(2000);
			System.out.println("Entered  passWord");
			test.log(LogStatus.PASS, "Entered passWord");
			
			LP.login();
			Thread.sleep(2000);
			System.out.println("Clicked on login button");
			test.log(LogStatus.PASS, "Clicked on login button");
			
			/*
			 * LP.homepage(); Thread.sleep(2000); System.out.println("We are in homepage");
			 * test.log(LogStatus.PASS, "We are in homepage");
			 */
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, ".Fileoutput\\login"+sdf.format(new Date())+".png");
			
			
		}
		catch(IOException e)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, ".Fileoutput\\login"+sdf.format(new Date())+".png");
			test.log(LogStatus.FAIL,"Error occured: Unable to complete login test");
			e.printStackTrace();
		}
	}
		@AfterClass
		public static void endTest()
		{
			System.out.println("After class is executed");
			driver.quit();
		}
		
		@AfterSuite
		public void afterSuite()
		{
			reports.endTest(test);
			reports.flush();
		}
	}
	
