package comsocksapi;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
        "file:${user.home}/.test-secrets.properties",
        "classpath:config.properties"
})
public interface ProjectConfig extends Config {

    String loginBaseURL();
    String authBaseURL();
    String paymentBaseURL();

    @DefaultValue("en")
    String locale(String en);

    Boolean logging();

    String paymentEmail();     // новое
    String paymentPassword();  // новое

    @DefaultValue("true")
    boolean headless();

    @DefaultValue("")
    String remoteUrl();
}
