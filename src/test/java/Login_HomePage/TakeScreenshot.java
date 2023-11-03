package Login_HomePage;

import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

@SuppressWarnings("unused")
public class TakeScreenshot 
{
	WebDriver driver;
	
	public TakeScreenshot(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void takesnapshot(WebDriver webdriver, String fileWithPath) throws Exception
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
		
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		
		File DestFile = new File(fileWithPath);
		
		FileUtils.copyFile(SrcFile, DestFile);
		
		
	}

	public static void takesnapAlert(WebDriver webdriver,String fileWithPath) throws Exception
	{
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image,"png",new File(fileWithPath));
	}
}
