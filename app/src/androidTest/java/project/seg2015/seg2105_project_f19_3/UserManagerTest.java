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
public class UserManagerTest {
    @Test(expected = ExceptionInInitializerError.class)
    public void Test() {
        assertEquals(null, UserManager.getInstance().signIn(UserType.Admin, "admin", Util.stringToHash("123")));
        assertEquals("admin", UserManager.getInstance().signIn(UserType.Admin, "admin", Util.stringToHash("5T5ptQ")).getAccount());
    }
}
