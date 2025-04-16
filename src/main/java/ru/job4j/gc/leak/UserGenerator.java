package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "files/names.txt";
    public static final String PATH_SURNAMES = "files/surnames.txt";
    public static final String PATH_PATRONS = "files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final Integer NEW_users = 1000;

    public static List<String> names;
    public static List<String> surnames;
    public static List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_users; i++) {
            StringBuilder nameBuilder = new StringBuilder();
            nameBuilder.append(surnames.get(random.nextInt(surnames.size())));
            nameBuilder.append(SEPARATOR);
            nameBuilder.append(names.get(random.nextInt(names.size())));
            nameBuilder.append(SEPARATOR);
            nameBuilder.append(patrons.get(random.nextInt(patrons.size())));
            String name = nameBuilder.toString();

            var user = new User();
            user.setName(name);
            users.add(user);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}