package comsocksapi;


import lombok.Builder;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config{
    

    @DefaultValue("https://auth.cinescope.krisqa.ru")
    String baseURL();
    @DefaultValue("en")
    String locale();
    Boolean logging();
}
