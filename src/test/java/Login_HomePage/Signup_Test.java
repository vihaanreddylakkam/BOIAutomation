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

public class Signup_Test
{

	static WebDriver driver;
	Signup_Page SP;
	TakeScreenshot shot = new TakeScreenshot(driver);
	static ExtentReports reports;
	static ExtentTest test;
	Properties prop, prop2;
	String Env_URL = null;
	String QA = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	String Stage = "https://web-staging.diathrive.com/";
	String UAT = "http://www.facebook.com";

	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("BeforeSuite");
		System.out.println(System.getProperty("user.dir"));
		reports = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports\\Login_Test.html", false);
		test = reports.startTest("Signup_Test started");
	}

	@BeforeClass
	public static void startTest() throws Exception
	{
		MyScreenRecorder.startRecording("startTest");
		System.out.println("Signup_Test started");
	}

	@BeforeTest
	@Parameters(
	{ "browser" })

	public void beforeTest(String browser) throws InterruptedException, IOException
	{
		if (browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			/* WebDriverManager.chromedriver().setup(); */
			System.out.println("Chrome browser is opened");

		} else if (browser.equalsIgnoreCase("Edge"))
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
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		prop = new Properties();
		prop.load(fileInput1);

		Env_URL = prop.getProperty("CurrentExecution");
		System.out.println("current exeution environment is " + Env_URL);

		if (Env_URL.contains("orangehrmlive"))
		{
			System.out.println("Accessing orangehrmlive environment" + Env_URL);
			driver.get(Env_URL);
			Thread.sleep(2000);
		} else if (Env_URL.contains("UAT"))
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
		SP = new Signup_Page(driver);
		Thread.sleep(2000);

		File file2 = new File("Config\\Signup.Properties");
		FileInputStream fileInput2 = null;

		try
		{
			fileInput2 = new FileInputStream(file2);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		prop = new Properties();
		prop.load(fileInput2);
	}

	@Test(priority = 1)
	public void TC_001_Verify_Signup_Button_in_Login_page() throws Exception
	{
		try
		{
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");
			
			SP.title();
			Thread.sleep(2000);	
			
			SP.signup();
			Thread.sleep(2000);
			System.out.println("Clicked on signup Link"); 
			test.log(LogStatus.PASS,"Clicked on signup Link");
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\Verify_Signup_Button_in_Login_page" + sdf.format(new Date()) + ".png");
			
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");

		} 
		catch (IOException e)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\Verify_Signup_Button_in_Login_page" + sdf.format(new Date()) + ".png");
			test.log(LogStatus.FAIL, "Error occured: Unable to complete Verify_Signup_Button_in_Login_test");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void TC_002_options_displayed_in_Signup_Button() throws Exception
	{
		try
		{
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");
			
			SP.Employers_Uniqueid();
			Thread.sleep(2000);	
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\options_displayed_in_Signup_Button" + sdf.format(new Date()) + ".png");
			
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");

		} 
		catch (IOException e)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\options_displayed_in_Signup_Button" + sdf.format(new Date()) + ".png");
			test.log(LogStatus.FAIL, "Error occured: Unable to complete options_displayed_in_Signup_Button test");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	public void TC_003_Invalid_uniqueid() throws Exception
	{
		try
		{
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");
			
			SP.Requestaccessnow();
			System.out.println("Verified Request access now link"); 
			test.log(LogStatus.PASS,"Verified Request access now link");
			
			SP.signIn();
			System.out.println("Verified signin link"); 
			test.log(LogStatus.PASS,"Verified signin link");
			
			SP.Enter_Employers_Uniqueid(prop.getProperty("Invalid_id"));
			System.out.println("Entered employers invalid unique id"); 
			test.log(LogStatus.PASS,"Entered employers invalid unique id");
			
			SP.Continue_Button();
			System.out.println("Clicked on Continue_Button"); 
			test.log(LogStatus.PASS,"Clicked on Continue_Button");
			
			Thread.sleep(7000);
			
			SP.close();
			System.out.println("Clicked on close Button"); 
			test.log(LogStatus.PASS,"Clicked on close Button");
			
	
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\Invalid_uniqueid" + sdf.format(new Date()) + ".png");
			
			System.out.println("*****************************");
			test.log(LogStatus.PASS, "********************************");

		} 
		catch (IOException e)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD-hh-mm-ss-ms");
			TakeScreenshot.takesnapshot(driver, "Fileoutput\\Invalid_uniqueid" + sdf.format(new Date()) + ".png");
			test.log(LogStatus.FAIL, "Error occured: Unable to complete Invalid_uniqueid test");
			e.printStackTrace();
		}
	}
	
	
	

	@AfterClass
	public static void endTest() throws Exception
	{
		System.out.println("After class is executed");
		MyScreenRecorder.stopRecording();
		driver.quit();
	}

	@AfterSuite
	public void afterSuite()
	{
		reports.endTest(test);
		reports.flush();
	}
}
