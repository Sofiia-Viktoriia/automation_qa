package reqres.restassured;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static reqres.logic.Endpoints.BASE_PATH;

public class BaseAPITest {

    public RequestSpecification getBaseURI() {
        return given().baseUri(BASE_PATH);
    }
}
