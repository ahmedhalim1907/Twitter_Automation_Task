import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
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
    public void HS01_checkTweetSubmissonWithin280Chars() throws IOException {
        Reporting.startTC("Check tweet submission within 280 characters");

        //steps
        //write tweet <280
        //assert on char numbers
        //click tweet btn

        WebDriverWait wait = new WebDriverWait(Homepage.Homedriver,10);
        wait.until(ExpectedConditions.visibilityOf(Homepage.tweetTextBox));

        Homepage.writetweet(5,"b");
        try {
            Assert.assertEquals(Homepage.countCharacters(), 5);
            Homepage.clicktweetbtn();
            Reporting.test.log(LogStatus.PASS,"tweet submited successfully");
        }
        catch (AssertionError e){
            Reporting.test.log(LogStatus.FAIL,"failed to submit a tweet");
            ScreenShots.LogScreenShootInReport(Homepage.Homedriver,"failed_HS01_checkTweetSubmissonWithin280Chars");
        }

    }

    @Test(dependsOnMethods = "HS01_checkTweetSubmissonWithin280Chars")
    public void HS02_checkUserCanCreatePoll() throws IOException {

        Reporting.startTC("check user can create a poll ");

        Homepage.writetweet(5,"c");
        Homepage.createPoll("choice1","choice2");
        Homepage.clicktweetbtn();

        try{
            Assert.assertEquals(Homepage.Homedriver.findElement(By.xpath("//span[text()='choice1']")).getText(),"choice1");
            Reporting.test.log(LogStatus.PASS,"poll created successfully");
        }
        catch(AssertionError e){
            Reporting.test.log(LogStatus.FAIL,"failed to crete a poll");
            ScreenShots.LogScreenShootInReport(Homepage.Homedriver, "failed_HS02_checkUserCanCreatePoll");

        }
    }

    @Test(dependsOnMethods = "HS02_checkUserCanCreatePoll")
    public void HS03_checktweetSubmissionWithLink() throws IOException {
        Reporting.startTC("check tweet submission with link");

        Homepage.tweetaLink("https://www.google.com/",1);
        Homepage.clicktweetbtn();
        try {
            Assert.assertEquals(Homepage.Homedriver.findElement(By.xpath("//a[text()='google.com']")).getText(), "google.com");
            Reporting.test.log(LogStatus.PASS,"submit tweet with link successfully");
        } catch(AssertionError e){
            Reporting.test.log(LogStatus.FAIL,"failed to submit tweet with link");
            ScreenShots.LogScreenShootInReport(Homepage.Homedriver,"failed_HS03_checktweetSubmissionWithLink");
        }
    }

    @Test(dependsOnMethods = "HS03_checktweetSubmissionWithLink")
    public void BS01_checkTweetIsNotSubmittedMoreThan280Chars() throws IOException {

        Reporting.startTC("Check tweet is not submitted more than 280 characters");

        //steps
        //write tweet >280
        //assert on char numbers
        //click tweet btn

        Homepage.writetweet(281,"d");
        Homepage.clicktweetbtn();
        try{
            Assert.assertTrue(Homepage.Homedriver.findElement(By.xpath("//div[@aria-disabled='true']")).isDisplayed());
            Reporting.test.log(LogStatus.PASS,"tweet more than 280 characters isn't submitted");
        }catch(AssertionError e){
            Reporting.test.log(LogStatus.FAIL,"submitted tweet more than 280 characters");
            ScreenShots.LogScreenShootInReport(Homepage.Homedriver, "failed_BS01_checkTweetIsNotSubmittedMoreThan280Chars");
        }

    }

 @Test(dependsOnMethods = "BS01_checkTweetIsNotSubmittedMoreThan280Chars")
    public void BS02_checktweetSubmissionOfRepeatedTweet() throws InterruptedException, IOException {
        Reporting.startTC("check tweet submission of repeated tweet");

        Homepage.writetweet(5,"b");
        Homepage.clicktweetbtn();

        Homepage.writetweet(5,"b");
        Homepage.clicktweetbtn();

        try{
            Assert.assertTrue(Homepage.Homedriver.findElement(By.xpath("//div[text()='Something went wrong, but don’t fret — let’s give it another shot.']")).isDisplayed());
            Reporting.test.log(LogStatus.PASS,"repeated tweet is not submitted");
        } catch (AssertionError e){
            Reporting.test.log(LogStatus.FAIL,"repeated tweet is submitted");
            ScreenShots.LogScreenShootInReport(Homepage.Homedriver, "failed_BS02_checktweetSubmissionOfRepeatedTweet");
        }

    }

}