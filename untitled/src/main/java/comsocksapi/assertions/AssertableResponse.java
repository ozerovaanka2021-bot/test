package comsocksapi.assertions;

import comsocksapi.conditions.Condition;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

       private final Response response;

    public AssertableResponse soudHave(Condition condition){
       log.info("About check condition {}", condition);
        condition.check(response);
        return this;
    }

    public<T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }
}
