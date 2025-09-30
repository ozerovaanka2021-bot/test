package comsocksapi.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class UserRegisterResponse{
    public String id;
    public String email;
    public String fullName;
    public ArrayList<String> roles;
    public boolean verified;
    public Date createdAt;
    public boolean banned;
}

