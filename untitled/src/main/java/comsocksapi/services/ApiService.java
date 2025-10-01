package comsocksapi.services;

import comsocksapi.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class ApiService {
    private final String baseUrl;

    public ApiService(String baseUrl){
        this.baseUrl = baseUrl;
    }

    protected RequestSpecification setup(){


        return RestAssured
                .given()
                .baseUri(this.baseUrl)
                .contentType(ContentType.JSON)
                .filters(getFilters());
    }

    private List<Filter> getFilters(){
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        if (config.logging()){
            return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
            return Collections.emptyList();
    }
}
