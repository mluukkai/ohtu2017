
package ohtu.data_access;

import java.util.List;
import ohtu.domain.User;
import org.springframework.stereotype.Component;


public interface UserDao {
    List<User> listAll();
    User findByName(String name);
    void add(User user);
}
