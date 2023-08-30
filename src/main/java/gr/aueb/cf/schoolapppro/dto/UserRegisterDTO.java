package gr.aueb.cf.schoolapppro.dto;

public class UserRegisterDTO {
    private String username;
    private String password;
    private String confirmationPassword;

    public UserRegisterDTO() {}

    public UserRegisterDTO(String username, String password, String confirmationPassword) {
        this.username = username;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }
}
