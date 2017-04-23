
package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;


public class FileUserDao implements UserDao{

    private String filename;
    private List<User> users;
    FileWriter writer;

    public FileUserDao() {
    }
    
    
    
    public FileUserDao(String filename) throws IOException{
        this.users = new ArrayList<>();
        this.filename = filename;
        if(!readFile()){
            File f = new File(filename);
        }
      
    }
    
    private boolean readFile(){
            
            Scanner reader;
        try {
            reader = new Scanner(new File(filename));
             while (reader.hasNextLine()) {
                String rivi = reader.nextLine();
                String [] userData = rivi.split("\t");
                users.add(new User(userData[0], userData[1]));
            
            }
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
            
      
        
  
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
        try {
            
            writer = new FileWriter(filename,true);
            writer.write(user.getUsername() + "\t" + user.getPassword() + "\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    public List<User> getUsers() {
        return users;
    }
    
}
