package Letscodeit;

import java.util.Iterator; 
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;

public class LetsCodeIT {


	static WebDriver driver;
	static Properties prop = new Properties();
	
	@BeforeTest
	public static  void setUp()
	{
		try {
				InputStream input = new FileInputStream("C:\\Users\\sarthak.purwar\\eclipse-workspace\\LETSCODEIT2\\Files\\locators.properties");
			prop.load(input);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				System.out.println(e.getStackTrace());
			
			}
			
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().deleteAllCookies();
	

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test()
	public void RadioButtonTest() throws Throwable
	{
		WebElement radio = driver.findElement(By.id(prop.getProperty("radio")));
		radio.click();
		System.out.println(radio.isSelected());
		Thread.sleep(2000);
		
	}
	
	@Test
	public void DropDown() throws Throwable
	{
	   WebElement dropDown = driver.findElement(By.id(prop.getProperty("dropDown")));
	   Select select1 = new Select(dropDown);
	   select1.selectByVisibleText("Benz");
	   Thread.sleep(2000);
	   
		
	}
	
	@Test
	public void MultipleSelect() throws Throwable
	{
	  WebElement multi = driver.findElement(By.id(prop.getProperty("multiarray")));
	  Select select = new Select(multi);
	  select.selectByVisibleText("Orange");
	  select.selectByVisibleText("Peach");
	  Thread.sleep(2000);
		
	}
	
	@Test
	public void CheckBox() throws Throwable
	{
		WebElement check1 = driver.findElement(By.id(prop.getProperty("check1")));
		WebElement check2 = driver.findElement(By.id(prop.getProperty("check2")));
		
		check1.click();
		check2.click();
		System.out.println(check1.isSelected());
		System.out.println(check2.isSelected());
		
		Thread.sleep(2000);
		
		
	}
	
	@Test
	public void SwitchWindow() throws Throwable
	{
		
		WebElement window = driver.findElement(By.id(prop.getProperty("window")));
		window.click();
		System.out.println(driver.getTitle());
		String parent=driver.getWindowHandle();

		Set<String>s=driver.getWindowHandles();

		
		Iterator<String> I1= s.iterator();

		while(I1.hasNext())
		{

		String child_window=I1.next();


		if(!parent.equals(child_window))
		{
		driver.switchTo().window(child_window);
		Thread.sleep(2000);

		System.out.println(driver.switchTo().window(child_window).getTitle());

		driver.close();
		}

		}
		driver.switchTo().window(parent);
		Thread.sleep(2000);
		}
		
		
	@Test
	public void newTab()
	{
		WebElement newTab = driver.findElement(By.id(prop.getProperty("newTab")));
		newTab.click();
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    System.out.println(driver.getTitle());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    System.out.println(driver.getTitle());
	}
	
	@Test
	public void Alert() throws Throwable
	{
		WebElement alertInput = driver.findElement(By.xpath(prop.getProperty("alertInput")));
		alertInput.sendKeys("Sarthak");
		WebElement alertbtn = driver.findElement(By.id(prop.getProperty("alertbtn")));
		alertbtn.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		alertInput.sendKeys("Sarthak");
		WebElement confirmbtn = driver.findElement(By.id(prop.getProperty("confirmbtn")));
		confirmbtn.click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		
		
		
	}
	
	@Test
	public void Enable() throws Throwable
	{
		WebElement input = driver.findElement(By.id(prop.getProperty("enInput")));
		input.sendKeys("Sarthak");
		WebElement disable = driver.findElement(By.id(prop.getProperty("disable")));
		disable.click();
		Thread.sleep(2000);
		WebElement enable =  driver.findElement(By.id(prop.getProperty("enable")));
		enable.click();
		input.sendKeys("Purwar");
		Thread.sleep(2000);
		
		
	}
		
	
	@Test
	public void hide() throws Throwable
	{
		WebElement input = driver.findElement(By.id(prop.getProperty("input")));
		input.sendKeys("Sarthak");
		WebElement hide = driver.findElement(By.id(prop.getProperty("hide")));
		hide.click();
		Thread.sleep(2000);
		WebElement show=driver.findElement(By.id(prop.getProperty("show")));
		show.click();
		Thread.sleep(2000);
	}
	
	@Test
	public void mouseHover() throws Throwable
	{
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 500);");
		Thread.sleep(2000);
		
		WebElement mouseHover = driver.findElement(By.id(prop.getProperty("mouseHover")));
		mouseHover.click();
		Thread.sleep(2000);
		
		WebElement top = driver.findElement(By.xpath(prop.getProperty("top")));
		top.click();
		Thread.sleep(2000);
		
		jse.executeScript("scroll(0, 500);");
		mouseHover.click();
		WebElement reload =driver.findElement(By.xpath(prop.getProperty("reload")));
		reload.click();
		Thread.sleep(2000);
		
	}
	
	@Test(priority=1)
	public void iframe() throws Throwable
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 500);");
		Thread.sleep(2000);
		driver.switchTo().frame(prop.getProperty("iframe"));
		WebElement course = driver.findElement(By.className(prop.getProperty("course")));
		course.click();
		Thread.sleep(2000);
		WebElement button = driver.findElement(By.xpath(prop.getProperty("button")));
		button.click();
		Thread.sleep(2000);
		WebElement name = driver.findElement(By.xpath(prop.getProperty("name")));
		System.out.println(name.getText());
		
		
	}
	
	
	@AfterTest
	public void setDown()
	{
		driver.close();
		driver.quit();
		System.out.println("Test Completed Successfully");
		
	}
	

}
