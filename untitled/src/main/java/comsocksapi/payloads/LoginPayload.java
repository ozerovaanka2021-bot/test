package comsocksapi.payloads;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent  = true)
public class LoginPayload {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
