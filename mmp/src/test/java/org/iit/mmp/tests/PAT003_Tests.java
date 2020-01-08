package org.iit.mmp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PAT003_Tests {
	
	
      WebDriver driver;
      @Test
      public void validateInformationTab()
      {
    	  
    	  launchBrowser();
    	SoftAssert sa = new SoftAssert();
    	
    	 boolean result1 = login("ria1","Ria12345");
    	 sa.assertTrue(result1);
    	 
    	 boolean result2 = navigateToAModule("Information");
    	 sa.assertTrue(result2);
    	 
    	  String actual=fetchInformationMessage();
          String expected="Manage My Patient (MMP) is a medical practice management solution that boosts productivity by automating the day-to-day tasks that can slow an office manager down. Central delivers much more than medical billing software. Sure, it has the tools to help generate cleaner claims and reduce denials, but our easy-to-use practice management software also streamlines your workflow to deliver seamless handoffs across departments";
    	  sa.assertEquals(actual, expected);
    	  
    	  sa.assertAll();
      }
      
      public void launchBrowser()
      {
    	WebDriverManager.firefoxdriver().setup();
    	driver = new FirefoxDriver();
    	driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/login.php");
    	String title = driver.getTitle();
    	System.out.println("Title :" +title);
    	  
      }
      
      public boolean login(String username,String password)
      {
    	driver.findElement(By.id("username")).sendKeys("ria1"); 
    	driver.findElement(By.id("password")).sendKeys("Ria12345");
    	driver.findElement(By.name("submit")).click();
    	boolean result = driver.findElement(By.tagName("h3")).getText().contains("ria1");
        return result;
    	  
     }
      
      public boolean navigateToAModule(String moduleName)
      {
    	  driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
    	  boolean result = driver.findElement(By.xpath("//h3[@class='panel-title']")).getText().contains("Information");
          return result;
      }
      
      public String fetchInformationMessage()
      {
    	  String actual = driver.findElement(By.xpath("//div[@class='panel-title']")).getText();
    	  System.out.println("The Actual Value" + actual);
          return actual;
    	  
      }
      
      

}
