
package ohtu;

import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import ohtu.domain.User;

public class UserDaoForTests implements UserDao {

    private List<User> users;

    public UserDaoForTests() {
        this.users = new ArrayList<>();
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}