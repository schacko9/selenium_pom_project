package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;


public class validateNavigationBar extends base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());

	
	@Test(groups={"Regression","Smoke"})
	public void validateAppNavBar() throws Exception
	{
		driver = getDriver();	
		log.info("AppNavBar: Driver is Initialized");
		driver.get(getProperty("url"));
		log.info("AppNavBar: Navigated to Home Page");
		
		LandingPage land = new LandingPage(driver);
		//if(land.getPopupSize()>0){
			land.getPopup();
		//}
		
	    Assert.assertTrue(land.getNavigationBar().isDisplayed());				// Compare the text from the browser with actual text.
	    	log.info("AppNavBar: Test completed");
	}
}
