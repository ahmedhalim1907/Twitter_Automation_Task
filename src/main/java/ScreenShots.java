import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;

public class ScreenShots {

    public static void  takeScreenShotandSaveOnLocalDisk(WebDriver driver,String screenshotName) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        String screenshotPath = "src/main/resources/Screenshots/"+ screenshotName;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File Destination = new File(screenshotPath);
        FileUtils.copyFile(source,Destination);

    }

    public static void LogScreenShootInReport(WebDriver driver,String screenshotName) throws IOException {

        takeScreenShotandSaveOnLocalDisk(driver,screenshotName +".png");

        Reporting.test.log(LogStatus.FAIL,"screen shoot for Fail Test",
                Reporting.test.addScreenCapture("../Screenshots/"+ screenshotName+".png"));

    }

}
