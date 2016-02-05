package com.mycompany.autotest;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

/**
 *
 * @author nguyen Duc Thien
 */
public class Reporter implements IReporter {

    private ExtentReports extend;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extend = new ExtentReports("./test_result/TestNG-result.html", false, NEWEST_FIRST);
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            Set ref = result.keySet();
            Iterator it = ref.iterator();

            for (ISuiteResult r : result.values()) {
                String test_name = String.valueOf(it.next());
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), test_name, LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), test_name, LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), test_name, LogStatus.SKIP);
            }
        }

        extend.flush();
        extend.close();
    }

    ;
    
    private void buildTestNodes(IResultMap tests, String test_name, LogStatus status) {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extend.startTest(test_name);

                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                extend.endTest(test);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
