package Login_HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_Page 
{
	
	WebDriver driver;
	WebDriverWait wait;
	boolean flg=false;
	
	@FindBy(xpath="//input[@type='email']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement Password;
	
	//@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/div[1]/div[1]/div/div/div/div/div[6]/div")
	@FindBy(xpath="//div[contains(text(),'Continue')]")
	WebElement Login; 
	
	@FindBy(xpath="//div[@data-testid='Header-Text-WelcomeBack']")
	WebElement HomePage;
	
	public Login_Page(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void username(String userName)
	{
		UserName.sendKeys(userName);
	}
	
	public void password(String passWord)
	{
		Password.sendKeys(passWord);
	}
	
	public void login()
	{
		Login.click();
	}
	
	/*
	 * public void homepage() {
	 * 
	 * String home = HomePage.getText(); System.out.println(home);
	 * 
	 * }
	 */
  
}
