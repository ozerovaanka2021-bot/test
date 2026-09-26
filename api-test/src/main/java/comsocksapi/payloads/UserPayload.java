package comsocksapi.payloads;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;



@Getter
@Setter
@ToString
@Accessors(fluent  = true)
public class UserPayload {

    @JsonProperty("email")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("passwordRepeat")
    private String passwordRepeat;

    @JsonProperty("login")
    private String login;



}