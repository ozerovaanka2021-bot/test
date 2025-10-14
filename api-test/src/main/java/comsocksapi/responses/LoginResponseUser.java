package comsocksapi.responses;

import java.util.ArrayList;
import java.util.Date;

public class LoginResponseUser {
    public String id;
    public String email;
    public String fullName;
    public Date createdAt;
    public boolean verified;
    public boolean banned;
    public ArrayList<String> roles;
}

