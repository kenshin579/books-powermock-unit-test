package packt.ch1;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

/**
 * @author Deep Shah
 */
public class EmployeeControllerTest {
    //Simple Example
    @Test
    public void shouldGetCountOfEmployees() {
        EmployeeController employeeController = new EmployeeController(new EmployeeService());
        Assert.assertEquals(10, employeeController.getProjectedEmployeeCount());
    }

    /**
     * MockSettings은 거의 사용하지 않음
     * - 하지만, debugging할때 logging(debug)로 더 자세히 프린트하도록 할 수 있음
     */
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
    public void shouldReturnProjectedCountOfEmployeesFromTheServiceRoundedToTheCeiling() {
        //Creating a mock using the PowerMockito.mock method for the EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);

        PowerMockito.when(mock.getEmployeeCount()).thenReturn(6);

        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(8, employeeController.getProjectedEmployeeCount());
    }

    @Test
    public void shouldReturnProjectedCountOfEmployeesFromTheService() {
        //Creating a mock using the PowerMockito.mock method for the EmployeeService class.
        EmployeeService mock = PowerMockito.mock(EmployeeService.class);

        //Next statement essentially says that when getProjectedEmployeeCount method
        //is called on the mocked EmployeeService instance, return 8.
        PowerMockito.when(mock.getEmployeeCount()).thenReturn(8);

        EmployeeController employeeController = new EmployeeController(mock);
        Assert.assertEquals(10, employeeController.getProjectedEmployeeCount());
    }

    /**
     * 이건 어떻게 동작하는지 이해가 잘 안됨
     */
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
        Assert.assertEquals(12, employeeController.getProjectedEmployeeCount());
    }


}
