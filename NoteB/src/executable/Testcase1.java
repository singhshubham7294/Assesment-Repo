package executable;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.util.Arrays;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseclass.BaseClass;

public class Testcase1 extends BaseClass {
	
	
	@Test
	@Parameters({"Min", "Max"})
	public void TC_012(int min, int max) throws Exception {
		
		//Launch the browser
		browser("https://noteb.com/");
		Thread.sleep(3000);
        
		//Created Javascript for ScrollUP and ScrollDown method
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
		POM p1=new POM();

		 WebElement minBudget = p1.getMinBudgetelement();
		 
		//Created Action for sending the value to the Min and Max Budget Field
		Actions a = new Actions(driver);
		a.moveToElement(minBudget).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		minBudget.sendKeys(Integer.toString(min));
		Thread.sleep(1000);
		
		WebElement maxBudget = p1.getMaxBudgetelement();
		a.moveToElement(maxBudget).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		maxBudget.sendKeys(Integer.toString(max));
		
		Screenshot("..//NoteB//Screenshot//MinMaxEntered.png");
		
		//Clicking the search button
		WebElement search = p1.getSearchButton();
		a.doubleClick(search).perform();
		
		js.executeScript("window.scrollBy(0,-300)", "");
		Thread.sleep(2000);
		Screenshot("..//NoteB//Screenshot//SearchResults.png");
		
		List<WebElement> pagecount = driver.findElements(By.xpath("//ul[@class=\"pagination\"]//li"));
		System.out.println(pagecount.size());
		for (int i=1;i<=pagecount.size()-4;i++) {			
		List<WebElement> allele = driver.findElements(By.xpath("//div[@class='btn btn-outline-secondary searchprice fakeBtn']"));
		allele.size();
		int flag=0;
		System.out.println("Search results on current Page : "+allele.size());
		for (WebElement an : allele) {
			String txt = an.getText();
			String value = txt.split(" - ")[0].split(" ")[1];
			System.out.println("Min Range of an individual Item : "+value);
			int LowerValue = Integer.parseInt(value);
			if(LowerValue<min && LowerValue>max) {
				flag=1;
			}
			
		}
		assertEquals(flag, 0);
		js.executeScript("window.scrollBy(0,3000)", "");
		Thread.sleep(3000);
		if(i!=pagecount.size()-4) {
			Screenshot("..//NoteB//Screenshot//Pagination.png");
		driver.findElement(By.xpath("//a[text()='"+(i+1)+"']")).click();
		System.out.println("*******Navigating to Next Page*******");
		js.executeScript("window.scrollBy(0,-3500)", "");
		Thread.sleep(1500);
		}		
		}
		Thread.sleep(1500);
		driver.close();
		
	}
	
	
	@Test	
	public void TC_017() throws Exception  {
		browser("https://noteb.com/");
		Thread.sleep(3000);
		Screenshot("..//NoteB//Screenshot//HomePage.png");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		String minBudgetLength = driver.findElement(By.id("bdgmin")).getAttribute("maxlength");
		assertEquals(Integer.parseInt(minBudgetLength), 5);
		
		System.out.println("Maximum Budget Length for Min Budget Field :"+minBudgetLength);
		String maxBudgetLength = driver.findElement(By.id("bdgmax")).getAttribute("maxlength");
		assertEquals(Integer.parseInt(maxBudgetLength), 5);
		
		System.out.println("Maximum Length for Max Budget Field :"+maxBudgetLength);
		driver.close();
		
	}
	
	
	@Test
	@Parameters({"Min", "Max"})
	public void TC_016(int min, int max) throws Exception {
		browser("https://noteb.com/");
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		
		WebElement minBudget = driver.findElement(By.id("bdgmin"));
		Actions a = new Actions(driver);
		a.moveToElement(minBudget).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		minBudget.sendKeys(Integer.toString(min));
		Screenshot("..//NoteB//Screenshot//BeforeRoundup.png");
		System.out.println("Entered value to the Min Budget Field before roundup : "+Integer.toString(min));
		a.moveToElement(minBudget).sendKeys(Keys.TAB).perform();
		Thread.sleep(1000);
		
		a.moveToElement(minBudget).doubleClick().keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();
		
		//capture the text from the minfield
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		System.out.println(clipboard.getData(DataFlavor.stringFlavor));
		String clipcount= (String) clipboard.getData(DataFlavor.stringFlavor);
		
		int clip = Integer.parseInt(clipcount);
		int once = clip%10;
		int tenth = (clip%100)/10;
		int minonce = min%10;
	    int mintenth = (min%100)/10;
	    
	    System.out.println("After RoundUp once digit value is : "+once);
	    System.out.println("After RoundUp tenth digit value is : "+tenth);
	    System.out.println("Before RoundUp once digit value is : "+minonce);
	    System.out.println("Before RoundUp tenth digit value is : "+mintenth);
	    
	    //Verifying the round up value for min budget
	    
	    if ( minonce <5) {
	    	assertEquals(tenth, mintenth);
	    	assertEquals(once, 0);
	    	System.out.println("After Roundup value displayed as : "+clip);
	    	Screenshot("..//NoteB//Screenshot//AfterRoundup.png");
	    	}
	    
	    if(minonce>4) {
	    	assertEquals(tenth, mintenth+1);
	    	assertEquals(once, 0);
	    	System.out.println("After Roundup value displayed as : "+clip);
	    	Screenshot("..//NoteB//Screenshot//AfterRoundup.png");
	    }
	    
		
driver.close();
		
	}
	


}
