package project.seg2015.seg2105_project_f19_3;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ServiceTest {

    @Test
    public void serviceTest() {
        Service service = new Service("service1", 100, "doctor");
        assertEquals("service1", service.getServiceName());
    }

    @Test
    public void serviceTest2() {
        Service service = new Service("service1", 100, "doctor");
        assertEquals(100, service.getServiceCost());
    }

    @Test
    public void serviceTest3() {
        Service service = new Service("service1", 100, "doctor");
        assertEquals("doctor", service.getRole());
    }
}
