import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.junit.After;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class Reporting {

    public static int tcNumb = 1;
    public static ExtentReports extent;
    public static ExtentTest test;

    //===========================================================================================================
    @BeforeTest
    @Parameters("ReportName")
    public void startReport(String ReportName) {
        tcNumb = 1;
        extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/resources/reports/"
                + ReportName + ".html");
    }

    //-----------------------------------------------------------------------------------------------------------
    @AfterTest
    public void endReport() {
        extent.flush();
        //extent.close();
    }

    //===========================================================================================================
    public static void startTC(String tcName) {
        String tcNumber = "1";
        String testCaseName = "0";
        tcNumber = Integer.toString(tcNumb++); //Counting test case number
        testCaseName = tcNumber + "-  " + tcName; //store test case name with its number
        test = extent.startTest(testCaseName); //Test Case name in extent report
    }

    //-----------------------------------------------------------------------------------------------------------
    @After
    public static void endTC() {
        extent.endTest(test); //close the test case in extent report
    }
}
