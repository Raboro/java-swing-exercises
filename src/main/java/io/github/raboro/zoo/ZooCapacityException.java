package io.github.raboro.zoo;

public class ZooCapacityException extends RuntimeException {

    ZooCapacityException() {
       super("Zoo is full");
    }
}
