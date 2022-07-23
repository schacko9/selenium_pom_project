package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;

public class base {

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	@Parameters({"browser", "execution", "port"})
	@BeforeMethod(alwaysRun = true)
	public void setup(String browser, String execution, String port) throws Exception{
		String host = getProperty("host");
		String endpoint = getProperty("endpoint");
		DesiredCapabilities cap = new DesiredCapabilities();
		
		if(execution.equalsIgnoreCase("local"))
		{		
			if(browser.equalsIgnoreCase("chrome")){
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
			}
			else if(browser.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver().setup();   
				driver.set(new FirefoxDriver());
			}
			else if(browser.equalsIgnoreCase("edge")){
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
			}
			else{
				throw new Error("Invalid Local Browser Selection!");
			}
		}
		else if(execution.equalsIgnoreCase("grid"))
			if(browser.equalsIgnoreCase("chrome")){
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.LINUX);
				cap.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
				
				driver.set(new RemoteWebDriver(new URL(host + port + endpoint), cap));
			}
			else if (browser.equalsIgnoreCase("firefox")){				
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.LINUX);	
				
				driver.set(new RemoteWebDriver(new URL(host + port + endpoint), cap));
			}
			else if (browser.equalsIgnoreCase("edge")){				
				cap.setBrowserName("MicrosoftEdge");
				cap.setPlatform(Platform.LINUX);	
				
				driver.set(new RemoteWebDriver(new URL(host + port + endpoint), cap));
			}
			else{
				throw new Error("Invalid Grid Browser Selection!");
			}
		else {
			throw new Error("Invalid Execution Selection!");
		}
}

	public WebDriver getDriver() {
		return driver.get();
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		getDriver().quit();
	}
	
	
	
	
	public void click(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public void sendKeys(WebElement locator, String text) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.visibilityOf(locator)).sendKeys(text);
	}
	
	public String get_Text(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.visibilityOf(locator));
        return locator.getText();
	}
	public int get_Size(List<WebElement> locator) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 25);
        wait.until(ExpectedConditions.visibilityOfAllElements(locator));
        return locator.size();
	}
	
	
	
	public static String getProperty(String key) throws Exception {
		String src = System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties";
		FileInputStream fis = new FileInputStream(src);
		Properties prop = new Properties();

		prop.load(fis);
		String value = prop.get(key).toString().toLowerCase();
		
		if(StringUtils.isEmpty(value)) {
			throw new Exception("Value is not present: "+ key + ", in data property file");
		}
		
		return value;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		
		return destinationFile;
	}
	

}
