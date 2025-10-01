package comsocksapi;


import lombok.Builder;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config{
    


    String authBaseURL();
    String paymentBaseURL();
    @DefaultValue("en")
    String locale();
    Boolean logging();
}
