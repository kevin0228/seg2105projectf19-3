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

    @Test
    public void employeeTest3() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setServices(new String[]{"service1", "service2"});
        assertEquals("service1_service2", employee.getServicesString());
    }

    @Test
    public void employeeTest4() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setPhone("000-000-0000");
        assertEquals("000-000-0000", employee.getPhone());
    }

    @Test
    public void employeeTest5() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setAddress("test address");
        assertEquals("test address", employee.getAddress());
    }

    @Test
    public void employeeTest6() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setClinicName("test clinic name");
        assertEquals("test clinic name", employee.getClinicName());
    }

    @Test
    public void employeeTest7() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setStartTime("09:00");
        assertEquals("09:00", employee.getStartTime());
    }

    @Test
    public void employeeTest8() {
        ClinicEmployee employee = new ClinicEmployee("test", null);
        employee.setEndTime("21:00");
        assertEquals("21:00", employee.getEndTime());
    }
}
