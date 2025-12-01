package Task;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class FlipKart {
	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		try {
			//If element is not present it will wait for the time passed in implicit wait
			WebElement logion = driver.findElement(By.xpath("//span[@role='button']"));
			logion.click();

		} catch (Exception e) {
			
//			 e.printStackTrace();
		}
		// Searching for the product
		driver.findElement(By.xpath("//input[@class='Pke_EE']")).sendKeys("Bluetooth Speakers", Keys.ENTER);

		// Clicking on BRAND filter option and selecting boat
		driver.findElement(By.xpath("//div[text()='Brand']")).click();
		driver.findElement(By.xpath("//div[@title='boAt']")).click();
		Thread.sleep(1000);

		// Scrolling to the filter option
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500);");
		Thread.sleep(1000);

		// Clicking on 4* and above filter checkbox
		driver.findElement(By.xpath("//div[text()='4★ & above']")).click();
		Thread.sleep(1000);

		// Clicking on price high to low filter option
		WebElement priceLowToHigh = driver.findElement(By.xpath("//div[text()='Price -- Low to High']"));
		priceLowToHigh.click();
		Thread.sleep(2000);

		// Clicking on the first product of all the products
		List<WebElement> allproducts = driver.findElements(By.xpath("//a[@class='pIpigb']"));
		allproducts.get(0).click();

		// Switching the driver control to product description page
		String parent = driver.getWindowHandle();
		Set<String> chaild = driver.getWindowHandles();
		for (String id : chaild) {
			if (!id.equals(parent)) {
				driver.switchTo().window(id);

			}
		}
		Thread.sleep(2000);

		// Checking if "Available offers" is displayed and printing total number of
		if (driver.findElement(By.xpath("//div[text()='Available offers']")).isDisplayed()) {
			WebElement offersList = driver.findElement(By.xpath("//span[contains(text(),'offers')]"));
			if (offersList.isDisplayed()) {
				offersList.click();
			}
			List<WebElement> offers = driver.findElements(By.xpath("//span[@class='T7pkhK row']"));
			if (!offers.isEmpty()) {
				System.out.println("The Available offers " + offers.size());
			}
		}
		Thread.sleep(2000);

	// Checking if the "Add to Cart" button is displayed and enabled and taking screenshot of the webpage after clicking on the button
		try {
			WebElement addbutton = driver.findElement(By.xpath("//button[text()='Add to cart']"));
			if (addbutton.isDisplayed() && addbutton.isEnabled()) {
				addbutton.click();
				Thread.sleep(2000);
				takeScreenshot(driver, "cart_result");
			}
			else {
				// Taking screenshot of the webpage in case the "Add to Cart" button is missing or disabled
				System.out.println("Product unavilable-could not be added to cat");
				takeScreenshot(driver, "result");
			}

		} catch (Exception e1) {
          // Taking screenshot of the webpage in case the "Add to Cart" button is missing r disabled
			System.out.println("Product unavilable-could not be added to cat");
			takeScreenshot(driver, "result");
		}

		// Closing the browser
		driver.quit();

	}

	/**
	 * Generic method to capture the screenshot of the webpage
	 * @param driver
	 * @param name
	 * @throws IOException
	 */
	private static void takeScreenshot(WebDriver driver, String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(".\\Screenshots\\" + name + ".png");
		Files.copy(src, des);
	}
}