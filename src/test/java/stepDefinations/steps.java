package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.LogManager;

import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.core.logging.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utlities.DataReader;

public class steps 
{
	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;
	
	List<HashMap<String, String>> datamap;
	
	Logger logger;
	ResourceBundle rb;
	//String br;
	
	@Before
	public void setup()
	{
		logger = LogManager.getLogger(this.getClass());
		
		rb = ResourceBundle.getBundle("config");
		
		//br=rb.getString(br)
		
		macc = new MyAccountPage();
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
      System.out.println("Scenario status =======>" + scenario.getStatus());
      
      if(scenario.isFailed())
      {
    	  byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    	  
    	  scenario.attach(screenshot, "image/png", scenario.getName());
    	  }
       driver.quit();
       
}
	
	@Given("User Launch browser")
	public void user_launch_browser()
	{
		//System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		
		//WebDriverManger.chromedriver.setup();
		  
		  ChromeOptions option = new ChromeOptions();
	      option.addArguments("--remote-allow-origins=*");
	      
	      driver = new ChromeDriver(option);	
	}

	@Given("opens URL {string}")
	public void opens_url(String url) 
	{
	    driver.get(url);
	    driver.manage().window().maximize();
	}

	@When("User navigate to MyAccount menu")
	public void user_navigate_to_my_account()
	{
		hp=new HomePage();
		hp.ClickMyAccount();
		logger.info("Clicked on My Account");
	}

	@When("Click on Login")
	public void click_on_login()
	{
	  lp.ClickLogin();
	  logger.info("Clicked on Login");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password)
	{
	  lp=new LoginPage(driver);
	  
	  lp.setEmail(email);
	  
	  logger.info("Provided Email ");
	  
	  lp.setPassword(pwd);
	  
	  logger.info("Provided Password ");
	  
	}

	@When("Click on Login button")
	public void click_on_login_button() 
	{
	  lp.ClickLogin();
	  
	  logger.info("Clicked on Login button");
	}

	@Then("User navigates to MyAccount Page")
	public void user_navigates_to_my_account_page() 
	{
	   macc=new MyAccountPage();
	   
	   boolean targetpage=macc.isMyAccountPage();
	   
	   if(targetpage)
	   {
		   logger.info("Login Success");
		   
		   Assert.assertTrue(true);
	   }
	   
	   else
	   {
		   logger.error("Login Failed");
		   
		   Assert.assertTrue(false);
	   }
	}
	
	// ************  Data Driven **********
	
	@Then("check User navigates to MyAccount Page by passing Email and Password with excel row {String} ")
	public void checl_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows)
	{
		datamap=DataReader.data(System.getProperty("user.dir")+"\\testData\\opencart_LoginData.xlsx" "Sheet1");
		
		int index = Integer.parseInt(rows)-1;
		String email= datamap.get(index).get("username");
		String password= datamap.get(index).get("password");
		String exp_result= datamap.get(index).get("result");
		
		lp=new LoginPage(driver);
		
		lp.setEmail(email);
		
		lp.setPassword(pwd);
		
		lp.ClickLogin();
		
		try
		{
			boolean targetpage= macc.isMyAccountPageExists();
			
			if(exp_result.equals("Valid"))
			{
				if(targetpage==true)
				{
					MyAccountPage myaccpage = new MyAccountPage();
					myaccpage.ClickLogout();
					Assert.assertTrue(true);
				}
			}
			
			if(exp_result.equals("Invalid"))
			{
				if(targetpage==true)
				{		
				macc.ClickLogout();
				Assert.assertTrue(false);
			}
			
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
		
		catch(Exception e)
		{
			Assert.assertTrue(false);
		}
		
		driver.close();
		
	}
}
