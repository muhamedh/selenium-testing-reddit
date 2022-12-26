package selenium_testing_reddit.ibu.edu.ba;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


class testChromeProfile {
	
	/**
	 * This test can be used as a setup in order to create a Chrome Profile,
	 * it eliminates the need of constant log in
	 * 
	 */
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
		
		baseURL = "https://www.reddit.com/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@Test
	void testCommentPersistance() throws InterruptedException {
		webDriver.get(baseURL);
		webDriver.manage().window().maximize();
		
		Thread.sleep(300000);
	}

}
