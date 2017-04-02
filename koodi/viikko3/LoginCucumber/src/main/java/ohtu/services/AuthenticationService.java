package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    public AuthenticationService() { }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        if (username.length() <= 2) return true;
        if (!username.chars().allMatch(ch -> ch >= (int)'a' && ch <= (int)'z')) return true;

        if (password.length() < 8) return true;
        if (password.chars().allMatch(ch -> Character.isLetter(ch))) return true;

        return false;
    }
}
