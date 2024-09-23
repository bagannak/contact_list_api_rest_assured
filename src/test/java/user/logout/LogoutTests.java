package user.logout;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import user.BaseTest;
import user.login.LoginRequestBody;
import utils.Utils;

import java.io.IOException;

public class LogoutTests extends BaseTest {
    @BeforeClass
    public void loginSetUp() throws IOException {
        String email = Utils.getGlobalValue("email");
        String password = Utils.getGlobalValue("password");

        userService.loginUser(LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build());
    }

    @Test
    public void shouldTestUserLogout() throws IOException {
        //Arrange
        Response response = userService.logoutUser();
        //Act
        int statusCode = response.getStatusCode();
        //Assert
        Assert.assertEquals(statusCode, 200);
    }
}
