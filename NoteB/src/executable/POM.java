package executable;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import baseclass.BaseClass;

public class POM extends BaseClass {
	
	public POM()
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getMinBudgetelement() {
		return minBudgetelement;
	}

	@FindBy(id="bdgmin")
	private WebElement minBudgetelement;
	
	@FindBy(id="bdgmax")
	private WebElement maxBudgetelement;

	public WebElement getMaxBudgetelement() {
		return maxBudgetelement;
	}
	
	@FindBy(id="s_search_btn")
	private WebElement searchButton;

	public WebElement getSearchButton() {
		return searchButton;
	}

	

	

	
	

	

}
