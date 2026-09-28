package comsocksapi.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterResponse{
    public String id;
    public String email;
    public String fullName;
    public String login;
    public ArrayList<String> roles;
    public boolean verified;
    public Date createdAt;
    public boolean banned;
}

