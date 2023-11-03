package Login_HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Signup_Page 
{
	
	WebDriver driver;
	WebDriverWait wait;
	boolean flg=false;
	
	@FindBy(xpath="//span[@class='css-1qaijid r-o99278 r-1mgje8r r-1b43r93 r-1ddef8g r-1loqt21']")
	WebElement SignUp;
	
	@FindBy(xpath="//span[normalize-space()='Sign In']")
	WebElement SignIn;
	
	@FindBy(xpath="//input[@placeholder='Type here...']")
	WebElement employers_uniqueid;
	
	@FindBy(xpath="//span[normalize-space()='Request Access Now']")
	WebElement Request_Access_Now;
	
	@FindBy(xpath="//div[@class='css-1rynq56 r-1mgje8r r-ubezar r-135wba7 r-1r5su4o']")
	WebElement Continue;
	
	@FindBy(xpath="//div[@class='css-175oi2r r-1i6wzkk r-lrvibr r-1loqt21 r-1otgn73 r-1y4qvnn r-1867qdf r-d045u9 r-13awgt0 r-18u37iz r-1awozwy r-1777fci']")
	WebElement Close;
	
	
	
	public Signup_Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void title()
	{
		String actualUrl="https://web-staging.diathrive.com/login";
		String expectedUrl= driver.getCurrentUrl();
		if(actualUrl.contains(expectedUrl))
		{
			Assert.assertEquals(actualUrl, expectedUrl);
			System.out.println("Verified this is login Page");
		}
		else
		{
			System.out.println("Verified this is not login page");
		}
	}
	
	
	public void signup()
	{
		boolean button =SignUp.isDisplayed();
		if(button)
		{
			System.out.println("Verified Signup link is displayed");
		}
		else
		{
			System.out.println("verified Signup link is not displayed");
		}
		SignUp.click();
	}
	
	public void Employers_Uniqueid()
	{
		boolean uniqueidtextbox = employers_uniqueid.isDisplayed();
		if(uniqueidtextbox)
		{
			System.out.println("Employers UniqueID text box is displayed");
		}
		else
		{
			System.out.println("Employers UniqueID text box is not displayed");
		}
	}
	
	public void Requestaccessnow()
	{
		boolean requestaccess = Request_Access_Now.isDisplayed();
		if(requestaccess)
		{
			System.out.println("Request access now link is displayed");
		}
		else
		{
			System.out.println("Request access now link is not displayed");
		}
	}
	
	
	public void signIn()
	{
		boolean signin = SignIn.isDisplayed();
		if(signin)
		{
			System.out.println("Sign in link is displayed");
		}
		else
		{
			System.out.println("Sign in link is not displayed");
		}
	}
	
	public void Enter_Employers_Uniqueid(String Invalid_id)
	{
		employers_uniqueid.sendKeys(Invalid_id );
	}
	
	public void Continue_Button()
	{
		Continue.click();
		
	}
	
	public void close()
	{
		Close.click();
	}

	
	
	
	
	
  
}
