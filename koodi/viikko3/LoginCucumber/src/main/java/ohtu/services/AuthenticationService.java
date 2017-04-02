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

        if (!invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        char c;
        boolean special = false;
        boolean number = false;
        if(username.length() < 4){
            return false;
        }
        
        if(password.length() < 8){
            return false;
        }
                
        for(int i=0;i<username.length();i++){
            c = username.charAt(i);
            if(!Character.isLetter(c)){
                return false;
            }
        }
                
        for(int j=0;j<password.length();j++){
           
            c = password.charAt(j);
            if(Character.isDigit(c)){
                number = true;
                
            }
            if(!Character.isDigit(c) && !Character.isLetter(c)){
                special = true;
            }
            
        }
        return (number && special);
    }
}
