package packt.ch14_testListeners;

import org.powermock.core.spi.PowerMockTestListener;
import org.powermock.core.spi.testresult.Result;
import org.powermock.core.spi.testresult.TestMethodResult;
import org.powermock.core.spi.testresult.TestSuiteResult;

import java.lang.reflect.Method;

/**
 * A very simple listener to keep track of how many tests ran
 * and how many passed or failed.
 *
 * @author Deep Shah
 */
public class TestStatsListener implements PowerMockTestListener {

    /**
     * This field will count the number of tests.
     */
    private long totalTests;

    /**
     * This field will count the number of passed tests.
     */
    private long totalTestPassed;

    /**
     * This field will count the number of failed tests.
     */
    private long totalTestFailed;


    /**
     * This method is called before the test suite start executing.
     * We will initialize our stat counters in this method.
     * {@inheritDoc}
     */
    @Override
    public void beforeTestSuiteStarted(Class<?> testClass, Method[] testMethods) throws Exception {
        System.out.println("Before Suite");
    }

    /**
     * This method is called before every test method starts running.
     * We will increment the totalTests counter in this method.
     * {@inheritDoc}
     */
    @Override
    public void beforeTestMethod(Object testInstance, Method method, Object[] arguments) throws Exception {
        System.out.println("Before test");
    }

    /**
     * This method is called after every test method ends running.
     * We will increment the totalTestPassed and totalTestFailed
     * counters depending on the test result.
     * {@inheritDoc}
     */
    @Override
    public void afterTestMethod(Object testInstance, Method method, Object[] arguments, TestMethodResult testResult) throws Exception {
        System.out.println("After test");
        totalTests++;
        if (testResult.getResult() == Result.SUCCESSFUL) totalTestPassed++;
        if (testResult.getResult() == Result.FAILED) totalTestFailed++;
    }

    /**
     * This method is called after test suite has ended.
     * We will print a nice summary of how many tests ran and how many passed or failed.
     * {@inheritDoc}
     */
    @Override
    public void afterTestSuiteEnded(Class<?> testClass, Method[] methods, TestSuiteResult testResult) throws Exception {
        System.out.println(String.format("Tests Run: %d, Passed: %d, Failed: %d", totalTests, totalTestPassed, totalTestFailed));
        System.out.println(String.format("Tests Run: %d, Passed: %d, Failed: %d",
                testResult.getTestCount(), testResult.getSuccessCount(), testResult.getFailureCount()));
    }
}
