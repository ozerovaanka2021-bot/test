package comsocksapi.payloads;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent  = true)
public class PaymentPayload {
    @JsonProperty("movieId")
    private int movieId;
    @JsonProperty("amount")
    private int amount;
    @JsonProperty("card")
    private PaymentPayloadCard card;
}
