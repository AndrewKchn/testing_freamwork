package org.example;

import java.util.function.Predicate;

public class Validator {
    public <T> boolean validate(T val, Predicate<T> predicate) {
        return predicate.test(val);
    }
}
