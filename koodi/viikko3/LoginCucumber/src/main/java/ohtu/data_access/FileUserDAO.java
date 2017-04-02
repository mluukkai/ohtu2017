package ohtu.data_access;

import java.io.File;
import java.io.FileWriter;
import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class FileUserDao implements UserDao {

    private List<User> users;

    public FileUserDao() {
        users = new ArrayList<User>();

        Scanner lukija = new Scanner(new File("salasanat.txt"));
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine()split(":");
            users.add(new User(rivi[0],password[1]));
        }

        lukija.close();
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
        FileWriter kirjoittaja = new FileWriter("salasanat.txt");
        kirjoittaja.write(user.getUsername().":".user.getPassword());
        kirjoittaja.close();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
