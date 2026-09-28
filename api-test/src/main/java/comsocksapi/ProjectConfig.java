package comsocksapi;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({
        "file:${user.home}/.test-secrets.properties",
        "classpath:config.properties"
})
public interface ProjectConfig extends Config {
    @DefaultValue("https://dev-cinescope.t-qa.ru/login")
    String loginBaseURL();

    @DefaultValue("https://auth.cinescope.t-qa.ru")
    String authBaseURL();

    @DefaultValue("https://payment.dev-cinescope.t-qa.ru")
    String paymentBaseURL();

    @DefaultValue("ru")
    String locale(String ru);

    @DefaultValue("true")
    Boolean logging();

    String paymentEmail();
    String paymentPassword();

    @DefaultValue("true")
    boolean headless();

    @DefaultValue("")
    String remoteUrl();
}
