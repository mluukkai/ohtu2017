package ohtu.data_access;

import ohtu.domain.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUserDao implements UserDao {
    private String fileName;
    private InMemoryUserDao cache;

    public FileUserDao(String fileName) {
        this.fileName = fileName;
        this.cache = new InMemoryUserDao();

        List<User> users = loadFromFile();

        for (User user : users) {
            this.cache.add(user);
        }
    }

    @Override
    public List<User> listAll() {
        return cache.listAll();
    }

    @Override
    public User findByName(String name) {
        return cache.findByName(name);
    }

    @Override
    public void add(User user) {
        cache.add(user);
        persist();
    }

    private List<User> loadFromFile() {
        try {
            return Files.readAllLines(Paths.get(fileName))
                    .stream()
                    .map(s -> s.split(":"))
                    .map(parts -> new User(parts[0], parts[1]))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void persist() {
        List<String> lines = new ArrayList<>();

        for (User user : cache.listAll()) {
            StringBuilder sb = new StringBuilder();

            sb.append(user.getUsername());
            sb.append(':');
            sb.append(user.getPassword());

            lines.add(sb.toString());
        }

        try {
            Files.write(Paths.get(fileName), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
