package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

import java.time.Duration;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndNavigateToURL();
//        driver=new ChromeDriver();
//        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
       // LoginPage loginPage=new LoginPage();
      // WebElement usernameField= driver.findElement(By.id("txtUsername"));
     //  WebElement passwordField= driver.findElement(By.id("txtPassword"));
      // usernameField.sendKeys("Admin");
      // passwordField.sendKeys("Hum@nhrm123");
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My batch 16 test case starts here");
        sendText(ConfigReader.getPropertyValue("username"),loginPage.usernameField);
        Log.info("username entered");
        sendText(ConfigReader.getPropertyValue("password"),loginPage.passwordField);
        Log.info("password entered");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
      //  LoginPage loginPage=new LoginPage();
       // WebElement loginBtn=driver.findElement(By.name("Submit"));
        //click(loginBtn);
        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("My test case is passed");
    }
    @When("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
       // LoginPage loginPage=new LoginPage();
       // WebElement usernameField= driver.findElement(By.id("txtUsername"));
        //WebElement passwordField= driver.findElement(By.id("txtPassword"));
       // usernameField.sendKeys("dalima123");
        //passwordField.sendKeys("Hum@nhrm123");
        sendText("dalima",loginPage.usernameField);
        sendText("Hum@nhrm123",loginPage.passwordField);

    }
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
       // LoginPage loginPage=new LoginPage();
       // WebElement usernameField= driver.findElement(By.id("txtUsername"));
       // WebElement passwordField= driver.findElement(By.id("txtPassword"));
       // usernameField.sendKeys("admin123");
        sendText("admin123",loginPage.usernameField);
       // passwordField.sendKeys("Hum@nhrm123");
        sendText("Hum@nhrm123",loginPage.passwordField);

    }
    @Then("error message is displayed")
    public void error_message_is_displayed() {
        System.out.println("Error message is displayed");
    }
    @When("user enters {string} and {string} and verifying the {string} for the combination")
    public void user_enters_and_and_verifying_the_for_the_combination(String username, String password, String errorMessageExpected) {
     sendText(username,loginPage.usernameField);
     sendText(password,loginPage.passwordField);
     click(loginPage.loginButton);
     String errorMessageActual=loginPage.errorMessageField.getText();
        Assert.assertEquals(errorMessageExpected,errorMessageActual);

    }




}
