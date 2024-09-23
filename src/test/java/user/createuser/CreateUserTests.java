package user.createuser;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import user.BaseTest;
import user.UserService;
import user.createuser.response.CreateUserResponseBody;
import utils.Utils;

import java.io.IOException;

public class CreateUserTests extends BaseTest {

    @Test
    public void shouldTestCreateUser() throws IOException {
        //Arrange
        CreateUserRequestBody requestBody = CreateUserRequestBody.builder().firstName(Utils.generateFirstName())
                .lastName(Utils.generateLastName())
                .email(Utils.generateEmail())
                .password(Utils.generatePassword())
                .build();
        //Act
        CreateUserResponseBody responseBody = userService.createUser(requestBody);
        //Assert
        Assert.assertEquals(responseBody.getStatuscode(),201);
    }
}
