package reqres.utils.providers;

import reqres.logic.dto.AuthDto;
import reqres.logic.dto.CreateUserDto;

public class RequestProvider {

    public static CreateUserDto getDefaultCreateUserDto() {
        return new CreateUserDto("morpheus", "leader");
    }

    public static AuthDto getDefaultCorrectAuthDtoForRegister() {
        return new AuthDto("eve.holt@reqres.in", "pistol");
    }

    public static AuthDto getDefaultIncorrectAuthDto() {
        return new AuthDto("sydney@fife");
    }

    public static AuthDto getDefaultCorrectAuthDtoForLogin() {
        return new AuthDto("eve.holt@reqres.in", "cityslicka");
    }
}
