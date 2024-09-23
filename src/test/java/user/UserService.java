package user;

import io.restassured.response.Response;
import user.createuser.CreateUserRequestBody;
import user.createuser.response.CreateUserResponseBody;
import user.login.LoginRequestBody;
import user.login.response.LoginResponseBody;
import utils.Utils;

import java.io.IOException;

public class UserService {
   UserClient userClient;

    public UserService() throws IOException {
        userClient= new UserClient();
    }

    public CreateUserResponseBody createUser(CreateUserRequestBody requestBody) throws IOException {

        Response response = userClient.create(requestBody);
        CreateUserResponseBody responseBody = response.as(CreateUserResponseBody.class);
        responseBody.setStatuscode(response.statusCode());
        Utils.setGlobalValue("token",responseBody.getToken());
        return responseBody;
    }

    public LoginResponseBody loginUser(LoginRequestBody requestBody) throws IOException {
        Response response = userClient.login(requestBody);
        LoginResponseBody loginResponseBody = response.as(LoginResponseBody.class);
        loginResponseBody.setStatuscode(response.getStatusCode());
        Utils.setGlobalValue("loginToken",loginResponseBody.getToken());
        return loginResponseBody;
    }
    public Response logoutUser() throws IOException {
        return userClient.logout();
    }
}
