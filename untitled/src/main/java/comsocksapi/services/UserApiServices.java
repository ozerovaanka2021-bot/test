package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.UserPayload;


public class UserApiServices extends ApiService {


    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("/register"));
    }

   public AssertableResponse confirmUser(String userToken){
   return  new AssertableResponse(setup()
           .pathParam("token", userToken)
           .when()
           .get("/confirm/{token}"));
    }
}
