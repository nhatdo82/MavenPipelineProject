package framework;


import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchPage {
	
	private WebDriver driver;
	By searchTextbox = By.xpath("//input[@id='twotabsearchtextbox']");
	By productType = By.xpath("//select[@aria-describedby='searchDropdownDescription']");
	By searchResult = By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
	By dataCellWidget = By.xpath("//div[starts-with(@data-cel-widget,'search_result_')");
	
	static String sharedUrl;
	
	public searchPage (WebDriver driver)
	{
		this.driver = driver;
		//adding more comment
		
	}
	public void enterKeyword (String keyword)
	{
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		
		if (driver == null) return;
		
		//search an Item
		driver.findElement(searchTextbox).sendKeys(keyword);
		driver.findElement(searchTextbox).sendKeys(Keys.RETURN);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(searchResult));
		String strResult = driver.findElement(searchResult).getText();
		
		DBManager dbManager = new DBManager();
		
		if (strResult.matches(".* of over .* results for .*") || strResult.contains("No results for"))
			dbManager.writeTestResult(strResult, true);
		else dbManager.writeTestResult("Test Error", false);
		
	}
	
	
	public void selectProductType (String type)
	{
		WebElement element = driver.findElement(productType);
		Select itemList = new Select(element);
		itemList.selectByValue(type);
	}
	public String selectAProduct(int index)
	{
		dataCellWidget = By.xpath("//div[@data-index='"+index+"']");
		WebElement element = driver.findElement(dataCellWidget);
		return element.findElement(By.xpath("//a[@class='a-link-normal']")).getAttribute("href");
	}
	public void cleanSearch ()
	{
		//Option 1 Use HotKey Command+A then Delete
		driver.findElement(searchTextbox).sendKeys(Keys.COMMAND+ "a");
		driver.findElement(searchTextbox).sendKeys(Keys.DELETE);
		
		//Option 2 Use Clear() Function
		//driver.findElement(searchTextbox).clear();
	}
}
