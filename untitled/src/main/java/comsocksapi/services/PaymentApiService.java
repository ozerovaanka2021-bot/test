package comsocksapi.services;

import comsocksapi.assertions.AssertableResponse;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;

public class PaymentApiService extends ApiService {

    public PaymentApiService(String baseUrl) {
        super(baseUrl);
    }

    public AssertableResponse payment(PaymentPayload payment){
        return new AssertableResponse(setup()
                .body(payment)
                .when()
                .post("/create"));
    }
}
