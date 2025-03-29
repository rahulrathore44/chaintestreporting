
package com.learning.stepdfn;


import com.learning.hooks.WebDriverContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class StepDefinition {

    private WebDriver driver;

    public StepDefinition(WebDriverContext context){
        this.driver = context.getDriver();
    }

    @Given("I open the BugZilla webpage at {string}")
    public void i_open_the_bug_zilla_webpage_at(String url) {
        driver.get(url);
    }
    @Then("I clicked on the login button")
    public void i_clicked_on_the_login_button() {
        driver.findElement(By.id("login_link_top")).click();
    }
    @Then("I provided the username as {string} and password as {string}")
    public void i_provided_the_username_as_and_password_as(String username, String password) {
        driver.findElement(By.id("Bugzilla_login_top")).sendKeys(username);
        driver.findElement(By.id("Bugzilla_password_top")).sendKeys(password);
    }
    @When("I click the login button again")
    public void i_click_the_login_button_again() {
        driver.findElement(By.id("log_in_top")).click();
    }
    @Then("Page should be open wth title {string}")
    public void page_should_be_open_wth_title(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }


}
