package baseclass;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {

	static public WebDriver driver;
	public static void browser(String url)
	{
		System.setProperty("webdriver.chrome.driver", "G:\\WorkSpace\\NoteB\\driver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	public static void kill()
	{
		driver.quit();
		
	}
	
	public static void dragDrop(WebElement w, String t)
	{
		Select s=new Select(w);
		s.selectByValue(t);	
		
	}
	
	public static void klik(WebElement w)
	{
		w.click();
	}
	
	public static void Screenshot(String path) throws Exception {
		TakesScreenshot tk = (TakesScreenshot)driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File Dec = new File(path);
		FileUtils.copyFile(src, Dec);
	}

}
