package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class PaymentApiService extends ApiService {

    public PaymentApiService(String baseUrl) {
        super(baseUrl);
    }


    public AssertableResponse payment(PaymentPayload payment, String authToken){

        return new AssertableResponse(setup()
                .headers(
                        "Authorization",
                        "Bearer " + authToken)
                .body(payment)
                .when()
                .post("/create"));
    }
}
