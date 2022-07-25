package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;


public class LandingPage extends base {
	
	public WebDriver driver;
	
	@FindBy(css="a[href*='sign_in']") WebElement signin;
	@FindBy(css=".text-center>h2") WebElement title;
	@FindBy(css=".nav.navbar-nav.navbar-right>li>a") WebElement NavBar;
	@FindBy(css="[href*='password/new/index.php']") WebElement forgotPassword;
	@FindBy(xpath="//button[contains(text(), 'NO THANKS')]") WebElement popup;

	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	
	public LoginPage getSignin(){
		click(signin);
		LoginPage login = new LoginPage(driver); 
		return login;
	}
	
	public WebElement getNavigationBar(){
		return NavBar;
	}
	
	public String getTitle(){
		return get_Text(title);
	}
	
	public void getPopup(){
		try{
			click(popup);
			System.out.println("");
			System.out.println("***********"+" Popup Closed, " + "Thread ID: " + Thread.currentThread().getId()+" ***********");
			System.out.println("");
		}
		catch(Exception e){
			System.out.println("");
			System.out.println("***********"+" Popup not Clickable, " + "Thread ID: " + Thread.currentThread().getId()+" ***********");
			System.out.println("");
		}
	}
}
