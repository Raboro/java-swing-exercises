package io.github.raboro.librarytwo;


public record Book(String title, String author, int year, String publisher) {

    @Override
    public String toString() {
        return String.format("%s,%s,%d,%s", title, author, year, publisher);
    }
}
