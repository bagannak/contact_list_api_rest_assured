package user;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {
    protected UserService userService;
    @BeforeSuite
    public void setUp() throws IOException {
        userService = new UserService();
    }

}
