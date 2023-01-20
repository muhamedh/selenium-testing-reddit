package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class testLoginUserFlow {

	private static WebDriver webDriver;
	private static String baseURL;
	
	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amira\\Downloads\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseURL = "https://ibu.edu.ba";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
		//String path = "C:\\Users\\Amira\\Downloads\\ReditProfile";
		//options.addArguments("--user-data-dir="+path); 

		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/login/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}


	@Test
	void testUserBlock() throws InterruptedException {
		webDriver.get(baseURL);
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		WebElement u = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/div/div[2]/form/fieldset[1]/input"))
				);
		u.sendKeys("Single_Monitor_9475");
		
		WebElement p = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/div/div[2]/form/fieldset[2]/input"))
				);
		p.sendKeys("Sc8f&9fxwMfAcSPSuUt!V*5QFsgq");
		
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div[1]/div/div[2]/form/fieldset[5]/button"))
				).click();
		
		String username = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[1]/header/div/div[2]/div[2]/div/div[2]/button/span[1]/span/span"))
				).getText();
		Thread.sleep(3000);
		assertEquals("Single_Monitor_9475", username);
		
		
		
	}

}
