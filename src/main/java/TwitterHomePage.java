import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TwitterHomePage {
    WebDriver Homedriver;


    //locators
    By tweetTextBoxLocator = By.xpath("//div[@aria-label='Tweet text']");
    By tweetbtnLocator = By.xpath("//div[@data-testid='tweetButtonInline']"); //loop
    By pollbtnLocator = By.xpath("//div[@aria-label='Add poll']");
    By pollChoice1Locator = By.name("Choice1");
    By pollChoice2Locator = By.name("Choice2");
    By polllengthDaysLocator =By.id("Days");

    //elements
    WebElement tweetTextBox ;
    WebElement tweetbtn ;
    WebElement pollbtn ;


    //constructor
    public TwitterHomePage (WebDriver driver){
        Homedriver = driver;

        tweetTextBox = Homedriver.findElement(tweetTextBoxLocator);
        tweetbtn = Homedriver.findElement(tweetbtnLocator);
        pollbtn = Homedriver.findElement(pollbtnLocator);
    }

    //functions
    public void writetweet (int numofcharacters,String character){
        WebDriverWait wait = new WebDriverWait(Homedriver,20);
        wait.until(ExpectedConditions.elementToBeClickable(tweetTextBox));
        tweetTextBox.click();

        for(int i=0;i<=numofcharacters;i++){
            tweetTextBox.sendKeys(character);//sendkeys doesn't work as expected (documentation)

        }

    }
    public void clicktweetbtn (){
        WebDriverWait wait = new WebDriverWait(Homedriver,10);
        wait.until(ExpectedConditions.elementToBeClickable(tweetbtn));
        tweetbtn.click();
    }

    public void createPoll(String choice1,String choice2){
        pollbtn.click();

        WebElement pollChoice1 = Homedriver.findElement(pollChoice1Locator);
        WebElement pollChoice2 = Homedriver.findElement(pollChoice2Locator);
        WebElement polllengthDays = Homedriver.findElement(polllengthDaysLocator);

        Select Days = new Select(polllengthDays);
        Days.selectByIndex(1);

        pollChoice1.sendKeys(choice1);
        pollChoice2.sendKeys(choice2);

    }

    public int countCharacters (){
        return tweetTextBox.getText().length();
    }

    public void tweetaLink (String link,int numoflinks){

        for(int i=0;i<numoflinks;i++){
            tweetTextBox.sendKeys(link);
        }
    }

}
