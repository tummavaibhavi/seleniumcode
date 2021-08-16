package newpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginClass {
	static WebDriver driver;
LoginClass(){
	//launch driver
	driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver", "F:java//Login//src//chromedriver.exe");
	driver.get("https://accounts.datoms.io/login");
}

public static void main(String[] arg)
{
	try{
	new LoginClass();
	//login page is loaded
	driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	driver.findElement(By.id("login_form")).isDisplayed();
	//verify all fields are visible & then enter value
	if (driver.findElement(By.id("email")).isDisplayed() && driver.findElement(By.id("email")).isEnabled())
	driver.findElement(By.id("email")).sendKeys("tumma212@gmail.com");
	
	if (driver.findElement(By.id("password")).isDisplayed() && driver.findElement(By.id("password")).isEnabled())
		driver.findElement(By.id("password")).sendKeys("Admin123");
	
	if (driver.findElement(By.id("form_submit_btn")).isDisplayed() && driver.findElement(By.id("form_submit_btn")).isEnabled())
		driver.findElement(By.id("form_submit_btn")).click();
	
	//incorrect email id msg should display
	driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	driver.findElement(By.id("show_message")).isDisplayed();
	String value=driver.findElement(By.id("show_message")).getText();
	
	if(value.equals("Email id does not exist!")){
		System.out.print("Pass");
	}
	else {
		System.out.print("Fail");		
	}
	
	}
	catch(Exception e){
		System.out.print("Fail Exception");	
	}
	
}
}
