package packt.ch5_mockingFinal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

/**
 * Mocking final classes or methods
 *
 * @author Deep Shah
 */
public class EmployeeControllerTest {

    @Test
    public void shouldReturnCountOfEmployeesFromTheService() {
        //Creating a mock using the PowerMockito.mock method for the EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);

        //Next statement essentially says that when getEmployeeCount method
        //is called on the mocked EmployeeService instance, return 10.
        PowerMockito.when(mock.getEmployeeCount()).thenReturn(10);

        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(10, employeeController.getEmployeeCount());
    }

    @Test
    public void shouldReturnCountOfEmployeesFromTheServiceWithDefaultAnswer() {
        //Creating a mock using the PowerMockito.mock method for the EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class,
                /**
                 * Passing in a default answer instance.
                 * This method will be called when no matching mock methods have been setup.
                 */
                new Answer() {
                    /**
                     * We are simply implementing the answer method of the interface
                     * and returning hardcoded 10.
                     * @param invocation The context of the invocation.
                     *                   Holds useful information like what arguments where passed.
                     * @return Object the value to return for this mock.
                     */
                    @Override
                    public Object answer(InvocationOnMock invocation) {
                        return 10;
                    }
                });

        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(10, employeeController.getEmployeeCount());
    }

    @Test
    public void shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployee() {
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);

        EmployeeController employeeController = new EmployeeController(mock);

        Employee employee = new Employee();
        employeeController.saveEmployee(employee);

        //Verifying that controller did call the
        //saveEmployee method on the mocked service instance.
        Mockito.verify(mock).saveEmployee(employee);
    }

    @Test
    public void shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployeeWithMockSettings() {
        EmployeeService mock = PowerMockito.mock(EmployeeService.class, Mockito
                .withSettings()
                .name("EmployeeServiceMock")
                .verboseLogging());

        EmployeeController employeeController = new EmployeeController(mock);

        Employee employee = new Employee();
        employeeController.saveEmployee(employee);

        //Verifying that controller did call the
        //saveEmployee method on the mocked service instance.
        Mockito.verify(mock).saveEmployee(employee);
    }
}
