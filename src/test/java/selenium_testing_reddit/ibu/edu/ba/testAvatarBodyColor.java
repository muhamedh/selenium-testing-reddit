package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

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

class testAvatarBodyColor {

	private static WebDriver webDriver;
	private static String baseURL;
	
	@BeforeAll
	static void setUP() {
		System.setProperty("webdriver.chrome.driver", "/home/musaka/Desktop/Semester 5/svvt/chromedriver");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox",
				             "--disable-notifications",
				             "--disable-extensions");
		String path = "/home/musaka/Desktop/RedditProfile";
		options.addArguments("--user-data-dir="+path); 

		options.addArguments("--start-maximized");

		webDriver = new ChromeDriver(options);
		
		baseURL = "https://www.reddit.com/user/CharacterOk8020";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}


	@Test
	void testIfAvatarChangesColor() throws InterruptedException {
		webDriver.get(baseURL);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		//open create avatar
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[2]/div/div[1]/div/button"))
				).click();
		//open you section
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/nav/div/div[4]"))
				).click();
		//open body section
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/section/div/div[1]"))
				).click();
		// check body change color
		// get all color body options
		WebElement holder = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/section/div[2]/div[2]/div/div[1]/div/div"))
				);
		List<WebElement> colorOptions = holder.findElements(By.xpath("./child::*"));
		
		for(int i = 0; i < colorOptions.size()-1;i++) {
			colorOptions.get(i).click();
			System.out.print(colorOptions.get(i).getCssValue("background-color"));
			Thread.sleep(1000);
			WebElement body = webDriver.findElement(By.xpath("/html/body"));
			String style = body.getAttribute("style");
			int startIndex = style.indexOf("--color-body:");
			String color = style.substring(startIndex + 13 , startIndex + 20);
			
			int r = Integer.valueOf(color.substring(1,3),16);
			int g = Integer.valueOf(color.substring(3,5),16);
			int b = Integer.valueOf(color.substring(5,7),16);
			
			
			System.out.println(" - " + "rgba(" + r + ", " + g + ", " + b + ", 1)");
			assertEquals(colorOptions.get(i).getCssValue("background-color"),"rgba(" + r + ", " + g + ", " + b + ", 1)" );
		}
		
	}

}
