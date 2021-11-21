package reqres.logic.dto;

public class AuthDto {

    private String email;
    private String password;

    public AuthDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AuthDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"email\": \"" + email + "\",\n" +
                "    \"password\": \"" + password + "\"\n" +
                "}";
    }

    public String toStringNotFull() {
        return "{\n" +
                "   \"email\": \"" + email + "\"\n" +
                "}";
    }
}
