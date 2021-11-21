package reqres.restassured;

import org.testng.annotations.Test;
import reqres.logic.dto.CreateUserDto;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static reqres.logic.Endpoints.*;
import static reqres.utils.providers.RequestProvider.getDefaultCreateUserDto;

public class UserTests extends BaseAPITest {

    private static final int CORRECT_USER_ID = 2;
    private static final int WRONG_USER_ID = 23;

    @Test
    public void getUsersList() {
        getBaseURI().when()
                .get(GET_USERS_LIST)
                .then().statusCode(200)
                .assertThat()
                .body("page", equalTo(2))
                .body("data", hasSize(6));
    }

    @Test
    public void getUserByIdSuccess() {
        getBaseURI().when()
                .get(GET_USER_BY_ID, CORRECT_USER_ID)
                .then().statusCode(200)
                .body("data.id", equalTo(CORRECT_USER_ID))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void getUserByIdFailed() {
        getBaseURI().when()
                .get(GET_USER_BY_ID, WRONG_USER_ID)
                .then().statusCode(404);
    }

    @Test
    public void createUser() {
        CreateUserDto requestBody = getDefaultCreateUserDto();
        getBaseURI()
                .contentType("application/json")
                .body(requestBody.toString())
                .when().post(CREATE_USER)
                .then().statusCode(201)
                .body("name", equalTo(requestBody.getName()))
                .body("job", equalTo(requestBody.getJob()));
    }

    @Test
    public void deleteUser() {
        getBaseURI().when()
                .delete(DELETE_USER_BY_ID, CORRECT_USER_ID)
                .then().statusCode(204);
    }
}
