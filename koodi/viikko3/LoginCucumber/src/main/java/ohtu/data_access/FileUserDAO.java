/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jarkko
 */
@Component
public class FileUserDAO implements UserDao{    
    private Scanner scanner;
    private File file;
    List<User> users;
    
    @Autowired
    public FileUserDAO(String filename) throws FileNotFoundException{
        try{
            file = new File(filename);
            scanner = new Scanner(file);
        }catch(FileNotFoundException fnfe){
            
        }
        
        users = new ArrayList();   
        while(scanner.hasNext()){
            String name = scanner.nextLine();
            String pass = scanner.nextLine();
            users.add(new User(name,pass));
        }
    }

    @Override
    public List<User> listAll() {
        return new ArrayList(users);
    }

    @Override
    public User findByName(String name) {
        for(User u : users){
            if(u.getUsername().equals(name))
                return u;
        }
        return null;
    }

    @Override
    public void add(User user) {
        try {
            new FileWriter(file, true).append(
                    user.getUsername() + '\n' + 
                    user.getPassword() + '\n'
            );
        } catch (IOException ex) {
            Logger.getLogger(
                    FileUserDAO.class.getName()).log(Level.SEVERE, null, ex
            );
        }
        users.add(user);
    }
    
}
