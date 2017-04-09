package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

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
    
    public CreationStatus createUser(String username, String password, String confirmation) {
        CreationStatus status = new CreationStatus();

        if(invalid(username, password, confirmation, status).errors().isEmpty()){
        userDao.add(new User(username, password));
        }

        return status;
    }

    private CreationStatus invalid(String username, String password, String confirmation, CreationStatus status) {
        // validity check of username and password
        if (username.length() < 3) {
            status.addError("username should have atleast 3 characters");
        } 
            
           if(password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }
        for (int i = 0; i < username.length(); i++) {
            Character x = username.charAt(i);
            if (Character.getType(x) != Character.LOWERCASE_LETTER) {
                status.addError("username should contain letters a-z only");
            }
        }
        boolean eiErikoisia = true;
        for (int j = 0; j < password.length(); j++) {
            Character x = password.charAt(j);
            if (Character.getType(x) != Character.LOWERCASE_LETTER && Character.getType(x) != Character.UPPERCASE_LETTER) {
                eiErikoisia = false;

            }
        }
        if(eiErikoisia){
            status.addError("password can not contain only letters");
        }
        
        if(password != confirmation){
            status.addError("password does not match password confirmation");
        }
        return status;
    }

}
