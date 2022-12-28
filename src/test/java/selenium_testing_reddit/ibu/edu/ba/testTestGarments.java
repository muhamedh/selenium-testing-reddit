package selenium_testing_reddit.ibu.edu.ba;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
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

class testTestGarments {

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
	void testGarments() throws InterruptedException {
		webDriver.get(baseURL);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		
		//open create avatar
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div/div/div[2]/div[3]/div[2]/div/div[1]/div/button"))
				).click();
		//open style section
		
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/nav/div/div[3]"))
				).click();
		
		// open face section
		// 
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[2]/div[4]"))
				).click();
		//  get the item holder
		WebElement itemHolder = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[1]/div/div/div[3]/div[2]/div/div[2]"))
				);
		// get all face options
		List<WebElement> faceOptions = itemHolder.findElements(By.xpath("./child::*"));
		List<WebElement> actualCurrentState = new ArrayList<WebElement>();
		for( WebElement faceOption : faceOptions) {
			System.out.println(faceOption.getAttribute("class"));
			WebElement child = faceOption.findElement(By.xpath("./child::*"));
			WebElement child2 = child.findElement(By.xpath("./child::*"));
			
			System.out.println(child2.getAttribute("data-testid"));
			
			
			List<WebElement> children = child2.findElements(By.xpath("./child::*"));
			
			System.out.println(children.get(3).getAttribute("data-testid"));
			actualCurrentState.add(children.get(3));
			
			System.out.println("--- End of children ---");
		}
		
		// avatar:layer:content avatar:layer:content:#basic_face_upper_001
		// where to assert from
		// /html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div[4]
		for( int i = 0;i < 10;i++) {
			faceOptions.get(i).click();
			String s = actualCurrentState.get(i).getAttribute("data-testid");
			String s1 = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div[4]")).getAttribute("data-testid");
			//System.out.println( s + " - " + s1 );
			assertEquals(s,s1);
			Thread.sleep(500);
		}
		/*
		for( WebElement faceOption : faceOptions) {
			faceOption.click();
			String s = faceOption.getAttribute("data-testid");
			String s1 = webDriver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[4]/div/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div[4]")).getAttribute("data-testid");
			System.out.println( s + " - " + s1 );
			Thread.sleep(500);
		}*/
	}
}
