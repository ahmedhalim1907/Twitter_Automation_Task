import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Tests extends BaseTests {

    TwitterHomePage Homepage ;

    @BeforeMethod
    public void clearTweetBox (){
        Homepage = new TwitterHomePage(driver);
        Homepage.tweetTextBox.clear();
        Homepage.Homedriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void HS01_checkTweetSubmissonWithin280Chars() {
        Reporting.startTC("Check tweet submission within 280 characters");


        //steps
        //write tweet <280
        //assert on char numbers
        //click tweet btn

        WebDriverWait wait = new WebDriverWait(Homepage.Homedriver,10);
        wait.until(ExpectedConditions.visibilityOf(Homepage.tweetTextBox));

        Homepage.writetweet(50,"b");
        Assert.assertEquals(Homepage.countCharacters(), 49);
        Homepage.clicktweetbtn();

    }
    @Test(dependsOnMethods = "HS01_checkTweetSubmissonWithin280Chars")
    public void HS02_checkUserCanCreatePoll() {

        Reporting.startTC("check user can create a poll ");

        Homepage.writetweet(5,"c");
        Homepage.createPoll();
        Homepage.clicktweetbtn();

    }
    @Test(dependsOnMethods = "HS02_checkUserCanCreatePoll")
    public void HS03_checktweetSubmissionWithLink(){
        Reporting.startTC("check tweet submission with link");


        Homepage.tweetaLink("https://www.google.com/",1);
        Homepage.clicktweetbtn();

    }

    @Test(dependsOnMethods = "HS03_checktweetSubmissionWithLink")
    public void BS01_checkTweetSubmissionmorethan280chars() {

        Reporting.startTC("Check tweet submission morethan 280 characters");


        //steps
        //write tweet >280
        //assert on char numbers
        //click tweet btn

        Homepage.writetweet(281,"d");
        Assert.assertEquals(Homepage.countCharacters(), 281);
        Homepage.clicktweetbtn();

    }

    @Test(dependsOnMethods = "BS01_checkTweetSubmissionmorethan280chars")
    public void BS02_checktweetSubmissionOfRepeatedTweet(){
        Reporting.startTC("check tweet submission of repeated tweet");

        Homepage.writetweet(5,"a");
        Homepage.clicktweetbtn();
        Homepage.writetweet(5,"a");
        Assert.assertEquals(Homepage.Homedriver.findElement(By.xpath("//div[text()='Something went wrong, but don’t fret — let’s give it another shot.']")).getText(),"Something went wrong, but don’t fret — let’s give it another shot.");
    }

}