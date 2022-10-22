package executable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testcase {
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}
	
	@Test
	public void Test1() {
		System.setProperty("webdriver.chrome.driver", "G:\\WorkSpace\\NoteB\\driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://noteb.com/");
		driver.manage().window().maximize();
		driver.close();
	}
	
	@Test
	public void Test2() {
		System.setProperty("webdriver.chrome.driver", "G:\\WorkSpace\\NoteB\\driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://noteb.com/");
		driver.manage().window().maximize();
		driver.close();
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");
	}

}
