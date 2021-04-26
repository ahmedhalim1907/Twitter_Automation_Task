import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TwitterLoginPage {

    WebDriver Logindriver;

    //locators
    By TextContainerLocator = By.xpath("//div[@class='css-1dbjc4n r-ymttw5 r-1f1sjgu']");
    By usernameTextboxlocator = By.xpath("//input[@name='session[username_or_email]']");
    By passwordTextBoxLocator = By.xpath("//input[@name='session[password]']");
    By loginBtnLocator = By.xpath("//span[text()='Log in']");

    // elements
    List<WebElement>  Textcontainer;
    WebElement usernameTextBox ;
    WebElement passwordTextBox ;
    WebElement loginBtn ;

    //constructor
    public TwitterLoginPage(WebDriver driver){
        this.Logindriver = driver;

         Textcontainer = Logindriver.findElements(TextContainerLocator);
         usernameTextBox = Logindriver.findElement(usernameTextboxlocator);
         passwordTextBox = Logindriver.findElement(passwordTextBoxLocator);
         loginBtn = Logindriver.findElement(loginBtnLocator);
    }

    //functions
    public void setusername (String username){
        Textcontainer.get(0).click();
        usernameTextBox.sendKeys(username);
    }

    public void setpassword (String password){
        Textcontainer.get(1).click();
        passwordTextBox.sendKeys(password);
    }

    public void clickLogin (){
        loginBtn.click();
    }

}
