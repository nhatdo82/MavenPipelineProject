package functional_tests;

import org.testng.annotations.Test;
import framework.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;


public class searchPageTest {
  searchPage searcher;
  
  @Test(dataProvider = "dp" )
  public void f(String keyword, String s) {
	  searcher.enterKeyword(keyword);
  }
  @BeforeMethod
  public void beforeMethod() {  
	  searcher.cleanSearch();
  }

  @AfterMethod
  public void afterMethod() {
  }


  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "laptop", "a" },
      new Object[] { "game", "b" },
      new Object[] {"book" , "c"},
    };
  }
  @BeforeClass
  public void beforeClass() {
	  
	  
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest 
  public void beforeTest() {
	  try {
		  DriverManager.init("https://www.amazon.com/", DriverManager.ENV.FIREFOX);
		  DriverManager.start();
		  searcher = new searchPage(DriverManager.getWebDriver());
		  }
	  catch (Exception e) {
			  
		  }
	  
  }

  @AfterTest
  public void afterTest() {
  }

}
