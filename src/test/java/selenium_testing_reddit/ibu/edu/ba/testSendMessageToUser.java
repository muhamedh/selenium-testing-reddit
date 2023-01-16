package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class testSendMessageToUser {
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	private static WebDriver webDriver;
	private static String baseURL;
	
	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Amira\\Downloads\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseURL = "https://ibu.edu.ba";
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
		String path = "C:\\Users\\Amira\\Downloads\\ReditProfile";
		options.addArguments("--user-data-dir="+path); 

		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/chat/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}


	@Test
	void testUserBlock() throws InterruptedException {
		webDriver.get(baseURL);
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

		// i must find the user
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[1]/div[1]/div/button"))
				).click();
		
		// send search atttributes
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div/div[2]/div[1]/span/input"))
				).sendKeys("muhasupa");
		// wait till search loads
		Thread.sleep(1000);
		// seelect user to send
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div/div[2]/div[2]/div"))
				).click();
		// press start chat 
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/div/form/div/div[2]/div/button[2]"))
				).click();
		// now i send message to user
		WebElement input = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/form/div/div/textarea"))
				);
		input.clear();
		String salt = this.getSaltString();
		input.sendKeys(salt);
		// then i press the send button
		
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/main/div[1]/div[2]/div/form/div/button"))
				).click();
		//check if page contains our salt
		String source = webDriver.getPageSource();
		assertTrue(source.contains(salt));
		
		Thread.sleep(5000);
	
		
		
	}


}
