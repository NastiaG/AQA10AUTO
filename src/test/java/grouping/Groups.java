package grouping;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Groups {
    @Test(priority = 1, groups = "first")
    public void one() {
        assertTrue(true);
    }

    @Test(priority = 1, groups = "second")
    public void two() {
        assertTrue(true);
    }

    @Test(priority = 2, groups = "first")
    public void three() {
        assertTrue(true);
    }

    @Test(priority = 2, groups = "second")
    public void four() {
        assertTrue(true);
    }

    @Test(priority = 3, groups = "first")
    public void five() {
        assertTrue(true);
    }

    @Test(priority = 3, groups = "second")
    public void six() {
        assertTrue(true);
    }

    @Test(priority = 4, groups = "first")
    public void seven() {
        assertTrue(true);
    }

    @Test(priority = 4, groups = "second")
    public void eight() {
        assertTrue(true);
    }
}
