package io.github.raboro.zoo;

public class ZooFileException extends RuntimeException {

    ZooFileException() {
        super("Error occurred when saving data to file");
    }
}
