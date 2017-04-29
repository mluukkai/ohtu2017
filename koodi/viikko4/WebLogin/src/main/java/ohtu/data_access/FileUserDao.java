
package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import ohtu.domain.User;
import java.util.*;

public class FileUserDao implements UserDao {
    private String file;
    private List<User> users;
    
    public FileUserDao(String file) {
        this.file = file;
        users = new ArrayList<>();
        
        try{
            Scanner scanner = new Scanner(new File(file));
            while( scanner.hasNextLine() ){
                String[] parts = scanner.nextLine().split(";");
                users.add(new User(parts[0], parts[1]));
            }
        } catch(Exception e) {
        }
    }
        
    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if ( user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User newUser) {
        users.add(newUser);
        
        try{
            FileWriter kirjoittaja = new FileWriter(file);
            for (User user : users) {
                String rivi = user.getUsername()+";"+user.getPassword()+"\n";
                kirjoittaja.write(rivi);
            }
            
            kirjoittaja.close();
        } catch(Exception e) {
        }
    }
    
}
