package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    private UserDao userDao;

    @Autowired
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

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
        boolean numeroTaiMerkki = true;
        String SPECIAL_CHARACTERS = "!@#$%^&*()~`-=_+[]{}|:\";',./<>?";
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
        if (username.length() < 3) {
            return true;
        }
        String passwordTrim = password.trim();
        if (passwordTrim.length() < 8) {
            return true;
        }
        char[] passArray = passwordTrim.toCharArray();
        for (char c : passArray) {
            if (Character.isDigit(c)) {
                numeroTaiMerkki = false;
            } else if (SPECIAL_CHARACTERS.contains(Character.toString(c))) {
                numeroTaiMerkki = false;
            } else if (!CHARACTERS.contains(Character.toString(c))) {
                return true;
            }
        }

        return numeroTaiMerkki;
    }
}
