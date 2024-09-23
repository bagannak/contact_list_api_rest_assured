package user.login;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import user.BaseTest;
import user.UserService;
import user.login.response.LoginResponseBody;
import utils.Utils;

import java.io.IOException;

public class LoginTests extends BaseTest {
    String email;
    String password;
    @BeforeClass
    public void setUP() throws IOException {
        email = Utils.getGlobalValue("email");
        password = Utils.getGlobalValue("password");
    }
    @Test
    public void shouldTestLoginWithValidCredentials() throws IOException {
        //Arrange
        LoginRequestBody requestBody = LoginRequestBody.builder()
                .email(email)
                .password(password)
                .build();
        //Act

        LoginResponseBody responseBody = userService.loginUser(requestBody);
        //Assert

        Assert.assertEquals(responseBody.getStatuscode(),200);
        Assert.assertNotNull(responseBody.getToken());
    }

}
