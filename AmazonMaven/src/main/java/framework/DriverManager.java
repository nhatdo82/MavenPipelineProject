package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverManager {
	static WebDriver  driver = null;
	static String urlBase = "";
	
	public static  enum ENV
	{
		FIREFOX, CHROME;
	}
	public static void init(String url, ENV browser) throws Exception
	{
		urlBase = url;
		
		switch (browser)
		{
			case CHROME: {System.setProperty("webdriver.chrome.driver","/Users/nhatdo/Documents/Automation/libs/webdrivers/chromedriver");driver=new ChromeDriver(); break;}
			case FIREFOX: {System.setProperty("webdriver.gecko.driver","/Users/nhatdo/Documents/Automation/libs/webdrivers/geckodriver");driver=new FirefoxDriver();}
		}
	}
	public static void start()
	{
		driver.get(urlBase);
	}

	public static WebDriver getWebDriver()
	{
		return driver;
	}
}
