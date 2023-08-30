package gr.aueb.cf.schoolapppro.dto;

public class UserUpdateDTO extends BaseDTO {
    private String username;
    private String password;

    public UserUpdateDTO() {}

    public UserUpdateDTO(String username, String password, Integer id) {
        this.setId(id);
        this.username = username;
        this.password = password;
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
}
