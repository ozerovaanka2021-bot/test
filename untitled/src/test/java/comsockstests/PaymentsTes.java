package comsockstests;

import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
import comsocksapi.services.PaymentApiService;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentsTes {

    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private PaymentApiService payment = new PaymentApiService(config.paymentBaseURL());

    @Test
    public void testCanCreatePayment(){
        PaymentPayloadCard payloadCard = new PaymentPayloadCard()
                .cardNumber("423423423")
                .cardHolder("dfgdfg hfghf")
                .expirationDate("12/12")
                .securityCode(122);
        PaymentPayload payload = new PaymentPayload()
                .movieId(1)
                .amount(1)
                .card(payloadCard);
        payment.payment(payload)
                .soudHave(Conditions.statusCode(200));



    }

}
