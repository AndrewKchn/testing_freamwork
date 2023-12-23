package org.example;

import java.util.function.Predicate;

public class ValidateStringIsNotNull implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s != null;
    }
}
