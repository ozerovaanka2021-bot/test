package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.payloads.UserPayload;
import comsocksapi.responses.LoginResponse;


public class UserApiServices extends ApiService {


    public UserApiServices(String baseUrl) {
        super(baseUrl);
    }

    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("/register"));
    }

   public AssertableResponse confirmUser(String userToken){
    return  new AssertableResponse(setup()
           .queryParam("token", userToken)
           .when()
           .get("/confirm"));
    }
    public AssertableResponse loginUser(LoginPayload login){
        return  new AssertableResponse(setup()
                .body(login)
                .when()
                .post("/login"));
}
    public String getAuthToken(String email, String password){
        LoginPayload user = new LoginPayload()
                .email(email)
                .password(password);
        AssertableResponse response =  this.loginUser(user);
        LoginResponse obgectResponse =  response.asPojo(LoginResponse.class);
        String auth = obgectResponse.accessToken;
        return auth;
    }
}
