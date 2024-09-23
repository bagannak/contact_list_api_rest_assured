package user;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import user.createuser.CreateUserRequestBody;
import user.login.LoginRequestBody;
import utils.APIResources;
import utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class UserClient {
    private final String loginToken;

    public UserClient() throws IOException {
        loginToken = Utils.getGlobalValue("loginToken");
    }

    public Response create(CreateUserRequestBody requestBody) throws IOException {
        String resource = APIResources.CreateUserAPI.getResource();
        Response response = given().spec(Utils.requestSpecificationBuilder())
                .body(requestBody)
                .when().post(resource);
        response.then().log().body();
        return response;
    }

    public Response login(LoginRequestBody requestBody) throws IOException {
        String resource = APIResources.LogInUserAPI.getResource();
        Response response = given().spec(Utils.requestSpecificationBuilder())
                .body(requestBody)
                .when().post(resource);
        response.then().log().body();
        return response;
    }

    public Response logout() throws IOException {
        String resource = APIResources.LogOutUserAPI.getResource();
        Response response = given().spec(Utils.requestSpecificationBuilder())
                .header("Authorization", String.format("Bearer %s", loginToken))
                .when().post(resource);
        response.then().log().body();
        return response;
    }
}
