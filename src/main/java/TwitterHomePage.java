import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TwitterHomePage {
    WebDriver Homedriver;


    //locators
    By tweetTextBoxLocator = By.xpath("//div[@aria-label='Tweet text']");
    By tweetbtnLocator = By.xpath("//span[text()='Tweet']"); //loop
    By pollbtnLocator = By.xpath("//div[@aria-label='Add poll']");
    By pollChoice1Locator = By.name("Choice1");
    By pollChoice2Locator = By.name("Choice2");
    By polllengthDaysLocator =By.id("Days");

    //elements
    WebElement tweetTextBox ;
    List<WebElement> tweetbtn ;
    WebElement pollbtn ;


    //constructor
    public TwitterHomePage (WebDriver driver){
        Homedriver = driver;

         tweetTextBox = Homedriver.findElement(tweetTextBoxLocator);
         tweetbtn = Homedriver.findElements(tweetbtnLocator);
         pollbtn = Homedriver.findElement(pollbtnLocator);
    }

    //functions
    public void writetweet (int numofcharacters,String character){
        tweetTextBox.click();
        for(int i=0;i<numofcharacters;i++){
            tweetTextBox.sendKeys(character);
        }

    }
    public void clicktweetbtn (){
        tweetbtn.get(1).click();
    }

    public void createPoll(){
        pollbtn.click();

        WebElement pollChoice1 = Homedriver.findElement(pollChoice1Locator);
        WebElement pollChoice2 = Homedriver.findElement(pollChoice2Locator);
        WebElement polllengthDays = Homedriver.findElement(polllengthDaysLocator);

        Select Days = new Select(polllengthDays);
        Days.selectByIndex(1);

        pollChoice1.sendKeys("ahmed");
        pollChoice2.sendKeys("halim");

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
