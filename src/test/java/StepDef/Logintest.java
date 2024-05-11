package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Logintest {
    private WebDriver driver;
    @Given("I am on Saucedemo website")
    public void iAmOnSaucedemoWebsite() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#login-button")).isDisplayed();
    }

    @When("I enter a valid usernma {string}")
    public void iEnterAValidUsernma(String arg0) {
        driver.findElement(By.cssSelector("#user-name"))
                .sendKeys("standard_user");
    }

    @And("I enter a valid password {string}")
    public void iEnterAValidPassword(String arg0) {
        driver.findElement(By.cssSelector("#password"))
                .sendKeys("secret_sauce");
    }

    @And("Click the {string} button")
    public void clickTheButton(String arg0) {
        driver.findElement(By.name("login-button"))
                .click();
    }

    @Then("I should rederected to homepage")
    public void iShouldRederectedToHomepage() {
        driver.findElement(By.cssSelector("[alt='Sauce Labs Backpack']"))
                .isDisplayed();

        driver.quit();

    }


}