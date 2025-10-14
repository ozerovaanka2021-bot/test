package comsocksapi.assertions;

import comsocksapi.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

       private final Response response;
    @Step
    public AssertableResponse soudHave(Condition condition){
       log.info("About check condition {}", condition);
        condition.check(response);
        return this;
    }

    /*
    * Создаёт объектное представление ответа. Возвращает объект того типа, который передали в параметре
    * @param tClass - тип возвращаемого значения
    * */
    public<T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }
}
