package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;


public class validateTitle extends base{
	
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	
	@Test(groups={"Regression","Smoke"})
	public void validateAppTitle() throws Exception
	{
		driver = getDriver();
		log.info("AppTitle: Driver is Initialized");	
		driver.get(getProperty("url"));
		log.info("AppTitle: Navigated to Home Page");


		LandingPage land = new LandingPage(driver);
		land.getPopup();
		
		
		try {
			String title = land.getTitle();
			Assert.assertEquals(title, "FEATURED COURSES1");     						// Failed on purpose to add a screenshot to the reports folder via listeners
																						// Actual: "FEATURED CO123URSES" 
																						// Written:"FEATURED CO123URSES1"
			log.info("AppTitle: Test Complete");
		}
		catch(Exception e){
			log.info("AppTitle: Test Failed");
		}
	}		
}
