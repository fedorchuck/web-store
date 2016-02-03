package fedorchuck.com.github.webstore.data;

import fedorchuck.com.github.webstore.User;

/**
 * Created by v on 28/01/16.
 */
public interface UserRepository {
    User save(User user);
    User findByUsername(String username);
}
