package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.ForgotPassword;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	
	@Test(dataProvider="getData", groups="Regression")
	public void basePageNavigation(String Username,String Password,String text) throws Exception
	{
		driver = getDriver();
		log.info("BasePage: Driver is Initialized");
		
		driver.get(getProperty("url"));
		log.info("BasePage: Navigated to Home Page");
		

		LandingPage land = new LandingPage(driver);
		//if(land.getPopupSize()>0){
			land.getPopup();
		//}
		
		LoginPage login = land.getSignin(); 					// Get Login Page from Landing Page Page Object
		login.getEmail(Username)					 			// Using Login Page Object to give email
			 .getPassword(Password)			     				// Using Login Page Object to give password
			 .getLogin();			           					// Using Login Page Object to complete Sign-in
		
		log.info(text);
	
		ForgotPassword forgot = login.forgotPassword();	
		forgot.getEmail(Username)
			  .sendMeInstructions();
		
		log.info("BasePage: Test Complete");
	}

	
	@DataProvider(name = "getData")
	public Object[][] getData(){
		Object[][] data = new Object[2][3];

		data[0][0] = "nonrestricteduser@qw.com";
		data[0][1] = "123456";
		data[0][2] = "Restrcited User";
		
		data[1][0] = "restricteduser@qw.com";
		data[1][1] = "456788";
		data[1][2] = "Non restricted user";
				
		
		return data;	
	}
}


