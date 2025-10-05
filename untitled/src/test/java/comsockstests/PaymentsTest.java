package comsockstests;

import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
import comsocksapi.services.PaymentApiService;
import comsocksapi.services.UserApiServices;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentsTest {

    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private PaymentApiService payment = new PaymentApiService(config.paymentBaseURL());
    private UserApiServices userApiServices = new UserApiServices(config.authBaseURL());
    private String authToken;

   @BeforeAll
   public void setup(){
       authToken = userApiServices.getAuthToken("aozerova1234@gmail.com", "jChZV2wnGJ8Ufxh");
   }

    @Test
    public void testCanCreatePayment(){
        PaymentPayloadCard payloadCard = new PaymentPayloadCard()
                .cardNumber("4242424242424242")
                .cardHolder("dfgdfg hfghf")
                .expirationDate("12/25")
                .securityCode(123);
        PaymentPayload payload = new PaymentPayload()
                .movieId(2143)
                .amount(1)
                .card(payloadCard);
        payment.payment(payload, authToken)

                .soudHave(Conditions.statusCode(201));



    }

}
