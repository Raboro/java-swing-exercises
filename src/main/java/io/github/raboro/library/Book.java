package io.github.raboro.library;

import java.util.Date;

public record Book(String name, String author, Date releaseDate) {

    @Override
    public String toString() {
        return String.format("%s - %s - %s", name, author, releaseDate.toString());
    }
}
