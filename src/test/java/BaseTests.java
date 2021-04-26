import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    public WebDriver driver;
    Reporting report;

    @BeforeClass
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://twitter.com/login");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        TwitterLoginPage loginPage = new TwitterLoginPage(driver);

        WebDriverWait wait = new WebDriverWait(loginPage.Logindriver,20);
        wait.until(ExpectedConditions.visibilityOf(loginPage.usernameTextBox));

        loginPage.setusername("@ahmed07655567");
        loginPage.setpassword("twittertask");
        loginPage.clickLogin();

        report = new Reporting();
        report.startReport("Tweet submission Report");
    }

    @AfterClass
    public void TearDown() {
        // driver.quit();
        report.endReport();
    }


}
