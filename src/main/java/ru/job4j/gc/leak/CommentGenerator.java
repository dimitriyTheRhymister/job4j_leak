package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "files/phrases.txt";

    public static final String SEPARATOR = System.lineSeparator();
    private final List<Comment> comments = new ArrayList<>();
    public static final Integer COUNT = 50;
    private static List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < COUNT; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(phrases.get(random.nextInt(phrases.size())));
            sb.append(SEPARATOR);
            sb.append(phrases.get(random.nextInt(phrases.size())));
            sb.append(SEPARATOR);
            sb.append(phrases.get(random.nextInt(phrases.size())));
            String text = sb.toString();

            var comment = new Comment();
            comment.setText(text);
            comment.setUser(userGenerator.randomUser());
            comments.add(comment);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }
}