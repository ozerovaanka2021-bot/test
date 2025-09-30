import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.User;
import static org.hamcrest.Matchers.*;

public class confirm {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://auth.cinescope.krisqa.ru";
    }
    @Test
    public void testConfirm(){
}}
