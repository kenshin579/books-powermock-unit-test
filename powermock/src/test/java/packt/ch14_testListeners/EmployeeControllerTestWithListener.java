package packt.ch14_testListeners;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockListener;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * The class that will
 * demonstrate the use of PowerMockListener.
 *
 * @author Deep Shah
 */
@RunWith(PowerMockRunner.class)
@PowerMockListener(TestStatsListener.class)
public class EmployeeControllerTestWithListener {

    /**
     * This field will be automatically injected by the listener.
     */
    @Mock
    private EmployeeService employeeService;

    @Test
    public void shouldGetTheNameOfTheDepartment() {

        final Employee employee = new Employee();
        final String employeeEmail = "deep@gitshah.com";

        PowerMockito.when(employeeService.findEmployeeByEmail(employeeEmail)).thenReturn(employee);

        final EmployeeController employeeController = new EmployeeController(employeeService);
        Assert.assertSame(employeeController.findEmployeeByEmail(employeeEmail), employee);
    }

}
