package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.payloads.UserPayload;


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

}
