package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchAProduct {
	private WebDriver driver;
	By searchTextbox = By.xpath("//input[@id='twotabsearchtextbox']");
	By productType = By.xpath("//select[@aria-describedby='searchDropdownDescription']");
	By searchResult = By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']");
	By dataCellWidget = By.xpath("//div[starts-with(@data-cel-widget,'search_result_')");
	
	
	public void typeKeyword (String keyword)
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
}
