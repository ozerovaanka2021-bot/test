package comsocksapi.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponseUser {
    public String id;
    public String email;
    public String login;
    public String fullName;
    public Date createdAt;
    public boolean verified;
    public boolean banned;
    public ArrayList<String> roles;
}

