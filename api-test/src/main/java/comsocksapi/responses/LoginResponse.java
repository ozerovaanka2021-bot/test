package comsocksapi.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse{
    public LoginResponseUser user;
    public String accessToken;
    public String refreshToken;
    public long expiresIn;

}

