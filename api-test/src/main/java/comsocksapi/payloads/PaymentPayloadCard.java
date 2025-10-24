package comsocksapi.payloads;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent  = true)
public class PaymentPayloadCard {
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("cardHolder")
    private String cardHolder;
    @JsonProperty("expirationDate")
    private String expirationDate;
    @JsonProperty("securityCode")
    private int securityCode;
    @JsonProperty("expirationMonth")
    private String expirationMonth;
    @JsonProperty("expirationYear")
    private String expirationYear;
}
