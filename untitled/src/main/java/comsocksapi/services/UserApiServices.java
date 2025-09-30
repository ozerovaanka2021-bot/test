package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserApiServices extends ApiService {


    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("/register"));
    }
}
