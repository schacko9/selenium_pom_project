package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;
 
public class LoginPage extends base{

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	@FindBy(css="[id='user_email']") WebElement email;
	@FindBy(css="[type='password']") WebElement password;
	@FindBy(css="[value='Log In']") WebElement login;
	@FindBy(css="[href*='password/new/index.php']") WebElement forgotPassword;
	
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public LoginPage getLogin(){
		 click(login);
		 return this;
	}
	
	
	public LoginPage getEmail(String username){
		if(email.isDisplayed() && email.isEnabled()) {
			sendKeys(email, username);
		}
		else {
			log.info("Unsuccessful username attempt");
		}
		return this;
	}
	

	public LoginPage getPassword(String pass){
		if(password.isDisplayed() && password.isEnabled()) {
			sendKeys(password, pass);
		}
		else {
			log.info("Unsuccessful password attempt");
		}
		return this;
	}
	
	public ForgotPassword forgotPassword(){
		click(forgotPassword);
		ForgotPassword forgot = new ForgotPassword(driver);
		return forgot;
	}
	
	
	
}
