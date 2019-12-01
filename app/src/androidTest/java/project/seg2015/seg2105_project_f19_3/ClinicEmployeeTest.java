package project.seg2015.seg2105_project_f19_3;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ClinicEmployeeTest {
    @Test
    public void employeeTest1() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setInsuranceTypes(new String[]{"type1", "type2"});
        assertEquals("type1_type2", employee.getInsuranceTypeString());
    }

    @Test
    public void employeeTest2() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setPaymentMethods(new String[]{"Cash", "DebitCard"});
        assertEquals("Cash_DebitCard", employee.getPaymentMethodsString());
    }
}
