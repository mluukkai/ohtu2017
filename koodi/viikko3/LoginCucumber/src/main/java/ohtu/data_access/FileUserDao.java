package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import ohtu.domain.User;
import org.springframework.stereotype.Component;

public class FileUserDao implements UserDao {

    String filename;
    List<User> users;

    public FileUserDao(String filename) throws IOException {
        this.users = new ArrayList<>();
        this.filename = filename;
        Scanner lukija = null;
        File userFile = new File(filename);

        //if file doesn't exist, create it before opening
        if (userFile.createNewFile()) {
            add(new User("pekka", "akkep"));
        } else {

            lukija = new Scanner(userFile);

            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();

                //salasana voi sisältää puolipisteen, jos näin on niin tulee ongelma. En jaksa korjata nyt
                String[] osat = rivi.split(";");

                if (osat.length == 2) {
                    users.add(new User(osat[0], osat[1]));
                }

            }

            lukija.close();
        }
    }

    @Override
    public void add(User user) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(user.getUsername() + ";" + user.getPassword() + "\n");
            users.add(user);
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write to file " + filename);
        }

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
    public List<User> listAll() {
        return users;
    }

}
