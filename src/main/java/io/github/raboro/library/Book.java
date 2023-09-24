package io.github.raboro.library;

public record Book(String name, String author, String releaseDate) {

    @Override
    public String toString() {
        return String.format("%s - %s - %s", name, author, releaseDate);
    }
}
