package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.base;

public class ForgotPassword extends base{

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@FindBy(css="[id='user_email']") WebElement email;
	@FindBy(css="[type='submit']") WebElement sendMeInstructions;	

	
	public ForgotPassword(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}


	public ForgotPassword getEmail(String username){
		if(email.isDisplayed() && email.isEnabled()) {
			sendKeys(email, username);
		}
		else {
			log.info("Unsuccessful forgot password attempt");
		}
		return this;
	}
	
	
	public ForgotPassword sendMeInstructions(){
		click(sendMeInstructions);
		return this;
	}
	
}
