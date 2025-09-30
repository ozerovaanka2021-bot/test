import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.User;
import static org.hamcrest.Matchers.*;

public class Mytest {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://auth.cinescope.krisqa.ru";

    }

    @Test
    public void testRegisterUser(){
        try {
            User us09 = new User()
                    .email("aozerova1234@gmail.com")
                    .fullName("Asdf Fghj")
                    .password("123hblernjQ")
                    .passwordRepeat("123hblernjQ");


            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                    .body(us09)
                    .when()
                    .post("/register");
            // Детальный вывод ответа
            System.out.println("=== ОТВЕТ СЕРВЕРА ===");
            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Body: " + response.getBody().asString());
            System.out.println("====================");

            // Валидация
            response.then()
                    .assertThat()
                    .statusCode(anyOf(greaterThan(99), lessThan(600)))
                    .body("id", not(emptyString()));

        } catch (Exception e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
            throw e;
        }}
}