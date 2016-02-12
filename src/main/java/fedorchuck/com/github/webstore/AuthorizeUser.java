package fedorchuck.com.github.webstore;

import fedorchuck.com.github.webstore.data.UserRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by v on 11/02/16.
 */
public class AuthorizeUser {

    @NotNull
    @Size(min=5, max=16, message="{username.size}")
    private String username;

    @NotNull
    @Size(min=5, max=25, message="{password.size}")
    private String password;

    public AuthorizeUser() {}

    public AuthorizeUser(String username, String password) {
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

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean check(UserRepository userRepository) {
        User user = userRepository.findByUsername(username);
        return user != null && password.equals(user.getPassword());
    }

    public User getUser(UserRepository userRepository) {
        return userRepository.findByUsername(username);
    }
}
